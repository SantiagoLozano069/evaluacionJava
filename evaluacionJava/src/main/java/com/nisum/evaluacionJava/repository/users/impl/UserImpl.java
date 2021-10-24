package com.nisum.evaluacionJava.repository.users.impl;

import com.nisum.evaluacionJava.model.entities.User;
import com.nisum.evaluacionJava.repository.users.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserImpl implements IUserFacade {

    private final IUserRepository iUserFacade;

    @Autowired
    UserImpl(IUserRepository iuserFacade) {
        this.iUserFacade = iuserFacade;
    }

    @Override
    public void createUser(User user) {
        iUserFacade.save(user);
    }

    @Override
    public User getUserByEmail(String email) {
        return iUserFacade.getUserByEmail(email);
    }

    @Override
    public User getUserById(String id) {
        return iUserFacade.getById(id);
    }

}
