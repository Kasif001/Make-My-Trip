package com.accio.makeMyTrip.RequestDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class AddBusSeatsReqDto {
    private Integer noOfNoramalSeats;
    private Integer noOfSleepers;
    private Integer BusId;
    private Integer priceNormalSeat;
    private Integer priceOfSleeper;
}
