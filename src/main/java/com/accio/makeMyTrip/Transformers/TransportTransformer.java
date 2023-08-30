package com.accio.makeMyTrip.Transformers;

import com.accio.makeMyTrip.Entities.Transport;
import com.accio.makeMyTrip.RequestDto.AddTransportReqDto;
import com.accio.makeMyTrip.ResponseDto.SearchFlightResDto;
import com.accio.makeMyTrip.Services.TransportService;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class TransportTransformer {
    public static Transport convertDtoToEntity(AddTransportReqDto addTransportReqDto){

        String timeString = addTransportReqDto.getStartTime(); // Replace this with your actual time string

        // Define the format of the time string
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");

        // Parse the string into a LocalTime object
        LocalTime startTimeConverter = LocalTime.parse(timeString, formatter);


        Transport obj = Transport.builder()
                .modeOfTp(addTransportReqDto.getModeOfTp())
                .journeyTime(addTransportReqDto.getJourneyTime())
                .journeyDate(addTransportReqDto.getJourneyDate())
                .startTime(startTimeConverter)
                .companyName(addTransportReqDto.getCompanyName()).build();

        return obj;
    }

    public static SearchFlightResDto convertEntityToFlightDto(Transport transport){

        SearchFlightResDto obj = SearchFlightResDto.builder()
                .transportId(transport.getTransId())
                .companyName(transport.getCompanyName())
                .journeyTime(transport.getJourneyTime())
                .startTime(transport.getStartTime())
                .build();

        return obj;
    }
}
