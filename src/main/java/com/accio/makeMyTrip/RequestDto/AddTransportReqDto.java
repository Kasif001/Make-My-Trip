package com.accio.makeMyTrip.RequestDto;

import com.accio.makeMyTrip.Enums.ModeOfTp;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

@Data

public class AddTransportReqDto {
    private ModeOfTp modeOfTp;
    private LocalDate journeyDate;
    private String startTime;
    private double journeyTime;
    private Integer routeId;
    private String companyName;


}
