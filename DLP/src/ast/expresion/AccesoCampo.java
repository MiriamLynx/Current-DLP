package ast.expresion;

import ast.visitor.Visitor;

public class AccesoCampo extends AbstractExpresion implements Expresion {

	public String campo;
	public Expresion struct;

	public AccesoCampo(int linea, int columna, Expresion struct, String campo) {
		super(linea, columna);
		this.campo = campo;
		this.struct = struct;
	}

	@Override
	public String toString() {
		return "Acceso a campo [ " + struct + " , " + campo + " ] ";
	}

	public void accept(Visitor visitor, Object param) {
		visitor.visit(this, param);
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