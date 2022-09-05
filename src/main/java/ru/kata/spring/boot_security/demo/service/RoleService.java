package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;

import java.util.List;

public interface RoleService {
    Role findRoleById(Long id);

    User setRolesByStrings(String[] roles, User user);

    void add(Role role);

    List<Role> listRoless();

    void delete(Role role);

    void update(Role role);

    Role getRole(Long id);
}
