package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.entity.Role;
import ru.kata.spring.boot_security.demo.entity.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.Set;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {
    private final UserService userService;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AdminController(UserService userService, RoleService roleService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping()
    public String getAdminPage(Model model) {
        model.addAttribute("allUsers", userService.getAllUsers());
        return "admin_page";
    }
    @PostMapping("/createNew")
    public String addUser(@ModelAttribute("user") User user,
                                 Model model,
                                 BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            model.addAttribute("user", user);
            model.addAttribute("roles", roleService.getRoles());
            return "new_user_form";
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.saveUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/addUser")
    public String getNewUserForm(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("roles", roleService.getRoles());
        return "new_user_form";
    }

    @GetMapping("show_user")
    public String showSingleUser(@RequestParam(value = "id") Long id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "user";
    }

    @GetMapping(value = "/{id}/edit")
    public String getUserEditForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        model.addAttribute("roles", roleService.getRoles());
        return "edit_user_form";
    }

    @PostMapping("/{id}")
    public String updateUser(@ModelAttribute("user") User user,
                         @PathVariable ("id") Long id) {
        user.setPassword(user.getPassword());
        userService.updateUser(id, user);
        return "redirect:/admin";
    }

    @GetMapping(value = "/{id}/delete")
    public String deleteUserById(@PathVariable("id") Long id) {
//        roleService.deleteRoleById(id);
        userService.delete(id);
        return "redirect:/admin";
    }
}
