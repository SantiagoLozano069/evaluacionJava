package com.nisum.evaluacionJava.repository.users.impl;

import com.nisum.evaluacionJava.model.entities.User;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IUserFacade {
    void createUser(User user);

    User getUserByEmail(String email);

    User getUserById(String id);
}
