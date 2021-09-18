package com.rayllanderson.compilador;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TesteAnalisador {
    /* o throws IOException é necessário para tratar erro de exceção,
     se o arquivo de entrada não for localizado
     */
    public static void main(String[] args) throws IOException {
        String arq = "src/main/resources/teste.txt";

         /* o Jflex pode ler String ou um fluxo de dados(um arquivo),
         nesse caso usamos o Reader
         */

        BufferedReader in = new BufferedReader(new FileReader(arq));
         /* Lexer é a classe criada pela especificação.flex, o analisador
         e analise é uma instancia da classe Lexer */

        Lexer analise = new Lexer(in);
         /* criamos um loop para ler todos os tokens identificados
         no arquivo de entrada */
        while (true) {
            // o método yylex() do objeto analise, retorna uma constante
            Token token = analise.yylex(); /* token conterá um tipo de padrão reconhecido pelo lexer */
            if (token == null)
                break; /* EOF
                 se o token for null indica
                 fim do arquivo de entrada
                 e o processo é encerrado(break)
                 */
        } //fim do loop
    }// fim método main
}//fim da classe
