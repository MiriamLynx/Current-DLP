package ast.expresion;

import ast.visitor.Visitor;

public class ConstanteEntera extends AbstractExpresion implements Expresion {

	public int valor;

	public ConstanteEntera(int linea, int columna, int valor) {
		super(linea, columna);
		this.valor = valor;
	}

	@Override
	public String toString() {
		return "Constante entera [ " + valor + " ] ";
	}

	public void accept(Visitor visitor, Object param) {
		visitor.visit(this, null);
	}

	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}

}