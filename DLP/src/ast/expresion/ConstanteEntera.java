package ast.expresion;

import ast.AbstractAST;
import ast.visitor.Visitor;

public class ConstanteEntera extends AbstractAST implements Expresion {

	private int valor;

	public ConstanteEntera(int linea, int columna, int valor) {
		super(linea, columna);
		this.valor = valor;
	}

	@Override
	public String toString() {
		return "Constante entera [ " + valor + " ] ";
	}
	
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}

	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}

}