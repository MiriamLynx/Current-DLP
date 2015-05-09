package ast.sentencia;

import ast.AbstractAST;
import ast.declaracion.DeclaracionFuncion;

public abstract class AbstractSentencia extends AbstractAST {

	public AbstractSentencia(int linea, int columna) {
		super(linea, columna);
	}

	public DeclaracionFuncion declaracionFuncion;

	public DeclaracionFuncion getDeclaracionFuncion() {
		return declaracionFuncion;
	}

	public void setDeclaracionFuncion(DeclaracionFuncion declaracionFuncion) {
		this.declaracionFuncion = declaracionFuncion;
	}

}
