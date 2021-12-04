package com.codegym.repository;

import com.codegym.model.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IPostRepository extends JpaRepository<Post, Long> {
    @Query(value = "select * from post order by date_time desc", nativeQuery = true)
    Page<Post> findAllOrderByDateTime(Pageable pageable);

    @Query(value = "select * from post where user_id = ? order by date_time desc", nativeQuery = true)
    Page<Post> findAllByUserOrderByDateTime(Long id, Pageable pageable);
}
