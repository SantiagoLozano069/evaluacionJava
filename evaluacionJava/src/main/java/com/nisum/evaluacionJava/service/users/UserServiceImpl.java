package com.nisum.evaluacionJava.service.users;

import com.nisum.evaluacionJava.commons.converter.PhonesConverter;
import com.nisum.evaluacionJava.commons.converter.UserConverter;
import com.nisum.evaluacionJava.commons.dtos.request.PhonesDTO;
import com.nisum.evaluacionJava.commons.dtos.request.UserDTO;
import com.nisum.evaluacionJava.commons.dtos.response.ResponseError;
import com.nisum.evaluacionJava.commons.dtos.response.ResponseUnauthorized;
import com.nisum.evaluacionJava.commons.dtos.response.ResponseUserDTO;
import com.nisum.evaluacionJava.commons.enums.ValidateRequest;
import com.nisum.evaluacionJava.commons.generic.Response;
import com.nisum.evaluacionJava.model.entities.Phone;
import com.nisum.evaluacionJava.model.entities.Token;
import com.nisum.evaluacionJava.model.entities.User;
import com.nisum.evaluacionJava.repository.tokens.impl.ITokenFacade;
import com.nisum.evaluacionJava.repository.users.impl.IUserFacade;
import com.nisum.evaluacionJava.service.tokens.ITokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserServiceImpl implements IUserService {

    private final IUserFacade iUserFacade;
    private final UserConverter userConverter;
    private final PhonesConverter phonesConverter;
    private final ITokenFacade iTokenFacade;
    private final ITokenService iTokenService;

    @Autowired
    UserServiceImpl(IUserFacade iUserFacade, UserConverter userConverter, PhonesConverter phonesConverter,
                    ITokenFacade iTokenFacade, ITokenService iTokenService) {
        this.iUserFacade = iUserFacade;
        this.userConverter = userConverter;
        this.phonesConverter = phonesConverter;
        this.iTokenFacade = iTokenFacade;
        this.iTokenService = iTokenService;
    }

    @Override
    public Response createUser(UserDTO userDto) throws Exception {

        String[] bearer = userDto.getBearer().split("Bearer ");
        //Validar que el token est?? activo
        Optional<Token> token = Optional.ofNullable(iTokenFacade.getTokenByBearer(bearer[1]));
        if (token.isPresent()) {

            String idUser = UUID.randomUUID().toString();
            try {
                //Validar request
                ValidateRequest validationRequest = validateRequest(userDto);
                if (validationRequest == ValidateRequest.SUCCESS_RESQUEST) {

                    //Validar si el Email exite en base de datos
                    Optional<User> IsEmailExist = Optional.ofNullable(iUserFacade.getUserByEmail(userDto.getEmail()));
                    if (!IsEmailExist.isPresent()) {

                        //Transformaciones DTO to Entities
                        User user = userConverter.converterUserDtoToUserEntity(userDto);
                        List<Phone> phonesEntitiesList = phonesConverter.listPhonesDtoToListPhonesEntities(userDto.getPhones(), user);

                        user.setId(idUser);
                        user.setPhones(phonesEntitiesList);
                        user.setBearer(bearer[1]);

                        //Guardar informaci??n en base de datos
                        iUserFacade.createUser(user);

                        //Validamos que el usuario se haya creado en base de datos
                        Optional<User> resultBd = Optional.ofNullable(iUserFacade.getUserById(idUser));
                        if (resultBd.isPresent()) {
                            User us = resultBd.get();
                            List<PhonesDTO> phonesDtoList = phonesConverter.listPhonesEntitiesToListPhonesDto(us.getPhones());
                            ResponseUserDTO usDto = ResponseUserDTO.builder().id(us.getId()).name(us.getName()).email(us.getEmail())
                                    .password(us.getPassword()).created(us.getCreated()).modified(us.getModified())
                                    .lastLogin(us.getLastLogin()).isActive(us.getActive() == 1 ? true : false)
                                    .bearer(us.getBearer()).phones(phonesDtoList)
                                    .build();
                            return new Response<ResponseUserDTO>(usDto);
                        } else {
                            return new Response<ResponseError>(ResponseError.builder()
                                    .mensaje("Error, No se pudo recuperar los datos del usuario en la base de datos")
                                    .build());
                        }
                    } else {
                        return new Response<ResponseError>(ResponseError.builder()
                                .mensaje("El E-mail ya est?? registrado en Base de datos").build());
                    }
                } else {
                    String mensaje = "";
                    switch (validationRequest) {
                        case NULL_ATRIBUTE:
                            mensaje = "Hacen falta atributos del usuario";
                            break;
                        case NULL_PHONE:
                            mensaje = "Debe agregar almenos un dato de contacto";
                            break;
                        case INVALIDATE_EMAIL:
                            mensaje = "El E-mail no tiene un formato v??lido";
                            break;
                        case INVALIDATE_PASSWORD:
                            mensaje = "La contrase??a debe tener una letras mayusculas, letras min??sculas y numeros";
                            break;
                    }
                    return new Response<ResponseError>(ResponseError.builder()
                            .mensaje(mensaje).build());
                }
            } catch (Exception ex) {
                throw new Exception(ex.getMessage(), ex.getCause());
            }
        } else {
            return new Response<ResponseUnauthorized>(ResponseUnauthorized.builder()
                    .mensaje(iTokenService.createToken()).build());
        }
    }

    public ValidateRequest validateRequest(UserDTO userDto) {

        //Validar que ning??n atributo del usuario llegue nulo o vacio
        if (userDto.getEmail() == null || userDto.getName() == null || userDto.getPassword() == null
                || userDto.getEmail().isEmpty() || userDto.getName().isEmpty() || userDto.getPassword().isEmpty()) {
            return ValidateRequest.NULL_ATRIBUTE;
        }

        //Validar que el usuario contenta con almenos un phone
        if (userDto.getPhones() == null || userDto.getPhones().size() == 0) {
            return ValidateRequest.NULL_PHONE;
        }

        //Validar el formato del email
        Pattern patternEmail = Pattern
                .compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        Matcher matherEmail = patternEmail.matcher(userDto.getEmail());
        if (matherEmail.find() != true) {
            return ValidateRequest.INVALIDATE_EMAIL;
        }

        //Validar el formato de la password
        Pattern patternPassword = Pattern
                .compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)([A-Za-z\\d]|[^ ]){3,}$");
        Matcher matherPassword = patternPassword.matcher(userDto.getPassword());
        if (matherPassword.find() != true) {
            return ValidateRequest.INVALIDATE_PASSWORD;
        }

        return ValidateRequest.SUCCESS_RESQUEST;
    }
}
