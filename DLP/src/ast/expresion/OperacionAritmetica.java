package ast.expresion;

import ast.AbstractAST;

public class OperacionAritmetica extends AbstractAST implements Expresion {

	public Expresion izquierda;
	public Expresion derecha;
	public String operador;

	public OperacionAritmetica(int linea, int columna, Expresion izquierda,
			String operador, Expresion derecha) {
		super(linea, columna);
		this.izquierda = izquierda;
		this.derecha = derecha;
		this.operador = operador;
	}

	@Override
	public String toString() {
		return "OperacionAritmetica [izquierda=" + izquierda + ", derecha="
				+ derecha + ", operador=" + operador + "]";
	}

}