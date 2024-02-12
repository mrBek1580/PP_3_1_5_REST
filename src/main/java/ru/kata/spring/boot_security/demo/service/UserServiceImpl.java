package ru.kata.spring.boot_security.demo.service;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.dao.RoleRepository;
import ru.kata.spring.boot_security.demo.dao.UserRepository;
import ru.kata.spring.boot_security.demo.entity.Role;
import ru.kata.spring.boot_security.demo.entity.User;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void delete(Long userId) {
        userRepository.deleteById(userId);
    }

    @Override
    @Transactional(readOnly = true)
    public User getUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return user.get();
        } else {
            throw new UsernameNotFoundException("User with Id " + id + " not found");
        }
    }

    @Override
    public void updateUser(Long id, User user) {
        Optional<User> userBuId = userRepository.findById(id);
        if (userBuId.isPresent()) {
            User userFromRepo = userBuId.get();
            userFromRepo.setId(id);
            userFromRepo.setUsername(user.getUsername());
            userFromRepo.setFirstName(user.getFirstName());
            userFromRepo.setLastName(user.getLastName());
            userFromRepo.setBirthDay(user.getBirthDay());
            userRepository.save(userFromRepo);
        } else {
            throw new UsernameNotFoundException("User with Id " + id + " not found");
        }
    }

    @Override
    @Transactional(readOnly = true)
    public User getUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByUsername(username);
//        if (user == null) {
//            throw new UsernameNotFoundException("User with login " + username + " not found");
//        }
        return user;
    }

//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        return userRepository.findUserByUsername(username);
//    }

}
