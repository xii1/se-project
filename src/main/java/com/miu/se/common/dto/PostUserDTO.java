package com.miu.se.common.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.miu.se.common.entity.User;
import com.miu.se.common.entity.UserStatus;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class PostUserDTO {
    @Email
    String email;
    @NotBlank(message = "Password is required")
    @Size(min = 5, message = "Password should have min 5 characters")
    String password;
    UserStatus status;
    String nickname;
    String bio;
    String gender;
    String dob;
    String phoneNumber;

    @JsonIgnore
    public User toUser() {
        ModelMapper modelMapper = new ModelMapper();
        User user = modelMapper.map(this, User.class);
        return user;
    }
}
