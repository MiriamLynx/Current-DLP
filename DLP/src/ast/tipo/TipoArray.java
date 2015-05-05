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

	public int size() {
		int mul = 1;
		for (int i = 0; i < size.size(); i++) {
			mul *= size.get(i);
		}
		return tipoBase.size() * mul;
	}

	public String getMAPLname() {
		String cad = "";
		for (int i : size) {
			cad += "<" + i + "> *";
		}
		cad += tipoBase.getMAPLname();
		return cad;
	}

	public String getSufijo() {
		return "nope";
	}
}