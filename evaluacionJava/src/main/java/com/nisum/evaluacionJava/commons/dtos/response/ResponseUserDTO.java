package com.nisum.evaluacionJava.commons.dtos.response;

import com.nisum.evaluacionJava.commons.dtos.request.PhonesDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseUserDTO implements Serializable {
    String id;
    String name;
    String email;
    String password;
    Date created;
    Date modified;
    Date lastLogin;
    Boolean isActive;
    List<PhonesDTO> phones;
}
