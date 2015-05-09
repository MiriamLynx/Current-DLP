package ast.sentencia;

import ast.expresion.Expresion;
import ast.visitor.Visitor;

public class Asignacion extends AbstractSentencia implements Sentencia {

	public Expresion izquierda;
	public Expresion derecha;

	public Asignacion(int linea, int columna, Expresion izquierda,
			Expresion derecha) {
		super(linea, columna);
		this.izquierda = izquierda;
		this.derecha = derecha;
	}

	@Override
	public String toString() {
		return "Asignacion [ " + izquierda + " , " + derecha + " ] \n";
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
}