package ast.expresion;

import ast.AbstractAST;

public class ConstanteReal extends AbstractAST implements Expresion {

	public double valor;

	public ConstanteReal(int linea, int columna, double valor) {
		super(linea, columna);
		this.valor = valor;
	}

	@Override
	public String toString() {
		return "LiteralReal [valor=" + valor + "]";
	}

}
