
%%
// ************  Opciones ********************
// % debug // * Opción para depurar
%byaccj
%class Lexico
%public
%unicode
%line
%column

%{
// ************  Atributos y métodos ********************

// * El gestor de errores
private GestorErrores gestor;

// * El analizador sintáctico
private Parser parser;


public void setParser(Parser parser) {
	this.parser=parser;
}

// * Para acceder al número de línea 
public int getLinea() { 
	// * Flex empieza en cero
	return yyline+1;
}

// * Para acceder al número de columna 
public int getColumna() { 
	// * Flex empieza en cero
	return yycolumn+1;
}

public String lexeme(){
	return yytext();
  }
%}

// ************  Patrones (macros) ********************

OPERADOR = [+\-*/]

ENTERO = [0-9]+
REAL = ({DECIMAL}|{ENTERO})([eE]"-"?{ENTERO})?
DECIMAL = ({ENTERO}".")|("."{ENTERO})|({ENTERO}"."{ENTERO})
CHAR = \'[^\']\'

STRING = [a-zA-Z_ñÑáéíóúÁÉÍÓÚäëïöüÄËÏÖÜ]+
IDENTIFICADOR = {STRING}({STRING}|{ENTERO})*

COMENTARIOSIMPLE = "//"~\n
COMENTARIOMULTILINEA = "/*"~"*/"
ESPACIOS = [ \f\t]
SALTOSDELINEA = \r\n|\n|\r

BASURA = {COMENTARIOSIMPLE}|{ESPACIOS}|{COMENTARIOMULTILINEA}|{SALTOSDELINEA}

%%

print { return Parser.PRINT;  }

integer { return Parser.INTEGER;  }

real { return Parser.REAL;  }

character { return Parser.CHARACTER;  }

if { return Parser.IF; }

else { return Parser.ELSE; }

while { return Parser.WHILE; }

integer { return Parser.INTEGER; }

real { return Parer.REAL; }

character { return Parser.CHARACTER; }

void { return Parser.VOID; }

return { return Parser.RETURN; }

do { return Parser.DO; }

end { return Parser.END; }

then {return Parser.THEN; }

as {return Parser.AS; }

read {return Parser.READ; }

and {return Parser.AND; }

or {return Parser.OR; }

not {return Parser.NOT; }

ctype {return Parser.CTYPE; }

dim {return Parser.DIM; }

function {return Parser.FUNCTION; }

proc {return Parser.PROC; }

type {return Parser.TYPE; }

{IDENTIFICADOR} { parser.setYylval(yytext()); return Parser.IDENT;  }

{ENTERO} { parser.setYylval(new Integer(yytext())); return Parser.CTE_ENTERA; }
{CHAR} { parser.setYylval(new Character(yycharat(1))); return Parser.CTE_CHAR;  }
{REAL} { parser.setYylval(new Double(yytext())); return Parser.CTE_REAL;  } 

{OPERADOR}  { return (int) yycharat(0); }

"="|

"<"|

">"|

"("|

")"|

"{"|

"}"|

";"|

","|

"!"|

"."|

"["|

"]"                {  return (int) yycharat(0); }

"<="			   {  return Parser.MENORIGUAL; }

">="			   {  return Parser.MAYORIGUAL; }

"=="			   {  return Parser.IGUAL; }

"<>"			   {  return Parser.DISTINTO; }

{BASURA} { }

. { System.out.println("Error léxico en la linea: "+ this.getLinea() + ", columna: "+ this.getColumna() +"\nCaracter: '"+yycharat(0)+"' no reconocido"); }