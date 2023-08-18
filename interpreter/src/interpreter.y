// fuente byaccj para una calculadora sencilla


%{
  import java.io.*;
  import java.util.List;
  import java.util.ArrayList;
%}


// lista de tokens por orden de prioridad

%token NL         // nueva línea
%token CONSTANT   // constante
%token WORLD
%token X

// Tokens de las operaciones
%token PUT
%token REM

// Tokens de los elementos de las operaciones put y rem
%token GOLD
%token PIT
%token WUMPUS
%token HERO

%token IN
%token LEFT_BRACKET
%token COMMA_SEPARATOR
%token RIGHT_BRACKET
%%

program
  : statement_list            // Lista de sentencias
  |                           // Programa vacio
  ;

statement_list
  : statement                // Unica sentencia
  | statement statement_list // Sentencia,y lista
  ;

statement
  : CONSTANT NL {System.out.println("constante: "+ $1); $$ = $1;}
  | world_stmt NL
  | put_stmt NL
  | rem_stmt NL
  ;

world_stmt
  : WORLD CONSTANT "x" CONSTANT {
    System.out.println("Mundo: " + $2 + " (filas) " + "x " + $4 + " (columnas)");
    world.setSize((Integer) $2, (Integer) $4);
    System.out.println("Tamaño (casillas): " + $2 + "x" + $4 + " = " + world.size());
    System.out.println();
    }

// Reglas de las operaciones put y rem
put_stmt
  : PUT put_gold_stmt
  | PUT put_pit_stmt
  | PUT put_wumpus_stmt
  | PUT put_hero_stmt
  ;

rem_stmt
  : REM rem_gold_stmt
  | REM rem_pit_stmt
  | REM rem_wumpus_stmt
  ;

// Reglas para colocar elementos en el mundo Wumpus
put_gold_stmt
  : GOLD IN LEFT_BRACKET CONSTANT COMMA_SEPARATOR CONSTANT RIGHT_BRACKET {
    world.putGold((Integer) $4, (Integer) $6);
    }
  ;

put_pit_stmt
  : PIT IN LEFT_BRACKET CONSTANT COMMA_SEPARATOR CONSTANT RIGHT_BRACKET {
    world.putPit((Integer) $4, (Integer) $6);
    }
  ;

put_wumpus_stmt
  : WUMPUS IN LEFT_BRACKET CONSTANT COMMA_SEPARATOR CONSTANT RIGHT_BRACKET {
    world.putWumpus((Integer) $4, (Integer) $6);
    }
  ;

put_hero_stmt
  : HERO IN LEFT_BRACKET CONSTANT COMMA_SEPARATOR CONSTANT RIGHT_BRACKET {
    world.putHero((Integer) $4, (Integer) $6);
    }
  ;

// Reglas para eliminar elementos del mundo Wumpus
rem_gold_stmt
  : GOLD IN LEFT_BRACKET CONSTANT COMMA_SEPARATOR CONSTANT RIGHT_BRACKET {
    world.removeGold((Integer) $4, (Integer) $6);
    }
  ;

rem_pit_stmt
  : PIT IN LEFT_BRACKET CONSTANT COMMA_SEPARATOR CONSTANT RIGHT_BRACKET {
    world.removePit((Integer) $4, (Integer) $6);
    }
  ;

rem_wumpus_stmt
  : WUMPUS IN LEFT_BRACKET CONSTANT COMMA_SEPARATOR CONSTANT RIGHT_BRACKET {
    world.removeWumpus((Integer) $4, (Integer) $6);
    }
  ;

%%

  /** referencia al analizador léxico
  **/
  private Lexer lexer ;

  private WumpusWorld world;

  /** constructor: crea el Interpreteranalizador léxico (lexer)
  **/
  public Parser(Reader r)
  {
     lexer = new Lexer(r, this);
     world = new WumpusWorld();
  }

  /** esta función se invoca por el analizador cuando necesita el
  *** siguiente token del analizador léxico
  **/
  private int yylex ()
  {
    int yyl_return = -1;

    try
    {
       yylval = new Object();
       yyl_return = lexer.yylex();
    }
    catch (IOException e)
    {
       System.err.println("error de E/S:"+e);
    }

    return yyl_return;
  }

  /** invocada cuando se produce un error
  **/
  public void yyerror (String descripcion, int yystate, int token)
  {
     System.err.println ("Error en línea "+Integer.toString(lexer.lineaActual())+" : "+descripcion);
     System.err.println ("Token leído : "+yyname[token]);
  }

  public void yyerror (String descripcion)
  {
     System.err.println ("Error en línea "+Integer.toString(lexer.lineaActual())+" : "+descripcion);
     //System.err.println ("Token leido : "+yyname[token]);
  }
