package com.codegym.model.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;

@Data
public class UserForm {
    @NotEmpty
    private Long id;

    private String password;

    private String email;

    private String phone;

    private String birthday;

    private String gender;

    private MultipartFile avatar;

    private MultipartFile cover;
}
