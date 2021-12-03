package com.codegym.model.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Email;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class UserForm {
    @NotEmpty
    private Long id;

    @NotEmpty
    private String password;

    @NotEmpty
    @Email
    private String email;

    @NotEmpty
    @Pattern(regexp = "[0-9]{10}", message = "Phone length is 10 and about 0-9")
    private String phone;

    @NotNull
    private String birthday;

    @NotNull
    private String gender;

    private MultipartFile avatar;

    private MultipartFile cover;
}
