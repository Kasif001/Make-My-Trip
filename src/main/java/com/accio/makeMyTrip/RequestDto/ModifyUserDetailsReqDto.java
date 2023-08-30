package com.accio.makeMyTrip.RequestDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class ModifyUserDetailsReqDto {
    private Integer UserId;
    private String newName;
    private Integer newAge;
    private String newEmail;
}
