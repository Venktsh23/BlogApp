package edu.blogapp.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.blogapp.entity.User;
import edu.blogapp.repository.UserRepository;
import edu.blogapp.service.UserService;

@Service
public class UserServiceImpl implements UserService {

   @Autowired
   UserRepository repository;

   @Override
   public User saveUser(User user) {
      return repository.save(user);
   }

   @Override
   public Optional<User> getUserByEmail(String email) {
      return repository.findByEmail(email);
   }

}
