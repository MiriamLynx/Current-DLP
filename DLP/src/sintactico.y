%token CTE_ENTERA
%token CTE_REAL
%token CTE_CHAR
%token CTE_STRING
%token INTEGER
%token REAL
%token CHARACTER
%token DIM
%token AS
%token IDENT
%token PRINT

%left '+' '-'
%left '*' '/' '%'

%%

programa: listaDeDeclaraciones | listaDeSentencias { System.out.println("Programa reconocido"); };

listaDeDeclaraciones: listaDeDeclaraciones declaracion | declaracion ;

declaracion: DIM IDENT AS tipo ';' { System.out.println("Declaración de variable"); };

tipo: REAL | CHARACTER | INTEGER ;

listaDeSentencias: listaDeSentencias sentencia | sentencia ;

sentencia: print ;

print: PRINT expresion ';' { System.out.println("Impresion"); };

expresion: expresion '+' expresion | expresion '*' expresion | '(' expresion ')' | CTE_ENTERA ;

%%

Lexico lex;

Parser (Lexico lex){
	this.lex=lex;	
}

void yyerror(String s){
	System.out.println("Syntax error");
}

int yylex(){

	try{
	
	int token =lex.yylex();
	yylval=lex.lexeme();
	
		return token;
		
	}catch(Exception e){
		return -1;
	}
}