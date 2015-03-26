package ast.sentencia;

import java.util.List;

import ast.AbstractAST;
import ast.declaracion.DeclaracionFuncion;
import ast.expresion.Expresion;
import ast.visitor.Visitor;

public class While extends AbstractAST implements Sentencia {

	public List<Sentencia> sentencias;
	public Expresion expresion;
	public DeclaracionFuncion funcion;

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

	public DeclaracionFuncion getFuncion() {
		return funcion;
	}

	public void setFuncion(DeclaracionFuncion funcion) {
		this.funcion = funcion;
	}
}