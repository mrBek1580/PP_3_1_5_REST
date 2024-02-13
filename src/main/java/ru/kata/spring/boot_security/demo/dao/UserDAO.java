package ru.kata.spring.boot_security.demo.dao;

import ru.kata.spring.boot_security.demo.entity.User;

import java.util.List;


public interface UserDAO {
    void addUser(User user);

    List<User> getAllUsers();

    void removeUserById(Long userId);

    User getUserById(Long userId);

    void updateUser(User user);
    User findUserByUsername(String username);
}
