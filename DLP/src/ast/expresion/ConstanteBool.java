package ast.expresion;

import ast.visitor.Visitor;

public class ConstanteBool extends AbstractExpresion implements Expresion {

	public int valor;

	public ConstanteBool(int linea, int columna, int valor) {
		super(linea, columna);
		this.valor = valor;
	}

	@Override
	public String toString() {
		return "Constante bool [ " + valor + " ] ";
	}

	public void accept(Visitor visitor, Object param) {
		visitor.visit(this, param);
	}

	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}

}