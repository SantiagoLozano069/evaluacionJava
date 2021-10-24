package com.nisum.evaluacionJava.commons.converter;

import com.nisum.evaluacionJava.commons.dtos.request.UserDTO;
import com.nisum.evaluacionJava.model.entities.User;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class UserConverter {

    public User converterUserDtoToUserEntity(UserDTO userDto) throws Exception {
        Date fechaActual = new Date();
        try {
            return User.builder()
                    .name(userDto.getName())
                    .email(userDto.getEmail())
                    .password(userDto.getPassword())
                    .created(fechaActual)
                    .modified(fechaActual)
                    .lastLogin(fechaActual)
                    .active(1)
                    .build();
        } catch (Exception ex) {
            throw new Exception(ex.getMessage(), ex.getCause());
        }

    }
}
