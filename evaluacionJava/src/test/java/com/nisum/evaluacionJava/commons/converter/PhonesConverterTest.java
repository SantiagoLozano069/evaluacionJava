package com.nisum.evaluacionJava.commons.converter;

import com.nisum.evaluacionJava.commons.dtos.request.PhonesDTO;
import com.nisum.evaluacionJava.model.entities.Phone;
import com.nisum.evaluacionJava.model.entities.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@SpringBootTest
class PhonesConverterTest {

    @InjectMocks
    private PhonesConverter phonesConverter;

    private PhonesDTO phonesDto;
    private Phone phone;
    private User user;

    @BeforeEach
    void setUp() {
        Date date = new Date();
        //Entradas
        phonesDto = PhonesDTO.builder().number("3126550636").citycode("64").countrycode("57").build();
        phone = Phone.builder().number("3126550636").cityCode("54").countryCode("57").build();
        user = User.builder()
                .name("santiago lozano")
                .email("santtiagolozano@gmail.com")
                .password("Aa123")
                .created(date)
                .modified(date)
                .lastLogin(date)
                .active(1)
                .build();
    }

    @Test
    void listPhonesDtoToListPhonesEntities() throws Exception {
        //Dado que
        List<Phone> phonesEntitiesList = phonesConverter.listPhonesDtoToListPhonesEntities(Arrays.asList(phonesDto), user);

        //Validaciones
        assertNotNull(phonesEntitiesList);
        assertTrue(phonesEntitiesList.size() > 0);
        assertSame(phonesEntitiesList.get(0).getUser(), user);
        assertTrue(phonesEntitiesList.get(0).getNumber() == phonesDto.getNumber() &&
                phonesEntitiesList.get(0).getCityCode() == phonesDto.getCitycode() &&
                phonesEntitiesList.get(0).getCountryCode() == phonesDto.getCountrycode());
        assertDoesNotThrow(() -> phonesConverter.listPhonesDtoToListPhonesEntities(Arrays.asList(phonesDto), user));
        assertThrows(Exception.class, () -> phonesConverter.listPhonesDtoToListPhonesEntities(null, user));
    }

    @Test
    void listPhonesEntitiesToListPhonesDto() throws Exception {
        //Dado que
        List<PhonesDTO> phonesDtoList = phonesConverter.listPhonesEntitiesToListPhonesDto(Arrays.asList(phone));

        //Validaciones
        assertNotNull(phonesDtoList);
        assertTrue(phonesDtoList.size() > 0);
        assertTrue(phonesDtoList.get(0).getNumber() == phone.getNumber() &&
                phonesDtoList.get(0).getCitycode() == phone.getCityCode() &&
                phonesDtoList.get(0).getCountrycode() == phone.getCountryCode());
        assertDoesNotThrow(() -> phonesConverter.listPhonesEntitiesToListPhonesDto(Arrays.asList(phone)));
        assertThrows(Exception.class, () -> phonesConverter.listPhonesEntitiesToListPhonesDto(null));
    }
}