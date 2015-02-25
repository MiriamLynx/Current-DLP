package ast.declaracion;

import java.util.List;

import ast.AbstractAST;

public class DeclaracionStruct extends AbstractAST implements Declaracion {

	public String nombre;
	public List<DeclaracionVariable> declaraciones;

	public DeclaracionStruct(int linea, int columna, String nombre,
			List<DeclaracionVariable> declaraciones) {
		super(linea, columna);
		this.nombre = nombre;
		this.declaraciones = declaraciones;
	}

	@Override
	public String toString() {
		return "DeclaracionStruct [nombre=" + nombre + ", Declaraciones="
				+ declaraciones + "]";
	}

}
