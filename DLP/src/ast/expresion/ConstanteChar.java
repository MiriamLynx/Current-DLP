package ast.expresion;

import ast.visitor.Visitor;

public class ConstanteChar extends AbstractExpresion implements Expresion {

	public char valor;

	public ConstanteChar(int linea, int columna, char valor) {
		super(linea, columna);
		this.valor = valor;
	}

	@Override
	public String toString() {
		return "Constante caracter [ " + valor + " ] ";
	}

	public void accept(Visitor visitor, Object param) {
		visitor.visit(this, param);
	}

	public char getValor() {
		return valor;
	}

	public void setValor(char valor) {
		this.valor = valor;
	}

}