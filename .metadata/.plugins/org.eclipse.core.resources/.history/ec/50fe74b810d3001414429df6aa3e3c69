package ast.tipo;

import ast.AST;
import ast.AbstractAST;
import ast.visitor.Visitor;

public class TipoError extends AbstractAST implements Tipo {

	public AST nodo;
	public String error;

	public TipoError(AST nodo, String error) {
		super(nodo.getLinea(), nodo.getColumna());
		this.nodo = nodo;
		this.error = error;
	}

	public void accept(Visitor visitor) {
		visitor.visit(this);
	}

	@Override
	public String toString() {
		return "Error semántico en la línea " + nodo.getLinea() + " y columna "
				+ nodo.getColumna() + " : " + error;
	}

}