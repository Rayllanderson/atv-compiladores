package com.rayllanderson.compilador;

import jflex.exceptions.SilentExit;

public class GeraLexer {

    public static void main(String[] args) {
        String arq = "src/main/java/com/rayllanderson/compilador/Lexer.flex";
        gerarLexer(arq);
    }

    private static void gerarLexer(String arq){
        try {
            jflex.Main.generate(new String[]{arq});
        }catch (SilentExit e){
            System.out.println("Erro ao gerar Lexer. Motivo: " + e.getMessage());
        }
    }

}
