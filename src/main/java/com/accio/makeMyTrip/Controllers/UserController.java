package com.accio.makeMyTrip.Controllers;

import com.accio.makeMyTrip.RequestDto.AddUserReqDto;
import com.accio.makeMyTrip.RequestDto.ModifyUserDetailsReqDto;
import com.accio.makeMyTrip.ResponseDto.UserObjRespDto;
import com.accio.makeMyTrip.Services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@Slf4j

public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/addUser")
    public ResponseEntity addUser(@RequestBody AddUserReqDto addUserReqDto){
        String result = userService.addUser(addUserReqDto);
        return new ResponseEntity(result, HttpStatus.CREATED);
    }

    @PutMapping("/modifyUserDetails")
    private ResponseEntity modifyDetails(@RequestBody ModifyUserDetailsReqDto modifyUserDetailsReqDto){
        try{
            String resutl = userService.modifyDetails(modifyUserDetailsReqDto);
            return new ResponseEntity(resutl,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getUserDetails")
    public ResponseEntity getDetails(@RequestParam("userId")Integer userId){
        try{
            UserObjRespDto respDto = userService.getDetails(userId);
            return new ResponseEntity(respDto,HttpStatus.OK);
        }catch (Exception e){
            log.error("User id is invalid in user control",e.getMessage());
            return new ResponseEntity(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }
}
