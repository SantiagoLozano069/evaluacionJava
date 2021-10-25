package com.nisum.evaluacionJava.service.tokens;

import com.nisum.evaluacionJava.model.entities.Token;
import com.nisum.evaluacionJava.repository.tokens.impl.ITokenFacade;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.*;

@SpringBootTest
class TokenServiceImplTest {

    @InjectMocks
    private TokenServiceImpl tokenService;

    @Mock
    private ITokenFacade iTokenFacade;

    private String bearer;
    private Token token;
    private Date date;

    @BeforeEach
    void setUp() {
        date = new Date();
        bearer = UUID.randomUUID().toString();
        token = Token.builder().id(1).token(bearer).created(date).build();
    }

    @Test
    void createToken() {
        //Teniendo en cuenta que
        doNothing().when(iTokenFacade).createToken(Token.builder().token(bearer).created(date).build());
        when(iTokenFacade.getTokenByBearer(bearer)).thenReturn(token);
        String bearer = tokenService.createToken();
        //Validaciones
        assertNotNull(bearer);
        assertDoesNotThrow(() -> tokenService.createToken());

    }
}