%%
%byaccj
%unicode
%class Lexico
%public
%line
%column

// ************  Patrones (macros) ********************
OPERADOR = [+\-*/;=()]
ENTERO = [0-9]+
REAL = ({DECIMAL}|{ENTERO})([eE]"-"?{ENTERO})?
DECIMAL = ({ENTERO}".")|("."{ENTERO})|({ENTERO}"."{ENTERO})
CHAR = \'[^\']\'
STRING = [a-zA-Z_Ò—·ÈÌÛ˙¡…Õ”⁄‰ÎÔˆ¸ƒÀœ÷‹]+
IDENTIFICADOR = {STRING}({STRING}|{ENTERO})*
COMENTARIO = "//"~\n
MULTICOMENTARIO = "/*"~"*/"
ESPACIOS = [ \n\r\t]
BASURA = {COMENTARIO}|{ESPACIOS}|{MULTICOMENTARIO}

%%
if { System.out.println("SENTENCIA IF"); }
while { System.out.println("SENTENCIA WHILE"); }
else { System.out.println("SENTENCIA ELSE"); }

{OPERADOR} { return (int) yycharat(0); }
{ENTERO} { return Token.CTE_ENTERA; }
{IDENTIFICADOR} { return Token.IDENTIFICADOR;  }
{CHAR} { return Token.CTE_CHAR;  }
{REAL} { return Token.CTE_REAL;  } 

{BASURA} { }

. { System.out.println("Error:" + yytext()); }