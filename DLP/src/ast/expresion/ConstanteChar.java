package ast.expresion;

import ast.AbstractAST;

public class ConstanteChar extends AbstractAST implements Expresion {

	public char valor;

	public ConstanteChar(int linea, int columna, char valor) {
		super(linea, columna);
		this.valor = valor;
	}

	@Override
	public String toString() {
		return "LiteralCaracter [valor=" + valor + "]";
	}

}