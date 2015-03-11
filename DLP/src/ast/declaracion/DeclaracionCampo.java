package ast.declaracion;

import ast.AbstractAST;
import ast.tipo.Tipo;
import ast.visitor.Visitor;

public class DeclaracionCampo extends AbstractAST implements Declaracion {

	private String variable;
	private Tipo tipo;

	public DeclaracionCampo(int linea, int columna, Tipo tipo, String variable) {
		super(linea, columna);
		this.variable = variable;
		this.tipo = tipo;
	}

	@Override
	public String toString() {
		return "Campo [ " + variable + " , " + tipo + " ] ";
	}

	public void accept(Visitor visitor) {
		visitor.visit(this);
	}

	public String getVariable() {
		return variable;
	}

	public void setVariable(String variable) {
		this.variable = variable;
	}

	public Tipo getTipo() {
		return tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

}