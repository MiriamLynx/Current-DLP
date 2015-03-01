package ast.tipo;

import java.util.List;

import ast.AbstractAST;

public class TipoArray extends AbstractAST implements Tipo {

	public List<Integer> tama�o;

	public TipoArray(int linea, int columna, List<Integer> tama�o) {
		super(linea, columna);
		this.tama�o = tama�o;
	}

}