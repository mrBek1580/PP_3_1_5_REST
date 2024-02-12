package ru.kata.spring.boot_security.demo.service;


import ru.kata.spring.boot_security.demo.entity.User;

import java.util.List;

public interface UserService {
    void saveUser(User user);

    List<User> getAllUsers();

    void delete(Long userId);

    User getUserById(Long userId);

    void updateUser(Long id, User user);

    User getUserByUsername(String username);
//    UserDetails loadUserByUsername(String username);
}
