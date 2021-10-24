package com.nisum.evaluacionJava.repository.users;

import com.nisum.evaluacionJava.model.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends JpaRepository<User, String> {

    @Query("SELECT u FROM User u WHERE u.email = :email")
    User getUserByEmail(String email);
}
