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

"print world" {
  return Parser.PRINT_WORLD;
}

/** Operaciones put y rem **/
"put" {
  return Parser.PUT;
}

"rem" {
  return Parser.REM;
}

/** Elementos de las operaciones put y rem **/
"gold" {
  yyparser.yylval = yytext();
  return Parser.ELEMENT;
}

"pit" {
  yyparser.yylval = yytext();
  return Parser.ELEMENT;
}

"wumpus" {
  yyparser.yylval = yytext();
  return Parser.ELEMENT;
}

"hero" {
  yyparser.yylval = yytext();
  return Parser.ELEMENT;
}

"in" {
  return Parser.IN;
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

[x\[\],] {
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
