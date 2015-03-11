package ast;

import java.util.List;

import ast.declaracion.Declaracion;

public class Programa extends AbstractAST {

	public List<Declaracion> declaraciones;

	public Programa(int linea, int columna, List<Declaracion> declaraciones) {
		super(linea, columna);
		this.declaraciones = declaraciones;
	}

	@Override
	public String toString() {
		return "Declaraciones del programa ->\n" + declaraciones + "\n";
	}

}
