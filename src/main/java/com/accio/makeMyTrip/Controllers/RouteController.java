package com.accio.makeMyTrip.Controllers;

import com.accio.makeMyTrip.Enums.City;
import com.accio.makeMyTrip.RequestDto.AddRouteReqDto;
import com.accio.makeMyTrip.ResponseDto.FindAllRoutesRespDto;
import com.accio.makeMyTrip.Services.RouteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/route")
@Slf4j

public class RouteController {

    @Autowired
    private RouteService routeService;

    @PostMapping("/addRoute")
    public String add(@RequestBody AddRouteReqDto reqDto){
        return routeService.addRoute(reqDto);
    }

    @GetMapping("/findAllRoute")
    public ResponseEntity findRoute(@RequestParam("Source") City source, @RequestParam("Destination") City dest){
        try {
            List<FindAllRoutesRespDto> list = routeService.findAllRoutes(source,dest);
            return new ResponseEntity(list, HttpStatus.OK);
        }catch (Exception e){
            log.error("There is not route found between source to dest in route controller",e.getMessage());
            return new ResponseEntity(e.getMessage(),HttpStatus.NOT_FOUND);

        }
    }

}
