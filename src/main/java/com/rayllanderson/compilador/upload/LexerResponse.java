package com.rayllanderson.compilador.upload;

import com.rayllanderson.compilador.geradores.Token;

public class LexerResponse {

    private final Token token;
    private final String value;

    public LexerResponse(Token token, String value) {
        this.token = token;
        this.value = value;
    }

    public Token getToken() {
        return token;
    }

    public String getValue() {
        return value;
    }
}
