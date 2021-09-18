package com.rayllanderson.compilador;
import static com.rayllanderson.compilador.Token.*;

%% // inicio da seção
 // aqui introduzimos um método para mostrar
 // o lexema == >> classificação do token
%{
 private void imprimir(String token, String lexema) {
 System.out.println(lexema + " ==>> " + token);
 }
%}
%class Lexer // aqui definimos o nome do analisador léxico que
 // desejamos que o JFlex crie.
 // Nesse caso a classe chamará Lexer
%type Token // como iremos usar um eNum Token, aqui definimos
 // o tipo de objeto que o método yytext() irá retorna
 // o método yytext() do JFlex, retorna o tipo do token
 // reconhecido
// aqui digitamos as expressões definidas, para o nosso léxico do
//mini-C, como explicado anteriormente nos itens 1, 2 e 3
nomeVariavel = [_a-zA-Z][_a-zA-z0-9]*
inteiro = [0-9]+
decimal = [0-9]+[”.”]+[0-9]+
blocoComentario = "/*" ~"*/"
branco = [\t\n\r ]+
linhaComentario = {branco}* "//" .*
palavraChave = "if" | "class" | "int" | "while" | "for" | "void" | "final" | "static"
operador = "+" | "-" | "*" | "/"
%% // final da seção
// identificando as palavras-chaves
{palavraChave} { imprimir("PALAVRA-CHAVE : ", yytext()); return PALAVRA_CHAVE; }
// especificação para mostrar o tipo do token, e o lexema
// e retorna o token correspondente identificado na
// classe Token eNum ( veja figura 2.18 )
{nomeVariavel} { imprimir("VARIAVEL : ", yytext()); return NOME_VARIAVEL; }
// agora fazermos o mesmo processo para os demais tokens
{inteiro} { imprimir("NUMERO INTEIRO : ", yytext()); return INT; }
{decimal} { imprimir("NUMERO DECIMAL : ", yytext()); return DEC; }
{blocoComentario} { imprimir("COMENTARIO : ", yytext()); return COMENTARIO; }
{linhaComentario} { imprimir("COMENTARIO : ", yytext()); return COMENTARIO; }
{operador} { imprimir("OPERADOR ARITMETICO : ", yytext()); return OPERADOR; }
{branco} { return BRANCO; }
// para concluir: Os caracteres que não foram reconhecidos pela
// gramática, serão reconhecido pelo símbolo ponto (.)
// e o comando a frente é gerado, no caso retorna ERROR e a
// mensagem CARACTER NÃO VÁLIDO
. { imprimir ("<<< CARACTER NÃO VALIDO!!! >>> ",yytext());return ERROR; }
// identifica o final do arquivo de entrada e retorna null
<<EOF>> { return null; }