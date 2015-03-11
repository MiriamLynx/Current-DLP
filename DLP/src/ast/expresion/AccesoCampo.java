package ast.expresion;

import ast.AbstractAST;
import ast.visitor.Visitor;

public class AccesoCampo extends AbstractAST implements Expresion {

	private String campo;
	private Expresion struct;

	public AccesoCampo(int linea, int columna, Expresion struct, String campo) {
		super(linea, columna);
		this.campo = campo;
		this.struct = struct;
	}

	@Override
	public String toString() {
		return "Acceso a campo [ " + struct + " , " + campo + " ] ";
	}
	
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}

	public String getCampo() {
		return campo;
	}

	public void setCampo(String campo) {
		this.campo = campo;
	}

	public Expresion getStruct() {
		return struct;
	}

	public void setStruct(Expresion struct) {
		this.struct = struct;
	}

}