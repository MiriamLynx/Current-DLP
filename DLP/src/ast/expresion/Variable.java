package ast.expresion;

import ast.AbstractAST;
import ast.visitor.Visitor;

public class Variable extends AbstractAST implements Expresion {

	public String nombre;

	public Variable(int linea, int columna, String nombre) {
		super(linea, columna);
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "Variable [ " + nombre + " ] ";
	}

	public void accept(Visitor visitor) {
		visitor.visit(this);
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
