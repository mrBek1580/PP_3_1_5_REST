package ru.kata.spring.boot_security.demo.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.entity.Role;
import ru.kata.spring.boot_security.demo.entity.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
@Transactional
public class InitDataBase {
    private final UserService userService;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public InitDataBase(UserService userService, RoleService roleService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void initUsers() {
        initRoles();
        initAdmin();
        initFirstUser();
    }
    private void initRoles() {
        Set<Role> roles = new HashSet<>();
        roles.add(new Role("ROLE_ADMIN"));
        roles.add(new Role("ROLE_USER"));
        roleService.saveRoles(roles);
    }

    private void initAdmin() {
        List<Role> adminRoles = new ArrayList<>();
        List<Role> roles = roleService.getRoles();
        for (Role role : roles) {
            if (role.getRoleName().equals("ROLE_ADMIN")){
                adminRoles.add(role);
            }
        }
        User admin = new User("admin", "admin");
        admin.setUsername("admin");
        admin.setPassword(passwordEncoder.encode("admin"));
        admin.setRoles(adminRoles);
        userService.saveUser(admin);
    }

    private void initFirstUser() {
        List<Role> userRoles = new ArrayList<>();
        List<Role> roles = roleService.getRoles();
        for (Role role : roles) {
            if (role.getRoleName().equals("ROLE_USER")) {
                userRoles.add(role);
            }
        }
        User user = new User("test", "test");
        user.setUsername("test");
        user.setPassword(passwordEncoder.encode("test"));
        user.setRoles(userRoles);
        userService.saveUser(user);
    }
}
