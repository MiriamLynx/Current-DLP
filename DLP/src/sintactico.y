%token CTE_ENTERA
%token CTE_REAL
%token CTE_CHAR
%token INTEGER
%token REAL
%token CHARACTER
%token IDENT
%token PRINT
%token IF
%token ELSE
%token WHILE
%token MAIN
%token VOID
%token RETURN
%token MAYORIGUAL
%token MENORIGUAL
%token IGUAL
%token DO
%token END
%token THEN
%token READ
%token AS
%token DIM
%token AND
%token OR
%token NOT
%token DISTINTO
%token CTYPE


%right '='
%left AND OR NOT
%left '>' MAYORIGUAL '<' MENORIGUAL IGUAL DISTINTO
%left '+' '-'
%left '*' '/' '%'
%nonassoc '[' ']'
%nonassoc '(' ')'
%nonassoc '.'

%%

// * Programa
programa: listaDeclaraciones {root= new Programa(lexico.getLinea(), lexico.getColumna(),(List<Declaracion>)$1);}
;

// * Cero o más declaraciones
listaDeclaraciones: { $$ = new ArrayList<Declaracion>(); } |
listaDeclaraciones declaracion	{ $$ = $1; ((List<Declaracion>)$1).add((Declaracion)$2); $$ = d;}
;

// * Declaracion
declaracion: declaracionVariable {$$ = $1;	}
// * |declaracionFuncion {$$ = $1;	}
// * |declaracionProcedimiento {$$ = $1;	}
// * |declaracionStruct {$$ = $1;	}
;


// * Declaraciones de variable
declaracionVariable: DIM IDENT declaracionArray AS tipo ';' {$$ = new DeclaracionVariable(lexico.getLinea(), lexico.getColumna(),(String)$2,(Tipo)$5);}
;


declaracionArray:|
listaDimensiones {$$ = new TipoArray(lexico.getLinea(), lexico.getColumna(), (List<Integer>)$1);	}
;

// * Una o mas dimensiones de array
listaDimensiones: '[' CTE_ENTERA ']' {$$ = $2;}
| listaDimensiones '[' CTE_ENTERA ']' {$$ = $1; ((List<Integer>)$$).add((int)$2);}
;

// * Cero o más sentencias
listaSentencias: {$$ = new ArrayList<Sentencia>();	}|
listaSentencias sentencia {$$ = $1; ((List<Sentencia>)$$).add((Sentencia)$2);	}
;

/// * Sentencias
sentencia: PRINT expresion ';' {$$ = new Print(lexico.getLinea(), lexico.getColumna(),(Expresion)$2);	}
| WHILE expresion DO listaSentencias END WHILE ';' {$$ = new While(lexico.getLinea(), lexico.getColumna(),(Expresion)$2,(List<Sentencia>$4);	}
|IF expresion THEN listaSentencias ELSE listaSentencias END IF ';' {$$ = new If(lexico.getLinea(), lexico.getColumna(),(Expresion)$2, (List<Sentencia>)$4, (List<Sentencia>)$6;	}
|IF expresion THEN listaSentencias END IF ';' {$$ = new If(lexico.getLinea(), lexico.getColumna(),(Expresion)$2, (List<Sentencia>)$4, new ArrayList<Sentencia>());	}
|RETURN expresion ';' {$$ = new Return(lexico.getLinea(), lexico.getColumna(),(Expresion)$2);	}
|IDENT '(' parametrosLlamada ')' ';' {$$ = new LlamadaFuncion(lexico.getLinea(), lexico.getColumna(),(String)$1, (List<Expresion>)$3);	}
|READ expresion ';' {$$ = new Read(lexico.getLinea(), lexico.getColumna(),(Expresion)$2);	}
|expresion '=' expresion ';' {$$ = new Asignacion(lexico.getLinea(), lexico.getColumna(),(Expresion)$1, (Expresion)$3);	}
;

// * Cero o más parametros (llamada a funcion) con separadores
parametrosLlamada: {$$ = new ArrayList<Expresion>();	}|
parametroLlamada {$$ = $1;}
;
parametroLlamada: expresion {$$ = new ArrayList<Expresion>(); ((List<Expresion>)$$).add(Expresion)$1);	}
| parametroLlamada ',' expresion {$$ = $1; ((List<Expresion>$$).add((Expresion)$3);	}
;

// * Cero o más parametros (declaracion de funcion) con separadores
parametrosFuncion: {$$ = new ArrayList<DeclaracionVariable>();	}|
parametroFuncion {$$ = $1;}
;
parametroFuncion: IDENT AS tipo {$$ = new ArrayList<DeclaracionVariable>(); ((List<DeclaracionVariable>)$$).add(new DeclaracionVariable(lexico.getLinea(), lexico.getColumna(),(Tipo)$3,(String)$1))$1);	}
| parametroFuncion ',' IDENT AS tipo {$$ = $1; ((List<DeclaracionVariable>$$).add(new DeclaracionVariable(lexico.getLinea(), lexico.getColumna(),(Tipo)$5,(String)$1))$3);	}
;

//	* Expresiones
expresion: CTE_ENTERA {$$ = new ConstanteEntera(lexico.getLinea(), lexico.getColumna(),(int)$1);	}
|CTE_CHAR {$$ = new ConstanteChar(lexico.getLinea(), lexico.getColumna(),(char)$1);	}
|CTE_REAL {$$ = new ConstanteReal(lexico.getLinea(), lexico.getColumna(),(double)$1);	}
|expresion '+' expresion {$$ = new OperacionAritmetica(lexico.getLinea(), lexico.getColumna(), (Expresion)$1, "+", (Expresion)$3);	}
|expresion '-' expresion {$$ = new OperacionAritmetica(lexico.getLinea(), lexico.getColumna(), (Expresion)$1, "-", (Expresion)$3);	}
|expresion '*' expresion {$$ = new OperacionAritmetica(lexico.getLinea(), lexico.getColumna(), (Expresion)$1, "*", (Expresion)$3);	}
|expresion '/' expresion {$$ = new OperacionAritmetica(lexico.getLinea(), lexico.getColumna(), (Expresion)$1, "/", (Expresion)$3);	}
|expresion '>' expresion {$$ = new OperacionComparacion(lexico.getLinea(), lexico.getColumna(), (Expresion)$1, ">", (Expresion)$3);	}
|expresion '<' expresion {$$ = new OperacionComparacion(lexico.getLinea(), lexico.getColumna(), (Expresion)$1, "<", (Expresion)$3);	}
|expresion MAYORIGUAL expresion {$$ = new OperacionComparacion(lexico.getLinea(), lexico.getColumna(), (Expresion)$1, ">=", (Expresion)$3);	}
|expresion MENORIGUAL expresion {$$ = new OperacionComparacion(lexico.getLinea(), lexico.getColumna(), (Expresion)$1, "<=", (Expresion)$3);	}
|expresion AND expresion {$$ = new OperacionLogica(lexico.getLinea(), lexico.getColumna(), (Expresion)$1, "&&", (Expresion)$3);	}
|expresion OR expresion {$$ = new OperacionLogica(lexico.getLinea(), lexico.getColumna(), (Expresion)$1, "||", (Expresion)$3);	}
|expresion DISTINTO expresion {$$ = new OperacionLogica(lexico.getLinea(), lexico.getColumna(), (Expresion)$1, "<>", (Expresion)$3);	}
|expresion IGUAL expresion {$$ = new OperacionLogica(lexico.getLinea(), lexico.getColumna(), (Expresion)$1, "==", (Expresion)$3);	}
|expresion '[' expresion ']' {$$ = new AccesoArray(lexico.getLinea(), lexico.getColumna(),(Expresion)$1,(String)$3);	}
|expresion '.' IDENT {$$ = new AccesoCampo(lexico.getLinea(), lexico.getColumna(),(Expresion)$1,(String)$3);	}
|'(' expresion ')' {$$ = (Expresion)$2;	}
|IDENT '(' parametrosLlamada ')' {$$ = new LlamadaFuncion((String)$1, (List<Expresion>$3);	}
|CTYPE '(' tipo ',' expresion ')' {$$ = new Cast((Tipo)$3, (Expresion)$5);	}
;

//	* Tipos
tipo: INTEGER {$$ = new TipoEntero.getInstance();}
| CHARACTER {$$ = new TipoChar.getInstance();} 
| REAL {$$ = new TipoReal.getInstance();}

%%
Lexico lex;
AST root;
int token;

Parser(Yylex lex){

this.lex = lex;

}

void yyerror(String s) {
System.out.println("Error sintáctico en " + lex.line() + ":" + lex.column() + " Token = " + token + " lexema = \"" + lex.lexeme()+"\"");
}

int yylex(){
try{
int token = lex.yylex();
yylval= lex.lexeme();
return token;
} catch (Exception e) {
return -1;
  }
}
public AST getAST() {
return root;
}