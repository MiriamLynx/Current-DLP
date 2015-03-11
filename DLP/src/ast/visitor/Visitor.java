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

	public Object visit(Programa programa);

	public Object visit(DeclaracionCampo declaracionCampo);

	public Object visit(DeclaracionFuncion declaracionFuncion);

	public Object visit(DeclaracionStruct declaracionStruct);

	public Object visit(DeclaracionVariable declaracionVariable);

	public Object visit(AccesoArray accesoArray);

	public Object visit(AccesoCampo accesoCampo);

	public Object visit(Cast cast);

	public Object visit(Comparacion comparacion);

	public Object visit(ConstanteChar constanteChar);

	public Object visit(ConstanteEntera constanteEntera);

	public Object visit(ConstanteReal constanteReal);

	public Object visit(LlamadaFuncion llamadaFuncion);

	public Object visit(OperacionAritmetica operacionAritmetica);

	public Object visit(OperacionLogica operacionLogica);

	public Object visit(Variable variable);

	public Object visit(NotLogico notLogico);

	public Object visit(Asignacion asignacion);

	public Object visit(If if1);

	public Object visit(LlamadaFuncionSent llamadaFuncionSent);

	public Object visit(Print print);

	public Object visit(Read read);

	public Object visit(Return return1);

	public Object visit(While while1);

	public Object visit(TipoArray tipoArray);

	public Object visit(TipoChar tipoChar);

	public Object visit(TipoEntero tipoEntero);

	public Object visit(TipoError tipoError);

	public Object visit(TipoReal tipoReal);

	public Object visit(TipoStruct tipoStruct);

}