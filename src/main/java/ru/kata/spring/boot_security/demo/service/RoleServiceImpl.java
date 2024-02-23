package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.dao.RoleDAO;
import ru.kata.spring.boot_security.demo.entity.Role;

import java.util.List;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {
    private final RoleDAO roleRepository;

    @Autowired
    public RoleServiceImpl(RoleDAO roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Role> getAllRoles() {
        return roleRepository.getAllRoles();
    }

    @Override
    public Role saveRole(Role role) {
        return roleRepository.saveRole(role);
    }

    @Override
    public Role getRoleById(Long id) {
        return roleRepository.getRoleById(id);
    }

    @Override
    public Role getRoleByName(String name) {
        return roleRepository.getRoleByName(name);
    }

    @Override
    public void deleteRoleById(Long id) {
        roleRepository.deleteRoleById(id);
    }

    @Override
    public List<Role> findDyIds(List<Long> ids) {
        return roleRepository.findByIds(ids);
    }
}
