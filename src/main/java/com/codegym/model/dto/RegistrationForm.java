package com.codegym.model.dto;

import com.codegym.exception.UniqueEmail;
import com.codegym.exception.UniqueUsername;
import com.codegym.model.entity.Regex;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


@Data
public class RegistrationForm {
    @UniqueUsername
    @NotEmpty
    @Size(min = 4, max = 20)
    @Pattern(regexp = Regex.USER_REGEX, message = Regex.USERNAME_MESSAGE)
    private String username;

    @NotEmpty
    @Size(min = 4, max = 20)
    @Pattern(regexp = Regex.PASSWORD_REGEX, message = Regex.PASSWORD_MESSAGE)
    private String password;

    @NotEmpty
    @Size(min = 4, max = 20)
    @Pattern(regexp = Regex.PASSWORD_REGEX, message = Regex.RE_PASSWORD_MESSAGE)
    private String rePassword;

    @UniqueEmail
    @NotEmpty
    @Pattern(regexp = Regex.EMAIL_REGEX, message = Regex.PASSWORD_MESSAGE)
    private String email;

}
