package com.codegym.service.post;

import com.codegym.model.entity.Post;
import com.codegym.service.IGeneralService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IPostService extends IGeneralService<Post> {
    Page<Post> findAll(Pageable pageable);

    Page<Post> findAllOrderByDateTime(Pageable pageable);

    Page<Post> findAllByUserOrderByDateTime(Long id, Pageable pageable);

}
