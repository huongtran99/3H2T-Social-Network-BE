package com.codegym.repository;

import com.codegym.model.entity.User;
import com.codegym.model.querry.IUserChat;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    User findByEmail(String email);

    @Query(value = "select * from user where user.id != ? and user.id Not in (select receiver_id from friend where sender_id = ?);", nativeQuery = true)
    Iterable<User> findAllUser(Long id, Long senderId);

    @Query(value = "select social_network_3h2t.user.id, social_network_3h2t.user.avatar, social_network_3h2t.user.username, social_network_3h2t.user.password,social_network_3h2t.message.content, social_network_3h2t.message.date_time " +
            "from user " +
            "left join message on user.id = message.sender_id " +
            "where user.id = ?1 " +
            "order by date_time desc " +
            "limit 1;", nativeQuery = true)
    IUserChat getUserChatInfo(Long id);

    @Query(value = " call social_network_3h2t.getAllFriendHasRole() ", nativeQuery = true)
    Iterable<User> getAllUserHasRoleUser();

    @Query(value = "select status from user where username = ?", nativeQuery = true)
    Boolean getStatusByUsername(String username);

    Page<User> findAllByUsernameContaining(String username, Pageable pageable);
}
