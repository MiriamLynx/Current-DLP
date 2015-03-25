package ast.expresion;

import ast.AST;
import ast.tipo.Tipo;

public interface Expresion extends AST {

	public boolean isLvalue();

	public Tipo getTipo();

}
