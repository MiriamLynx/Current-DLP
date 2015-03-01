package ast.sentencia;

import java.util.List;

import ast.AbstractAST;
import ast.expresion.Expresion;

public class Read extends AbstractAST implements Sentencia {

	public List<Expresion> expresiones;

	public Read(int linea, int columna, List<Expresion> expresiones) {
		super(linea, columna);
		this.expresiones = expresiones;
	}

	@Override
	public String toString() {
		return "Lectura [expresiones=" + expresiones + "]";
	}

}