package ast.sentencia;

import ast.AST;
import ast.declaracion.DeclaracionFuncion;

public interface Sentencia extends AST {

	public void setDeclaracionFuncion(DeclaracionFuncion declaracion);

	public DeclaracionFuncion getDeclaracionFuncion();

}
