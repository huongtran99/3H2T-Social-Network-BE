package com.codegym.controller;

import com.codegym.model.entity.Comment;
import com.codegym.service.comment.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    private ICommentService commentService;



    @PostMapping()
    public ResponseEntity<Comment> createPost(@RequestBody Comment comment) {
        return new ResponseEntity<>(commentService.save(comment), HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<Iterable<Comment>> findCommentByPost(@PathVariable Long id){
        return new ResponseEntity<>(commentService.findCommentByPostId(id), HttpStatus.OK);
    }
}
