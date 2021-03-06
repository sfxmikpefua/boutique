package com.michael.service.contracts;

import com.michael.model.User;

public interface IUserService {

    void addUser(User user);

    User login(String email, String password);

    User getUserById(long id);
}
