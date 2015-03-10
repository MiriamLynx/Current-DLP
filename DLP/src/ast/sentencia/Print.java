package ast.sentencia;

import ast.AbstractAST;
import ast.expresion.Expresion;

public class Print extends AbstractAST implements Sentencia {

	public Expresion expresion;

	public Print(int linea, int columna, Expresion expresion) {
		super(linea, columna);
		this.expresion = expresion;
	}

	@Override
	public String toString() {
		return "Escritura [expresion=" + expresion + "]";
	}

}