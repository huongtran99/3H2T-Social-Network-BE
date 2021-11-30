package com.codegym.model.dto;

import com.codegym.model.entity.Comment;
import com.codegym.model.entity.Message;
import com.codegym.model.entity.Post;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class FileForm {
    private Long id;

    private List<MultipartFile> fileNames;

    private Post post;

    private Comment comment;

    private Message message;
}
