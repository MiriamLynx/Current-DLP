package ast.sentencia;

import java.util.List;

import ast.AbstractAST;
import ast.expresion.Expresion;

public class If extends AbstractAST implements Sentencia {

	public Expresion expresion;
	public List<Sentencia> sentencias;
	public List<Sentencia> alternativas;

	public If(int linea, int columna, Expresion expresion,
			List<Sentencia> sentencias, List<Sentencia> alternativas) {
		super(linea, columna);
		this.expresion = expresion;
		this.sentencias = sentencias;
		this.alternativas = alternativas;
	}

	@Override
	public String toString() {
		return "IfElse [expresion=" + expresion + ", sentencias=" + sentencias
				+ ", alternativas=" + alternativas + "]";
	}

}