// fuente byaccj para una calculadora sencilla


%{
  import java.io.*;
  import java.util.List;
  import java.util.ArrayList;
  import org.unp.plp.interprete.Coordinate;
%}


// lista de tokens por orden de prioridad

%token NL         // nueva línea
%token CONSTANT   // constante
%token WORLD
%token X
%token PRINT_WORLD

// Tokens de las operaciones
%token PUT
%token REM

// Token para los elementos de las operaciones put y rem
%token ELEMENT
%token IN
%%

program
  : world_stmt NL statement_list // Lista de sentencias
  |                              // Programa vacio
  ;

statement_list
  : statement                // Unica sentencia
  | statement statement_list // Sentencia,y lista
  ;

statement
  : operation_stmt NL { System.out.println("Operacion ejecutada..."); System.out.println(); }
  | print_world_stmt NL
  | NL
  ;

world_stmt
  : WORLD CONSTANT "x" CONSTANT { world.create((Integer) $2, (Integer) $4); }
  ;

print_world_stmt
  : PRINT_WORLD { world.print(); }

// Reglas de las operaciones put y rem
operation_stmt
  : PUT ELEMENT IN coordinate { world.put((String) $2, (Coordinate) $4); }
  ;

coordinate
  : '[' CONSTANT ',' CONSTANT ']' { $$ = new Coordinate((Integer) $2, (Integer) $4); }
  ;

coordinates
  : '[' ']'
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
