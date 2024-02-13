package ru.kata.spring.boot_security.demo.dao;

import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.entity.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Set;
@Repository
public class RoleDaoImpl implements RoleDAO {
    private EntityManager entityManager;

    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    @Override
    public Role getRoleById(Long id) {
        return entityManager.find(Role.class, id);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Role> getRoles() {
        return entityManager.createQuery("from Role").getResultList();
    }

    @Override
    public void saveRoles(Set<Role> roles) {
        for (Role role : roles) {
            entityManager.persist(role);
        }
    }

    @Override
    public void saveRole(Role role) {
        entityManager.persist(role);
    }

    @Override
    public void deleteRoleById(Long id) {
        entityManager.createQuery("delete Role where id = :roleId")
                .setParameter("roleId", id)
                .executeUpdate();
    }
}
