package ast.declaracion;

import ast.AbstractAST;
import ast.tipo.Tipo;

public class DeclaracionVariable extends AbstractAST implements Declaracion {

	public String variable;
	public Tipo tipo;

	public DeclaracionVariable(int linea, int columna, Tipo tipo,
			String variable) {
		super(linea, columna);
		this.variable = variable;
		this.tipo = tipo;
	}

	@Override
	public String toString() {
		return "DefVariable [variable=" + variable + ", tipo=" + tipo + "]";
	}

}
