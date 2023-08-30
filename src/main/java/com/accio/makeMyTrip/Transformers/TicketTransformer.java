package com.accio.makeMyTrip.Transformers;

import com.accio.makeMyTrip.Entities.Ticket;
import com.accio.makeMyTrip.ResponseDto.GenerateTicketsRespDto;

public class TicketTransformer {
    public static GenerateTicketsRespDto convertTicketToRespDto(Ticket ticket){
        GenerateTicketsRespDto respDto = GenerateTicketsRespDto.builder()
                .Source_And_Destination(ticket.getRouteDetails())
                .modeOfTp(ticket.getBooking().getTransport321().getModeOfTp())
                .totalPrice(ticket.getTotalCost())
                .journeyDate(ticket.getJourneyDate())
                .startTime(ticket.getBooking().getTransport321().getStartTime())
                .seatNos(ticket.getSeatNo())
                .companyName(ticket.getBooking().getTransport321().getCompanyName())
                .noOfPeopleCanTravel(ticket.getNoOfPeopleCanTravel())
                .build();

        return respDto;
    }
}
