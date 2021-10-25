package com.nisum.evaluacionJava.service.users;

import com.nisum.evaluacionJava.commons.converter.PhonesConverter;
import com.nisum.evaluacionJava.commons.converter.UserConverter;
import com.nisum.evaluacionJava.commons.dtos.request.PhonesDTO;
import com.nisum.evaluacionJava.commons.dtos.request.UserDTO;
import com.nisum.evaluacionJava.commons.enums.ValidateRequest;
import com.nisum.evaluacionJava.model.entities.Phone;
import com.nisum.evaluacionJava.model.entities.Token;
import com.nisum.evaluacionJava.model.entities.User;
import com.nisum.evaluacionJava.repository.tokens.impl.ITokenFacade;
import com.nisum.evaluacionJava.repository.users.impl.IUserFacade;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Date;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@SpringBootTest
class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    void validateRequest() {

        //validaciones
        assertNotNull(userService.validateRequest(UserDTO.builder().build()));

        //valdiacion de NULL_ATRIBUTE
        assertEquals(ValidateRequest.NULL_ATRIBUTE, userService.validateRequest(UserDTO.builder().build()));

        //valdiacion de NULL_PHONE
        assertEquals(ValidateRequest.NULL_PHONE, userService.validateRequest(UserDTO.builder()
                .name("santiago").email("santtiagolozano@gmail.com").password("1asA3").build()));

        //valdiacion de INVALIDATE_EMAIL
        assertEquals(ValidateRequest.INVALIDATE_EMAIL, userService.validateRequest(UserDTO.builder()
                .name("santiago").email("sagmail.com").password("1asA3").phones(Arrays.asList(PhonesDTO.builder().build())).build()));


        //valdiacion de INVALIDATE_PASSWORD
        assertEquals(ValidateRequest.INVALIDATE_PASSWORD, userService.validateRequest(UserDTO.builder()
                .name("Santiago Lozano").email("santtiagolozano@gmail.com").password("123").phones(Arrays.asList(PhonesDTO.builder().build())).build()));


        //valdiacion de SUCCESS_RESQUEST
        assertEquals(ValidateRequest.SUCCESS_RESQUEST, userService.validateRequest(UserDTO.builder()
                .name("Santiago Lozano").email("santtiagolozano@gmail.com").password("1asA3").phones(Arrays.asList(PhonesDTO.builder().build())).build()));

    }
}