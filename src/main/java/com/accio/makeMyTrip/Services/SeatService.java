package com.accio.makeMyTrip.Services;

import com.accio.makeMyTrip.Entities.Seat;
import com.accio.makeMyTrip.Entities.Transport;
import com.accio.makeMyTrip.Enums.SeatType;
import com.accio.makeMyTrip.Repositories.SeatRepository;
import com.accio.makeMyTrip.Repositories.TransportRepository;
import com.accio.makeMyTrip.RequestDto.AddBusSeatsReqDto;
import com.accio.makeMyTrip.RequestDto.AddFlightSeatsDto;
import com.accio.makeMyTrip.RequestDto.AddTrainSeatsReqDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SeatService {

    @Autowired
    private SeatRepository seatRepository;

    @Autowired
    private TransportRepository transportRepository;


    public String addPlaneSeats(AddFlightSeatsDto addFlightSeatsDto){

        Transport transport0404 = transportRepository.findById(addFlightSeatsDto.getPlaneId()).get();

        for(int i = 1; i <= addFlightSeatsDto.getNoOfEconomySeats(); i++){

            Seat seat = Seat.builder()
                    .seatNo(String.valueOf("E"+i))
                    .seatType(SeatType.ECONOMY)
                    .price(addFlightSeatsDto.getPriceEco())
                    .transport(transport0404)
                    .build();

            //BiDirectional Mapping so we are saving in both C/P
            transport0404.getSeatList().add(seat);
        }

        for(int i = 1; i <= addFlightSeatsDto.getNoOfBusinessSeats(); i++){

            Seat seat = Seat.builder()
                    .seatNo(String.valueOf("B"+i))
                    .seatType(SeatType.BUSINESS)
                    .price(addFlightSeatsDto.getPriceBuis())
                    .transport(transport0404)
                    .build();

            //BiDirectional Mapping, so we are saving in both C/P
            transport0404.getSeatList().add(seat);
        }

        //BiDirectional Mapping saving to parent only
        transportRepository.save(transport0404);

        return "Seat has been associated successfully";
    }





    public String addBusSeats(AddBusSeatsReqDto addBusSeatsReqDto){

        Transport transport0404 = transportRepository.findById(addBusSeatsReqDto.getBusId()).get();

        for(int i = 1; i <= addBusSeatsReqDto.getNoOfNoramalSeats(); i++){

            Seat seat = Seat.builder()
                    .seatNo(String.valueOf("N"+i))
                    .seatType(SeatType.NORMAL)
                    .price(addBusSeatsReqDto.getPriceNormalSeat())
                    .transport(transport0404)
                    .build();

            //BiDirectional Mapping so we are saving in both C/P
            transport0404.getSeatList().add(seat);
        }

        for(int i = 1; i <= addBusSeatsReqDto.getNoOfSleepers(); i++){

            Seat seat = Seat.builder()
                    .seatNo(String.valueOf("S"+i))
                    .seatType(SeatType.SLEEPER)
                    .price(addBusSeatsReqDto.getPriceOfSleeper())
                    .transport(transport0404)
                    .build();

            //BiDirectional Mapping, so we are saving in both C/P
            transport0404.getSeatList().add(seat);
        }

        //BiDirectional Mapping saving to parent only
        transportRepository.save(transport0404);

        return "Bus seats have been added successfully";
    }

    public String addTrainSeats(AddTrainSeatsReqDto addTrainSeatsReqDto){

        Transport transport0404 = transportRepository.findById(addTrainSeatsReqDto.getTrainId()).get();

        for(int i = 1; i <= addTrainSeatsReqDto.getNoOfJournalSeats(); i++){

            Seat seat = Seat.builder()
                    .seatNo(String.valueOf("J"+i))
                    .seatType(SeatType.JOURNAL)
                    .price(addTrainSeatsReqDto.getPriceOfJournal())
                    .transport(transport0404)
                    .build();

            //BiDirectional Mapping so we are saving in both C/P
            transport0404.getSeatList().add(seat);
        }

        for(int i = 1; i <= addTrainSeatsReqDto.getNoOfACSeats(); i++){

            Seat seat = Seat.builder()
                    .seatNo(String.valueOf("A"+i))
                    .seatType(SeatType.AC)
                    .price(addTrainSeatsReqDto.getPriceOfAC())
                    .transport(transport0404)
                    .build();

            //BiDirectional Mapping, so we are saving in both C/P
            transport0404.getSeatList().add(seat);
        }

        //BiDirectional Mapping saving to parent only
        transportRepository.save(transport0404);

        return "Trian seats have been added successfully";
    }
}
