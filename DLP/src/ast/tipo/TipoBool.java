package ast.tipo;

import ast.AbstractAST;
import ast.visitor.Visitor;

public class TipoBool extends AbstractAST implements Tipo {

	private static TipoBool instance;

	private TipoBool(int linea, int columna) {
		super(linea, columna);
	}

	public static TipoBool getInstance(int linea, int columna) {
		if (instance == null) {
			instance = new TipoBool(linea, columna);
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