package ast.tipo;

import ast.AbstractAST;
import ast.visitor.Visitor;

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

	public void accept(Visitor visitor, Object param) {
		visitor.visit(this, null);
	}

	public int size() {
		return 4;
	}

	public String getMAPLname() {
		return "real";
	}

	public String getSufijo() {
		return "f";
	}
}