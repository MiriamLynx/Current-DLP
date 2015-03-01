//### This file created by BYACC 1.8(/Java extension  1.14)
//### Java capabilities added 7 Jan 97, Bob Jamison
//### Updated : 27 Nov 97  -- Bob Jamison, Joe Nieten
//###           01 Jan 98  -- Bob Jamison -- fixed generic semantic constructor
//###           01 Jun 99  -- Bob Jamison -- added Runnable support
//###           06 Aug 00  -- Bob Jamison -- made state variables class-global
//###           03 Jan 01  -- Bob Jamison -- improved flags, tracing
//###           16 May 01  -- Bob Jamison -- added custom stack sizing
//###           04 Mar 02  -- Yuval Oren  -- improved java performance, added options
//###           14 Mar 02  -- Tomas Hurka -- -d support, static initializer workaround
//### Please send bug reports to tom@hukatronic.cz
//### static char yysccsid[] = "@(#)yaccpar	1.8 (Berkeley) 01/20/90";










public class Parser
{

boolean yydebug;        //do I want debug output?
int yynerrs;            //number of errors so far
int yyerrflag;          //was there an error?
int yychar;             //the current working character

//########## MESSAGES ##########
//###############################################################
// method: debug
//###############################################################
void debug(String msg)
{
  if (yydebug)
    System.out.println(msg);
}

//########## STATE STACK ##########
final static int YYSTACKSIZE = 500;  //maximum stack size
int statestk[] = new int[YYSTACKSIZE]; //state stack
int stateptr;
int stateptrmax;                     //highest index of stackptr
int statemax;                        //state when highest index reached
//###############################################################
// methods: state stack push,pop,drop,peek
//###############################################################
final void state_push(int state)
{
  try {
		stateptr++;
		statestk[stateptr]=state;
	 }
	 catch (ArrayIndexOutOfBoundsException e) {
     int oldsize = statestk.length;
     int newsize = oldsize * 2;
     int[] newstack = new int[newsize];
     System.arraycopy(statestk,0,newstack,0,oldsize);
     statestk = newstack;
     statestk[stateptr]=state;
  }
}
final int state_pop()
{
  return statestk[stateptr--];
}
final void state_drop(int cnt)
{
  stateptr -= cnt; 
}
final int state_peek(int relative)
{
  return statestk[stateptr-relative];
}
//###############################################################
// method: init_stacks : allocate and prepare stacks
//###############################################################
final boolean init_stacks()
{
  stateptr = -1;
  val_init();
  return true;
}
//###############################################################
// method: dump_stacks : show n levels of the stacks
//###############################################################
void dump_stacks(int count)
{
int i;
  System.out.println("=index==state====value=     s:"+stateptr+"  v:"+valptr);
  for (i=0;i<count;i++)
    System.out.println(" "+i+"    "+statestk[i]+"      "+valstk[i]);
  System.out.println("======================");
}


//########## SEMANTIC VALUES ##########
//## **user defined:Object
String   yytext;//user variable to return contextual strings
Object yyval; //used to return semantic vals from action routines
Object yylval;//the 'lval' (result) I got from yylex()
Object valstk[] = new Object[YYSTACKSIZE];
int valptr;
//###############################################################
// methods: value stack push,pop,drop,peek.
//###############################################################
final void val_init()
{
  yyval=new Object();
  yylval=new Object();
  valptr=-1;
}
final void val_push(Object val)
{
  try {
    valptr++;
    valstk[valptr]=val;
  }
  catch (ArrayIndexOutOfBoundsException e) {
    int oldsize = valstk.length;
    int newsize = oldsize*2;
    Object[] newstack = new Object[newsize];
    System.arraycopy(valstk,0,newstack,0,oldsize);
    valstk = newstack;
    valstk[valptr]=val;
  }
}
final Object val_pop()
{
  return valstk[valptr--];
}
final void val_drop(int cnt)
{
  valptr -= cnt;
}
final Object val_peek(int relative)
{
  return valstk[valptr-relative];
}
//#### end semantic value section ####
public final static short CTE_ENTERA=257;
public final static short CTE_REAL=258;
public final static short CTE_CHAR=259;
public final static short INTEGER=260;
public final static short REAL=261;
public final static short CHARACTER=262;
public final static short IDENT=263;
public final static short PRINT=264;
public final static short IF=265;
public final static short ELSE=266;
public final static short WHILE=267;
public final static short MAIN=268;
public final static short VOID=269;
public final static short RETURN=270;
public final static short MAYORIGUAL=271;
public final static short MENORIGUAL=272;
public final static short IGUAL=273;
public final static short DO=274;
public final static short END=275;
public final static short THEN=276;
public final static short READ=277;
public final static short AS=278;
public final static short DIM=279;
public final static short AND=280;
public final static short OR=281;
public final static short NOT=282;
public final static short DISTINTO=283;
public final static short CTYPE=284;
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
    0,    1,    1,    2,    3,    4,    4,    6,    6,    7,
    7,    8,    8,    8,    8,    8,    8,    8,    8,   10,
   10,   11,   11,   12,   12,   13,   13,    9,    9,    9,
    9,    9,    9,    9,    9,    9,    9,    9,    9,    9,
    9,    9,    9,    9,    9,    9,    9,    5,    5,    5,
};
final static short yylen[] = {                            2,
    1,    0,    2,    1,    6,    0,    1,    3,    4,    0,
    2,    3,    7,    9,    7,    3,    5,    3,    4,    0,
    1,    1,    3,    0,    1,    3,    5,    1,    1,    1,
    3,    3,    3,    3,    3,    3,    3,    3,    3,    3,
    3,    3,    4,    3,    3,    4,    6,    1,    1,    1,
};
final static short yydefred[] = {                         2,
    0,    0,    0,    3,    4,    0,    0,    0,    0,    0,
    0,    0,    8,   48,   50,   49,    0,    0,    5,    9,
};
final static short yydgoto[] = {                          1,
    2,    4,    5,    8,   17,    9,    0,    0,    0,    0,
    0,    0,    0,
};
final static short yysindex[] = {                         0,
    0, -276, -259,    0,    0,  -86, -251, -271,  -83,  -84,
 -260, -247,    0,    0,    0,    0,  -48,  -81,    0,    0,
};
final static short yyrindex[] = {                         0,
    0,   13,    0,    0,    0, -264,    0,    0, -263,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
};
final static short yygindex[] = {                         0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,
};
final static int YYTABLESIZE=15;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         14,
   15,   16,    3,    6,    7,   10,   11,   12,   13,   18,
   19,   20,    1,    6,    7,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                        260,
  261,  262,  279,  263,   91,  257,  278,   91,   93,  257,
   59,   93,    0,  278,  278,
};
}
final static short YYFINAL=1;
final static short YYMAXTOKEN=284;
final static String yyname[] = {
"end-of-file",null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,"'%'",null,null,"'('","')'","'*'","'+'",
"','","'-'","'.'","'/'",null,null,null,null,null,null,null,null,null,null,null,
"';'","'<'","'='","'>'",null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,"'['",null,"']'",null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,"CTE_ENTERA","CTE_REAL","CTE_CHAR",
"INTEGER","REAL","CHARACTER","IDENT","PRINT","IF","ELSE","WHILE","MAIN","VOID",
"RETURN","MAYORIGUAL","MENORIGUAL","IGUAL","DO","END","THEN","READ","AS","DIM",
"AND","OR","NOT","DISTINTO","CTYPE",
};
final static String yyrule[] = {
"$accept : programa",
"programa : listaDeclaraciones",
"listaDeclaraciones :",
"listaDeclaraciones : listaDeclaraciones declaracion",
"declaracion : declaracionVariable",
"declaracionVariable : DIM IDENT declaracionArray AS tipo ';'",
"declaracionArray :",
"declaracionArray : listaDimensiones",
"listaDimensiones : '[' CTE_ENTERA ']'",
"listaDimensiones : listaDimensiones '[' CTE_ENTERA ']'",
"listaSentencias :",
"listaSentencias : listaSentencias sentencia",
"sentencia : PRINT expresion ';'",
"sentencia : WHILE expresion DO listaSentencias END WHILE ';'",
"sentencia : IF expresion THEN listaSentencias ELSE listaSentencias END IF ';'",
"sentencia : IF expresion THEN listaSentencias END IF ';'",
"sentencia : RETURN expresion ';'",
"sentencia : IDENT '(' parametrosLlamada ')' ';'",
"sentencia : READ expresion ';'",
"sentencia : expresion '=' expresion ';'",
"parametrosLlamada :",
"parametrosLlamada : parametroLlamada",
"parametroLlamada : expresion",
"parametroLlamada : parametroLlamada ',' expresion",
"parametrosFuncion :",
"parametrosFuncion : parametroFuncion",
"parametroFuncion : IDENT AS tipo",
"parametroFuncion : parametroFuncion ',' IDENT AS tipo",
"expresion : CTE_ENTERA",
"expresion : CTE_CHAR",
"expresion : CTE_REAL",
"expresion : expresion '+' expresion",
"expresion : expresion '-' expresion",
"expresion : expresion '*' expresion",
"expresion : expresion '/' expresion",
"expresion : expresion '>' expresion",
"expresion : expresion '<' expresion",
"expresion : expresion MAYORIGUAL expresion",
"expresion : expresion MENORIGUAL expresion",
"expresion : expresion AND expresion",
"expresion : expresion OR expresion",
"expresion : expresion DISTINTO expresion",
"expresion : expresion IGUAL expresion",
"expresion : expresion '[' expresion ']'",
"expresion : expresion '.' IDENT",
"expresion : '(' expresion ')'",
"expresion : IDENT '(' parametrosLlamada ')'",
"expresion : CTYPE '(' tipo ',' expresion ')'",
"tipo : INTEGER",
"tipo : CHARACTER",
"tipo : REAL",
};

//#line 133 "sintactico.y"
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
//#line 270 "Parser.java"
//###############################################################
// method: yylexdebug : check lexer state
//###############################################################
void yylexdebug(int state,int ch)
{
String s=null;
  if (ch < 0) ch=0;
  if (ch <= YYMAXTOKEN) //check index bounds
     s = yyname[ch];    //now get it
  if (s==null)
    s = "illegal-symbol";
  debug("state "+state+", reading "+ch+" ("+s+")");
}





//The following are now global, to aid in error reporting
int yyn;       //next next thing to do
int yym;       //
int yystate;   //current parsing state from state table
String yys;    //current token string


//###############################################################
// method: yyparse : parse input and execute indicated items
//###############################################################
int yyparse()
{
boolean doaction;
  init_stacks();
  yynerrs = 0;
  yyerrflag = 0;
  yychar = -1;          //impossible char forces a read
  yystate=0;            //initial state
  state_push(yystate);  //save it
  val_push(yylval);     //save empty value
  while (true) //until parsing is done, either correctly, or w/error
    {
    doaction=true;
    if (yydebug) debug("loop"); 
    //#### NEXT ACTION (from reduction table)
    for (yyn=yydefred[yystate];yyn==0;yyn=yydefred[yystate])
      {
      if (yydebug) debug("yyn:"+yyn+"  state:"+yystate+"  yychar:"+yychar);
      if (yychar < 0)      //we want a char?
        {
        yychar = yylex();  //get next token
        if (yydebug) debug(" next yychar:"+yychar);
        //#### ERROR CHECK ####
        if (yychar < 0)    //it it didn't work/error
          {
          yychar = 0;      //change it to default string (no -1!)
          if (yydebug)
            yylexdebug(yystate,yychar);
          }
        }//yychar<0
      yyn = yysindex[yystate];  //get amount to shift by (shift index)
      if ((yyn != 0) && (yyn += yychar) >= 0 &&
          yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
        {
        if (yydebug)
          debug("state "+yystate+", shifting to state "+yytable[yyn]);
        //#### NEXT STATE ####
        yystate = yytable[yyn];//we are in a new state
        state_push(yystate);   //save it
        val_push(yylval);      //push our lval as the input for next rule
        yychar = -1;           //since we have 'eaten' a token, say we need another
        if (yyerrflag > 0)     //have we recovered an error?
           --yyerrflag;        //give ourselves credit
        doaction=false;        //but don't process yet
        break;   //quit the yyn=0 loop
        }

    yyn = yyrindex[yystate];  //reduce
    if ((yyn !=0 ) && (yyn += yychar) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
      {   //we reduced!
      if (yydebug) debug("reduce");
      yyn = yytable[yyn];
      doaction=true; //get ready to execute
      break;         //drop down to actions
      }
    else //ERROR RECOVERY
      {
      if (yyerrflag==0)
        {
        yyerror("syntax error");
        yynerrs++;
        }
      if (yyerrflag < 3) //low error count?
        {
        yyerrflag = 3;
        while (true)   //do until break
          {
          if (stateptr<0)   //check for under & overflow here
            {
            yyerror("stack underflow. aborting...");  //note lower case 's'
            return 1;
            }
          yyn = yysindex[state_peek(0)];
          if ((yyn != 0) && (yyn += YYERRCODE) >= 0 &&
                    yyn <= YYTABLESIZE && yycheck[yyn] == YYERRCODE)
            {
            if (yydebug)
              debug("state "+state_peek(0)+", error recovery shifting to state "+yytable[yyn]+" ");
            yystate = yytable[yyn];
            state_push(yystate);
            val_push(yylval);
            doaction=false;
            break;
            }
          else
            {
            if (yydebug)
              debug("error recovery discarding state "+state_peek(0)+" ");
            if (stateptr<0)   //check for under & overflow here
              {
              yyerror("Stack underflow. aborting...");  //capital 'S'
              return 1;
              }
            state_pop();
            val_pop();
            }
          }
        }
      else            //discard this token
        {
        if (yychar == 0)
          return 1; //yyabort
        if (yydebug)
          {
          yys = null;
          if (yychar <= YYMAXTOKEN) yys = yyname[yychar];
          if (yys == null) yys = "illegal-symbol";
          debug("state "+yystate+", error recovery discards token "+yychar+" ("+yys+")");
          }
        yychar = -1;  //read another
        }
      }//end error recovery
    }//yyn=0 loop
    if (!doaction)   //any reason not to proceed?
      continue;      //skip action
    yym = yylen[yyn];          //get count of terminals on rhs
    if (yydebug)
      debug("state "+yystate+", reducing "+yym+" by rule "+yyn+" ("+yyrule[yyn]+")");
    if (yym>0)                 //if count of rhs not 'nil'
      yyval = val_peek(yym-1); //get current semantic value
    switch(yyn)
      {
//########## USER-SUPPLIED ACTIONS ##########
case 1:
//#line 43 "sintactico.y"
{root= new Programa(lexico.getLinea(), lexico.getColumna(),(List<Declaracion>)val_peek(0));}
break;
case 2:
//#line 47 "sintactico.y"
{ yyval = new ArrayList<Declaracion>(); }
break;
case 3:
//#line 48 "sintactico.y"
{ yyval = val_peek(1); ((List<Declaracion>)val_peek(1)).add((Declaracion)val_peek(0)); yyval = d;}
break;
case 4:
//#line 52 "sintactico.y"
{yyval = val_peek(0);	}
break;
case 5:
//#line 59 "sintactico.y"
{yyval = new DeclaracionVariable(lexico.getLinea(), lexico.getColumna(),(String)val_peek(4),(Tipo)val_peek(1));}
break;
case 7:
//#line 64 "sintactico.y"
{yyval = new TipoArray(lexico.getLinea(), lexico.getColumna(), (List<Integer>)val_peek(0));	}
break;
case 8:
//#line 68 "sintactico.y"
{yyval = val_peek(1);}
break;
case 9:
//#line 69 "sintactico.y"
{yyval = val_peek(3); ((List<Integer>)yyval).add((int)val_peek(2));}
break;
case 10:
//#line 73 "sintactico.y"
{yyval = new ArrayList<Sentencia>();	}
break;
case 11:
//#line 74 "sintactico.y"
{yyval = val_peek(1); ((List<Sentencia>)yyval).add((Sentencia)val_peek(0));	}
break;
case 12:
//#line 78 "sintactico.y"
{yyval = new Print(lexico.getLinea(), lexico.getColumna(),(Expresion)val_peek(1));	}
break;
case 13:
//#line 79 "sintactico.y"
{yyval = new While(lexico.getLinea(), lexico.getColumna(),(Expresion)val_peek(5),(List<Sentencia>val_peek(3));	}
break;
case 14:
//#line 80 "sintactico.y"
{yyval = new If(lexico.getLinea(), lexico.getColumna(),(Expresion)val_peek(7), (List<Sentencia>)val_peek(5), (List<Sentencia>)val_peek(3);	}
break;
case 15:
//#line 81 "sintactico.y"
{yyval = new If(lexico.getLinea(), lexico.getColumna(),(Expresion)val_peek(5), (List<Sentencia>)val_peek(3), new ArrayList<Sentencia>());	}
break;
case 16:
//#line 82 "sintactico.y"
{yyval = new Return(lexico.getLinea(), lexico.getColumna(),(Expresion)val_peek(1));	}
break;
case 17:
//#line 83 "sintactico.y"
{yyval = new LlamadaFuncion(lexico.getLinea(), lexico.getColumna(),(String)val_peek(4), (List<Expresion>)val_peek(2));	}
break;
case 18:
//#line 84 "sintactico.y"
{yyval = new Read(lexico.getLinea(), lexico.getColumna(),(Expresion)val_peek(1));	}
break;
case 19:
//#line 85 "sintactico.y"
{yyval = new Asignacion(lexico.getLinea(), lexico.getColumna(),(Expresion)val_peek(3), (Expresion)val_peek(1));	}
break;
case 20:
//#line 89 "sintactico.y"
{yyval = new ArrayList<Expresion>();	}
break;
case 21:
//#line 90 "sintactico.y"
{yyval = val_peek(0);}
break;
case 22:
//#line 92 "sintactico.y"
{yyval = new ArrayList<Expresion>(); ((List<Expresion>)yyval).add(Expresion)val_peek(0));	}
break;
case 23:
//#line 93 "sintactico.y"
{yyval = val_peek(2); ((List<Expresion>yyval).add((Expresion)val_peek(0));	}
break;
case 24:
//#line 97 "sintactico.y"
{yyval = new ArrayList<DeclaracionVariable>();	}
break;
case 25:
//#line 98 "sintactico.y"
{yyval = val_peek(0);}
break;
case 26:
//#line 100 "sintactico.y"
{yyval = new ArrayList<DeclaracionVariable>(); ((List<DeclaracionVariable>)yyval).add(new DeclaracionVariable(lexico.getLinea(), lexico.getColumna(),(Tipo)val_peek(0),(String)val_peek(2)))val_peek(2));	}
break;
case 27:
//#line 101 "sintactico.y"
{yyval = val_peek(4); ((List<DeclaracionVariable>yyval).add(new DeclaracionVariable(lexico.getLinea(), lexico.getColumna(),(Tipo)val_peek(0),(String)val_peek(4)))val_peek(2));	}
break;
case 28:
//#line 105 "sintactico.y"
{yyval = new ConstanteEntera(lexico.getLinea(), lexico.getColumna(),(int)val_peek(0));	}
break;
case 29:
//#line 106 "sintactico.y"
{yyval = new ConstanteChar(lexico.getLinea(), lexico.getColumna(),(char)val_peek(0));	}
break;
case 30:
//#line 107 "sintactico.y"
{yyval = new ConstanteReal(lexico.getLinea(), lexico.getColumna(),(double)val_peek(0));	}
break;
case 31:
//#line 108 "sintactico.y"
{yyval = new OperacionAritmetica(lexico.getLinea(), lexico.getColumna(), (Expresion)val_peek(2), "+", (Expresion)val_peek(0));	}
break;
case 32:
//#line 109 "sintactico.y"
{yyval = new OperacionAritmetica(lexico.getLinea(), lexico.getColumna(), (Expresion)val_peek(2), "-", (Expresion)val_peek(0));	}
break;
case 33:
//#line 110 "sintactico.y"
{yyval = new OperacionAritmetica(lexico.getLinea(), lexico.getColumna(), (Expresion)val_peek(2), "*", (Expresion)val_peek(0));	}
break;
case 34:
//#line 111 "sintactico.y"
{yyval = new OperacionAritmetica(lexico.getLinea(), lexico.getColumna(), (Expresion)val_peek(2), "/", (Expresion)val_peek(0));	}
break;
case 35:
//#line 112 "sintactico.y"
{yyval = new OperacionComparacion(lexico.getLinea(), lexico.getColumna(), (Expresion)val_peek(2), ">", (Expresion)val_peek(0));	}
break;
case 36:
//#line 113 "sintactico.y"
{yyval = new OperacionComparacion(lexico.getLinea(), lexico.getColumna(), (Expresion)val_peek(2), "<", (Expresion)val_peek(0));	}
break;
case 37:
//#line 114 "sintactico.y"
{yyval = new OperacionComparacion(lexico.getLinea(), lexico.getColumna(), (Expresion)val_peek(2), ">=", (Expresion)val_peek(0));	}
break;
case 38:
//#line 115 "sintactico.y"
{yyval = new OperacionComparacion(lexico.getLinea(), lexico.getColumna(), (Expresion)val_peek(2), "<=", (Expresion)val_peek(0));	}
break;
case 39:
//#line 116 "sintactico.y"
{yyval = new OperacionLogica(lexico.getLinea(), lexico.getColumna(), (Expresion)val_peek(2), "&&", (Expresion)val_peek(0));	}
break;
case 40:
//#line 117 "sintactico.y"
{yyval = new OperacionLogica(lexico.getLinea(), lexico.getColumna(), (Expresion)val_peek(2), "||", (Expresion)val_peek(0));	}
break;
case 41:
//#line 118 "sintactico.y"
{yyval = new OperacionLogica(lexico.getLinea(), lexico.getColumna(), (Expresion)val_peek(2), "<>", (Expresion)val_peek(0));	}
break;
case 42:
//#line 119 "sintactico.y"
{yyval = new OperacionLogica(lexico.getLinea(), lexico.getColumna(), (Expresion)val_peek(2), "==", (Expresion)val_peek(0));	}
break;
case 43:
//#line 120 "sintactico.y"
{yyval = new AccesoArray(lexico.getLinea(), lexico.getColumna(),(Expresion)val_peek(3),(String)val_peek(1));	}
break;
case 44:
//#line 121 "sintactico.y"
{yyval = new AccesoCampo(lexico.getLinea(), lexico.getColumna(),(Expresion)val_peek(2),(String)val_peek(0));	}
break;
case 45:
//#line 122 "sintactico.y"
{yyval = (Expresion)val_peek(1);	}
break;
case 46:
//#line 123 "sintactico.y"
{yyval = new LlamadaFuncion((String)val_peek(3), (List<Expresion>val_peek(1));	}
break;
case 47:
//#line 124 "sintactico.y"
{yyval = new Cast((Tipo)val_peek(3), (Expresion)val_peek(1));	}
break;
case 48:
//#line 128 "sintactico.y"
{yyval = new TipoEntero.getInstance();}
break;
case 49:
//#line 129 "sintactico.y"
{yyval = new TipoChar.getInstance();}
break;
case 50:
//#line 130 "sintactico.y"
{yyval = new TipoReal.getInstance();}
break;
//#line 614 "Parser.java"
//########## END OF USER-SUPPLIED ACTIONS ##########
    }//switch
    //#### Now let's reduce... ####
    if (yydebug) debug("reduce");
    state_drop(yym);             //we just reduced yylen states
    yystate = state_peek(0);     //get new state
    val_drop(yym);               //corresponding value drop
    yym = yylhs[yyn];            //select next TERMINAL(on lhs)
    if (yystate == 0 && yym == 0)//done? 'rest' state and at first TERMINAL
      {
      if (yydebug) debug("After reduction, shifting from state 0 to state "+YYFINAL+"");
      yystate = YYFINAL;         //explicitly say we're done
      state_push(YYFINAL);       //and save it
      val_push(yyval);           //also save the semantic value of parsing
      if (yychar < 0)            //we want another character?
        {
        yychar = yylex();        //get next character
        if (yychar<0) yychar=0;  //clean, if necessary
        if (yydebug)
          yylexdebug(yystate,yychar);
        }
      if (yychar == 0)          //Good exit (if lex returns 0 ;-)
         break;                 //quit the loop--all DONE
      }//if yystate
    else                        //else not done yet
      {                         //get next state and push, for next yydefred[]
      yyn = yygindex[yym];      //find out where to go
      if ((yyn != 0) && (yyn += yystate) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yystate)
        yystate = yytable[yyn]; //get new state
      else
        yystate = yydgoto[yym]; //else go to new defred
      if (yydebug) debug("after reduction, shifting from state "+state_peek(0)+" to state "+yystate+"");
      state_push(yystate);     //going again, so push state & val...
      val_push(yyval);         //for next action
      }
    }//main loop
  return 0;//yyaccept!!
}
//## end of method parse() ######################################



//## run() --- for Thread #######################################
/**
 * A default run method, used for operating this parser
 * object in the background.  It is intended for extending Thread
 * or implementing Runnable.  Turn off with -Jnorun .
 */
public void run()
{
  yyparse();
}
//## end of method run() ########################################



//## Constructors ###############################################
/**
 * Default constructor.  Turn off with -Jnoconstruct .

 */
public Parser()
{
  //nothing to do
}


/**
 * Create a parser, setting the debug to true or false.
 * @param debugMe true for debugging, false for no debug.
 */
public Parser(boolean debugMe)
{
  yydebug=debugMe;
}
//###############################################################



}
//################### END OF CLASS ##############################
