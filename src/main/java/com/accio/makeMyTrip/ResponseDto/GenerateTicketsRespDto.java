package com.accio.makeMyTrip.ResponseDto;

import com.accio.makeMyTrip.Enums.City;
import com.accio.makeMyTrip.Enums.ModeOfTp;
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
public class GenerateTicketsRespDto {

    private String companyName;
    private ModeOfTp modeOfTp;
    private String Source_And_Destination;
    private LocalDate journeyDate;
    private LocalTime startTime;
    private Integer noOfPeopleCanTravel;
    private String seatNos;
    private Integer totalPrice;
}
