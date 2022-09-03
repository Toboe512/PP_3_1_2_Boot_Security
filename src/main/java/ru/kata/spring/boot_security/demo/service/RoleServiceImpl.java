package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.dao.RoleDAO;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;


import java.util.HashSet;
import java.util.Set;

@Service
@Transactional(readOnly = true)
public class RoleServiceImpl implements RoleService {
    private final RoleDAO roleDAO;

    @Autowired
    public RoleServiceImpl(RoleDAO roleDAO) {
        this.roleDAO = roleDAO;
    }

    @Override
    public Role findRoleById(Long id) {
        return roleDAO.findRoleById(id);
    }

    @Override
    public User setRolesByStrings(String[] roles, User user) {
        if (roles != null) {
            Set<Role> roleSet = new HashSet<>();
            for (String role : roles) {
                roleSet.add(roleDAO.findRoleById(Long.valueOf(role)));
            }
            user.setRoles(roleSet);
        }
        return user;
    }

    @Override
    public void add(Role role) {
        roleDAO.add(role);
    }
}
