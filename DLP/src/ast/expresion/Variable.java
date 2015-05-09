package ast.expresion;

import ast.declaracion.DeclaracionVariable;
import ast.visitor.Visitor;

public class Variable extends AbstractExpresion implements Expresion {

	public String nombre;
	public DeclaracionVariable declaracion;

	public DeclaracionVariable getDeclaracion() {
		return declaracion;
	}

	public void setDeclaracion(DeclaracionVariable declaracion) {
		this.declaracion = declaracion;
	}

	public Variable(int linea, int columna, String nombre) {
		super(linea, columna);
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "Variable [ " + nombre + " ] ";
	}

	public void accept(Visitor visitor, Object param) {
		visitor.visit(this, null);
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
