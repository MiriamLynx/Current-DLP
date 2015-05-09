package ast.sentencia;

import ast.expresion.Expresion;
import ast.visitor.Visitor;

public class Read extends AbstractSentencia implements Sentencia {

	public Expresion expresion;

	public Read(int linea, int columna, Expresion expresion) {
		super(linea, columna);
		this.expresion = expresion;
	}

	@Override
	public String toString() {
		return "Lectura [ " + expresion + " ] \n";
	}

	public void accept(Visitor visitor, Object param) {
		visitor.visit(this, null);
	}

	public Expresion getExpresion() {
		return expresion;
	}

	public void setExpresion(Expresion expresion) {
		this.expresion = expresion;
	}
}