package ast.tipo;

import ast.AbstractAST;
import ast.visitor.Visitor;

public class TipoEntero extends AbstractAST implements Tipo {

	private static TipoEntero instance;

	private TipoEntero(int linea, int columna) {
		super(linea, columna);
	}

	public static TipoEntero getInstance(int linea, int columna) {
		if (instance == null) {
			instance = new TipoEntero(linea, columna);
		}
		return instance;
	}

	@Override
	public String toString() {
		return "Tipo entero";
	}

	public void accept(Visitor visitor, Object param) {
		visitor.visit(this, param);
	}

	public int size() {
		return 2;
	}

	public String getMAPLname() {
		return "int";
	}
	
	public String getSufijo() {
		return "i";
	}
}