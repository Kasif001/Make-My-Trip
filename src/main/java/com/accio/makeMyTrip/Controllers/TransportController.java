package com.accio.makeMyTrip.Controllers;

import com.accio.makeMyTrip.Enums.City;
import com.accio.makeMyTrip.Enums.ModeOfTp;
import com.accio.makeMyTrip.RequestDto.AddTransportReqDto;
import com.accio.makeMyTrip.ResponseDto.SearchFlightResDto;
import com.accio.makeMyTrip.Services.TransportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/transport")
@Slf4j

public class TransportController {

    @Autowired
    private TransportService transportService;

    @PostMapping("/addTransport")
    public ResponseEntity add(@RequestBody AddTransportReqDto addTransportReqDto){
        try{
            String res = transportService.add(addTransportReqDto);
            return new ResponseEntity(res, HttpStatus.OK);
        }catch (Exception e){
            log.error("Add Transport failed",e.getMessage());
            return new ResponseEntity(e.getMessage(),HttpStatus.NOT_FOUND);
        }

    }


    @GetMapping("/searchFlight")
    public ResponseEntity search(
            @RequestParam("Source") City source,
            @RequestParam("Destination") City dest,
            @RequestParam("Journey Date") LocalDate journeyDate,
            @RequestParam("Mode of Transport") ModeOfTp modeOfTp){

        try{

        List<SearchFlightResDto> resp = transportService.searchFlight(source,dest,journeyDate,modeOfTp);
        return new ResponseEntity(resp,HttpStatus.OK);
        }catch (Exception e){
            log.error("No transport on this route",e.getMessage());
            return new ResponseEntity(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }
}
