package ast;

public abstract class AbstractAST implements AST {

	public int linea;
	public int columna;

	public AbstractAST(int linea, int columna) {
		this.linea = linea;
		this.columna = columna;
	}

	public int getLinea() {
		return this.linea;
	}

	public int getColumna() {

		return this.columna;
	}
}