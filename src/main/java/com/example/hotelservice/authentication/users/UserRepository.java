package com.example.hotelservice.authentication.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class UserRepository {

    @Autowired
    private EntityManager entityManager;

    public AppUser findUserAccount(String userName) {

        try {

            String sql = "Select e from " + AppUser.class.getName() + " e " + " Where e.userName = :userName ";

            Query query = entityManager.createQuery(sql, AppUser.class);
            query.setParameter("userName", userName);

            return (AppUser) query.getSingleResult();

        } catch (NoResultException e) {

            return null;
        }
    }

    public List<AppUser> findAllUsers() {

        try {

            TypedQuery<AppUser> query = entityManager.createQuery("SELECT c FROM AppUser c", AppUser.class);

            return query.getResultList();

        } catch (NoResultException e) {

            return null;
        }
    }
}
