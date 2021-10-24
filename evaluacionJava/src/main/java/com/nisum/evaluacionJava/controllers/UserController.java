package com.nisum.evaluacionJava.controllers;

import com.nisum.evaluacionJava.commons.dtos.request.UserDTO;
import com.nisum.evaluacionJava.commons.dtos.response.ResponseError;
import com.nisum.evaluacionJava.commons.dtos.response.ResponseUserDTO;
import com.nisum.evaluacionJava.commons.generic.Response;
import com.nisum.evaluacionJava.service.users.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class UserController {

    private final IUserService iUserService;

    @Autowired
    UserController(IUserService iUserService) {
        this.iUserService = iUserService;
    }

    @PostMapping("/registro")
    public ResponseEntity<Object> createUser(@RequestBody UserDTO user) {
        try {
            Response response = iUserService.createUser(user);
            if (response.get() instanceof ResponseUserDTO) {
                return ResponseEntity.status(HttpStatus.OK).body(response.get());
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response.get());
            }
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResponseError.builder()
                    .mensaje(ex.getMessage() + ex.getCause())
                    .build());
        }
    }


}
