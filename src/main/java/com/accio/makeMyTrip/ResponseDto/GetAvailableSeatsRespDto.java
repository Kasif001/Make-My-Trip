package com.accio.makeMyTrip.ResponseDto;

import com.accio.makeMyTrip.Enums.SeatType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class GetAvailableSeatsRespDto {
    private String seatNo;
    private SeatType seatType;
    private Integer seatPrice;
}
