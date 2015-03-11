package ast.sentencia;

import ast.AbstractAST;
import ast.expresion.Expresion;
import ast.visitor.Visitor;

public class Print extends AbstractAST implements Sentencia {

	private Expresion expresion;

	public Print(int linea, int columna, Expresion expresion) {
		super(linea, columna);
		this.expresion = expresion;
	}

	@Override
	public String toString() {
		return "Escritura [ " + expresion + " ] \n";
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

}