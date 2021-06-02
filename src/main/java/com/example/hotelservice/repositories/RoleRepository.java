package com.example.hotelservice.repositories;

import java.util.List;

import com.example.hotelservice.models.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import javax.transaction.Transactional;

@Repository
@Transactional
public class RoleRepository {

    @Autowired
    private EntityManager entityManager;

    public List<String> getRoleNames(Long userId) {
        String sql = "Select ur.appRole.roleName from " + UserRole.class.getName() + " ur " //
                + " where ur.appUser.userId = :userId ";

        Query query = this.entityManager.createQuery(sql, String.class);
        query.setParameter("userId", userId);
        return query.getResultList();
    }

}
