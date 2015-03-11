package ast.declaracion;

import java.util.List;

import ast.AbstractAST;
import ast.sentencia.Sentencia;
import ast.tipo.Tipo;

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

}
