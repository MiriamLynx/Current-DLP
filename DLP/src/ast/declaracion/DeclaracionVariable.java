package ast.declaracion;

import ast.AbstractAST;
import ast.tipo.Tipo;

public class DeclaracionVariable extends AbstractAST implements Declaracion {

	public String nombre;
	public Tipo tipo;

	public DeclaracionVariable(int linea, int columna, Tipo tipo, String nombre) {
		super(linea, columna);
		this.nombre = nombre;
		this.tipo = tipo;
	}

	@Override
	public String toString() {
		return "Declaracion de variable [ " + nombre + " , " + tipo + " ] \n";
	}

}
