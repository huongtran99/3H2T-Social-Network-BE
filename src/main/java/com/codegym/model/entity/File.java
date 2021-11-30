package com.codegym.model.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class File {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fileName;

    @ManyToOne
    private Post post;

    @ManyToOne
    private Comment comment;

    @ManyToOne
    private Message message;
}
