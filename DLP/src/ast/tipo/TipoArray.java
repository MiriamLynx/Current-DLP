package ast.tipo;

import java.util.List;

import ast.AbstractAST;

public class TipoArray extends AbstractAST implements Tipo {

	public List<Integer> tamaño;

	public TipoArray(int linea, int columna, List<Integer> tamaño) {
		super(linea, columna);
		this.tamaño = tamaño;
	}

}