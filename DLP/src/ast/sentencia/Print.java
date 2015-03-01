package ast.sentencia;

import java.util.List;

import ast.AbstractAST;
import ast.expresion.Expresion;

public class Print extends AbstractAST implements Sentencia {

	public List<Expresion> expresiones;

	public Print(int linea, int columna, List<Expresion> expresiones) {
		super(linea, columna);
		this.expresiones = expresiones;
	}

	@Override
	public String toString() {
		return "Escritura [expresiones=" + expresiones + "]";
	}

}