package com.nisum.evaluacionJava.controllers;

import com.nisum.evaluacionJava.commons.dtos.request.UserDTO;
import com.nisum.evaluacionJava.commons.dtos.response.ResponseError;
import com.nisum.evaluacionJava.commons.dtos.response.ResponseUserDTO;
import com.nisum.evaluacionJava.commons.generic.Response;
import com.nisum.evaluacionJava.model.entities.User;
import com.nisum.evaluacionJava.service.tokens.ITokenService;
import com.nisum.evaluacionJava.service.users.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Se define esta clase como la encargada de recibir las peticiones del usaurio
 * @author: Santiago Lozano
 */
@RestController
public class UserController {

    private final IUserService iUserService;
    private final ITokenService iTokenService;

    @Autowired
    UserController(IUserService iUserService, ITokenService iTokenService) {
        this.iUserService = iUserService;
        this.iTokenService = iTokenService;

    }

    /**
     * Método que guarda un usuio en base de datos
     *
     * @param headers,user
     * @return los datos del usaurio guardado o mensajes de excepciones controladas
     * @author santiago lozano
     */
    @PostMapping("/registro")
    public ResponseEntity<Object> createUser(@RequestHeader HttpHeaders headers, @RequestBody UserDTO user) {
        try {
            StringBuilder bearer = new StringBuilder();
            if (headers.get("Authorization") != null) {

                headers.get("Authorization").forEach(x -> bearer.append(x));
                user.setBearer(bearer.toString());
                Response response = iUserService.createUser(user);

                //Valdiación de código de respuesta
                if (response.get() instanceof ResponseUserDTO) {
                    return ResponseEntity.status(HttpStatus.OK).body(response.get());
                } else if (response.get() instanceof ResponseError) {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response.get());
                } else {
                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response.get());
                }
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ResponseError.builder()
                        .mensaje(iTokenService.createToken()));
            }
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResponseError.builder()
                    .mensaje(ex.getMessage() + ex.getCause())
                    .build());
        }
    }

}
