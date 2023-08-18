// fuente 'jflex' para interprete

package org.unp.plp.interprete;

%%

%class Lexer
%byaccj

%{

  private Parser yyparser;

  /** constructor del analizador sintáctico
  *** acepta como parámetro el lector de caracteres a usar
  **/

  public Lexer(java.io.Reader r, Parser p )
  {
    this(r);
    yyparser = p  ;
  }

  /** devuelve el numero de linea donde está el último caracter leido
  **/

  public int lineaActual()
  {
     return yyline;
  }

%}

%%

"world" {
  return Parser.WORLD;
}

/** Operaciones put y rem **/
"put" {
  return Parser.PUT;
}

/** Elementos de las operaciones put y rem **/
"gold" {
  return Parser.GOLD;
}

"pit" {
  return Parser.PIT;
}

"in" {
  return Parser.IN;
}

["["] {
  return Parser.LEFT_BRACKET;
}

[","\t] {
  return Parser.COMMA_SEPARATOR;
}

["]"] {
  return Parser.RIGHT_BRACKET;
}

[0-9]+
   {  yyparser.yylval = new Integer(yytext());
      return Parser.CONSTANT;
   }

\r|\n|\r\n
  { // lleva la cuenta de lineas
    yyline++ ;
    return Parser.NL;
  }

[x] {
  /** Deja pasar los caracteres unicos como literales **/
  /** No es ninguna expresion regular, solo caracteres literales **/
  yyparser.yylval = yytext();
  return yytext().charAt(0);
}

[ \t]+ {  /** accion vacia: se ignoran los espacios y tabuladores **/
       }

.      {  /** token desconocido: se produce un mensaje de error **/
          yyparser.yyerror("El lexema '"+yytext()+"' no corresponden a ningún token conocido");
       }
