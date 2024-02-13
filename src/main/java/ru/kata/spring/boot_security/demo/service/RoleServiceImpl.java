package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.dao.RoleDAO;
import ru.kata.spring.boot_security.demo.entity.Role;

import java.util.List;
import java.util.Set;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {
    private final RoleDAO roleRepository;

    @Autowired
    public RoleServiceImpl(RoleDAO roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role getRoleById(Long id) {
        return roleRepository.getRoleById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Role> getRoles() {
        return roleRepository.getRoles();
    }

    @Override
    public void saveRoles(Set<Role> roles) {
        roleRepository.saveRoles(roles);
    }

    @Override
    public void saveRole(Role role) {
        roleRepository.saveRole(role);
    }

    @Override
    public void deleteRoleById(Long id) {
        roleRepository.deleteRoleById(id);
    }
}
