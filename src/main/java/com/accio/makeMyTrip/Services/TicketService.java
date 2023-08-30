package com.accio.makeMyTrip.Services;

import com.accio.makeMyTrip.Entities.Booking;
import com.accio.makeMyTrip.Entities.Ticket;
import com.accio.makeMyTrip.Entities.Transport;
import com.accio.makeMyTrip.Entities.User;
import com.accio.makeMyTrip.Exceptions.UserNotFoundException;
import com.accio.makeMyTrip.Repositories.BookingRepository;
import com.accio.makeMyTrip.Repositories.TicketRepository;
import com.accio.makeMyTrip.Repositories.UserRepository;
import com.accio.makeMyTrip.ResponseDto.GenerateTicketsRespDto;
import com.accio.makeMyTrip.Transformers.BookingTransformer;
import com.accio.makeMyTrip.Transformers.TicketTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class TicketService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private BookingRepository bookingRepository;

    public GenerateTicketsRespDto getTicket(Integer ticketId)throws Exception{
        if(!ticketRepository.existsById(ticketId)){
            throw new UserNotFoundException("Ticket id is invalid");
        }

        Ticket ticket = ticketRepository.findById(ticketId).get();
        GenerateTicketsRespDto respDtos = TicketTransformer.convertTicketToRespDto(ticket);
        return respDtos;

    }
}
