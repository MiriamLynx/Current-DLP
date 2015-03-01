package ast.declaracion;

import java.util.List;

import ast.AbstractAST;
import ast.tipo.Tipo;

public class DeclaracionVariable extends AbstractAST implements Declaracion {

	public List<String> variables;
	public Tipo tipo;

	public DeclaracionVariable(int linea, int columna, Tipo tipo,
			List<String> variables) {
		super(linea, columna);
		this.variables = variables;
		this.tipo = tipo;
	}

	@Override
	public String toString() {
		return "DefVariable [variables=" + variables + ", tipo=" + tipo + "]";
	}

}