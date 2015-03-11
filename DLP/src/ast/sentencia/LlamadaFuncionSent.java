package ast.sentencia;

import java.util.List;

import ast.AbstractAST;
import ast.expresion.Expresion;
import ast.visitor.Visitor;

public class LlamadaFuncionSent extends AbstractAST implements Sentencia {

	private String nombre;
	private List<Expresion> expresiones;

	public LlamadaFuncionSent(int linea, int columna, String nombre,
			List<Expresion> expresiones) {
		super(linea, columna);
		this.nombre = nombre;
		this.expresiones = expresiones;
	}

	@Override
	public String toString() {
		return "Llamada a funcion (sentencia) [ " + nombre + " ]"
				+ "\nParametros de la llamada->\n" + expresiones + "\n";
	}
	
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Expresion> getExpresiones() {
		return expresiones;
	}

	public void setExpresiones(List<Expresion> expresiones) {
		this.expresiones = expresiones;
	}

}