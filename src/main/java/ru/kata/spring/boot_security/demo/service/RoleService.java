package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.entity.Role;

import java.util.List;

public interface RoleService {
    Role getRoleById(Long id);
    Role getRoleByName(String name);

    List<Role> getAllRoles();

    Role saveRole(Role role);

    void deleteRoleById(Long id);
    List<Role> findDyIds(List<Long> ids);
}
