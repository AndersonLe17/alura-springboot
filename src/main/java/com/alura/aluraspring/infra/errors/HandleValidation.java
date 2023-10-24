package com.alura.aluraspring.infra.errors;

public class HandleValidation extends RuntimeException{
    public HandleValidation(String s) {
        super(s);
    }
}
