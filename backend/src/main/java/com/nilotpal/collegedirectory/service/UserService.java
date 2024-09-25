package com.nilotpal.collegedirectory.service;

import com.nilotpal.collegedirectory.exception.CollegeDirectoryException;
import com.nilotpal.collegedirectory.model.User;
import com.nilotpal.collegedirectory.repository.UserRepository;
import com.nilotpal.collegedirectory.request.LoginRequest;
import com.nilotpal.collegedirectory.request.SignupRequest;
import com.nilotpal.collegedirectory.response.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;


    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public BaseResponse createUser(SignupRequest request) {
        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setPhone(request.getPhone());
        user.setRole(request.getRole());
        user.setUsername(request.getUsername());
        user.setName(request.getUsername());
        userRepository.save(user);
        return BaseResponse.builder().message("User signed up successfully").build();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }


    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User updateUser(Long id, User userDetails) {
        User user = userRepository.findById(id).orElse(null);
        if (user != null) {
            user.setName(userDetails.getName());
            user.setEmail(userDetails.getEmail());
            user.setPhone(userDetails.getPhone());
            user.setRole(userDetails.getRole());
            userRepository.save(user);
        }
        return user;
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public BaseResponse login(LoginRequest request) throws CollegeDirectoryException {
        Optional<User> user = userRepository.findByUsernameAndPasswordAndRole(request.getUsername(), request.getPassword(), request.getRole());
        if (!user.isEmpty()) {
            return BaseResponse.builder().userId(user.get().getId()).message("Logged in successfully").build();
        }
        throw new CollegeDirectoryException("Invalid Credentials.");
    }
}