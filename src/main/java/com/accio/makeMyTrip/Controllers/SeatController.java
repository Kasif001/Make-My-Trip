package com.accio.makeMyTrip.Controllers;

import com.accio.makeMyTrip.RequestDto.AddBusSeatsReqDto;
import com.accio.makeMyTrip.RequestDto.AddFlightSeatsDto;
import com.accio.makeMyTrip.RequestDto.AddTrainSeatsReqDto;
import com.accio.makeMyTrip.RequestDto.AddTransportReqDto;
import com.accio.makeMyTrip.Services.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/seat")
public class SeatController {

    @Autowired
    private SeatService seatService;


    @PostMapping("/addPlaneSeats")
    public String add(@RequestBody AddFlightSeatsDto addFlightSeatsDto){
        return seatService.addPlaneSeats(addFlightSeatsDto);
    }

    @PostMapping("/addBusSeats")
    public String add(@RequestBody AddBusSeatsReqDto addBusSeatsReqDto){
        return seatService.addBusSeats(addBusSeatsReqDto);
    }

    @PostMapping("/addTrainSeats")
    public String add(@RequestBody AddTrainSeatsReqDto addTrainSeatsReqDto){
        return seatService.addTrainSeats(addTrainSeatsReqDto);
    }

}
