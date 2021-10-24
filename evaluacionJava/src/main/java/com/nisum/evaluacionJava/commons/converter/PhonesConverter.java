package com.nisum.evaluacionJava.commons.converter;

import com.nisum.evaluacionJava.commons.dtos.request.PhonesDTO;
import com.nisum.evaluacionJava.model.entities.Phone;
import com.nisum.evaluacionJava.model.entities.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PhonesConverter {

    public List<Phone> listPhonesDtoToListPhonesEntities(List<PhonesDTO> phonesList, User user) throws Exception {
        List<Phone> phonesEntitesList = new ArrayList<>();
        try {
            if (phonesList.size() > 0) {
                phonesList.forEach(x -> phonesEntitesList.add(Phone.builder().number(x.getNumber())
                        .cityCode(x.getCitycode())
                        .countryCode(x.getCountrycode())
                        .user(user)
                        .build()));
            }
            return phonesEntitesList;
        } catch (Exception ex) {
            throw new Exception(ex.getMessage(), ex.getCause());
        }
    }

    public List<PhonesDTO> listPhonesEntitiesToListPhonesDto(List<Phone> phonesEntitiesList) throws Exception {
        List<PhonesDTO> phonesDtoList = new ArrayList<>();
        try {
            if (phonesEntitiesList.size() > 0) {
                phonesEntitiesList.forEach(x -> phonesDtoList.add(PhonesDTO.builder().number(x.getNumber())
                        .citycode(x.getCityCode()).countrycode(x.getCountryCode())
                        .build()));
            }
            return phonesDtoList;
        } catch (Exception ex) {
            throw new Exception();
        }
    }

}
