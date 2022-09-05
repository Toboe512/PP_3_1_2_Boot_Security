package ru.kata.spring.boot_security.demo.dao;

import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;

import java.util.List;

public interface RoleDAO {

    Role findRoleById(Long id);

    void add(Role role);

    List<Role> listRoles();

    void delete(Role role);

    void update(Role role);

    Role getRole(Long id);
}
