package ast.sentencia;

import java.util.List;

import ast.AbstractAST;
import ast.expresion.Expresion;

public class While extends AbstractAST implements Sentencia {

	public List<Sentencia> sentencias;
	public Expresion expresion;

	public While(int linea, int columna, Expresion expresion,
			List<Sentencia> sentencias) {
		super(linea, columna);
		this.expresion = expresion;
		this.sentencias = sentencias;
	}

	@Override
	public String toString() {
		return "While [sentencias=" + sentencias + ", expresion=" + expresion
				+ "]";
	}
}