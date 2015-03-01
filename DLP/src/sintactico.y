%token CTE_ENTERA
%token CTE_REAL
%token CTE_CHAR
%token INTEGER
%token REAL
%token CHARACTER
%token IDENT
%token PRINT
%token MAYORIGUAL
%token MENORIGUAL
%token IGUAL


%right '='
%left 'AND' 'OR' 'NOT'
%left '>' MAYORIGUAL '<' MENORIGUAL IGUAL DISTINTO
%left '+' '-'
%left '*' '/' '%'
%nonassoc '[' ']'
%nonassoc '(' ')'
%nonassoc '.'

%%

programa: CHARACTER { System.out.println("Programa reconocido"); };





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