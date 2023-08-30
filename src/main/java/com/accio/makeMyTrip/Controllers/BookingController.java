package com.accio.makeMyTrip.Controllers;


import com.accio.makeMyTrip.RequestDto.MultipleTicketsBookingReqDto;
import com.accio.makeMyTrip.RequestDto.TicketBookingReqDto;
import com.accio.makeMyTrip.ResponseDto.GenerateTicketsRespDto;
import com.accio.makeMyTrip.ResponseDto.GetAvailableSeatsRespDto;
import com.accio.makeMyTrip.ResponseDto.ReturnBookingsRespDto;
import com.accio.makeMyTrip.Services.BookingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/booking")
@Slf4j

public class BookingController {

    @Autowired
    private BookingService bookingService;

    @GetMapping("/getAvailableSeats")
    public ResponseEntity getSeats(@RequestParam("Journey Date")LocalDate date, @RequestParam("Transport No")Integer trasportId){

        List<GetAvailableSeatsRespDto> resultList = bookingService.getAllSeats(date,trasportId);
        return new ResponseEntity(resultList, HttpStatus.OK);
    }

    @PostMapping("/bookTickets")
    public ResponseEntity book(@RequestBody TicketBookingReqDto ticketBookingReqDto){

        try{
            String result = bookingService.SeatBooking(ticketBookingReqDto);
            return new ResponseEntity(result,HttpStatus.OK);
        }catch (Exception e){
            log.error("There is error in booking seat and generate ticket",e.getMessage());
            return new ResponseEntity(e.getMessage(),HttpStatus.EXPECTATION_FAILED);
        }

    }

    @GetMapping("/findMyBookings")
    public ResponseEntity generate(@RequestParam("User Id")Integer userId, @RequestParam("Journey Date")LocalDate date){
        try{
            List<ReturnBookingsRespDto> respDto = bookingService.getBookings(userId,date);
            return new ResponseEntity(respDto, HttpStatus.OK);
        }catch (Exception e){
            log.error("There is error in generation of tickets",e.getMessage());
            return new ResponseEntity(e.getMessage(),HttpStatus.EXPECTATION_FAILED);
        }
    }

}
