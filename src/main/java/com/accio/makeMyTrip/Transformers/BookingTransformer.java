package com.accio.makeMyTrip.Transformers;

import com.accio.makeMyTrip.Entities.Booking;
import com.accio.makeMyTrip.Entities.Transport;
import com.accio.makeMyTrip.RequestDto.TicketBookingReqDto;
import com.accio.makeMyTrip.ResponseDto.GenerateTicketsRespDto;
import com.accio.makeMyTrip.ResponseDto.ReturnBookingsRespDto;

import java.util.List;

public class BookingTransformer {

    public static Booking convertReqDtoToBookingEntity(TicketBookingReqDto ticketBookingReqDto){
        List<String> seatList = ticketBookingReqDto.getSingleSeatNo();
        String convetString = "";
        for (String str: seatList){
            convetString+=str+",";
        }
        Booking bookingObj = Booking.builder()
                .seatNos(convetString)
                .journeyDate(ticketBookingReqDto.getJourneyDate())
                .build();

        return bookingObj;
    }
    public static ReturnBookingsRespDto converBookingToReturnBookings(Booking booking, Transport transport){
        ReturnBookingsRespDto respDto = ReturnBookingsRespDto.builder()
                .ticketId(booking.getTicket().getTicketId())
                .transportId(transport.getTransId())
                .source(transport.getRoute().getSource())
                .destination(transport.getRoute().getDest())
                .journeyDate(transport.getJourneyDate())
                .startTime(transport.getStartTime())
                .build();

        return respDto;
    }
}
