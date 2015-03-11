package ast.tipo;

import ast.AbstractAST;

public class TipoChar extends AbstractAST implements Tipo {

	private static TipoChar instance;

	private TipoChar(int linea, int columna) {
		super(linea, columna);
	}

	public static TipoChar getInstance(int linea, int columna) {
		if (instance == null) {
			instance = new TipoChar(linea, columna);
		}
		return instance;
	}
	
	@Override
	public String toString() {
		return "Tipo caracter";
	}

}