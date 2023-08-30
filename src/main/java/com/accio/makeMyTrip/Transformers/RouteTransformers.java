package com.accio.makeMyTrip.Transformers;

import com.accio.makeMyTrip.Entities.Route;
import com.accio.makeMyTrip.RequestDto.AddRouteReqDto;
import com.accio.makeMyTrip.ResponseDto.FindAllRoutesRespDto;

import static org.hibernate.boot.model.process.spi.MetadataBuildingProcess.build;

public class RouteTransformers {

    public static Route convertDtoToEntity(AddRouteReqDto addRouteReqDto){

        Route object = Route.builder()
                       .source(addRouteReqDto.getSource())
                       .dest(addRouteReqDto.getDes())
                       .ListOfStopsInBet(addRouteReqDto.getStopsList())
                        .modeOfTp(addRouteReqDto.getModeOfTp()).build();
        return object;
    }

    public static FindAllRoutesRespDto convertRouteEntityToDto(Route route){
        FindAllRoutesRespDto respDto = FindAllRoutesRespDto.builder()
                .routeId(route.getRouteId())
                .ListOfStopsInBet(route.getListOfStopsInBet())
                .modeOfTp(route.getModeOfTp()).build();
        return respDto;
    }
}
