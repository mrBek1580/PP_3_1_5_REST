package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.dao.UserDAO;
import ru.kata.spring.boot_security.demo.entity.User;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    private final UserDAO userRepository;

    @Autowired
    public UserServiceImpl(UserDAO userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void saveUser(User user) {
        userRepository.addUser(user);
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> getAllUsers() {
        return userRepository.getAllUsers();
    }

    @Override
    public void delete(Long userId) {
        userRepository.removeUserById(userId);
    }

    @Override
    @Transactional(readOnly = true)
    public User getUserById(Long id) {
        User user = userRepository.getUserById(id);
        if (user != null) {
            return user;
        } else {
            throw new UsernameNotFoundException("User with Id " + id + " not found");
        }
    }

    @Override
    public void updateUser(Long id, User user) {
        User userById = userRepository.getUserById(id);
        if (userById != null) {
            user.setId(id);
            userById.setUsername(user.getUsername());
            userById.setFirstName(user.getFirstName());
            userById.setLastName(user.getLastName());
            userById.setBirthDay(user.getBirthDay());
            userById.setRoles(user.getRoles());
            userRepository.updateUser(userById);
        } else {
            throw new UsernameNotFoundException("User with Id " + id + " not found");
        }
    }

    @Override
    @Transactional(readOnly = true)
    public User getUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findUserByUsername(username);
    }

}
