package com.nisum.evaluacionJava.service.tokens;

import com.nisum.evaluacionJava.commons.dtos.response.ResponseError;
import com.nisum.evaluacionJava.commons.generic.Response;
import com.nisum.evaluacionJava.model.entities.Token;
import com.nisum.evaluacionJava.repository.tokens.impl.ITokenFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TokenServiceImpl implements ITokenService {

    private final ITokenFacade iTokenFacade;

    @Autowired
    TokenServiceImpl(ITokenFacade iTokenFacade) {
        this.iTokenFacade = iTokenFacade;
    }

    @Override
    public String createToken() {
        Date date = new Date();
        String bearer = UUID.randomUUID().toString();
        iTokenFacade.createToken(Token.builder().token(bearer).created(date).build());
        Optional<Token> tokenBd = Optional.ofNullable(iTokenFacade.getTokenByBearer(bearer));
        if (tokenBd.isPresent()) {
            return "Bearer " + tokenBd.get().getToken();
        } else {
            return "No se ha podido generar el Bearer-token";
        }

    }

}
