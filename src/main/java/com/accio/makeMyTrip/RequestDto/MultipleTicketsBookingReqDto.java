package com.accio.makeMyTrip.RequestDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class MultipleTicketsBookingReqDto {
    private List<String> list;
    private LocalDate journeyDate;
    private Integer transportId;
    private Integer userId;
}
