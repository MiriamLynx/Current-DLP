package ast.expresion;

import ast.AbstractAST;
import ast.tipo.Tipo;

public class Cast extends AbstractAST implements Expresion {

	public Tipo tipo;
	public Expresion casteo;

	public Cast(int linea, int columna, Tipo tipo, Expresion casteo) {
		super(linea, columna);
		this.tipo = tipo;
		this.casteo = casteo;
	}

	@Override
	public String toString() {
		return "Cast [tipo=" + tipo + ", casteo=" + casteo + "]";
	}
}
