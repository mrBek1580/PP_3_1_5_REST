package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.entity.Role;

import java.util.List;
import java.util.Set;

public interface RoleService {
    Role getRoleById(Long id);

    List<Role> getRoles();

    void saveRoles(Set<Role> roles);
    void saveRole(Role role);

    void deleteRoleById(Long id);
}
