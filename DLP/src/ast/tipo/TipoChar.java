package ast.tipo;

import ast.AbstractAST;
import ast.visitor.Visitor;

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

	public void accept(Visitor visitor, Object param) {
		visitor.visit(this, param);
	}

	public int size() {
		return 1;
	}

	public String getMAPLname() {
		return "char";
	}
	
	public String getSufijo() {
		return "b";
	}

}