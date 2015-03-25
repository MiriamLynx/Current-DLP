package ast.expresion;

import ast.tipo.Tipo;
import ast.visitor.Visitor;

public class Cast extends AbstractExpresion implements Expresion {

	public Tipo tipo;
	public Expresion casteo;

	public Cast(int linea, int columna, Tipo tipo, Expresion casteo) {
		super(linea, columna);
		this.tipo = tipo;
		this.casteo = casteo;
	}

	@Override
	public String toString() {
		return "Casteo [ " + tipo + " , " + casteo + " ] \n";
	}

	public void accept(Visitor visitor) {
		visitor.visit(this);
	}

	public Tipo getTipo() {
		return tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

	public Expresion getCasteo() {
		return casteo;
	}

	public void setCasteo(Expresion casteo) {
		this.casteo = casteo;
	}
}
