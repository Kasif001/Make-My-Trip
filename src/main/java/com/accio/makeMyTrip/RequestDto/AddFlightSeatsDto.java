package com.accio.makeMyTrip.RequestDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddFlightSeatsDto {
    private Integer noOfEconomySeats;
    private Integer noOfBusinessSeats;
    private Integer PlaneId;
    private Integer priceEco;
    private Integer priceBuis;
}
