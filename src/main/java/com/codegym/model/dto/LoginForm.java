package com.codegym.model.dto;

import com.codegym.model.entity.Regex;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


@Data
public class LoginForm {
    @NotEmpty
    @Size(min = 4, max = 20)
    @Pattern(regexp = Regex.USER_REGEX, message = Regex.PASSWORD_MESSAGE)
    private String username;

    @NotEmpty
    @Size(min = 4, max = 20)
    @Pattern(regexp = Regex.PASSWORD_REGEX, message = Regex.PASSWORD_MESSAGE)
    private String password;
}
