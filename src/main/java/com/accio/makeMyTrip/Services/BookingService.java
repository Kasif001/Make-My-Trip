package com.accio.makeMyTrip.Services;


import com.accio.makeMyTrip.Entities.*;
import com.accio.makeMyTrip.Exceptions.UserNotFoundException;
import com.accio.makeMyTrip.Repositories.*;
import com.accio.makeMyTrip.RequestDto.MultipleTicketsBookingReqDto;
import com.accio.makeMyTrip.RequestDto.TicketBookingReqDto;
import com.accio.makeMyTrip.ResponseDto.GenerateTicketsRespDto;
import com.accio.makeMyTrip.ResponseDto.GetAvailableSeatsRespDto;
import com.accio.makeMyTrip.ResponseDto.ReturnBookingsRespDto;
import com.accio.makeMyTrip.Transformers.BookingTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.print.Book;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;


@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private TransportRepository transportRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RouteRepository routeRepository;

    @Autowired
    private TicketRepository ticketRepository;


    public List<GetAvailableSeatsRespDto> getAllSeats(LocalDate date, Integer transportId) {

        List<Booking> doneBookings = bookingRepository.findBooking(date, transportId);

        Set<String> bookedSeatsSet = new TreeSet<>();

        for (Booking booking : doneBookings) {

            String str = booking.getSeatNos(); //1A

            String[] bookedSeatsInThatBooking = str.split(",");
            for (String seatNo : bookedSeatsInThatBooking) {
                bookedSeatsSet.add(seatNo);
            }
        }

        Transport transport = transportRepository.findById(transportId).get();
        List<Seat> allBookedOrUnBookedSeatsOfThatTP = transport.getSeatList();

        List<GetAvailableSeatsRespDto> respDtos = new ArrayList<>();

        for (Seat seat : allBookedOrUnBookedSeatsOfThatTP) {
            if (!bookedSeatsSet.contains(seat.getSeatNo())) {
                GetAvailableSeatsRespDto resObj = new GetAvailableSeatsRespDto(seat.getSeatNo(), seat.getSeatType(), seat.getPrice());
                respDtos.add(resObj);
            }
        }

        return respDtos;
    }

    public String SeatBooking(TicketBookingReqDto ticketBookingReqDto) throws Exception{

        List<Booking> allBookings = bookingRepository.findAll();
        List<Booking> doneBookings = bookingRepository.findBooking(ticketBookingReqDto.getJourneyDate(), ticketBookingReqDto.getTransportId());

        List<String> seatWantedToBook = ticketBookingReqDto.getSingleSeatNo();

        Transport transportObj = transportRepository.findById(ticketBookingReqDto.getTransportId()).get();

        List<Seat> allSeatsofThisTP = transportObj.getSeatList();

        if (!allSeatsofThisTP.stream().map(Seat::getSeatNo).collect(Collectors.toList()).containsAll(seatWantedToBook)) {
            throw new Exception("One or more seat numbers are invalid");
        }

        StringBuilder allSeatsDoneBookings = new StringBuilder();
        for (Booking booking : doneBookings) {
            allSeatsDoneBookings.append(booking.getSeatNos());
        }

        if(allSeatsDoneBookings.length() != 0){
            boolean isAvailable = isSeatAvailable(seatWantedToBook, allSeatsDoneBookings);
            if (!isAvailable) {
                throw new Exception("Failed! " + "\n" + "Your are trying to book some already booked seats");
            }
        }


        User userObj = userRepository.findById(ticketBookingReqDto.getUserId()).get();
        Booking bookingObj = BookingTransformer.convertReqDtoToBookingEntity(ticketBookingReqDto);


        Ticket ticketObj = createTicketObj(transportObj, bookingObj, seatWantedToBook);

        //Setting FK obj
        bookingObj.setUser(userObj);
        bookingObj.setTransport321(transportObj);
        bookingObj.setTicket(ticketObj);
        bookingObj = bookingRepository.save(bookingObj);

        //Bidirectional mapping so FK sets both side
        userObj.getBookingList2().add(bookingObj);
        transportObj.getBookingList().add(bookingObj);
        ticketObj.setBooking(bookingObj);


        //Bidirectional mapping so save parent only
        userRepository.save(userObj);
        transportRepository.save(transportObj);



        return "Your Booking of " + seatWantedToBook.size() + " Seats have been Successful. Please pay $" + ticketObj.getTotalCost() + " before journey";
    }


    private Ticket createTicketObj(Transport transport, Booking booking, List<String> seatWantedToBook) {
        String routeDetails = findRouteDetails(transport);
        Integer totalPrice = calculateTotalPrice(transport, seatWantedToBook);

        Ticket ticket = Ticket.builder()
                .noOfPeopleCanTravel(seatWantedToBook.size())
                .routeDetails(routeDetails)
                .totalCost(totalPrice)
                .journeyDate(transport.getJourneyDate())
                .journeyTime(transport.getStartTime())
                .seatNo(booking.getSeatNos())
                .booking(booking)
                .build();

        return ticket;
    }

    private String findRouteDetails(Transport transport) {
        Route routeObj = transport.getRoute();
        return routeObj.getSource() + " To " + routeObj.getDest() + "";
    }

    private Integer calculateTotalPrice(Transport transport, List<String> seatsList) {
        Integer cost = 0;
        List<Seat> allSeats = transport.getSeatList();

        for (Seat seat : allSeats) {
            if (seatsList.contains(seat.getSeatNo())) {
                cost += seat.getPrice();
            }
        }
        return cost;
    }

    private boolean isSeatAvailable(List<String> seatWanteeToBook, StringBuilder allBookedSeats) {

        String bookedSeat = allBookedSeats.substring(0, allBookedSeats.length() - 1);
        String[] bookedSeatSplit = bookedSeat.split(",");

        for (String currStr : bookedSeatSplit) {
            for (String currStr2 : seatWanteeToBook) {
                if (currStr.equals(currStr2)) return false;
            }
        }
        return true;
    }


    public List<ReturnBookingsRespDto> getBookings(Integer userId, LocalDate date)throws Exception{
        if(!userRepository.existsById(userId)){
            throw new UserNotFoundException("User id is invalid");
        }

        User user = userRepository.findById(userId).get();
        List<Booking> allBookings = bookingRepository.findBookingByJourneyDateAndUser(date,user);

        if(allBookings.size() == 0){
            throw new RuntimeException("There is no tickets on this data: "+date+"");
        }
        List<ReturnBookingsRespDto> respDtos = new ArrayList<>();

        for(Booking booking: allBookings){
            Transport transport = booking.getTransport321();
            ReturnBookingsRespDto converted = BookingTransformer.converBookingToReturnBookings(booking,transport);
            respDtos.add(converted);
        }
        return respDtos;

    }
}