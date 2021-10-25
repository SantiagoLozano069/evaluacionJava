package com.nisum.evaluacionJava.commons.converter;

import com.nisum.evaluacionJava.commons.dtos.request.PhonesDTO;
import com.nisum.evaluacionJava.commons.dtos.request.UserDTO;
import com.nisum.evaluacionJava.model.entities.Phone;
import com.nisum.evaluacionJava.model.entities.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = UserConverterTest.class)
class UserConverterTest {

    @InjectMocks
    private UserConverter userConverter;

    private User user;
    private User user2;
    private UserDTO userDTO;
    private Phone phoneEntity;
    private PhonesDTO phonesDTO;
    private List<Phone> phonesEntitiesList;
    private List<PhonesDTO> phonesDtoList;

    @BeforeEach
    void setUp() {
        //Entrada del metodo converterUserDtoToUserEntity
        userDTO = UserDTO.builder()
                .name("santiago")
                .email("santtiago@gmail.com")
                .password("Aa123")
                .phones(phonesDtoList)
                .build();
    }

    @Test
    void converterUserDtoToUserEntity() throws Exception {
        //Teniendo en cuenta que
        User user = userConverter.converterUserDtoToUserEntity(userDTO);

        //Validaciones
        assertNotNull(user);
        assertTrue(user.getEmail().equals(userDTO.getEmail()) &&
                user.getName().equals(userDTO.getName()) &&
                user.getPassword().equals(userDTO.getPassword()));
        assertNull(user.getId());
        assertNull(user.getPhones());
        assertDoesNotThrow(() -> userConverter.converterUserDtoToUserEntity(userDTO));
        assertThrows(Exception.class, () -> userConverter.converterUserDtoToUserEntity(null));
    }
}