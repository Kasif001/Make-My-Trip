package com.accio.makeMyTrip.ResponseDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor

public class UserObjRespDto {
    private String name;
    private Integer age;
    private String gender;
    private String email;
}
