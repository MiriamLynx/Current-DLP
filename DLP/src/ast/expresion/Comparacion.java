package ast.expresion;

import ast.visitor.Visitor;

public class Comparacion extends AbstractExpresion implements Expresion {

	public Expresion izquierda;
	public Expresion derecha;
	public String operador;

	public Comparacion(int linea, int columna, Expresion izquierda,
			String operador, Expresion derecha) {
		super(linea, columna);
		this.izquierda = izquierda;
		this.derecha = derecha;
		this.operador = operador;
	}

	@Override
	public String toString() {
		return "Comparacion [ " + izquierda + " " + operador + " " + derecha
				+ " ] ";
	}

	public void accept(Visitor visitor, Object param) {
		visitor.visit(this, null);
	}

	public Expresion getIzquierda() {
		return izquierda;
	}

	public void setIzquierda(Expresion izquierda) {
		this.izquierda = izquierda;
	}

	public Expresion getDerecha() {
		return derecha;
	}

	public void setDerecha(Expresion derecha) {
		this.derecha = derecha;
	}

	public String getOperador() {
		return operador;
	}

	public void setOperador(String operador) {
		this.operador = operador;
	}

}