
%%
// ************  Opciones ********************
// % debug // * OpciÛn para depurar
%byaccj
%class Lexico
%public
%unicode
%line
%column

%{
// ************  Atributos y mÈtodos ********************

// * Para acceder al n˙mero de lÌnea 
public int getLinea() { 
	// * Flex empieza en cero
	return yyline+1;
}

// * Para acceder al n˙mero de columna 
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

STRING = [a-zA-Z_Ò—·ÈÌÛ˙¡…Õ”⁄‰ÎÔˆ¸ƒÀœ÷‹]+
IDENTIFICADOR = {STRING}({STRING}|{ENTERO})*

COMENTARIOSIMPLE = "//"~\n
COMENTARIOMULTILINEA = "/*"~"*/"
ESPACIOS = [ \n\r\t]

BASURA = {COMENTARIOSIMPLE}|{ESPACIOS}|{COMENTARIOMULTILINEA}

%%

print { return Parser.PRINT;  }
integer { return Parser.INTEGER;  }
real { return Parser.REAL;  }
character { return Parser.CHARACTER;  }

{ENTERO} { return Parser.CTE_ENTERA; }
{IDENTIFICADOR} { return Parser.IDENT;  }
{CHAR} { return Parser.CTE_CHAR;  }
{REAL} { return Parser.CTE_REAL;  } 

{OPERADOR}  { return (int) yycharat(0); }

"<="			   {  return Parser.MENORIGUAL; }

">="			   {  return Parser.MAYORIGUAL; }

"=="			   {  return Parser.IGUAL; }

{BASURA} { }

. { System.out.println("Error:" + yytext()); }