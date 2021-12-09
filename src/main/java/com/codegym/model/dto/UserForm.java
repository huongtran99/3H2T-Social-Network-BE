package com.codegym.model.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Email;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;


@Data
public class UserForm {

    private Long id;

    @NotEmpty
    private String password;

    @NotEmpty
    @Email
    @Pattern(regexp = "^[_A-Za-z0-9-\\\\+]+(\\\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\\\.[A-Za-z0-9]+)*(\\\\.[A-Za-z]{2,})$")
    private String email;

    @NotEmpty
    @Pattern(regexp = "\\d{9,11}")
    private String phone;

    @NotNull
    @Pattern(regexp = "dd/mm/yyyy")
    private String birthday;

    @NotNull
    private String gender;

    private MultipartFile avatar;

    private MultipartFile cover;
}
