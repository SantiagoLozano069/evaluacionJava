package com.nisum.evaluacionJava.service.users;

import com.nisum.evaluacionJava.commons.dtos.request.UserDTO;
import com.nisum.evaluacionJava.commons.dtos.response.ResponseUserDTO;
import com.nisum.evaluacionJava.commons.generic.Response;

public interface IUserService {
    Response createUser(UserDTO userDto) throws Exception;
}
