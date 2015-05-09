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
import ast.expresion.Expresion;
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

	public Object visit(Programa programa, Object param) {
		visitAll(programa.getDeclaraciones());
		return null;
	}

	public Object visit(DeclaracionCampo declaracionCampo, Object param) {
		declaracionCampo.getTipo().accept(this, null);
		return null;
	}

	public Object visit(DeclaracionFuncion declaracionFuncion, Object param) {
		if (declaracionFuncion.getRetorno() != null) {
			declaracionFuncion.getRetorno().accept(this, null);
		}
		visitAll(declaracionFuncion.getParametros());
		visitAll(declaracionFuncion.getDeclaraciones());
		visitAll(declaracionFuncion.getSentencias());
		return null;
	}

	public Object visit(DeclaracionStruct declaracionStruct, Object param) {
		visitAll(declaracionStruct.getDeclaraciones());
		return null;
	}

	public Object visit(DeclaracionVariable declaracionVariable, Object param) {
		declaracionVariable.getTipo().accept(this, null);
		return null;
	}

	public Object visit(AccesoArray accesoArray, Object param) {
		for (Expresion e : accesoArray.getIndex()) {
			e.accept(this, null);
		}
		accesoArray.getArray().accept(this, null);
		return null;
	}

	public Object visit(AccesoCampo accesoCampo, Object param) {
		accesoCampo.getStruct().accept(this, null);
		return null;
	}

	public Object visit(Cast cast, Object param) {
		cast.getTipoBase().accept(this, null);
		cast.getCasteo().accept(this, null);
		return null;
	}

	public Object visit(Comparacion comparacion, Object param) {
		comparacion.getIzquierda().accept(this, null);
		comparacion.getDerecha().accept(this, null);
		return null;
	}

	public Object visit(ConstanteChar constanteChar, Object param) {
		return null;
	}

	public Object visit(ConstanteEntera constanteEntera, Object param) {
		return null;
	}

	public Object visit(ConstanteReal constanteReal, Object param) {
		return null;
	}

	public Object visit(LlamadaFuncion llamadaFuncion, Object param) {
		visitAll(llamadaFuncion.getExpresiones());
		return null;
	}

	public Object visit(OperacionAritmetica operacionAritmetica, Object param) {
		operacionAritmetica.getIzquierda().accept(this, null);
		operacionAritmetica.getDerecha().accept(this, null);
		return null;
	}

	public Object visit(OperacionLogica operacionLogica, Object param) {
		operacionLogica.getIzquierda().accept(this, null);
		operacionLogica.getDerecha().accept(this, null);
		return null;
	}

	public Object visit(Variable variable, Object param) {
		return null;
	}

	public Object visit(NotLogico notLogico, Object param) {
		notLogico.getExpresion().accept(this, null);
		return null;
	}

	public Object visit(Asignacion asignacion, Object param) {
		asignacion.getIzquierda().accept(this, null);
		asignacion.getDerecha().accept(this, null);
		return null;
	}

	public Object visit(If sentIf, Object param) {
		sentIf.getExpresion().accept(this, null);
		visitAll(sentIf.getSentencias());
		visitAll(sentIf.getAlternativas());
		return null;
	}

	public Object visit(LlamadaFuncionSent llamadaFuncionSent, Object param) {
		visitAll(llamadaFuncionSent.getExpresiones());
		return null;
	}

	public Object visit(Print print, Object param) {
		print.getExpresion().accept(this, null);
		return null;
	}

	public Object visit(Read read, Object param) {
		read.getExpresion().accept(this, null);
		return null;
	}

	public Object visit(Return sentReturn, Object param) {
		if (sentReturn.getExpresion() != null) {
			sentReturn.getExpresion().accept(this, null);
		}
		return null;
	}

	public Object visit(While sentWhile, Object param) {
		sentWhile.getExpresion().accept(this, null);
		visitAll(sentWhile.getSentencias());
		return null;
	}

	public Object visit(TipoArray tipoArray, Object param) {
		tipoArray.getTipoBase().accept(this, null);
		return null;
	}

	public Object visit(TipoChar tipoChar, Object param) {
		return null;
	}

	public Object visit(TipoEntero tipoEntero, Object param) {
		return null;
	}

	public Object visit(TipoError tipoError, Object param) {
		return null;
	}

	public Object visit(TipoReal tipoReal, Object param) {
		return null;
	}

	public Object visit(TipoStruct tipoStruct, Object param) {
		return null;
	}

	private void visitAll(List<? extends AST> list) {
		if (list != null) {
			for (AST node : list) {
				node.accept(this, null);
			}
		}
	}
}
