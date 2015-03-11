package ast.expresion;

import ast.AbstractAST;
import ast.visitor.Visitor;

public class ConstanteReal extends AbstractAST implements Expresion {

	public double valor;

	public ConstanteReal(int linea, int columna, double valor) {
		super(linea, columna);
		this.valor = valor;
	}

	@Override
	public String toString() {
		return "Constante real [ " + valor + " ] ";
	}
	
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

}
