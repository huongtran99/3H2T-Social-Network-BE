package com.codegym.repository;

import com.codegym.model.entity.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IFileRepository extends JpaRepository<File, Long> {
    @Query(value = "select * from file where post_id=?", nativeQuery = true)
    Iterable<File> findFileByPostId(Long id);
}
