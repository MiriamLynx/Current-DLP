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

	public void accept(Visitor visitor, Object param) {
		visitor.visit(this, param);
	}

	@Override
	public String toString() {
		return "Error semántico en línea " + nodo.getLinea() + " y columna "
				+ nodo.getColumna() + " : " + error;
	}

	public int size() {
		return 0;
	}

	public String getMAPLname() {
		return "error";
	}

	public String getSufijo() {
		return "error";
	}

}