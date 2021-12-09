package com.codegym.repository;

import com.codegym.model.entity.Reaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IReactionRepository extends JpaRepository<Reaction, Long> {
    @Query(value = "select count(*) from reaction where post_id = ?", nativeQuery = true)
    Integer countByReaction(Long id);

    @Query(value = "select count(*) from reaction where post_id = ? and user_id = ?", nativeQuery = true)
    Integer checkReaction(Long postId, Long userId);

    @Query(value = "select * from reaction where post_id = ? and user_id = ?", nativeQuery = true)
    Optional<Reaction> getReaction(Long postId, Long userId);
}
