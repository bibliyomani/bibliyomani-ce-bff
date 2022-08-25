package com.bibliyomani.standalone.bff.factory;

import org.springframework.stereotype.Component;

import java.util.Base64;

@Component
public class Base64EncodingFactory implements EncodingFactory {

    private final static Base64.Encoder ENCODER = Base64.getEncoder();

    @Override
    public String valueOf(String input) {
        return ENCODER.encodeToString(input.getBytes());
    }
}
