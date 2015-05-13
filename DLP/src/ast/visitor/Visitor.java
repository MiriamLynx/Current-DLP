package ast.visitor;

import ast.Programa;
import ast.declaracion.DeclaracionCampo;
import ast.declaracion.DeclaracionFuncion;
import ast.declaracion.DeclaracionStruct;
import ast.declaracion.DeclaracionVariable;
import ast.expresion.AccesoArray;
import ast.expresion.AccesoCampo;
import ast.expresion.Cast;
import ast.expresion.Comparacion;
import ast.expresion.ConstanteChar;
import ast.expresion.ConstanteEntera;
import ast.expresion.ConstanteReal;
import ast.expresion.Incremento;
import ast.expresion.LlamadaFuncion;
import ast.expresion.NotLogico;
import ast.expresion.OperacionAritmetica;
import ast.expresion.OperacionLogica;
import ast.expresion.Variable;
import ast.sentencia.Asignacion;
import ast.sentencia.If;
import ast.sentencia.LlamadaFuncionSent;
import ast.sentencia.Print;
import ast.sentencia.Read;
import ast.sentencia.Return;
import ast.sentencia.While;
import ast.tipo.TipoArray;
import ast.tipo.TipoChar;
import ast.tipo.TipoEntero;
import ast.tipo.TipoError;
import ast.tipo.TipoReal;
import ast.tipo.TipoStruct;

public interface Visitor {

	public Object visit(Programa programa, Object param);

	public Object visit(DeclaracionCampo declaracionCampo, Object param);

	public Object visit(DeclaracionFuncion declaracionFuncion, Object param);

	public Object visit(DeclaracionStruct declaracionStruct, Object param);

	public Object visit(DeclaracionVariable declaracionVariable, Object param);

	public Object visit(AccesoArray accesoArray, Object param);

	public Object visit(AccesoCampo accesoCampo, Object param);

	public Object visit(Cast cast, Object param);

	public Object visit(Comparacion comparacion, Object param);

	public Object visit(ConstanteChar constanteChar, Object param);

	public Object visit(ConstanteEntera constanteEntera, Object param);

	public Object visit(ConstanteReal constanteReal, Object param);

	public Object visit(LlamadaFuncion llamadaFuncion, Object param);

	public Object visit(OperacionAritmetica operacionAritmetica, Object param);

	public Object visit(OperacionLogica operacionLogica, Object param);

	public Object visit(Variable variable, Object param);

	public Object visit(NotLogico notLogico, Object param);

	public Object visit(Asignacion asignacion, Object param);

	public Object visit(If if1, Object param);

	public Object visit(LlamadaFuncionSent llamadaFuncionSent, Object param);

	public Object visit(Print print, Object param);

	public Object visit(Read read, Object param);

	public Object visit(Return return1, Object param);

	public Object visit(While while1, Object param);

	public Object visit(TipoArray tipoArray, Object param);

	public Object visit(TipoChar tipoChar, Object param);

	public Object visit(TipoEntero tipoEntero, Object param);

	public Object visit(TipoError tipoError, Object param);

	public Object visit(TipoReal tipoReal, Object param);

	public Object visit(TipoStruct tipoStruct, Object param);

	public Object visit(Incremento incremento, Object param);

}
