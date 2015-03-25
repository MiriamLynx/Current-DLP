package ast.expresion;

import ast.AbstractAST;
import ast.tipo.Tipo;

public abstract class AbstractExpresion extends AbstractAST {

	public AbstractExpresion(int linea, int columna) {
		super(linea, columna);
	}

	public Tipo tipo;
	public boolean lvalue;

	public Tipo getTipo() {
		return tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

	public boolean isLvalue() {
		return lvalue;
	}

	public void setLvalue(boolean lvalue) {
		this.lvalue = lvalue;
	}

}
