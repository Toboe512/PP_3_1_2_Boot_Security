package ru.kata.spring.boot_security.demo.dao;

import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.models.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
}
