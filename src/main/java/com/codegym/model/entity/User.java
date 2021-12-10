package com.codegym.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(unique = true, nullable = false)
    private String email;

    private String phone;

    private String birthday;

    private String gender;

    @Column(columnDefinition = "varchar(255) default 'default-avatar.png'")
    private String avatar;

    @Column(columnDefinition = "varchar(255) default 'default-cover.png'")
    private String cover;

    @Column(columnDefinition = "boolean default true")
    private boolean status;

    @ManyToMany (fetch = FetchType.EAGER)
    private Set<Role> roles;
}
