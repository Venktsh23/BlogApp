package edu.blogapp.service;

import java.util.Optional;

import edu.blogapp.entity.User;

public interface UserService {

    User saveUser(User user);

    Optional<User> getUserByEmail(String email);

}
