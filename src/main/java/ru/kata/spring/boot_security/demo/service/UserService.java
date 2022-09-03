package ru.kata.spring.boot_security.demo.service;




import ru.kata.spring.boot_security.demo.models.User;
import java.util.List;

public interface UserService {
    void add(User user);

    List<User> listUsers();

    void delete(User user);

    void update(User user);

    User getUser(Long id);

    User getUserByUsername(String username);
}
