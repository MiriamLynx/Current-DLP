package ast;

import ast.visitor.Visitor;

public interface AST {

	public int getLinea();

	public int getColumna();

	public void accept(Visitor visitor, Object object);

}
