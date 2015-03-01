package ast.sentencia;

import ast.AbstractAST;
import ast.expresion.Expresion;

public class Return extends AbstractAST implements Sentencia {

	public Expresion expresion;

	public Return(int linea, int columna, Expresion expresion) {
		super(linea, columna);
		this.expresion = expresion;
	}

	@Override
	public String toString() {
		return "Return [expresion=" + expresion + "]";
	}

}