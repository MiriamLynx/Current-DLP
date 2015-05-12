package ast.sentencia;

import java.util.List;

import ast.expresion.Expresion;
import ast.visitor.Visitor;

public class If extends AbstractSentencia implements Sentencia {

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
		if (alternativas.size() > 0) {
			return "If Else [ " + expresion + " ]"
					+ "\n\t\tSentencias del cuerpo->\n" + sentencias()
					+ "\t\tSentencias de la alternativa->\n" + alternativas();
		} else {
			return "If [ " + expresion + " ]"
					+ "\nSentencias del cuerpo->\n" + sentencias();
		}
	}

	private String sentencias() {
		String s = "";
		for (Sentencia d : sentencias) {
			s += "\t\t\t" + d.toString();
		}

		return s;
	}

	private String alternativas() {
		String s = "";
		for (Sentencia d : alternativas) {
			s += "\t\t\t" + d.toString();
		}

		return s;
	}

	public void accept(Visitor visitor, Object param) {
		visitor.visit(this, param);
	}

	public Expresion getExpresion() {
		return expresion;
	}

	public void setExpresion(Expresion expresion) {
		this.expresion = expresion;
	}

	public List<Sentencia> getSentencias() {
		return sentencias;
	}

	public void setSentencias(List<Sentencia> sentencias) {
		this.sentencias = sentencias;
	}

	public List<Sentencia> getAlternativas() {
		return alternativas;
	}

	public void setAlternativas(List<Sentencia> alternativas) {
		this.alternativas = alternativas;
	}

}