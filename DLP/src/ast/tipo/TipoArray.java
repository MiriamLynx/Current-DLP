package ast.tipo;

import java.util.List;

import ast.AbstractAST;
import ast.visitor.Visitor;

public class TipoArray extends AbstractAST implements Tipo {

	public List<Integer> sizes;
	public Tipo tipoBase;

	public TipoArray(int linea, int columna, List<Integer> size, Tipo tipo) {
		super(linea, columna);
		this.sizes = size;
		this.tipoBase = tipo;
	}

	@Override
	public String toString() {
		return "Tipo array [ " + tipoBase + " , " + sizes + " ]";
	}

	public void accept(Visitor visitor, Object param) {
		visitor.visit(this, param);
	}

	public List<Integer> getSizes() {
		return sizes;
	}

	public void setSizes(List<Integer> sizes) {
		this.sizes = sizes;
	}

	public Tipo getTipoBase() {
		return tipoBase;
	}

	public void setTipoBase(Tipo tipo) {
		this.tipoBase = tipo;
	}

	public int size() {
		int mul = 1;
		for (int i = 0; i < sizes.size(); i++) {
			mul *= sizes.get(i);
		}
		return tipoBase.size() * mul;
	}

	public String getMAPLname() {
		String cad = "";
		for (int i : sizes) {
			cad += "<" + i + "> *";
		}
		cad += tipoBase.getMAPLname();
		return cad;
	}

	public String getSufijo() {
		return "nope";
	}
}