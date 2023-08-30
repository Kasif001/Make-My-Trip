package com.accio.makeMyTrip.Services;

import com.accio.makeMyTrip.Entities.Route;
import com.accio.makeMyTrip.Enums.City;
import com.accio.makeMyTrip.Repositories.RouteRepository;
import com.accio.makeMyTrip.RequestDto.AddRouteReqDto;
import com.accio.makeMyTrip.ResponseDto.FindAllRoutesRespDto;
import com.accio.makeMyTrip.Transformers.RouteTransformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.transform.Transformer;
import java.util.ArrayList;
import java.util.List;

@Service
public class RouteService {

    @Autowired
    private RouteRepository routeRepository;

    public String addRoute(AddRouteReqDto addRouteReqDto){
        Route routeObj = RouteTransformers.convertDtoToEntity(addRouteReqDto);
        routeRepository.save(routeObj);
        return "Route has been add Successfully";
    }

    public List<FindAllRoutesRespDto> findAllRoutes(City source, City dest) throws Exception{
        List<Route> allRoutes = routeRepository.findRouteBySourceAndDest(source,dest);
        if(allRoutes.size() == 0){
            throw new Exception("There is no route between "+source+" to "+dest+"");
        }

        List<FindAllRoutesRespDto> list = new ArrayList<>();
        for(Route route: allRoutes){
            FindAllRoutesRespDto respDto = RouteTransformers.convertRouteEntityToDto(route);
            list.add(respDto);
        }
        return list;
    }

}
