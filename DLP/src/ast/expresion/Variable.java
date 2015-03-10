package ast.expresion;

import ast.AbstractAST;

public class Variable extends AbstractAST implements Expresion {

	public String nombre;

	public Variable(int linea, int columna, String nombre) {
		super(linea, columna);
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "LiteralReal [nombre=" + nombre + "]";
	}
}
