package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kata.spring.boot_security.demo.entity.Role;
import ru.kata.spring.boot_security.demo.entity.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class UserController {
    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public UserController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/user")
    public String getUserPage(Model model, Principal principal) {
//        Long id = userService.getUserByUsername(principal.getName()).getId();
        User user = userService.getUserByUsername(principal.getName());
        model.addAttribute("user", user);
        model.addAttribute("authority", principal.getName());
        model.addAttribute("listRoles", roleService.getRoles());
        return "user";
    }

//    @GetMapping("/registration")
//    public String registrationPage(@ModelAttribute("user") User user) {
//        return "registration_user";
//    }
//
//    @PostMapping("/registration")
//    public String performRegistration(@ModelAttribute("user") User user,
//                                      @RequestParam(value = "my_roles") String stringRole) {
//        Set<Role> roles = new HashSet<>();
//        List<Role> list = roleService.getRoles();
//        for (Role role : list) {
//            if (role.getRoleName().equals(stringRole)) {
//                roles.add(role);
//            }
//        }
//
//        user.setRoles(roles);
//        userService.saveUser(user);
//        return "user";
//    }
}
