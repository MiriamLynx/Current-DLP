package ast.sentencia;

import ast.AbstractAST;
import ast.expresion.Expresion;

public class Asignacion extends AbstractAST implements Sentencia {

	private Expresion izquierda;
	private Expresion derecha;

	public Asignacion(int linea, int columna, Expresion izquierda,
			Expresion derecha) {
		super(linea,columna);
		this.izquierda = izquierda;
		this.derecha = derecha;
	}


	@Override
	public String toString() {
		return "Asignacion [izquierda=" + izquierda + ", derecha=" + derecha
				+ "]";
	}

}