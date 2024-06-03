package com.abhishek.user.service.Services;

import com.abhishek.user.service.entities.User;

import java.util.List;

public interface UserService {

    //create user
    User saveUser(User user);

    //get all user
    List<User> getAllUsers();

    //get single user of given userId
    User getUser(String userId);

    void deleteUser(String userId);

    void updateUser(User user);
}
