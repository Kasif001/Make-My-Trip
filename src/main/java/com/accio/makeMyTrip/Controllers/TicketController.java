package com.accio.makeMyTrip.Controllers;

import com.accio.makeMyTrip.ResponseDto.GenerateTicketsRespDto;
import com.accio.makeMyTrip.Services.TicketService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/ticket")
@Slf4j
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @GetMapping("/generateTickets")
    public ResponseEntity generate(@RequestParam("Ticket Id")Integer ticketId){
        try{
            GenerateTicketsRespDto respDto = ticketService.getTicket(ticketId);
            return new ResponseEntity(respDto, HttpStatus.OK);
        }catch (Exception e){
            log.error("There is error in generation of tickets",e.getMessage());
            return new ResponseEntity(e.getMessage(),HttpStatus.EXPECTATION_FAILED);
        }
    }
}
