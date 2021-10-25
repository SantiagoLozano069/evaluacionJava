package com.nisum.evaluacionJava.tareasProgramadas;

import com.nisum.evaluacionJava.repository.tokens.impl.ITokenFacade;
import com.nisum.evaluacionJava.repository.users.impl.IUserFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
/**
 * Se define esta clase como la encargada de eliminar los tokens (UUID) de BD y actualizar el estado
 * de los usuarios cada 2 minutos
 * @author: Santiago Lozano
 */
@Component
public class UpdateDataBase {

    private final ITokenFacade iTokenFacade;
    private final IUserFacade iUserFacade;

    @Autowired
    UpdateDataBase(ITokenFacade iTokenFacade, IUserFacade iUserFacade) {
        this.iTokenFacade = iTokenFacade;
        this.iUserFacade = iUserFacade;
    }

    @Scheduled(fixedDelay = 120000)
    public void deleteTokens() {
        iTokenFacade.deleteAll();
        updateStateToFalse();
    }

    public void updateStateToFalse() {
        iUserFacade.getAll().forEach(x -> iUserFacade.updateStateToFalse(x.getId()));
    }

}
