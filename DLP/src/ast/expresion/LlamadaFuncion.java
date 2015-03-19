package ast.expresion;

import java.util.List;

import ast.AbstractAST;
import ast.declaracion.DeclaracionFuncion;
import ast.visitor.Visitor;

public class LlamadaFuncion extends AbstractAST implements Expresion {

	public String nombre;
	public List<Expresion> expresiones;
	public DeclaracionFuncion declaracion;

	public DeclaracionFuncion getDeclaracion() {
		return declaracion;
	}

	public void setDeclaracion(DeclaracionFuncion declaracion) {
		this.declaracion = declaracion;
	}

	public LlamadaFuncion(int linea, int columna, String nombre,
			List<Expresion> expresiones) {
		super(linea, columna);
		this.nombre = nombre;
		this.expresiones = expresiones;
	}

	@Override
	public String toString() {
		return "Llamada a funcion (expresion) [ " + nombre + " ]"
				+ "\nParametros de la llamada ->\n" + expresiones + "\n";
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