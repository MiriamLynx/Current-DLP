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
					+ "\nSentencias del cuerpo->\n" + sentencias + "\n"
					+ "\nSentencias de la alternativa->\n" + alternativas
					+ "\n";
		} else {
			return "If Else [ " + expresion + " ]"
					+ "\nSentencias del cuerpo->\n" + sentencias + "\n" + "\n";
		}
	}

	public void accept(Visitor visitor, Object param) {
		visitor.visit(this, null);
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