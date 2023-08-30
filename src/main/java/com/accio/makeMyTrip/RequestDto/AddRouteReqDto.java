package com.accio.makeMyTrip.RequestDto;

import com.accio.makeMyTrip.Enums.City;
import com.accio.makeMyTrip.Enums.ModeOfTp;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data

public class AddRouteReqDto {
    private City source;
    private City des;
    private String stopsList;
    private ModeOfTp modeOfTp;

}
