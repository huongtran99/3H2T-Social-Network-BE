package com.codegym.controller;

import com.codegym.model.entity.Post;
import com.codegym.service.post.IPostService;
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
@RequestMapping("/posts")
public class PostRestController {
    @Autowired
    private IPostService postService;

    @GetMapping
    public ResponseEntity<Page<Post>> findAll(@PageableDefault(size = 3) Pageable pageable) {
        Page<Post> postPage = postService.findAllOrderByDateTime(pageable);
        return new ResponseEntity<>(postPage, HttpStatus.OK);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<Page<Post>> findAllByUser(@PathVariable Long id, @PageableDefault(size = 3) Pageable pageable) {
        Page<Post> posts = postService.findAllByUserOrderByDateTime(id, pageable);
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Post> findById(@PathVariable Long id) {
        Optional<Post> postOptional = postService.findById(id);
        if (postOptional.isPresent()) {
            return new ResponseEntity<>(postOptional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Post> createPost(@RequestBody Post post) {
        long millis = System.currentTimeMillis();
        Date date = new Date(millis);
        post.setDateTime(date);
        return new ResponseEntity<>(postService.save(post), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Post> editPostById(@PathVariable Long id, @RequestBody Post post) {
        Optional<Post> postOptional = postService.findById(id);
        if (postOptional.isPresent()) {
            long millis = System.currentTimeMillis();
            Date date = new Date(millis);
            post.setId(postOptional.get().getId());
            post.setDateTime(date);
            post.setUser(postOptional.get().getUser());
            return new ResponseEntity<>(postService.save(post), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Post> deletePostById(@PathVariable Long id) {
        Optional<Post> postOptional = postService.findById(id);
        if (postOptional.isPresent()) {
            postService.remove(id);
            return new ResponseEntity<>(postOptional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
