package ast.expresion;

import java.util.List;

import ast.visitor.Visitor;

public class AccesoArray extends AbstractExpresion implements Expresion {

	public Expresion array;
	public List<Expresion> index;

	public AccesoArray(int linea, int columna, Expresion array,
			List<Expresion> index) {
		super(linea, columna);
		this.index = index;
		this.array = array;
	}

	@Override
	public String toString() {
		return "Acceso a array [ " + array + " , " + index + " ] ";
	}

	public void accept(Visitor visitor, Object param) {
		visitor.visit(this, param);
	}

	public List<Expresion> getIndex() {
		return index;
	}

	public void setIndex(List<Expresion> index) {
		this.index = index;
	}

	public Expresion getArray() {
		return array;
	}

	public void setArray(Expresion array) {
		this.array = array;
	}
}