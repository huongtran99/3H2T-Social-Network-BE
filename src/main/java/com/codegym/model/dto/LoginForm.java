package com.codegym.model.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


@Data
public class LoginForm {
    @NotEmpty
    @Size(min = 4, max = 20)
    @Pattern(regexp = "^[a-zA-Z0-9]+([a-zA-Z0-9]([_\\\\-])[a-zA-Z0-9])*[a-zA-Z0-9]+${4,}", message = "* invalid username")
    private String username;

    @NotEmpty
    @Size(min = 4, max = 20)
    @Pattern(regexp = "^(?:(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).*)[^\\s]{4,}$", message = "* invalid password")
    private String password;
}
