package ast.tipo;

import java.util.List;

import ast.AbstractAST;
import ast.visitor.Visitor;

public class TipoArray extends AbstractAST implements Tipo {

	public List<Integer> size;
	public Tipo tipo;

	public TipoArray(int linea, int columna, List<Integer> size, Tipo tipo) {
		super(linea, columna);
		this.size = size;
		this.tipo = tipo;
	}

	@Override
	public String toString() {
		return "Tipo array [ " + tipo + " , " + size + " ]";
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

	public Tipo getTipo() {
		return tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

	public int size() {
		int mul = 1;
		for (int i = 0; i < size.size(); i++) {
			mul *= size.get(i);
		}
		return tipo.size() * mul;
	}

	public String getMAPLname() {
		String cad = "";
		for (int i : size) {
			cad += "<" + i + "> *";
		}
		cad += tipo.getMAPLname();
		return cad;
	}
	
	public String getSufijo() {
		return "nope";
	}
}