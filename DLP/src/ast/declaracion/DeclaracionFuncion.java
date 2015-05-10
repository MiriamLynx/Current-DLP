package ast.declaracion;

import java.util.List;

import ast.AbstractAST;
import ast.sentencia.Sentencia;
import ast.tipo.Tipo;
import ast.visitor.Visitor;

public class DeclaracionFuncion extends AbstractAST implements Declaracion {

	public Tipo retorno;
	public String nombre;
	public List<DeclaracionVariable> parametros;
	public List<DeclaracionVariable> declaraciones;
	public List<Sentencia> sentencias;

	public DeclaracionFuncion(int linea, int columna, Tipo retorno,
			String nombre, List<DeclaracionVariable> parametros,
			List<DeclaracionVariable> declaraciones, List<Sentencia> sentencias) {
		super(linea, columna);
		this.retorno = retorno;
		this.nombre = nombre;
		this.parametros = parametros;
		this.declaraciones = declaraciones;
		this.sentencias = sentencias;
	}

	@Override
	public String toString() {
		return "Declaracion de funcion " + " \nParametros de la funcion->\n"
				+ parametros + "\nDeclaraciones de la funcion->\n "
				+ declaraciones + "\nSentencias de la funcion->\n "
				+ sentencias + "\n";
	}

	public void accept(Visitor visitor, Object param) {
		visitor.visit(this, param);
	}

	public Tipo getRetorno() {
		return retorno;
	}

	public void setRetorno(Tipo retorno) {
		this.retorno = retorno;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<DeclaracionVariable> getParametros() {
		return parametros;
	}

	public void setParametros(List<DeclaracionVariable> parametros) {
		this.parametros = parametros;
	}

	public List<DeclaracionVariable> getDeclaraciones() {
		return declaraciones;
	}

	public void setDeclaraciones(List<DeclaracionVariable> declaraciones) {
		this.declaraciones = declaraciones;
	}

	public List<Sentencia> getSentencias() {
		return sentencias;
	}

	public void setSentencias(List<Sentencia> sentencias) {
		this.sentencias = sentencias;
	}

}
