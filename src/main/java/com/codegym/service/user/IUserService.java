package com.codegym.service.user;

import com.codegym.model.entity.User;
import com.codegym.model.querry.IUserChat;
import com.codegym.repository.IUserRepository;
import com.codegym.service.IGeneralService;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface IUserService extends IGeneralService<User>, UserDetailsService {
    User findByUsername(String username);

    User findByEmail(String email);

    Iterable<User> findAllUser(Long id, Long senderId);

    Iterable<IUserChat> getAllUserHasRoleUser();

    IUserChat getUserChatInfo(Long id);

}
