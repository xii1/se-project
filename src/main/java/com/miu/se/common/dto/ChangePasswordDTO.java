package com.miu.se.common.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class ChangePasswordDTO {
    @NotBlank(message = "Password is required")
    @Size(min = 5, message = "Password should have min 5 characters")
    String oldPassword;
    @NotBlank(message = "Password is required")
    @Size(min = 5, message = "Password should have min 5 characters")
    String newPassword;
}
