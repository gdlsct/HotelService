package com.example.hotelservice.repositories;

import com.example.hotelservice.models.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import javax.transaction.Transactional;

@Repository
@Transactional
public class UserRepository {

    @Autowired
    private EntityManager entityManager;

    public AppUser findUserAccount(String userName) {
        try {
            String sql = "Select e from " + AppUser.class.getName() + " e " //
                    + " Where e.userName = :userName ";

            Query query = entityManager.createQuery(sql, AppUser.class);
            query.setParameter("userName", userName);

            return (AppUser) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
