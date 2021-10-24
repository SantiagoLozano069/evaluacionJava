package com.nisum.evaluacionJava.commons.generic;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class Response<T> {

    private T type;

    public Response(T type) {
        this.type = type;
    }

    public T get() {
        return this.type;
    }
}
