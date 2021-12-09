package com.codegym.controller;

import com.codegym.model.entity.Comment;
import com.codegym.service.comment.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    private ICommentService commentService;

    @PostMapping()
    public ResponseEntity<Comment> createComment(@RequestBody Comment comment) {
        return new ResponseEntity<>(commentService.save(comment), HttpStatus.CREATED);
    }

    @GetMapping("/post/{id}")
    public ResponseEntity<Page<Comment>> getCommentByPost(@PathVariable Long id, @PageableDefault(size = 3) Pageable pageable) {
        Page<Comment> commentOptional = commentService.findCommentByPostId(id, pageable);
        return new ResponseEntity<>(commentOptional, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Comment> findCommentById(@PathVariable Long id) {
        Optional<Comment> commentOptional = commentService.findById(id);
        if (commentOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(commentOptional.get(), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Comment> delCommentById(@PathVariable Long id) {
        Optional<Comment> commentOptional = commentService.findById(id);
        if (commentOptional.isPresent()) {
            long millis = System.currentTimeMillis();
            Date date = new Date(millis);
            commentOptional.get().setDateTime(date);
            commentService.remove(id);
            return new ResponseEntity<>(commentOptional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<Comment> updateCommentById(@PathVariable Long id, @RequestBody Comment comment) {
        Optional<Comment> commentOptional = commentService.findById(id);
        if (commentOptional.isPresent()) {
            long millis = System.currentTimeMillis();
            Date date = new Date(millis);
            commentOptional.get().setDateTime(date);
            comment.setDateTime(date);
            comment.setId(id);
            return new ResponseEntity<>(commentService.save(comment), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}