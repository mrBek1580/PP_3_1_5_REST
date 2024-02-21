package ru.kata.spring.boot_security.demo.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.entity.Role;
import ru.kata.spring.boot_security.demo.entity.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/api")
public class AdminRestController {

    private final UserService userServiceImpl;
    private final RoleService roleServiceImpl;

    public AdminRestController(UserService userServiceImpl, RoleService roleServiceImpl) {
        this.userServiceImpl = userServiceImpl;
        this.roleServiceImpl = roleServiceImpl;
    }

    @GetMapping
    public ResponseEntity<List<User>> showAllUsers() {
        return ResponseEntity.ok(userServiceImpl.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> showOneUser(@PathVariable("id") Long id) {
        User user = userServiceImpl.findUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<HttpStatus> addNewUser(@RequestBody @Valid User newUser) {
        userServiceImpl.saveUser(newUser);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PutMapping("/patch")
    public ResponseEntity<HttpStatus> updateUser(@RequestBody @Valid User userFromWebPage) {
        userServiceImpl.saveUser(userFromWebPage);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable Long id) {
        userServiceImpl.delete(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping(value = "/roles")
    public ResponseEntity<Collection<Role>> getAllRoles() {
        return ResponseEntity.ok(roleServiceImpl.getAllRoles());
    }

//    @GetMapping("/roles/{id}")
//    public ResponseEntity<Collection<Role>> getRole(@PathVariable("id") Long id) {
//        return new ResponseEntity<>(userServiceImpl.findUserById(id).getRoles(), HttpStatus.OK);
//    }

}
