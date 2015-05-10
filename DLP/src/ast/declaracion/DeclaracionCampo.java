package ast.declaracion;

import ast.AbstractAST;
import ast.tipo.Tipo;
import ast.visitor.Visitor;

public class DeclaracionCampo extends AbstractAST implements Declaracion {

	public String nombre;
	public Tipo tipo;
	public int direccion;

	public DeclaracionCampo(int linea, int columna, Tipo tipo, String variable) {
		super(linea, columna);
		this.nombre = variable;
		this.tipo = tipo;
	}

	@Override
	public String toString() {
		return "Campo [ " + nombre + " , " + tipo + " , " + "Dir: " + direccion
				+ " ] ";
	}

	public void accept(Visitor visitor, Object param) {
		visitor.visit(this, param);
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Tipo getTipo() {
		return tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

	public int getDireccion() {
		return direccion;
	}

	public void setDireccion(int direccion) {
		this.direccion = direccion;
	}

}
