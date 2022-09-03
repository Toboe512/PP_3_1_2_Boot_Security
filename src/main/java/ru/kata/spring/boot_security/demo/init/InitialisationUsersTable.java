package ru.kata.spring.boot_security.demo.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.dao.RoleDAO;
import ru.kata.spring.boot_security.demo.dao.UserDAO;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;

import java.util.HashSet;
import java.util.Set;

@Service
@Transactional
public class InitialisationUsersTable implements ApplicationListener<ApplicationReadyEvent> {

    private final UserDAO userDAO;
    private final RoleDAO roleDAO;

    @Autowired
    public InitialisationUsersTable(UserDAO userDAO, RoleDAO roleDAO) {
        this.userDAO = userDAO;
        this.roleDAO = roleDAO;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        roleDAO.add(new Role(1L, "ROLE_USER"));
        roleDAO.add(new Role(2L, "ROLE_ADMIN"));

        Set<Role> userRole = new HashSet<>();
        userRole.add(new Role(1L, "ROLE_USER"));

        Set<Role> adminRole = new HashSet<>();
        adminRole.add(new Role(2L, "ROLE_ADMIN"));

        Set<Role> userAdminRole = new HashSet<>();
        userAdminRole.add(new Role(1L, "ROLE_USER"));
        userAdminRole.add(new Role(2L, "ROLE_ADMIN"));

        userDAO.add(new User("User1", "User1Last", "user1@mail.ru", 20, "user", userRole));
        userDAO.add(new User("User2", "User2Last", "user2@mail.ru", 30, "user", userRole));
        userDAO.add(new User("Admin", "AdminLast", "admin@mail.ru", 40, "admin", adminRole));
        userDAO.add(new User("Useradmin", "UsrerAdminLast", "useradmin@mail.ru", 50, "admin", userAdminRole));
    }
}
