package ast.expresion;

import ast.visitor.Visitor;

public class Incremento extends AbstractExpresion implements Expresion {

	public Expresion expresion;

	public Incremento(int linea, int columna, Expresion expresion) {
		super(linea, columna);
		this.setExpresion(expresion);
	}

	public void accept(Visitor visitor, Object param) {
		visitor.visit(this, param);
	}

	@Override
	public String toString() {
		return "Incremento [ " + expresion + " ] ";
	}

	public Expresion getExpresion() {
		return expresion;
	}

	public void setExpresion(Expresion expresion) {
		this.expresion = expresion;
	}

}
