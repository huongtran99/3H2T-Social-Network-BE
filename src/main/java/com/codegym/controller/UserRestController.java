package com.codegym.controller;

import com.codegym.model.dto.UserForm;
import com.codegym.model.dto.LoginForm;
import com.codegym.model.dto.RegistrationForm;
import com.codegym.model.entity.User;
import com.codegym.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Pattern;
import java.io.File;
import java.io.IOException;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/users")
public class UserRestController {
    @Autowired
    private IUserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Value("${file-upload}")
    private String fileUpload;

    @GetMapping
    public ResponseEntity<Iterable<User>> showAll() {
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        Optional<User> userOptional = userService.findById(id);
        return userOptional.map(user -> new ResponseEntity<>(user, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Sửa information
    @PutMapping("/{id}")
    public ResponseEntity<User> updateProfile(@Validated @PathVariable Long id, @RequestBody  User user, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Optional<User> userOptional = userService.findById(id);
        if (!userOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        User user1 = new User();
        user1.setId(id);
        user1.setUsername(userOptional.get().getUsername());
        user1.setPassword(userOptional.get().getPassword());
        user1.setEmail(user.getEmail());
        user1.setPhone(user.getPhone());
        user1.setBirthday(user.getBirthday());
        user1.setGender(user.getGender());
        user1.setStatus(userOptional.get().isStatus());
        user1.setRoles(userOptional.get().getRoles());
        user1.setAvatar(userOptional.get().getAvatar());
        user1.setCover(userOptional.get().getCover());
        return new ResponseEntity<>(userService.save(user1), HttpStatus.OK);
    }

    @PutMapping("/update-information/{id}")
    public ResponseEntity<User> changePassWord(@PathVariable Long id, @RequestBody LoginForm passNew ) {

        Optional<User> userOptional = userService.findById(id);
        if (!userOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        userOptional.get().setPassword(passwordEncoder.encode(passNew.getPassword()));
        userService.save(userOptional.get());
        return new ResponseEntity<>(userOptional.get(), HttpStatus.OK);
    }
    @PutMapping("/update-cover/{id}")
    public ResponseEntity<User> updateCover(@Validated @PathVariable Long id, UserForm userForm, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Optional<User> userOptional = userService.findById(id);
        if (!userOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        User user1 = new User();
        user1.setId(id);
        user1.setUsername(userOptional.get().getUsername());
        user1.setPassword(userOptional.get().getPassword());
        user1.setEmail(userOptional.get().getEmail());
        user1.setPhone(userOptional.get().getPhone());
        user1.setBirthday(userOptional.get().getBirthday());
        user1.setGender(userOptional.get().getGender());
        user1.setStatus(userOptional.get().isStatus());
        user1.setRoles(userOptional.get().getRoles());
        user1.setAvatar(userOptional.get().getAvatar());
        MultipartFile multipartFile = userForm.getCover();
        String fileName = multipartFile.getOriginalFilename();
        try {
            FileCopyUtils.copy(multipartFile.getBytes(), new File(fileUpload + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        user1.setCover(fileName);
        return new ResponseEntity<>(userService.save(user1), HttpStatus.OK);
    }

    @PutMapping("/update-avatar/{id}")
    public ResponseEntity<User> updateAvatar(@Validated @PathVariable Long id, UserForm userForm, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Optional<User> userOptional = userService.findById(id);
        if (!userOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        User user1 = new User();
        user1.setId(id);
        user1.setUsername(userOptional.get().getUsername());
        user1.setPassword(userOptional.get().getPassword());
        user1.setEmail(userOptional.get().getEmail());
        user1.setPhone(userOptional.get().getPhone());
        user1.setBirthday(userOptional.get().getBirthday());
        user1.setGender(userOptional.get().getGender());
        user1.setStatus(userOptional.get().isStatus());
        user1.setRoles(userOptional.get().getRoles());
        user1.setCover(userOptional.get().getCover());
        MultipartFile multipartFile = userForm.getAvatar();
        String fileName = multipartFile.getOriginalFilename();
        try {
            FileCopyUtils.copy(multipartFile.getBytes(), new File(fileUpload + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        user1.setAvatar(fileName);
        return new ResponseEntity<>(userService.save(user1), HttpStatus.OK);
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<User> updateUser(@Validated @PathVariable Long id, UserForm userForm, BindingResult bindingResult) {
//        if (bindingResult.hasFieldErrors()) {
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
//
//        Optional<User> userOptional = userService.findById(id);
//        if (!userOptional.isPresent()) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        User user1 = new User();
//        user1.setId(id);
//        user1.setUsername(userOptional.get().getUsername());
//        user1.setEmail(userForm.getEmail());
//        user1.setPhone(userForm.getPhone());
//        user1.setBirthday(userForm.getBirthday());
//        user1.setGender(userForm.getGender());
//        user1.setStatus(userOptional.get().isStatus());
//        user1.setRoles(userOptional.get().getRoles());
//
//        MultipartFile multipartFile = userForm.getAvatar();
//        String fileName = multipartFile.getOriginalFilename();
//        try {
//            FileCopyUtils.copy(multipartFile.getBytes(), new File(fileUpload + fileName));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        MultipartFile multipartFile1 = userForm.getCover();
//        String fileName1 = multipartFile1.getOriginalFilename();
//        try {
//            FileCopyUtils.copy(multipartFile.getBytes(), new File(fileUpload + fileName1));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        user1.setAvatar(fileName);
//        user1.setCover(fileName1);
//        return new ResponseEntity<>(userService.save(user1), HttpStatus.OK);
//    }
}
