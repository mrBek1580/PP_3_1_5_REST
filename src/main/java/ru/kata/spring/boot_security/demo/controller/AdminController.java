package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.entity.Role;
import ru.kata.spring.boot_security.demo.entity.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.RoleServiceImpl;
import ru.kata.spring.boot_security.demo.service.UserService;
import ru.kata.spring.boot_security.demo.service.UserServiceImpl;

import java.util.Set;


@Controller
@RequestMapping(value = "/admin")
public class AdminController {
    private static final String REDIRECT_TO_ADMIN = "redirect:/admin";
    private final UserServiceImpl userServiceImpl;
    private final RoleServiceImpl roleServiceImpl;
//    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AdminController(UserServiceImpl userServiceImpl, RoleServiceImpl roleServiceImpl) {
        this.userServiceImpl = userServiceImpl;
        this.roleServiceImpl = roleServiceImpl;
    }

    @GetMapping()
    public String getAdminPage(Model model) {
        model.addAttribute("allUsers", userServiceImpl.getAllUsers());
        return "admin_page";
    }

    @GetMapping(value = "/add_user")
    public String getNewUserForm(@ModelAttribute("user") User user, Model model) {
        model.addAttribute("roles", roleServiceImpl.getAllRoles());
        return "new_user_form";
    }

    @PostMapping(value = "/createNew")
    public String addUser(@ModelAttribute("user") User user, @RequestParam("roles") Set<Long> roleIds, Model model) {
        Set<Role> roles = roleServiceImpl.findDyIds(roleIds);
        user.setRoles(roles);
        userServiceImpl.saveUser(user);
        return REDIRECT_TO_ADMIN;
    }

    @GetMapping(value = "/{id}/show_user")
    public String showSingleUser(@PathVariable(value = "id") Long id, Model model) {
        model.addAttribute("user", userServiceImpl.findUserById(id));
        return "user";
    }

    @GetMapping(value = "/{id}/edit")
    public String getUserEditForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userServiceImpl.findUserById(id));
        model.addAttribute("roles", roleServiceImpl.getAllRoles());
        return "edit_user_form";
    }

    @PostMapping(value = "/{id}")
    public String updateUser(@ModelAttribute("user") User user,
                             @PathVariable("id") Long id,
                             @RequestParam("roles") Set<Long> roleIds,
                             Model model) {
        Set<Role> roles = roleServiceImpl.findDyIds(roleIds);
        user.setRoles(roles);
        userServiceImpl.updateUser(user);
        return REDIRECT_TO_ADMIN;
    }

    @GetMapping(value = "/{id}/delete")
    public String deleteUserById(@PathVariable("id") Long id) {
        userServiceImpl.delete(id);
        return REDIRECT_TO_ADMIN;
    }
}
