package com.rayllanderson.compilador.geradores;

import com.rayllanderson.compilador.upload.LexerResponse;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

@Component
public class AnalisadorLexer {

    public List<LexerResponse> analisar(File file, boolean comEspacoEmBranco) throws IOException {

        BufferedReader in = new BufferedReader(new FileReader(file));

        Lexer analise = new Lexer(in);

        if(comEspacoEmBranco) {
            return analisarComEspacoEmBranco(analise);
        }
        return analisarSemEspacoEmBranco(analise);
    }

    private List<LexerResponse> analisarComEspacoEmBranco(Lexer analise) throws IOException {
        var resultado = new ArrayList<LexerResponse>();
        while (true) {
            Token token = analise.yylex();
            resultado.add(new LexerResponse(token, analise.yytext()));
            if (token == null)
                break;
        }
        return resultado;
    }

    private List<LexerResponse> analisarSemEspacoEmBranco(Lexer analise) throws IOException {
        var resultado = new ArrayList<LexerResponse>();
        while (true) {
            Token token = analise.yylex();

            if (token == null)
                break;

            boolean lexerNaoVazio = !token.equals(Token.BRANCO);
            if (lexerNaoVazio){
                resultado.add(new LexerResponse(token, analise.yytext()));
            }
        }
        return resultado;
    }

}