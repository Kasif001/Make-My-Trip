package com.accio.makeMyTrip.ResponseDto;

import com.accio.makeMyTrip.Enums.City;
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

public class ReturnBookingsRespDto {
    private Integer ticketId;
    private Integer transportId;
    private City source;
    private City destination;
    private LocalDate journeyDate;
    private LocalTime startTime;
}
