package com.nisum.evaluacionJava.repository.tokens.impl;

import com.nisum.evaluacionJava.model.entities.Token;


public interface ITokenFacade {
    void createToken(Token token);

    Token getTokenByBearer(String bearer);

    void deleteAll();
}
