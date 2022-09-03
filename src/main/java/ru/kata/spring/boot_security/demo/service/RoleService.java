package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;

public interface RoleService {
    Role findRoleById(Long id);

    User setRolesByStrings(String[] roles, User user);
}
