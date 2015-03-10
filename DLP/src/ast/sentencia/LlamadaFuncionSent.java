package ast.sentencia;

import java.util.List;

import ast.AbstractAST;
import ast.expresion.Expresion;

public class LlamadaFuncionSent extends AbstractAST implements Sentencia {

	public String nombre;
	public List<Expresion> expresiones;

	public LlamadaFuncionSent(int linea, int columna, String nombre,
			List<Expresion> expresiones) {
		super(linea, columna);
		this.nombre = nombre;
		this.expresiones = expresiones;
	}

	@Override
	public String toString() {
		return "LlamadaProcedimiento [nombre=" + nombre + ", expresiones="
				+ expresiones + "]";
	}

}