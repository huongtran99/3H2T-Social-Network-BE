package com.codegym.repository;

import com.codegym.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    User findByEmail(String email);

    @Query(value = "select * from user where user.id != ? and user.id Not in (select receiver_id from friend where sender_id = ?);", nativeQuery = true)
    Iterable<User> findAllUser(Long id, Long senderId);
}
