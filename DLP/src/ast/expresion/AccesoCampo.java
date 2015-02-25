package ast.expresion;

import ast.AbstractAST;

public class AccesoCampo extends AbstractAST implements Expresion {

	public String campo;
	public Expresion struct;

	public AccesoCampo(int linea, int columna, Expresion struct, String campo) {
		super(linea, columna);
		this.campo = campo;
		this.struct = struct;
	}

	@Override
	public String toString() {
		return "AccesoCampo [campo=" + campo + ", struct=" + struct + "]";
	}

}