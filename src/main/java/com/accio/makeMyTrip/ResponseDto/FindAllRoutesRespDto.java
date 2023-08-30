package com.accio.makeMyTrip.ResponseDto;

import com.accio.makeMyTrip.Enums.ModeOfTp;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FindAllRoutesRespDto {
    private Integer routeId;
    private ModeOfTp modeOfTp;
    private String ListOfStopsInBet;
}
