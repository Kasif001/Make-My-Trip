package com.accio.makeMyTrip.ResponseDto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class SearchFlightResDto {

    private Integer transportId;


    private LocalTime startTime;

    private double journeyTime;
    private String companyName;
    private String stopsList;


}
