package ast.visitor;

import java.util.List;

import ast.AST;
import ast.Programa;
import ast.declaracion.DeclaracionCampo;
import ast.declaracion.DeclaracionFuncion;
import ast.declaracion.DeclaracionStruct;
import ast.declaracion.DeclaracionVariable;
import ast.expresion.AccesoArray;
import ast.expresion.AccesoCampo;
import ast.expresion.Cast;
import ast.expresion.Comparacion;
import ast.expresion.ConstanteChar;
import ast.expresion.ConstanteEntera;
import ast.expresion.ConstanteReal;
import ast.expresion.LlamadaFuncion;
import ast.expresion.NotLogico;
import ast.expresion.OperacionAritmetica;
import ast.expresion.OperacionLogica;
import ast.expresion.Variable;
import ast.sentencia.Asignacion;
import ast.sentencia.If;
import ast.sentencia.LlamadaFuncionSent;
import ast.sentencia.Print;
import ast.sentencia.Read;
import ast.sentencia.Return;
import ast.sentencia.While;
import ast.tipo.TipoArray;
import ast.tipo.TipoChar;
import ast.tipo.TipoEntero;
import ast.tipo.TipoError;
import ast.tipo.TipoReal;
import ast.tipo.TipoStruct;

public class AbstractVisitor implements Visitor {

	public Object visit(Programa programa) {
		visitAll(programa.getDeclaraciones());
		return null;
	}

	public Object visit(DeclaracionCampo declaracionCampo) {
		declaracionCampo.getTipo().accept(this);
		return null;
	}

	public Object visit(DeclaracionFuncion declaracionFuncion) {
		if (declaracionFuncion.getRetorno() != null) {
			declaracionFuncion.getRetorno().accept(this);
		}
		visitAll(declaracionFuncion.getParametros());
		visitAll(declaracionFuncion.getDeclaraciones());
		visitAll(declaracionFuncion.getSentencias());
		return null;
	}

	public Object visit(DeclaracionStruct declaracionStruct) {
		visitAll(declaracionStruct.getDeclaraciones());
		return null;
	}

	public Object visit(DeclaracionVariable declaracionVariable) {
		declaracionVariable.getTipo().accept(this);
		return null;
	}

	public Object visit(AccesoArray accesoArray) {
		accesoArray.getIndex().accept(this);
		accesoArray.getArray().accept(this);
		return null;
	}

	public Object visit(AccesoCampo accesoCampo) {
		accesoCampo.getStruct().accept(this);
		return null;
	}

	public Object visit(Cast cast) {
		cast.getTipo().accept(this);
		cast.getCasteo().accept(this);
		return null;
	}

	public Object visit(Comparacion comparacion) {
		comparacion.getIzquierda().accept(this);
		comparacion.getDerecha().accept(this);
		return null;
	}

	public Object visit(ConstanteChar constanteChar) {
		return null;
	}

	public Object visit(ConstanteEntera constanteEntera) {
		return null;
	}

	public Object visit(ConstanteReal constanteReal) {
		return null;
	}

	public Object visit(LlamadaFuncion llamadaFuncion) {
		visitAll(llamadaFuncion.getExpresiones());
		return null;
	}

	public Object visit(OperacionAritmetica operacionAritmetica) {
		operacionAritmetica.getIzquierda().accept(this);
		operacionAritmetica.getDerecha().accept(this);
		return null;
	}

	public Object visit(OperacionLogica operacionLogica) {
		operacionLogica.getIzquierda().accept(this);
		operacionLogica.getDerecha().accept(this);
		return null;
	}

	public Object visit(Variable variable) {
		return null;
	}

	public Object visit(NotLogico notLogico) {
		notLogico.getExpresion().accept(this);
		return null;
	}

	public Object visit(Asignacion asignacion) {
		asignacion.getIzquierda().accept(this);
		asignacion.getDerecha().accept(this);
		return null;
	}

	public Object visit(If sentIf) {
		sentIf.getExpresion().accept(this);
		visitAll(sentIf.getSentencias());
		visitAll(sentIf.getAlternativas());
		return null;
	}

	public Object visit(LlamadaFuncionSent llamadaFuncionSent) {
		visitAll(llamadaFuncionSent.getExpresiones());
		return null;
	}

	public Object visit(Print print) {
		print.getExpresion().accept(this);
		return null;
	}

	public Object visit(Read read) {
		read.getExpresion().accept(this);
		return null;
	}

	public Object visit(Return sentReturn) {
		if (sentReturn.getExpresion() != null) {
			sentReturn.getExpresion().accept(this);
		}
		return null;
	}

	public Object visit(While sentWhile) {
		sentWhile.getExpresion().accept(this);
		visitAll(sentWhile.getSentencias());
		return null;
	}

	public Object visit(TipoArray tipoArray) {
		tipoArray.getTipo().accept(this);
		return null;
	}

	public Object visit(TipoChar tipoChar) {
		return null;
	}

	public Object visit(TipoEntero tipoEntero) {
		return null;
	}

	public Object visit(TipoError tipoError) {
		return null;
	}

	public Object visit(TipoReal tipoReal) {
		return null;
	}

	public Object visit(TipoStruct tipoStruct) {
		return null;
	}

	private void visitAll(List<? extends AST> list) {
		if (list != null) {
			for (AST node : list) {
				node.accept(this);
			}
		}
	}

}
