package ast.sentencia;

import java.util.List;

import ast.AbstractAST;
import ast.expresion.Expresion;
import ast.visitor.Visitor;

public class While extends AbstractAST implements Sentencia {

	private List<Sentencia> sentencias;
	private Expresion expresion;

	public While(int linea, int columna, Expresion expresion,
			List<Sentencia> sentencias) {
		super(linea, columna);
		this.expresion = expresion;
		this.sentencias = sentencias;
	}

	@Override
	public String toString() {
		return "While [ Expresion " + expresion + " ]"
				+ "\nSentencias del while ->\n" + sentencias + "\n";
	}

	public void accept(Visitor visitor) {
		visitor.visit(this);
	}

	public List<Sentencia> getSentencias() {
		return sentencias;
	}

	public void setSentencias(List<Sentencia> sentencias) {
		this.sentencias = sentencias;
	}

	public Expresion getExpresion() {
		return expresion;
	}

	public void setExpresion(Expresion expresion) {
		this.expresion = expresion;
	}
}