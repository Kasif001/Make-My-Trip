package com.accio.makeMyTrip.RequestDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class AddUserReqDto {
    private String name;
    private Integer age;
    private String gender;
    private String email;
}
