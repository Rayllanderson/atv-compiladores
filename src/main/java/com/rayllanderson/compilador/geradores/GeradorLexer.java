package com.rayllanderson.compilador.geradores;

import jflex.exceptions.SilentExit;

/**
 * Responsável por gerar a class Lexer
 */
public class GeradorLexer {

    public static void main(String[] args) {
        String arq = "src/main/java/com/rayllanderson/compilador/geradores/Lexer.flex";
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
