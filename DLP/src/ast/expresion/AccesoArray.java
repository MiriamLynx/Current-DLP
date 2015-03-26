package ast.expresion;

import ast.visitor.Visitor;

public class AccesoArray extends AbstractExpresion implements Expresion {

	public Expresion array;
	public Expresion index;

	public AccesoArray(int linea, int columna, Expresion array, Expresion index) {
		super(linea, columna);
		this.index = index;
		this.array = array;
	}

	@Override
	public String toString() {
		return "Acceso a array [ " + array + " , " + index + " ] ";
	}

	public void accept(Visitor visitor) {
		visitor.visit(this);
	}

	public Expresion getIndex() {
		return index;
	}

	public void setIndex(Expresion index) {
		this.index = index;
	}

	public Expresion getArray() {
		return array;
	}

	public void setArray(Expresion array) {
		this.array = array;
	}
}