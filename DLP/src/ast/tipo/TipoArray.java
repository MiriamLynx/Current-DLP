package ast.tipo;

import java.util.List;

import ast.AbstractAST;
import ast.visitor.Visitor;

public class TipoArray extends AbstractAST implements Tipo {

	private List<Integer> size;
	private Tipo tipo;

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
}