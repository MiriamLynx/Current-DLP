package ast.sentencia;

import ast.AbstractAST;
import ast.expresion.Expresion;

public class Read extends AbstractAST implements Sentencia {

	public Expresion expresion;

	public Read(int linea, int columna, Expresion expresion) {
		super(linea, columna);
		this.expresion = expresion;
	}

	@Override
	public String toString() {
		return "Lectura [ " + expresion + " ] \n";
	}

}