package ast;

import java.util.List;

import ast.declaracion.Declaracion;
import ast.visitor.Visitor;

public class Programa extends AbstractAST {

	public List<Declaracion> declaraciones;

	public List<Declaracion> getDeclaraciones() {
		return declaraciones;
	}

	public void setDeclaraciones(List<Declaracion> declaraciones) {
		this.declaraciones = declaraciones;
	}

	public Programa(int linea, int columna, List<Declaracion> declaraciones) {
		super(linea, columna);
		this.declaraciones = declaraciones;
	}

	@Override
	public String toString() {
		return "Declaraciones del programa ->\n" + declaraciones + "\n";
	}

	public void accept(Visitor visitor) {
		visitor.visit(this);
	}

}
