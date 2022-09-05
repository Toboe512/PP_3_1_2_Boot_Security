package ru.kata.spring.boot_security.demo.dao;

import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class RoleDAOImpl implements RoleDAO {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public Role findRoleById(Long id) {
        return entityManager.createQuery("from Role role where role.id=:id", Role.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public void add(Role role) {
        entityManager.persist(entityManager.merge(role));
    }

    @Override
    public List<Role> listRoles() {
        return entityManager.createQuery("from Role", Role.class).getResultList();
    }

    @Override
    public void delete(Role role) {
        entityManager.remove(entityManager.merge(role));
    }

    @Override
    public void update(Role role) {
        entityManager.persist(entityManager.merge(role));
    }

    @Override
    public Role getRole(Long id) {
        return entityManager.createQuery("from Role role where role.id=:id", Role.class)
                .setParameter("id", id)
                .getSingleResult();
    }
}
