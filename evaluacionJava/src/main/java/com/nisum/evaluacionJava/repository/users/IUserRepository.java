package com.nisum.evaluacionJava.repository.users;

import com.nisum.evaluacionJava.model.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface IUserRepository extends JpaRepository<User, String> {

    @Query("SELECT u FROM User u WHERE u.email = :email")
    User getUserByEmail(String email);

    @Transactional
    @Modifying
    @Query("UPDATE User u SET u.active = 0 WHERE u.id = :id")
    void updateStateToFalse(String id);
}
