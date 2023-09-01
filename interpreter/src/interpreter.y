// fuente byaccj para una calculadora sencilla


%{
  import java.io.*;
  import java.util.List;
  import java.util.Set;
  import org.unp.plp.interprete.Coordinate;
%}


// lista de tokens por orden de prioridad

%token NL         // nueva línea
%token CONSTANT   // constante
%token VARIABLE_I
%token VARIABLE_J
%token WORLD
%token X
%token PRINT_WORLD

// Tokens de las operaciones
%token PUT
%token REM

// Token para los elementos de las operaciones put y rem
%token ELEMENT
%token PIT
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
  : operation_stmt NL
  | print_world_stmt NL
  | expr_cons NL { System.out.println("Expr_cons = " + $1); }
  | expr_fila NL { System.out.println("Expr_fila = " + $1); }
  | expr_col NL { System.out.println("Expr_columna = " + $1); }
  | relacion_const NL { System.out.println("Relacion_cons = " + $1); }
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
  | PUT PIT IN coordinate { world.putPit((String) $2, (Coordinate) $4); }
  | PUT PIT IN coordinates { /* Se le pasa un conjunto de coordenadas */ }
  | REM ELEMENT IN coordinate { world.rem((String) $2, (Coordinate) $4); }
  ;

coordinate
  : '[' CONSTANT ',' CONSTANT ']' { $$ = new Coordinate((Integer) $2, (Integer) $4); }
  ;

coordinates
  : '[' ']'
  | '[' rel_list ']' { /* Colocar un conjunto de coordenadas */ }
  ;

rel_list
  : rel
  | rel ',' rel_list
  ;

// ******************************** Reglas para las operaciones de comparacion ********************************
// Regla para la comparacion entre filas y constantes, y entre columnas y constantes
relacion_const
  : comp_fila_const
  | comp_columna_const
  ;

// Reglas para la comparacion fila-constante
comp_fila_const
  : expr_fila '=''=' expr_cons { $$ = world.rowEqualToConstant((int) $4, (Set<Coordinate>) $1); }
  | expr_cons '=''=' expr_fila { $$ = world.rowEqualToConstant((int) $1, (Set<Coordinate>) $4); }
  | expr_fila '<' expr_cons { $$ = world.rowLessThanConstant((int) $3, (Set<Coordinate>) $1); }
  | expr_cons '<' expr_fila { $$ = world.rowGraterThanConstant((int) $1, (Set<Coordinate>) $3); }
  | expr_fila '>' expr_cons { $$ = world.rowGraterThanConstant((int) $3, (Set<Coordinate>) $1); }
  | expr_cons '>' expr_fila { $$ = world.rowLessThanConstant((int) $1, (Set<Coordinate>) $3); }
  | expr_fila '<''=' expr_cons { $$ = world.rowLessThanOrEqualConstant((int) $4, (Set<Coordinate>) $1); }
  | expr_cons '<''=' expr_fila { $$ = world.rowGraterThanOrEqualConstant((int) $1, (Set<Coordinate>) $4); }
  | expr_fila '>''=' expr_cons { $$ = world.rowGraterThanOrEqualConstant((int) $4, (Set<Coordinate>) $1); }
  | expr_cons '>''=' expr_fila { $$ = world.rowLessThanOrEqualConstant((int) $1, (Set<Coordinate>) $4); }
  ;

// Reglas para la comparacion columna-constante
comp_columna_const
  : expr_col '=''=' expr_cons { $$ = world.columnEqualToConstant((int) $4, (Set<Coordinate>) $1); }
  | expr_cons '=''=' expr_col { $$ = world.columnEqualToConstant((int) $1, (Set<Coordinate>) $4); }
  | expr_col '<' expr_cons { $$ = world.columnLessThanConstant((int) $3, (Set<Coordinate>) $1); }
  | expr_cons '<' expr_col { $$ = world.columnGraterThanConstant((int) $1, (Set<Coordinate>) $3); }
  | expr_col '>' expr_cons { $$ = world.columnGraterThanConstant((int) $3, (Set<Coordinate>) $1); }
  | expr_cons '>' expr_col { $$ = world.columnLessThanConstant((int) $1, (Set<Coordinate>) $3); }
  | expr_col '<''=' expr_cons { $$ = world.columnLessThanOrEqualConstant((int) $4, (Set<Coordinate>) $1); }
  | expr_cons '<''=' expr_col { $$ = world.columnGraterThanOrEqualConstant((int) $1, (Set<Coordinate>) $4); }
  | expr_col '>''=' expr_cons { $$ = world.columnGraterThanOrEqualConstant((int) $4, (Set<Coordinate>) $1); }
  | expr_cons '>''=' expr_col { $$ = world.columnLessThanOrEqualConstant((int) $1, (Set<Coordinate>) $4); }
  ;

// Regla para la comparacion entre filas y columnas, y viceversa
relaciones_comp_vars
  : comp_fila_columna
  | comp_columna_fila
  ;

// Reglas para la comparacion fila-columna
comp_fila_columna
  : expr_fila '=''=' expr_col
  | expr_fila '<' expr_col
  | expr_fila '>' expr_col
  | expr_fila '<''=' expr_col
  | expr_fila '>''=' expr_col

// Reglas para la comparacion columna-fila
comp_columna_fila
  : expr_col '=''=' expr_fila
  | expr_col '<' expr_fila
  | expr_col '>' expr_fila
  | expr_col '<''=' expr_fila
  | expr_col '>''=' expr_fila
  ;

// Operaciones aritmeticas entre vars

// ******************************* Reglas para las operacions con filas *******************************
expr_fila
  : expr_fila '+' term_fila { $$ = world.joinSets((Set<Coordinate>) $1, (Set<Coordinate>) $3); }

  | expr_fila '+' term_cons { $$ = world.addScalarToRow((int) $3, (Set<Coordinate>) $1); }
  | expr_cons '+' term_fila { $$ = world.addScalarToRow((int) $1, (Set<Coordinate>) $3); }

  | expr_fila '-' expr_cons { $$ = world.subtractRow((int) $3, (Set<Coordinate>) $1); }

  // i * constante
  | factor_fila '*' factor_cons { $$ = world.multiplyRow((int) $3, (Set<Coordinate>) $1); }

  // constante * i
  | factor_cons '*' factor_fila  { $$ = world.multiplyRow((int) $1, (Set<Coordinate>) $3); }

  // i / constante
  | factor_fila '/' factor_cons { $$ = world.divisionRow((int) $3, (Set<Coordinate>) $1); }

  | term_fila
  ;

term_fila
  : term_fila '*' factor_fila { $$ = world.multiplyRow((int) $1, (Set<Coordinate>) $3); }
  | term_fila '/' factor_fila { $$ = world.divisionRow((int) $1, (Set<Coordinate>) $3); }
  | factor_fila
  ;

factor_fila
  : VARIABLE_I { $$ = world.generateAllCoordinates(); }
  ;

// **************************** Reglas para las operaciones con columnas ****************************
expr_col
  : expr_col '+' term_col { $$ = world.joinSets((Set<Coordinate>) $1, (Set<Coordinate>) $3); }

  | expr_col '+' term_cons { $$ = world.addScalarToColumn((int) $3, (Set<Coordinate>) $1); }
  | expr_cons '+' term_col { $$ = world.addScalarToColumn((int) $1, (Set<Coordinate>) $3); }

  | expr_col '-' expr_cons { $$ = world.subtractColumn((int) $3, (Set<Coordinate>) $1); }

  // j * constante
  | factor_col '*' factor_cons { $$ = world.multiplyColumn((int) $3, (Set<Coordinate>) $1); }

  // constante * j
  | factor_cons '*' factor_col  { $$ = world.multiplyColumn((int) $1, (Set<Coordinate>) $3); }

  // j / constante
  | factor_col '/' factor_cons { $$ = world.divisionColumn((int) $3, (Set<Coordinate>) $1); }

  | term_col
  ;

term_col
  : term_col '*' factor_col { $$ = world.multiplyColumn((int) $1, (Set<Coordinate>) $3); }
  | term_col '/' factor_col { $$ = world.divisionColumn((int) $1, (Set<Coordinate>) $3); }
  | factor_col
  ;

factor_col
  : VARIABLE_J { $$ = world.generateAllCoordinates(); } 
  ;

// *********************** Reglas para las operaciones con constantes ***********************
expr_cons
  : expr_cons '+' term_cons { $$ = (int) $1 + (int) $3; }
  | expr_cons '-' term_cons { $$ = (int) $1 - (int) $3; }
  | term_cons
  ;

term_cons
  : term_cons '*' factor_cons { $$ = (int) $1 * (int) $3; }
  | term_cons '/' factor_cons { $$ = (int) $1 / (int) $3; }
  | factor_cons
  ;

factor_cons
  : CONSTANT;

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
