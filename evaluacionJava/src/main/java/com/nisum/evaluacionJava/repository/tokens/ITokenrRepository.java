package com.nisum.evaluacionJava.repository.tokens;

import com.nisum.evaluacionJava.model.entities.Token;
import com.nisum.evaluacionJava.model.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ITokenrRepository extends JpaRepository<Token, Integer> {

    @Query("SELECT t FROM Token t WHERE t.token = :bearer")
    Token getTokenByBearer(String bearer);
}
