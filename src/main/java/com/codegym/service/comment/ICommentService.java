package com.codegym.service.comment;

import com.codegym.model.entity.Comment;
import com.codegym.service.IGeneralService;

public interface ICommentService extends IGeneralService<Comment> {
    Iterable<Comment> findCommentByPostId(Long id);
}
