package ast.sentencia;

import ast.AbstractAST;
import ast.declaracion.DeclaracionFuncion;
import ast.expresion.Expresion;
import ast.visitor.Visitor;

public class Return extends AbstractAST implements Sentencia {

	public Expresion expresion;
	public DeclaracionFuncion funcion;

	public Return(int linea, int columna, Expresion expresion) {
		super(linea, columna);
		this.expresion = expresion;
	}

	@Override
	public String toString() {
		return "Return [ " + expresion + " ] \n";
	}

	public void accept(Visitor visitor) {
		visitor.visit(this);
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