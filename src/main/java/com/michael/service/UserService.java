package com.michael.service;

import com.michael.model.User;
import com.michael.repository.UserRepository;
import com.michael.service.contracts.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements IUserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public void addUser(User user) {
        user.setType("customer");
        userRepository.save(user);
    }

    @Override
    public User login(String email, String password) {
        User user = null;
        Optional<User> userOptional = userRepository.findUserByEmailAndPassword(email, password);
        if (userOptional.isPresent()) {
            user = userOptional.get();
        }

       return user;
    }

    @Override
    public User getUserById(long id) {
        Optional<User> userOptional = userRepository.findById(id);
        User user = null;
        if (userOptional.isPresent()) {
            user = userOptional.get();
            return user;
        }

        return user;

    }


}
