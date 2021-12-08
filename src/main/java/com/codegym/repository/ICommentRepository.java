package com.codegym.repository;

import com.codegym.model.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ICommentRepository extends JpaRepository<Comment, Long> {
    @Query(value = "select * from comment where post_id=?", nativeQuery = true)
    Iterable<Comment> findCommentByPostId(Long id);
}
