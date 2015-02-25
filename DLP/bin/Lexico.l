%%
%byaccj
%unicode
%class Lexico
%public
%line
%column

%{
	public String lexeme(){
	return yytext();
  }
%}

// ************  Patrones (macros) ********************
OPERADORARITMETICO = [+\-*/]
OPERADOR LOGICO = ("AND")|("OR")|("NOT")
ENTERO = [0-9]+
REAL = ({DECIMAL}|{ENTERO})([eE]"-"?{ENTERO})?
DECIMAL = ({ENTERO}".")|("."{ENTERO})|({ENTERO}"."{ENTERO})
CHAR = \'[^\']\'
STRING = [a-zA-Z_Ò—·ÈÌÛ˙¡…Õ”⁄‰ÎÔˆ¸ƒÀœ÷‹]+
IDENTIFICADOR = {STRING}({STRING}|{ENTERO})*
COMENTARIOSIMPLE = "//"~\n
COMENTARIOMULTILINEA = "/*"~"*/"
ESPACIOS = [ \n\r\t]
BASURA = {COMENTARIO}|{ESPACIOS}|{MULTICOMENTARIO}

%%

print { return Parser.PRINT;  }
integer { return Parser.INTEGER;  }
real { return Parser.REAL;  }
character { return Parser.CHARACTER;  }

{OPERADOR} { return (int) yycharat(0); }
{ENTERO} { return Parser.CTE_ENTERA; }
{IDENTIFICADOR} { return Parser.IDENT;  }
{CHAR} { return Parser.CTE_CHAR;  }
{REAL} { return Parser.CTE_REAL;  } 

{BASURA} { }

. { System.out.println("Error:" + yytext()); }