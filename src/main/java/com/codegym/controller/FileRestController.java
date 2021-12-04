package com.codegym.controller;

import com.codegym.model.dto.FileForm;
import com.codegym.model.entity.File;
import com.codegym.model.entity.Post;
import com.codegym.service.file.IFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/files")
public class FileRestController {
    @Autowired
    private IFileService fileService;

    @Value("${file-upload}")
    private String fileUpload;

    @GetMapping
    public ResponseEntity<Iterable<File>> findAll() {
        return new ResponseEntity<>(fileService.findAll(), HttpStatus.OK);
    }

    @PostMapping("/postId")
    public ResponseEntity<Iterable<File>> findFileByPostId(@RequestBody Post post) {
        if (post == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(fileService.findFileByPostId(post.getId()), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<File> createFile(FileForm fileForm) {
        List<MultipartFile> multipartFiles = fileForm.getFileNames();
        for (MultipartFile file : multipartFiles) {
            String fileName = file.getOriginalFilename();
            try {
                FileCopyUtils.copy(file.getBytes(), new java.io.File(fileUpload + fileName));
            } catch (IOException e) {
                e.printStackTrace();
            }
            File newFile = new File();
            newFile.setFileName(fileName);
            newFile.setPost(fileForm.getPost());
            newFile.setComment(fileForm.getComment());
            newFile.setMessage(fileForm.getMessage());
            fileService.save(newFile);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
