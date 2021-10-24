package com.nisum.evaluacionJava.commons.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PhonesDTO implements Serializable {

    private String number;
    private String citycode;
    private String countrycode;
}
