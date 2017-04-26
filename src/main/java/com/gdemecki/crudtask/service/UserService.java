package com.gdemecki.crudtask.service;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gdemecki.crudtask.domain.User;

@Service
@Transactional
public class UserService {

    private final EntityManager em;

    @Inject
    public UserService(EntityManager em) {
        this.em = em;
    }

    public User createUser(User user) {
        em.persist(user);

        return user;
    }

    public User updateUser(User user) {
        findUserById(user.getId());

        return em.merge(user);
    }

    public User findUserById(Long id) throws UserNotFoundException {
        final User user = em.find(User.class, id);
        if (user == null) {
            throw new UserNotFoundException();
        }

        return user;
    }

    public List<User> findUsers() {
        String queryString = "SELECT u FROM User u";
        return em.createQuery(queryString, User.class).getResultList();
    }

    public User deleteUser(Long userId) {
        final User user = findUserById(userId);
        em.remove(user);

        return user;
    }
}
