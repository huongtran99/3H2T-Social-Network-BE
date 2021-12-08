package com.codegym.service.comment;

import com.codegym.model.entity.Comment;
import com.codegym.repository.ICommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CommentService implements ICommentService {

    @Autowired
    private ICommentRepository commentRepository;

    @Override
    public Iterable findAll() {
        return commentRepository.findAll();
    }

    @Override
    public Optional findById(Long id) {
        return commentRepository.findById(id);
    }

    @Override
    public Comment save(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    public void remove(Long id) {
        commentRepository.deleteById(id);
    }

    @Override
    public Page<Comment> findCommentByPostId(Long id, Pageable pageable) {
        return commentRepository.findCommentByPostId(id, pageable);
    }
}
