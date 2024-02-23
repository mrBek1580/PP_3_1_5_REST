package ru.kata.spring.boot_security.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.*;

@Entity
@Table(name = "roles")
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "role_name", unique = true)
    @NotEmpty
    private String roleName;

    @JsonIgnore
    @ManyToMany(mappedBy = "roles")
    private List<User> users = new ArrayList<>();

    public Role() {
    }

    public Role(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public String getAuthority() {
        return getRoleName();
    }
    public String getRoleNameString() {
        return getRoleName().replace("ROLE_", "");
    }
    public Long getRoleId() {
        return id;
    }

    public void setRoleId(Long roleId) {
        this.id = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return Objects.equals(id, role.id) && Objects.equals(roleName, role.roleName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, roleName);
    }

    @Override
    public String toString() {
        return "Role{" +
                "roleId=" + id +
                ", roleName='" + roleName + '\'' +
                '}';
    }
}
