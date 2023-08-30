package com.accio.makeMyTrip.RequestDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class AddTrainSeatsReqDto {
    private Integer noOfJournalSeats;
    private Integer noOfACSeats;
    private Integer trainId;
    private Integer priceOfJournal;
    private Integer priceOfAC;
}
