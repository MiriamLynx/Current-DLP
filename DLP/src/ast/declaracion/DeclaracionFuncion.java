package ast.declaracion;

import java.util.List;

import ast.AbstractAST;
import ast.sentencia.Sentencia;
import ast.tipo.Tipo;

public class DeclaracionFuncion extends AbstractAST implements Declaracion {

	public Tipo retorno;
	public String nombre;
	public List<DeclaracionVariable> parametros;
	public List<DeclaracionVariable> definiciones;
	public List<Sentencia> sentencias;

	public DeclaracionFuncion(int linea, int columna, Tipo retorno,
			String nombre, List<DeclaracionVariable> parametros,
			List<DeclaracionVariable> definiciones, List<Sentencia> sentencias) {
		super(linea, columna);
		this.retorno = retorno;
		this.nombre = nombre;
		this.parametros = parametros;
		this.definiciones = definiciones;
		this.sentencias = sentencias;
	}

	@Override
	public String toString() {
		return "DefFuncion [retorno=" + retorno + ", nombre=" + nombre
				+ ", parametros=" + parametros + ", definiciones="
				+ definiciones + ", sentencias=" + sentencias + "]";
	}

}