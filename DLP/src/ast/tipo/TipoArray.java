package ast.tipo;

import java.util.List;

import ast.AbstractAST;
import ast.visitor.Visitor;

public class TipoArray extends AbstractAST implements Tipo {

	public List<Integer> size;
	public Tipo tipoBase;

	public TipoArray(int linea, int columna, List<Integer> size, Tipo tipo) {
		super(linea, columna);
		this.size = size;
		this.tipoBase = tipo;
	}

	@Override
	public String toString() {
		return "Tipo array [ " + tipoBase + " , " + size + " ]";
	}

	public void accept(Visitor visitor) {
		visitor.visit(this);
	}

	public List<Integer> getSize() {
		return size;
	}

	public void setSize(List<Integer> size) {
		this.size = size;
	}

	public Tipo getTipoBase() {
		return tipoBase;
	}

	public void setTipoBase(Tipo tipo) {
		this.tipoBase = tipo;
	}
}