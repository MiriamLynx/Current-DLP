package ast.declaracion;

import java.util.List;

import ast.AbstractAST;

public class DeclaracionStruct extends AbstractAST implements Declaracion {

	public String nombre;
	public List<DeclaracionVariable> definiciones;

	public DeclaracionStruct(int linea, int columna, String nombre,
			List<DeclaracionVariable> definiciones) {
		super(linea, columna);
		this.nombre = nombre;
		this.definiciones = definiciones;
	}

	@Override
	public String toString() {
		return "DefStruct [nombre=" + nombre + ", definiciones=" + definiciones
				+ "]";
	}

}
