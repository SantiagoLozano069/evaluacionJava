package com.nisum.evaluacionJava.repository.tokens.impl;

import com.nisum.evaluacionJava.model.entities.Token;
import com.nisum.evaluacionJava.repository.tokens.ITokenrRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TokenImpl implements ITokenFacade {

    private final ITokenrRepository iTokenrRepository;

    @Autowired
    TokenImpl(ITokenrRepository iTokenrRepository) {
        this.iTokenrRepository = iTokenrRepository;
    }

    @Override
    public void createToken(Token token) {
        iTokenrRepository.save(token);
    }

    @Override
    public Token getTokenByBearer(String bearer) {
        return iTokenrRepository.getTokenByBearer(bearer);
    }

    @Override
    public void deleteAll() {
        iTokenrRepository.deleteAll();
    }

}
