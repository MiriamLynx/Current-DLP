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






//#line 2 "sintactico.y"
import ast.*;
import ast.declaracion.*;
import ast.expresion.*;
import ast.sentencia.*;
import ast.tipo.*;
import java.util.List;
import java.util.ArrayList;
//#line 25 "Parser.java"




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
public final static short VOID=268;
public final static short RETURN=269;
public final static short MAYORIGUAL=270;
public final static short MENORIGUAL=271;
public final static short IGUAL=272;
public final static short DO=273;
public final static short END=274;
public final static short THEN=275;
public final static short READ=276;
public final static short AS=277;
public final static short DIM=278;
public final static short AND=279;
public final static short OR=280;
public final static short NOT=281;
public final static short DISTINTO=282;
public final static short CTYPE=283;
public final static short FUNCTION=284;
public final static short PROC=285;
public final static short TYPE=286;
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
    0,    1,    1,    2,    2,    2,    2,    4,    6,    5,
    3,    3,   13,   13,   12,   12,    9,    9,   11,   11,
   10,   10,   14,   14,   14,   14,   14,   14,   14,   14,
   14,   16,   16,   17,   17,    7,    7,   18,   18,   15,
   15,   15,   15,   15,   15,   15,   15,   15,   15,   15,
   15,   15,   15,   15,   15,   15,   15,   15,   15,   15,
   15,    8,    8,    8,    8,
};
final static short yylen[] = {                            2,
    1,    0,    2,    1,    1,    1,    1,   12,    6,   10,
    5,    6,    4,    5,    3,    4,    0,    2,    0,    2,
    0,    2,    3,    7,    9,    7,    3,    2,    5,    3,
    4,    0,    1,    1,    3,    0,    1,    3,    5,    1,
    1,    1,    3,    3,    3,    3,    3,    3,    3,    3,
    3,    3,    3,    3,    4,    3,    3,    4,    6,    1,
    2,    1,    1,    1,    1,
};
final static short yydefred[] = {                         2,
    0,    0,    0,    0,    0,    0,    3,    4,    5,    6,
    7,    0,    0,    0,   19,    0,    0,    0,    0,    0,
    0,   62,   64,   63,   65,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   20,   11,   15,    0,    0,
    0,    0,    0,   17,    0,    0,    0,   12,   16,   38,
    0,    0,    0,    0,    0,    9,   17,    0,   18,    0,
   13,    0,    0,   39,   40,   42,   41,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   22,    0,   14,
    0,    0,    0,    0,    0,    0,   28,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,   23,   21,   21,   27,   10,   30,    0,
   57,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   56,    0,    0,    0,    0,
    0,    0,    0,   31,   55,    8,   29,    0,   58,   21,
    0,    0,    0,    0,    0,    0,   59,    0,   26,   24,
    0,   25,
};
final static short yydgoto[] = {                          1,
    2,    7,   59,    9,   10,   11,   31,   26,   53,   60,
   21,   18,   36,   78,   79,  111,  112,   32,
};
final static short yysindex[] = {                         0,
    0, -216, -254, -252, -249, -241,    0,    0,    0,    0,
    0,  -90,  -17,   -9,    0, -235, -219,  -89, -217, -217,
 -255,    0,    0,    0,    0,  -11,  -46, -235, -208, -226,
   11,    9,   14,  -88, -222,    0,    0,    0,    6,  -27,
 -235, -205, -190,    0, -235,  -87,   15,    0,    0,    0,
 -235, -201, -200,   20, -235,    0,    0, -235,    0,  -19,
    0,   21, -200,    0,    0,    0,    0,   41,   86,   86,
   86,   79, -203,   86,   86,   43,   86,    0,  530,    0,
    2,   86,   45,  567,  634,  730,    0,  771,   27,  798,
  928, -235,  413,   86,   86,   86,   86,   86,   86,   86,
   86,   86,   86,   86,   86,   86,   86, -176, -194,  922,
   50,   48,   86,    0,    0,    0,    0,    0,    0,   51,
    0,   -2,   -2,   -2,  928,  928,   -2,  821,   -2,   -2,
  -30,  -30,  -41,  -41,  848,    0,   35,   37,   86,   56,
  -40,   23,   86,    0,    0,    0,    0,  922,    0,    0,
 -167, -168,  435,   44,   42,   47,    0, -165,    0,    0,
   49,    0,
};
final static short yyrindex[] = {                         0,
    0,  102,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,   62,   62,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   63,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   65,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   65,    0,    0,    0,    0,  875,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   66,  100,    0,    0,    0,    0,    0,    0,    0,
  -26,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,  -31,
    0,   69,   66,    0,    0,    0,    0,    0,    0,    0,
    0,  324,  448,  461,   -5,   16,  474,    0,  487,  509,
  377,  390,  122,  364,    0,    0,    0,  900,    0,    0,
    0,    0,    0,    0,    0,    0,    0,  -12,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,
};
final static short yygindex[] = {                         0,
    0,    0,  109,    0,    0,    0,   92,  -21,   57,  -57,
    0,   81,    0,    0, 1137,    3,    0,    0,
};
final static int YYTABLESIZE=1280;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         77,
   17,   29,   17,   29,  108,   81,   39,   34,   12,   34,
   13,  105,   34,   14,   61,  108,  106,   61,   35,   50,
   77,   15,   19,   54,   22,   23,   24,   25,   35,   57,
   20,   35,   61,   62,   61,   51,   64,   27,   51,  105,
  103,   77,  104,  108,  106,   30,   38,   37,   40,  107,
   41,   42,   43,   51,   44,   51,   52,  141,  142,   52,
  107,    3,   77,   47,   48,   49,   61,    4,    5,    6,
  120,   51,   52,   56,   52,   58,   52,    3,   61,   80,
   82,   89,   92,   77,  113,  118,  136,   51,  107,  137,
  138,  139,  154,  146,  143,  147,  149,  155,  156,  161,
  159,    1,   36,   37,   21,  160,   32,  162,   52,   33,
    8,   33,    0,   63,   46,  140,    0,    0,   77,    0,
    0,    0,    0,    0,    0,   77,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   87,    0,    0,
   60,   60,   60,   60,   60,   60,   60,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,   60,   60,
   60,   60,   45,   45,   45,   45,   45,    0,   45,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   45,   45,   45,   45,    0,    0,   16,   28,   45,   55,
   60,    0,   60,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   45,    0,   65,   66,   67,    0,
    0,    0,   68,   69,   70,  150,   71,    0,   72,    0,
    0,    0,    0,  151,    0,   74,    0,   65,   66,   67,
   75,    0,   76,   68,   69,   70,   61,   71,   61,   72,
    0,    0,   61,   61,   73,    0,   74,    0,   65,   66,
   67,   75,    0,   76,   68,   69,   70,   51,   71,   51,
   72,    0,    0,   51,   51,  109,    0,   74,    0,   65,
   66,   67,   75,    0,   76,   68,   69,   70,   52,   71,
   52,   72,    0,    0,   52,   52,  152,    0,   74,    0,
   65,   66,   67,   75,    0,   76,   68,   69,   70,    0,
   71,    0,   72,    0,    0,    0,    0,  158,    0,   74,
    0,   21,   21,   21,   75,    0,   76,   21,   21,   21,
    0,   21,    0,   21,    0,   65,   66,   67,   21,    0,
   21,   83,   65,   66,   67,   21,    0,   21,   83,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,   75,
    0,   76,    0,    0,   49,    0,   75,   49,   76,   60,
   60,   60,   60,    0,   60,    0,    0,    0,   60,   60,
    0,   60,   49,   49,   49,   49,    0,    0,    0,    0,
    0,   45,   45,   45,   45,    0,   45,    0,    0,    0,
   45,   45,    0,   45,   46,   46,   46,   46,   46,    0,
   46,    0,    0,    0,    0,    0,   49,   43,    0,   43,
   43,   43,   46,   46,   46,   46,    0,    0,    0,    0,
   44,    0,   44,   44,   44,   43,   43,   43,   43,    0,
    0,    0,    0,    0,    0,    0,    0,    0,   44,   44,
   44,   44,    0,  121,  105,  103,   46,  104,  108,  106,
    0,    0,    0,    0,    0,    0,    0,    0,    0,   43,
    0,    0,  102,    0,  101,  157,  105,  103,    0,  104,
  108,  106,   44,    0,    0,    0,    0,    0,   50,    0,
    0,   50,    0,    0,  102,    0,  101,    0,    0,    0,
    0,   54,    0,  107,   54,    0,   50,   50,   50,   50,
    0,    0,    0,    0,   53,    0,    0,   53,    0,   54,
   54,   54,   54,    0,    0,  107,    0,   47,    0,    0,
   47,    0,   53,   53,   53,   53,    0,    0,    0,    0,
   50,    0,    0,    0,    0,   47,   47,   47,   47,   48,
    0,    0,   48,   54,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,   53,   48,   48,   48,
   48,  105,  103,    0,  104,  108,  106,    0,    0,   47,
    0,    0,    0,    0,    0,    0,    0,    0,    0,  102,
  100,  101,    0,   49,   49,   49,   49,    0,   49,    0,
    0,   48,   49,   49,    0,   49,    0,    0,  105,  103,
    0,  104,  108,  106,    0,    0,    0,    0,    0,    0,
  107,    0,    0,    0,    0,  114,  102,    0,  101,    0,
    0,    0,    0,   46,   46,   46,   46,    0,   46,    0,
    0,    0,   46,   46,    0,   46,   43,   43,   43,   43,
    0,   43,    0,    0,    0,   43,   43,  107,   43,   44,
   44,   44,   44,    0,   44,    0,    0,    0,   44,   44,
    0,   44,    0,    0,    0,  105,  103,    0,  104,  108,
  106,    0,   94,   95,   96,    0,    0,    0,    0,    0,
    0,   97,   98,  102,   99,  101,    0,    0,    0,    0,
    0,    0,    0,    0,   94,   95,   96,    0,    0,    0,
    0,    0,    0,   97,   98,    0,   99,   50,   50,   50,
   50,    0,   50,    0,  107,    0,   50,   50,    0,   50,
   54,   54,   54,   54,    0,   54,    0,    0,    0,   54,
   54,    0,   54,   53,   53,   53,   53,    0,   53,    0,
    0,    0,   53,   53,    0,   53,   47,   47,   47,   47,
    0,   47,    0,    0,    0,   47,   47,    0,   47,    0,
    0,  105,  103,    0,  104,  108,  106,    0,   48,   48,
   48,   48,    0,   48,    0,    0,    0,   48,   48,  102,
   48,  101,    0,    0,    0,    0,    0,    0,    0,   94,
   95,   96,    0,    0,    0,    0,    0,    0,   97,   98,
    0,   99,  105,  103,    0,  104,  108,  106,    0,    0,
  107,    0,    0,    0,    0,    0,    0,    0,    0,  117,
  102,    0,  101,    0,    0,    0,   94,   95,   96,  105,
  103,    0,  104,  108,  106,   97,   98,    0,   99,    0,
    0,    0,    0,    0,    0,    0,  119,  102,    0,  101,
    0,  107,  105,  103,    0,  104,  108,  106,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,  144,
  102,    0,  101,    0,    0,    0,    0,    0,  107,  105,
  103,    0,  104,  108,  106,    0,    0,    0,    0,    0,
    0,    0,    0,   94,   95,   96,    0,  102,  115,  101,
    0,  107,   97,   98,    0,   99,   60,   60,    0,   60,
   60,   60,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   60,   60,   60,    0,  107,    0,
  145,   58,   58,    0,   58,   58,   58,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,   58,
   58,   58,    0,  105,  103,   60,  104,  108,  106,  105,
  103,    0,  104,  108,  106,    0,    0,    0,    0,    0,
    0,  102,    0,  101,    0,    0,    0,  102,    0,  101,
   58,    0,    0,    0,    0,    0,    0,    0,    0,   94,
   95,   96,  116,    0,    0,    0,    0,    0,   97,   98,
    0,   99,  107,    0,    0,    0,    0,    0,  107,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   94,   95,   96,    0,    0,    0,    0,    0,    0,   97,
   98,    0,   99,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   94,   95,   96,
    0,    0,    0,    0,    0,    0,   97,   98,    0,   99,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   94,   95,   96,    0,    0,    0,    0,    0,    0,   97,
   98,    0,   99,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   94,   95,   96,
    0,    0,    0,    0,    0,    0,   97,   98,    0,   99,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   60,   60,   60,    0,    0,    0,
    0,    0,    0,   60,   60,    0,   60,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,   58,
   58,   58,    0,    0,    0,    0,    0,    0,   58,   58,
    0,   58,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   94,   95,   96,    0,    0,    0,   94,   95,   96,
   97,   98,    0,   99,    0,   84,   85,   86,   88,   99,
   90,   91,    0,   93,    0,    0,    0,    0,  110,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
  122,  123,  124,  125,  126,  127,  128,  129,  130,  131,
  132,  133,  134,  135,    0,    0,    0,    0,    0,  110,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,  148,    0,    0,    0,  153,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         40,
   91,   91,   91,   91,   46,   63,   28,  263,  263,   41,
  263,   42,   44,  263,   41,   46,   47,   44,  274,   41,
   40,  263,   40,   45,  260,  261,  262,  263,   41,   51,
   40,   44,   59,   55,   61,   41,   58,  257,   44,   42,
   43,   40,   45,   46,   47,  263,   93,   59,  257,   91,
  277,   41,   44,   59,   41,   61,   41,  115,  116,   44,
   91,  278,   40,  286,   59,   93,   93,  284,  285,  286,
   92,  277,  263,   59,   59,  277,   61,  278,   59,   59,
   40,  285,   40,   40,   40,   59,  263,   93,   91,  284,
   41,   44,  150,   59,   44,   59,   41,  265,  267,  265,
   59,    0,   41,   41,   40,   59,   41,   59,   93,   41,
    2,   20,   -1,   57,   34,  113,   -1,   -1,   40,   -1,
   -1,   -1,   -1,   -1,   -1,   40,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   59,   -1,   -1,
   41,   42,   43,   44,   45,   46,   47,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   59,   60,
   61,   62,   41,   42,   43,   44,   45,   -1,   47,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   59,   60,   61,   62,   -1,   -1,  277,  277,  277,  277,
   91,   -1,   93,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   93,   -1,  257,  258,  259,   -1,
   -1,   -1,  263,  264,  265,  266,  267,   -1,  269,   -1,
   -1,   -1,   -1,  274,   -1,  276,   -1,  257,  258,  259,
  281,   -1,  283,  263,  264,  265,  273,  267,  275,  269,
   -1,   -1,  279,  280,  274,   -1,  276,   -1,  257,  258,
  259,  281,   -1,  283,  263,  264,  265,  273,  267,  275,
  269,   -1,   -1,  279,  280,  274,   -1,  276,   -1,  257,
  258,  259,  281,   -1,  283,  263,  264,  265,  273,  267,
  275,  269,   -1,   -1,  279,  280,  274,   -1,  276,   -1,
  257,  258,  259,  281,   -1,  283,  263,  264,  265,   -1,
  267,   -1,  269,   -1,   -1,   -1,   -1,  274,   -1,  276,
   -1,  257,  258,  259,  281,   -1,  283,  263,  264,  265,
   -1,  267,   -1,  269,   -1,  257,  258,  259,  274,   -1,
  276,  263,  257,  258,  259,  281,   -1,  283,  263,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  281,
   -1,  283,   -1,   -1,   41,   -1,  281,   44,  283,  270,
  271,  272,  273,   -1,  275,   -1,   -1,   -1,  279,  280,
   -1,  282,   59,   60,   61,   62,   -1,   -1,   -1,   -1,
   -1,  270,  271,  272,  273,   -1,  275,   -1,   -1,   -1,
  279,  280,   -1,  282,   41,   42,   43,   44,   45,   -1,
   47,   -1,   -1,   -1,   -1,   -1,   93,   41,   -1,   43,
   44,   45,   59,   60,   61,   62,   -1,   -1,   -1,   -1,
   41,   -1,   43,   44,   45,   59,   60,   61,   62,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   59,   60,
   61,   62,   -1,   41,   42,   43,   93,   45,   46,   47,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   93,
   -1,   -1,   60,   -1,   62,   41,   42,   43,   -1,   45,
   46,   47,   93,   -1,   -1,   -1,   -1,   -1,   41,   -1,
   -1,   44,   -1,   -1,   60,   -1,   62,   -1,   -1,   -1,
   -1,   41,   -1,   91,   44,   -1,   59,   60,   61,   62,
   -1,   -1,   -1,   -1,   41,   -1,   -1,   44,   -1,   59,
   60,   61,   62,   -1,   -1,   91,   -1,   41,   -1,   -1,
   44,   -1,   59,   60,   61,   62,   -1,   -1,   -1,   -1,
   93,   -1,   -1,   -1,   -1,   59,   60,   61,   62,   41,
   -1,   -1,   44,   93,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   93,   59,   60,   61,
   62,   42,   43,   -1,   45,   46,   47,   -1,   -1,   93,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   60,
   61,   62,   -1,  270,  271,  272,  273,   -1,  275,   -1,
   -1,   93,  279,  280,   -1,  282,   -1,   -1,   42,   43,
   -1,   45,   46,   47,   -1,   -1,   -1,   -1,   -1,   -1,
   91,   -1,   -1,   -1,   -1,   59,   60,   -1,   62,   -1,
   -1,   -1,   -1,  270,  271,  272,  273,   -1,  275,   -1,
   -1,   -1,  279,  280,   -1,  282,  270,  271,  272,  273,
   -1,  275,   -1,   -1,   -1,  279,  280,   91,  282,  270,
  271,  272,  273,   -1,  275,   -1,   -1,   -1,  279,  280,
   -1,  282,   -1,   -1,   -1,   42,   43,   -1,   45,   46,
   47,   -1,  270,  271,  272,   -1,   -1,   -1,   -1,   -1,
   -1,  279,  280,   60,  282,   62,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,  270,  271,  272,   -1,   -1,   -1,
   -1,   -1,   -1,  279,  280,   -1,  282,  270,  271,  272,
  273,   -1,  275,   -1,   91,   -1,  279,  280,   -1,  282,
  270,  271,  272,  273,   -1,  275,   -1,   -1,   -1,  279,
  280,   -1,  282,  270,  271,  272,  273,   -1,  275,   -1,
   -1,   -1,  279,  280,   -1,  282,  270,  271,  272,  273,
   -1,  275,   -1,   -1,   -1,  279,  280,   -1,  282,   -1,
   -1,   42,   43,   -1,   45,   46,   47,   -1,  270,  271,
  272,  273,   -1,  275,   -1,   -1,   -1,  279,  280,   60,
  282,   62,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  270,
  271,  272,   -1,   -1,   -1,   -1,   -1,   -1,  279,  280,
   -1,  282,   42,   43,   -1,   45,   46,   47,   -1,   -1,
   91,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   59,
   60,   -1,   62,   -1,   -1,   -1,  270,  271,  272,   42,
   43,   -1,   45,   46,   47,  279,  280,   -1,  282,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   59,   60,   -1,   62,
   -1,   91,   42,   43,   -1,   45,   46,   47,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   59,
   60,   -1,   62,   -1,   -1,   -1,   -1,   -1,   91,   42,
   43,   -1,   45,   46,   47,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,  270,  271,  272,   -1,   60,  275,   62,
   -1,   91,  279,  280,   -1,  282,   42,   43,   -1,   45,
   46,   47,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   60,   61,   62,   -1,   91,   -1,
   93,   42,   43,   -1,   45,   46,   47,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   60,
   61,   62,   -1,   42,   43,   91,   45,   46,   47,   42,
   43,   -1,   45,   46,   47,   -1,   -1,   -1,   -1,   -1,
   -1,   60,   -1,   62,   -1,   -1,   -1,   60,   -1,   62,
   91,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  270,
  271,  272,  273,   -1,   -1,   -1,   -1,   -1,  279,  280,
   -1,  282,   91,   -1,   -1,   -1,   -1,   -1,   91,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
  270,  271,  272,   -1,   -1,   -1,   -1,   -1,   -1,  279,
  280,   -1,  282,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,  270,  271,  272,
   -1,   -1,   -1,   -1,   -1,   -1,  279,  280,   -1,  282,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
  270,  271,  272,   -1,   -1,   -1,   -1,   -1,   -1,  279,
  280,   -1,  282,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,  270,  271,  272,
   -1,   -1,   -1,   -1,   -1,   -1,  279,  280,   -1,  282,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,  270,  271,  272,   -1,   -1,   -1,
   -1,   -1,   -1,  279,  280,   -1,  282,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  270,
  271,  272,   -1,   -1,   -1,   -1,   -1,   -1,  279,  280,
   -1,  282,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,  270,  271,  272,   -1,   -1,   -1,  270,  271,  272,
  279,  280,   -1,  282,   -1,   69,   70,   71,   72,  282,
   74,   75,   -1,   77,   -1,   -1,   -1,   -1,   82,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   94,   95,   96,   97,   98,   99,  100,  101,  102,  103,
  104,  105,  106,  107,   -1,   -1,   -1,   -1,   -1,  113,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,  139,   -1,   -1,   -1,  143,
};
}
final static short YYFINAL=1;
final static short YYMAXTOKEN=286;
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
"INTEGER","REAL","CHARACTER","IDENT","PRINT","IF","ELSE","WHILE","VOID",
"RETURN","MAYORIGUAL","MENORIGUAL","IGUAL","DO","END","THEN","READ","AS","DIM",
"AND","OR","NOT","DISTINTO","CTYPE","FUNCTION","PROC","TYPE",
};
final static String yyrule[] = {
"$accept : programa",
"programa : listaDeclaraciones",
"listaDeclaraciones :",
"listaDeclaraciones : listaDeclaraciones declaracion",
"declaracion : declaracionVariable",
"declaracion : declaracionFuncion",
"declaracion : declaracionProcedimiento",
"declaracion : declaracionStruct",
"declaracionFuncion : FUNCTION IDENT '(' parametrosFuncion ')' AS tipo listaDeclaracionesVariable listaSentencias END FUNCTION ';'",
"declaracionStruct : TYPE IDENT listaDeclaracionesCampo END TYPE ';'",
"declaracionProcedimiento : PROC IDENT '(' parametrosFuncion ')' listaDeclaracionesVariable listaSentencias END PROC ';'",
"declaracionVariable : DIM IDENT AS tipo ';'",
"declaracionVariable : DIM IDENT listaDimensiones AS tipo ';'",
"declaracionCampo : IDENT AS tipo ';'",
"declaracionCampo : IDENT listaDimensiones AS tipo ';'",
"listaDimensiones : '[' CTE_ENTERA ']'",
"listaDimensiones : listaDimensiones '[' CTE_ENTERA ']'",
"listaDeclaracionesVariable :",
"listaDeclaracionesVariable : listaDeclaracionesVariable declaracionVariable",
"listaDeclaracionesCampo :",
"listaDeclaracionesCampo : listaDeclaracionesCampo declaracionCampo",
"listaSentencias :",
"listaSentencias : listaSentencias sentencia",
"sentencia : PRINT expresion ';'",
"sentencia : WHILE expresion DO listaSentencias END WHILE ';'",
"sentencia : IF expresion THEN listaSentencias ELSE listaSentencias END IF ';'",
"sentencia : IF expresion THEN listaSentencias END IF ';'",
"sentencia : RETURN expresion ';'",
"sentencia : RETURN ';'",
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
"expresion : IDENT",
"expresion : NOT expresion",
"tipo : INTEGER",
"tipo : CHARACTER",
"tipo : REAL",
"tipo : IDENT",
};

//#line 175 "sintactico.y"
Lexico lexico;
AST root;
int token;

// * Manejo de Errores Sint�cticos
public void yyerror (String error) {
    System.err.println ("Error Sint�ctico en l�nea " + lexico.getLinea()+
		" y columna "+lexico.getColumna()+":\n\t"+error);
}

// * Llamada al analizador l�xico
private int yylex () {
    int token=0;
    try { 
	token=lexico.yylex(); 
    } catch(Throwable e) {
	    System.err.println ("Error L�xico en l�nea " + lexico.getLinea()+
		" y columna "+lexico.getColumna()+":\n\t"+e); 
    }
    return token;
}

// * El yylval no es un atributo p�blico
public Object getYylval() {
    	return yylval;
}
public void setYylval(Object yylval) {
        this.yylval = yylval;
}

// * Constructor del Sint�ctico
public Parser(Lexico lexico) {
	this.lexico = lexico;
	lexico.setParser(this);
}

public AST getAst(){
	return root;
}
//#line 610 "Parser.java"
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
//#line 55 "sintactico.y"
{root = new Programa(lexico.getLinea(), lexico.getColumna(), (List<Declaracion>)val_peek(0));}
break;
case 2:
//#line 59 "sintactico.y"
{ yyval = new ArrayList<Declaracion>(); }
break;
case 3:
//#line 60 "sintactico.y"
{ List<Declaracion> l = (List<Declaracion>)val_peek(1); l.add((Declaracion)val_peek(0)); yyval = l;}
break;
case 4:
//#line 65 "sintactico.y"
{yyval = val_peek(0);	}
break;
case 5:
//#line 66 "sintactico.y"
{yyval = val_peek(0);	}
break;
case 6:
//#line 67 "sintactico.y"
{yyval = val_peek(0);	}
break;
case 7:
//#line 68 "sintactico.y"
{yyval = val_peek(0);	}
break;
case 8:
//#line 73 "sintactico.y"
{yyval = new DeclaracionFuncion(lexico.getLinea(), lexico.getColumna(), (Tipo)val_peek(5), (String)val_peek(10), (List<DeclaracionVariable>)val_peek(8), (List<DeclaracionVariable>)val_peek(4), (List<Sentencia>)val_peek(3)); }
break;
case 9:
//#line 77 "sintactico.y"
{yyval = new DeclaracionStruct(lexico.getLinea(), lexico.getColumna(), (String)val_peek(4), (List<DeclaracionVariable>)val_peek(3)); }
break;
case 10:
//#line 81 "sintactico.y"
{yyval = new DeclaracionFuncion(lexico.getLinea(), lexico.getColumna(), null, (String)val_peek(8),(List<DeclaracionVariable>)val_peek(6), (List<DeclaracionVariable>)val_peek(4), (List<Sentencia>)val_peek(3)); }
break;
case 11:
//#line 85 "sintactico.y"
{yyval = new DeclaracionVariable(lexico.getLinea(), lexico.getColumna(),(Tipo)val_peek(1), (String)val_peek(3)); }
break;
case 12:
//#line 86 "sintactico.y"
{ Tipo array = new TipoArray(lexico.getLinea(), lexico.getColumna(), (List<Integer>)val_peek(3), (Tipo)val_peek(1)); yyval = new DeclaracionVariable(lexico.getLinea(), lexico.getColumna(), array, (String)val_peek(4));}
break;
case 13:
//#line 90 "sintactico.y"
{yyval = new DeclaracionCampo(lexico.getLinea(), lexico.getColumna(),(Tipo)val_peek(1), (String)val_peek(3)); }
break;
case 14:
//#line 91 "sintactico.y"
{Tipo array = new TipoArray(lexico.getLinea(), lexico.getColumna(), (List<Integer>)val_peek(3), (Tipo)val_peek(1)); yyval = new DeclaracionCampo(lexico.getLinea(), lexico.getColumna(), array, (String)val_peek(4));}
break;
case 15:
//#line 95 "sintactico.y"
{ List<Integer> l = new ArrayList<Integer>(); l.add((Integer)val_peek(1)); yyval = l;}
break;
case 16:
//#line 96 "sintactico.y"
{ List<Integer> l  = (List<Integer>)val_peek(3); l.add((Integer)val_peek(1)); yyval = l;}
break;
case 17:
//#line 100 "sintactico.y"
{yyval = new ArrayList<DeclaracionVariable>(); }
break;
case 18:
//#line 101 "sintactico.y"
{List<DeclaracionVariable> l = (List<DeclaracionVariable>)val_peek(1); l.add((DeclaracionVariable)val_peek(0)); yyval = l;}
break;
case 19:
//#line 105 "sintactico.y"
{yyval = new ArrayList<DeclaracionCampo>(); }
break;
case 20:
//#line 106 "sintactico.y"
{List<DeclaracionCampo> l = (List<DeclaracionCampo>)val_peek(1); l.add((DeclaracionCampo)val_peek(0)); yyval = l;}
break;
case 21:
//#line 111 "sintactico.y"
{yyval = new ArrayList<Sentencia>();	}
break;
case 22:
//#line 112 "sintactico.y"
{List<Sentencia> l = (List<Sentencia>)val_peek(1); l.add((Sentencia)val_peek(0)); yyval = l; }
break;
case 23:
//#line 116 "sintactico.y"
{yyval = new Print(lexico.getLinea(), lexico.getColumna(),(Expresion)val_peek(1));	}
break;
case 24:
//#line 117 "sintactico.y"
{yyval = new While(lexico.getLinea(), lexico.getColumna(),(Expresion)val_peek(5),(List<Sentencia>)val_peek(3));	}
break;
case 25:
//#line 118 "sintactico.y"
{yyval = new If(lexico.getLinea(), lexico.getColumna(),(Expresion)val_peek(7), (List<Sentencia>)val_peek(5), (List<Sentencia>)val_peek(3));	}
break;
case 26:
//#line 119 "sintactico.y"
{yyval = new If(lexico.getLinea(), lexico.getColumna(),(Expresion)val_peek(5), (List<Sentencia>)val_peek(3), new ArrayList<Sentencia>());	}
break;
case 27:
//#line 120 "sintactico.y"
{yyval = new Return(lexico.getLinea(), lexico.getColumna(),(Expresion)val_peek(1));	}
break;
case 28:
//#line 121 "sintactico.y"
{yyval = new Return(lexico.getLinea(), lexico.getColumna(),null);	}
break;
case 29:
//#line 122 "sintactico.y"
{yyval = new LlamadaFuncionSent(lexico.getLinea(), lexico.getColumna(),(String)val_peek(4), (List<Expresion>)val_peek(2));	}
break;
case 30:
//#line 123 "sintactico.y"
{yyval = new Read(lexico.getLinea(), lexico.getColumna(),(Expresion)val_peek(1));	}
break;
case 31:
//#line 124 "sintactico.y"
{yyval = new Asignacion(lexico.getLinea(), lexico.getColumna(),(Expresion)val_peek(3), (Expresion)val_peek(1));	}
break;
case 32:
//#line 128 "sintactico.y"
{yyval = new ArrayList<Expresion>();	}
break;
case 33:
//#line 129 "sintactico.y"
{yyval = val_peek(0);}
break;
case 34:
//#line 131 "sintactico.y"
{yyval = new ArrayList<Expresion>(); ((List<Expresion>)yyval).add((Expresion)val_peek(0));	}
break;
case 35:
//#line 132 "sintactico.y"
{List<Expresion> l = (List<Expresion>)val_peek(2); l.add((Expresion)val_peek(0)); yyval = l;	}
break;
case 36:
//#line 136 "sintactico.y"
{yyval = new ArrayList<DeclaracionVariable>();	}
break;
case 37:
//#line 137 "sintactico.y"
{yyval = val_peek(0);}
break;
case 38:
//#line 139 "sintactico.y"
{yyval = new ArrayList<DeclaracionVariable>(); ((List<DeclaracionVariable>)yyval).add(new DeclaracionVariable(lexico.getLinea(), lexico.getColumna(), (Tipo)val_peek(0),(String)val_peek(2)));	}
break;
case 39:
//#line 140 "sintactico.y"
{List<DeclaracionVariable> l = (List<DeclaracionVariable>)val_peek(4); l.add(new DeclaracionVariable(lexico.getLinea(), lexico.getColumna(),(Tipo)val_peek(0),(String)val_peek(2))); yyval = l;	}
break;
case 40:
//#line 144 "sintactico.y"
{yyval = new ConstanteEntera(lexico.getLinea(), lexico.getColumna(),(Integer)val_peek(0));	}
break;
case 41:
//#line 145 "sintactico.y"
{yyval = new ConstanteChar(lexico.getLinea(), lexico.getColumna(),(Character)val_peek(0));	}
break;
case 42:
//#line 146 "sintactico.y"
{yyval = new ConstanteReal(lexico.getLinea(), lexico.getColumna(),(Double)val_peek(0));	}
break;
case 43:
//#line 147 "sintactico.y"
{yyval = new OperacionAritmetica(lexico.getLinea(), lexico.getColumna(), (Expresion)val_peek(2), "+", (Expresion)val_peek(0));	}
break;
case 44:
//#line 148 "sintactico.y"
{yyval = new OperacionAritmetica(lexico.getLinea(), lexico.getColumna(), (Expresion)val_peek(2), "-", (Expresion)val_peek(0));	}
break;
case 45:
//#line 149 "sintactico.y"
{yyval = new OperacionAritmetica(lexico.getLinea(), lexico.getColumna(), (Expresion)val_peek(2), "*", (Expresion)val_peek(0));	}
break;
case 46:
//#line 150 "sintactico.y"
{yyval = new OperacionAritmetica(lexico.getLinea(), lexico.getColumna(), (Expresion)val_peek(2), "/", (Expresion)val_peek(0));	}
break;
case 47:
//#line 151 "sintactico.y"
{yyval = new Comparacion(lexico.getLinea(), lexico.getColumna(), (Expresion)val_peek(2), ">", (Expresion)val_peek(0));	}
break;
case 48:
//#line 152 "sintactico.y"
{yyval = new Comparacion(lexico.getLinea(), lexico.getColumna(), (Expresion)val_peek(2), "<", (Expresion)val_peek(0));	}
break;
case 49:
//#line 153 "sintactico.y"
{yyval = new Comparacion(lexico.getLinea(), lexico.getColumna(), (Expresion)val_peek(2), ">=", (Expresion)val_peek(0));	}
break;
case 50:
//#line 154 "sintactico.y"
{yyval = new Comparacion(lexico.getLinea(), lexico.getColumna(), (Expresion)val_peek(2), "<=", (Expresion)val_peek(0));	}
break;
case 51:
//#line 155 "sintactico.y"
{yyval = new OperacionLogica(lexico.getLinea(), lexico.getColumna(), (Expresion)val_peek(2), "&&", (Expresion)val_peek(0));	}
break;
case 52:
//#line 156 "sintactico.y"
{yyval = new OperacionLogica(lexico.getLinea(), lexico.getColumna(), (Expresion)val_peek(2), "||", (Expresion)val_peek(0));	}
break;
case 53:
//#line 157 "sintactico.y"
{yyval = new OperacionLogica(lexico.getLinea(), lexico.getColumna(), (Expresion)val_peek(2), "<>", (Expresion)val_peek(0));	}
break;
case 54:
//#line 158 "sintactico.y"
{yyval = new OperacionLogica(lexico.getLinea(), lexico.getColumna(), (Expresion)val_peek(2), "==", (Expresion)val_peek(0));	}
break;
case 55:
//#line 159 "sintactico.y"
{yyval = new AccesoArray(lexico.getLinea(), lexico.getColumna(),(Expresion)val_peek(3),(Expresion)val_peek(1));	}
break;
case 56:
//#line 160 "sintactico.y"
{yyval = new AccesoCampo(lexico.getLinea(), lexico.getColumna(),(Expresion)val_peek(2),(String)val_peek(0));	}
break;
case 57:
//#line 161 "sintactico.y"
{yyval = (Expresion)val_peek(1);	}
break;
case 58:
//#line 162 "sintactico.y"
{yyval = new LlamadaFuncion(lexico.getLinea(), lexico.getColumna(), (String)val_peek(3), (List<Expresion>)val_peek(1));	}
break;
case 59:
//#line 163 "sintactico.y"
{yyval = new Cast(lexico.getLinea(), lexico.getColumna(), (Tipo)val_peek(3), (Expresion)val_peek(1));	}
break;
case 60:
//#line 164 "sintactico.y"
{yyval = new Variable(lexico.getLinea(), lexico.getColumna(), (String)val_peek(0)); }
break;
case 61:
//#line 165 "sintactico.y"
{yyval = new NotLogico(lexico.getLinea(), lexico.getColumna(), (Expresion)val_peek(0));}
break;
case 62:
//#line 169 "sintactico.y"
{yyval = TipoEntero.getInstance(lexico.getLinea(), lexico.getColumna());}
break;
case 63:
//#line 170 "sintactico.y"
{yyval = TipoChar.getInstance(lexico.getLinea(), lexico.getColumna());}
break;
case 64:
//#line 171 "sintactico.y"
{yyval = TipoReal.getInstance(lexico.getLinea(), lexico.getColumna());}
break;
case 65:
//#line 172 "sintactico.y"
{yyval = new TipoStruct(lexico.getLinea(), lexico.getColumna(), (String)val_peek(0));}
break;
//#line 1018 "Parser.java"
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
