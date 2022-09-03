package ru.kata.spring.boot_security.demo.dao;

import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDAOImp implements UserDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void add(User user) {
        entityManager.persist(entityManager.merge(user));
    }

    @Override
    public List<User> listUsers() {
        return entityManager.createQuery("from User", User.class).getResultList();
    }

    @Override
    public void delete(User user) {
        entityManager.remove(entityManager.merge(user));
    }

    @Override
    public void update(User user) {
        entityManager.persist(entityManager.merge(user));
    }

    @Override
    public User getUser(Long id) {
        return entityManager.createQuery("from User user where user.id=:id", User.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public User getUserByEmail(String email) {
        return entityManager.createQuery("from User user where user.username=:email", User.class)
                .setParameter("email", email)
                .getSingleResult();
    }

    @Override
    public Role getRoleByUser(User user) {
        return entityManager.createQuery("from User user where user.username=:email", Role.class)
                .setParameter("email", user.getEmail())
                .getSingleResult();
    }
}
