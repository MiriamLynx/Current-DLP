package ast.declaracion;

import java.util.List;

import ast.AbstractAST;
import ast.tipo.Tipo;
import ast.visitor.Visitor;

public class DeclaracionStruct extends AbstractAST implements Declaracion, Tipo {

	public String nombre;
	public List<DeclaracionCampo> declaraciones;
	public int direccion;

	public DeclaracionStruct(int linea, int columna, String nombre,
			List<DeclaracionCampo> declaraciones) {
		super(linea, columna);
		this.nombre = nombre;
		this.declaraciones = declaraciones;
	}

	public DeclaracionCampo getCampo(String campo) {
		for (DeclaracionCampo c : declaraciones) {
			if (c.getNombre().equals(campo)) {
				return c;
			}
		}
		return null;
	}

	@Override
	public String toString() {
		return "Declaracion de struct [ " + nombre + " , " + "Dir: "
				+ direccion + " ]" + "\nLista de Campos ->\n" + declaraciones
				+ "\n";
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

	public List<DeclaracionCampo> getDeclaraciones() {
		return declaraciones;
	}

	public void setDeclaraciones(List<DeclaracionCampo> declaraciones) {
		this.declaraciones = declaraciones;
	}

	public int size() {
		int sum = 0;
		for (DeclaracionCampo c : declaraciones) {
			sum += c.getTipo().size();
		}
		return sum;
	}

	public int getDireccion() {
		return direccion;
	}

	public void setDireccion(int direccion) {
		this.direccion = direccion;
	}

	public String getMAPLname() {
		return getNombre();
	}

	public String getSufijo() {
		return "nope";
	}

}
