package com.accio.makeMyTrip.Services;

import com.accio.makeMyTrip.Entities.User;
import com.accio.makeMyTrip.Exceptions.UserNotFoundException;
import com.accio.makeMyTrip.Repositories.UserRepository;
import com.accio.makeMyTrip.RequestDto.AddUserReqDto;
import com.accio.makeMyTrip.RequestDto.ModifyUserDetailsReqDto;
import com.accio.makeMyTrip.ResponseDto.UserObjRespDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JavaMailSender emailSender;


    public String addUser(AddUserReqDto addUserReqDto){
        User user = User.builder()
                .age(addUserReqDto.getAge())
                .email(addUserReqDto.getEmail())
                .name(addUserReqDto.getName())
                .gender(addUserReqDto.getGender())
                .bookingList2(new ArrayList<>()).build();
        userRepository.save(user);

        //Sending mail to user functionality
        SimpleMailMessage mailMessage = new SimpleMailMessage();

        String body = "Hi "+user.getName()+" "+
                "Sign in successful to Make my trip website.Explore different types of features here!";

        mailMessage.setSubject("Welcome to Make My Trip");
        mailMessage.setFrom("springacciojob@gmail.com");
        mailMessage.setTo(user.getEmail());
        mailMessage.setText(body);

        emailSender.send(mailMessage);


        return "User "+user.getName()+" has been sign successfully. Your UserId is :"+user.getUserId()+". Use this is to make any changes.";
    }

    public String modifyDetails(ModifyUserDetailsReqDto modifyUserDetailsReqDto) throws Exception{
        if(!userRepository.existsById(modifyUserDetailsReqDto.getUserId())){
            throw new UserNotFoundException("User Id is invalid");
        }
        User user = userRepository.findById(modifyUserDetailsReqDto.getUserId()).get();
        user.setName(modifyUserDetailsReqDto.getNewName());
        user.setAge(modifyUserDetailsReqDto.getNewAge());
        user.setEmail(modifyUserDetailsReqDto.getNewEmail());

        userRepository.save(user);

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        String body = "Congratulation "+user.getName()+"\n"+
                "Your modifications of personal details is successful.";
        mailMessage.setSubject("Modification is Successful");
        mailMessage.setFrom("springacciojob@gmail.com");
        mailMessage.setTo(user.getEmail());
        mailMessage.setText(body);
        emailSender.send(mailMessage);
        return "User details has been modified";
    }
    public UserObjRespDto getDetails(Integer userId)throws Exception{
        if(!userRepository.existsById(userId)){
            throw new UserNotFoundException("User not exists");
        }
        User user = userRepository.findById(userId).get();
        UserObjRespDto respDto = new UserObjRespDto(user.getName(),user.getAge(),user.getGender(),user.getEmail());

        return respDto;
    }
}
