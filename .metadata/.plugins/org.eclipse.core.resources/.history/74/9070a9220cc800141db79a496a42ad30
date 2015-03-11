package ast.tipo;

import ast.AbstractAST;

public class TipoReal extends AbstractAST implements Tipo {

	private static TipoReal instance;

	private TipoReal(int linea, int columna) {
		super(linea, columna);
	}

	public static TipoReal getInstance(int linea, int columna) {
		if (instance == null) {
			instance = new TipoReal(linea, columna);
		}
		return instance;
	}

	@Override
	public String toString() {
		return "Tipo real";
	}

}