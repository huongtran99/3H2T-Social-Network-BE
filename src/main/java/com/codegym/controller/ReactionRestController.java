package com.codegym.controller;

import com.codegym.model.entity.Post;
import com.codegym.model.entity.Reaction;
import com.codegym.service.post.IPostService;
import com.codegym.service.reaction.IReactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("reactions")
public class ReactionRestController {
    @Autowired
    private IReactionService reactionService;

    @Autowired
    private IPostService postService;

    @GetMapping("{id}")
    public ResponseEntity<Integer> count(@PathVariable Long id) {
        Optional<Post> postOptional = postService.findById(id);
        return postOptional.map(post -> new ResponseEntity<>(reactionService.countByReaction(post.getId()), HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Reaction> addReaction(@RequestBody Reaction reaction) {
        return new ResponseEntity<>(reactionService.save(reaction), HttpStatus.CREATED);
    }

    @GetMapping("check")
    public ResponseEntity<Integer> checkReaction(@RequestParam(name = "postId") Long postId, @RequestParam(name = "userId") Long userId) {
        return new ResponseEntity<>(reactionService.checkReaction(postId, userId), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<Reaction> unReaction(@RequestParam(name = "postId") Long postId, @RequestParam(name = "userId") Long userId) {
        Optional<Reaction> reactionOptional = reactionService.getReaction(postId, userId);
        if (!reactionOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        reactionService.remove(reactionOptional.get().getId());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
