package ast.declaracion;

import ast.AbstractAST;
import ast.tipo.Tipo;
import ast.visitor.Visitor;

public class DeclaracionVariable extends AbstractAST implements Declaracion {

	public String nombre;
	public Tipo tipo;
	public int direccion;
	public String ambito = "global";

	public DeclaracionVariable(int linea, int columna, Tipo tipo, String nombre) {
		super(linea, columna);
		this.nombre = nombre;
		this.tipo = tipo;
	}

	@Override
	public String toString() {
		return "Declaracion de variable [ " + nombre + " , " + tipo + " , "
				+ "Dir: " + direccion + " ] \n";
	}

	public void accept(Visitor visitor, Object param) {
		visitor.visit(this, null);
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

	public String getAmbito() {
		return ambito;
	}

	public void setAmbito(String ambito) {
		this.ambito = ambito;
	}

}
