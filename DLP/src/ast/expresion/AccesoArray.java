package ast.expresion;

import ast.AbstractAST;

public class AccesoArray extends AbstractAST implements Expresion {

	public Expresion index;
	public Expresion array;

	public AccesoArray(int linea, int columna, Expresion index, Expresion array) {
		super(linea, columna);
		this.index = index;
		this.array = array;
	}

}