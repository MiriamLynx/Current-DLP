package ast.expresion;

import ast.visitor.Visitor;

public class ConstanteReal extends AbstractExpresion implements Expresion {

	public double valor;

	public ConstanteReal(int linea, int columna, double valor) {
		super(linea, columna);
		this.valor = valor;
	}

	@Override
	public String toString() {
		return "Constante real [ " + valor + " ] ";
	}

	public void accept(Visitor visitor, Object param) {
		visitor.visit(this, null);
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

}
