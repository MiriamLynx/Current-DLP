package ast.expresion;

import ast.visitor.Visitor;

public class NotLogico extends AbstractExpresion implements Expresion {

	public Expresion expresion;

	public NotLogico(int linea, int columna, Expresion expresion) {
		super(linea, columna);
		this.setExpresion(expresion);
	}

	public void accept(Visitor visitor) {
		visitor.visit(this);
	}

	@Override
	public String toString() {
		return "Not logico [ " + expresion + " ] ";
	}

	public Expresion getExpresion() {
		return expresion;
	}

	public void setExpresion(Expresion expresion) {
		this.expresion = expresion;
	}

}
