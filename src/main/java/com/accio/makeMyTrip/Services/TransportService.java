package com.accio.makeMyTrip.Services;

import com.accio.makeMyTrip.Entities.Route;
import com.accio.makeMyTrip.Entities.Transport;
import com.accio.makeMyTrip.Enums.City;
import com.accio.makeMyTrip.Enums.ModeOfTp;
import com.accio.makeMyTrip.Exceptions.RouteNotFoundException;
import com.accio.makeMyTrip.Repositories.RouteRepository;
import com.accio.makeMyTrip.Repositories.TransportRepository;
import com.accio.makeMyTrip.RequestDto.AddTransportReqDto;
import com.accio.makeMyTrip.ResponseDto.SearchFlightResDto;
import com.accio.makeMyTrip.Transformers.TransportTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TransportService {

    @Autowired
    private TransportRepository transportRepository;

    @Autowired
    private RouteRepository routeRepository;

    public String add(AddTransportReqDto addTransportReqDto) throws Exception{

        Transport transportObj = TransportTransformer.convertDtoToEntity(addTransportReqDto);

        Integer routeId = addTransportReqDto.getRouteId();
        if(!routeRepository.existsById(routeId)){
            throw new RouteNotFoundException("Route not Found for this Transportation");
        }
        Optional<Route> optionalRoute = routeRepository.findById(routeId);
        Route routeObj = optionalRoute.get();

        //setting foreign key object
        transportObj.setRoute(routeObj);

        //Bidirectional mapping also needs to care
        routeObj.getTransportList().add(transportObj);

        //add parent only, child will auto- save
        routeRepository.save(routeObj);

        return "Transport has been add Successfully";
    }


    public List<SearchFlightResDto> searchFlight(City source, City dest, LocalDate date, ModeOfTp mode) throws Exception{


        List<Route> allRouteList = routeRepository.findAll();
        List<Route> routeList = new ArrayList<>();
        for(Route route: allRouteList){
            if(route.getSource().equals(source) && route.getDest().equals(dest) && route.getModeOfTp().equals(mode)){
                routeList.add(route);
            }
        }

        if(routeList.size() == 0){
            throw new Exception("There is no route available between");
        }

        List<SearchFlightResDto> responseList = new ArrayList<>();
        for(Route route: routeList){

            List<Transport> transportList = route.getTransportList();

            for(Transport transport: transportList){
                if(transport.getJourneyDate().equals(date)){
                    SearchFlightResDto resDto =  TransportTransformer.convertEntityToFlightDto(transport);
                    resDto.setStopsList(route.getListOfStopsInBet());
                    responseList.add(resDto);
                }
            }
        }

        if(responseList.size() == 0){
            throw new Exception("There is no specific Transport on this date. Kindly change date or transport");
        }
        return responseList;
    }
}
