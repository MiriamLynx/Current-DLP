package ast.tipo;

import java.util.List;

import ast.AbstractAST;

public class TipoArray extends AbstractAST implements Tipo {

	public List<Integer> size;
	public Tipo tipo;

	public TipoArray(int linea, int columna, List<Integer> size, Tipo tipo) {
		super(linea, columna);
		this.size = size;
		this.tipo = tipo;
	}

	@Override
	public String toString() {
		return "Tipo array [ " + tipo + " , " + size + " ]";
	}

}