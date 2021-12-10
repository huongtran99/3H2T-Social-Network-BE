package com.codegym.service.user;

import com.codegym.model.dto.UserPrincipal;
import com.codegym.model.entity.User;
import com.codegym.model.querry.IUserChat;
import com.codegym.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService {
    @Autowired
    private IUserRepository userRepository;

    @Override
    public Iterable<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public void remove(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public Iterable<User> findAllUser(Long id, Long senderId) {
        return userRepository.findAllUser(id, senderId);
    }

    @Override
    public Iterable<IUserChat> getAllUserHasRoleUser() {
        List<User> users = (List<User>) userRepository.getAllUserHasRoleUser();
        List<IUserChat> userChats = new ArrayList<>();
        for(User user: users) {
            userChats.add(this.getUserChatInfo(user.getId()));
        }
        return userChats;
    }

    @Override
    public IUserChat getUserChatInfo(Long id) {
        return userRepository.getUserChatInfo(id);
    }

    @Override
    public Boolean getStatusByUsername(String username) {
        return userRepository.getStatusByUsername(username);
    }

    @Override
    public Page<User> findAllByUsernameContaining(String username, Pageable pageable) {
        return userRepository.findAllByUsernameContaining(username, pageable);
    }

    @Override
    public Page<User> findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        return UserPrincipal.build(user);
    }
}
