package com.miu.se.common.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
public class AuthenticationUserDTO {
    @Email
    private String email;
    @NotBlank(message = "Password is required")
    @Size(min = 5, message = "Password should have min 5 characters")
    private String password;
}
