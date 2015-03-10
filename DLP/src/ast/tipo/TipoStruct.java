package ast.tipo;

import ast.AbstractAST;

public class TipoStruct extends AbstractAST implements Tipo {

	public String nombre;

	public TipoStruct(int linea, int columna, String nombre) {
		super(linea, columna);
		this.nombre = nombre;
	}

}