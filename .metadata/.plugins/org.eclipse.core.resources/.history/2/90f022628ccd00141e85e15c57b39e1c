package ast.tipo;

import ast.AbstractAST;
import ast.visitor.Visitor;

public class TipoStruct extends AbstractAST implements Tipo {

	private String nombre;

	public TipoStruct(int linea, int columna, String nombre) {
		super(linea, columna);
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "Tipo struct [ " + nombre + " ]";
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

}