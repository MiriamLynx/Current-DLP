package ast.expresion;

import ast.tipo.Tipo;
import ast.visitor.Visitor;

public class Cast extends AbstractExpresion implements Expresion {

	public Tipo tipoBase;
	public Expresion casteo;

	public Cast(int linea, int columna, Tipo tipo, Expresion casteo) {
		super(linea, columna);
		this.tipoBase = tipo;
		this.casteo = casteo;
	}

	@Override
	public String toString() {
		return "Casteo [ " + tipoBase + " , " + casteo + " ] \n";
	}

	public void accept(Visitor visitor, Object param) {
		visitor.visit(this, param);
	}

	public Tipo getTipoBase() {
		return tipoBase;
	}

	public void setTipoBase(Tipo tipo) {
		this.tipoBase = tipo;
	}

	public Expresion getCasteo() {
		return casteo;
	}

	public void setCasteo(Expresion casteo) {
		this.casteo = casteo;
	}
}
