package com.mkolongo.heros.domain.models.binding;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class UserRegisterModel {

    @NotBlank(message = "Username Required!")
    private String username;

    @NotBlank(message = "Password Required!")
    private String password;

    @NotBlank(message = "Confirm Password Required!")
    private String confirmPassword;

    @Email(regexp = "^\\w+[.\\w]*@\\w+[.\\w]*", message = "Invalid Email Address!")
    private String email;

}
