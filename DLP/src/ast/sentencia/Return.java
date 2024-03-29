package ast.sentencia;

import ast.expresion.Expresion;
import ast.visitor.Visitor;

public class Return extends AbstractSentencia implements Sentencia {

	public Expresion expresion;

	public Return(int linea, int columna, Expresion expresion) {
		super(linea, columna);
		this.expresion = expresion;
	}

	@Override
	public String toString() {
		return "Return [ " + expresion + " ] \n";
	}

	public void accept(Visitor visitor, Object param) {
		visitor.visit(this, param);
	}

	public Expresion getExpresion() {
		return expresion;
	}

	public void setExpresion(Expresion expresion) {
		this.expresion = expresion;
	}

}