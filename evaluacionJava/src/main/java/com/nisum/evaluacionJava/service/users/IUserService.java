package com.nisum.evaluacionJava.service.users;

import com.nisum.evaluacionJava.commons.dtos.request.UserDTO;
import com.nisum.evaluacionJava.commons.generic.Response;
import com.nisum.evaluacionJava.model.entities.User;

import java.util.List;

public interface IUserService {
    Response createUser(UserDTO userDto) throws Exception;
}
