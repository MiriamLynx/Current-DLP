package ast.tipo;

import ast.AST;
import ast.AbstractAST;
import ast.visitor.Visitor;

public class TipoError extends AbstractAST implements Tipo {

	public AST error;

	public TipoError(int linea, int columna, AST error) {
		super(linea, columna);
		this.error = error;
	}
	
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}

}