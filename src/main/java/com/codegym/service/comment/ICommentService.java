package com.codegym.service.comment;

import com.codegym.model.entity.Comment;
import com.codegym.service.IGeneralService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ICommentService extends IGeneralService<Comment> {
    Page<Comment> findCommentByPostId(Long id, Pageable pageable);
}
