package ast.visitor;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import ast.Programa;
import ast.declaracion.Declaracion;
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
import ast.sentencia.Sentencia;
import ast.sentencia.While;
import ast.tipo.TipoArray;

public class CodeVisitor extends AbstractVisitor {

	private String file;
	private PrintWriter writer;
	private Map<String, String> operation = new HashMap<String, String>();
	private int tag = 1;

	private enum Funcion {
		DIRECCION, VALOR
	}

	public CodeVisitor(String file, PrintWriter writer) {
		this.file = file;
		this.writer = writer;
		operation.put("+", "add");
		operation.put("-", "sub");
		operation.put("*", "mul");
		operation.put("/", "div");
		operation.put(">", "gt");
		operation.put("<", "lt");
		operation.put(">=", "ge");
		operation.put("<=", "le");
		operation.put("==", "eq");
		operation.put("not", "ne");
		operation.put("and", "and");
		operation.put("or", "or");
	}

	public Object visit(Programa programa, Object param) {
		out("#source\"" + file + "\"");
		out("call main");
		out("halt");
		for (Declaracion declaracion : programa.getDeclaraciones()) {
			declaracion.accept(this, null);
		}
		return null;
	}

	public Object visit(DeclaracionVariable variable, Object param) {
		out("#VAR " + variable.getNombre() + ":"
				+ variable.getTipo().getMAPLname());
		return null;
	}

	public Object visit(DeclaracionStruct struct, Object param) {
		out("#STRUCT " + struct.getNombre());
		return null;
	}

	public Object visit(DeclaracionCampo campo, Object param) {
		out("#FIELD " + campo.getNombre() + ":" + campo.getTipo().getMAPLname());
		return null;
	}

	public Object visit(DeclaracionFuncion funcion, Object param) {
		out("#FUNC " + funcion.getNombre());
		out(funcion.getNombre() + ":");
		for (DeclaracionVariable parametro : funcion.getParametros()) {
			out("#PARAM " + parametro.getNombre() + ":"
					+ parametro.getTipo().getMAPLname());
		}
		int locales = 0;
		for (DeclaracionVariable local : funcion.getDeclaraciones()) {
			locales += local.getTipo().size();
		}
		out("ENTER " + locales);
		for (Sentencia s : funcion.getSentencias()) {
			s.accept(this, null);
		}
		return null;
	}

	public Object visit(Variable variable, Object param) {
		if (param.equals(Funcion.VALOR)) {
			visit(variable, Funcion.DIRECCION);
			out("load" + variable.getTipo().getSufijo());
		} else {
			if (variable.getDeclaracion().getAmbito().equals("local")
					|| variable.getDeclaracion().getAmbito().equals("param")) {
				out("push bp");
				out("push " + variable.getDeclaracion().getDireccion());
				out("add" + variable.getTipo().getSufijo());
			} else {
				out("pusha " + variable.getDeclaracion().getDireccion());
			}
		}
		return null;
	}

	public Object visit(AccesoArray acceso, Object param) {
		if (param.equals(Funcion.VALOR)) {
			visit(acceso, Funcion.DIRECCION);
			out("load" + acceso.getTipo().getSufijo());
		} else {
			acceso.getArray().accept(this, Funcion.DIRECCION);
			out("pushi "
					+ ((TipoArray) acceso.getArray().getTipo()).getTipoBase()
							.size());

			acceso.getIndex().get(0).accept(this, Funcion.VALOR);

			if (acceso.getIndex().size() > 1) {
				for (int i = 1; i < acceso.getIndex().size(); i++) {
					acceso.getIndex().get(i).accept(this, Funcion.VALOR);
					out("addi");
				}
			}

			out("muli");
			out("addi");
		}
		return null;
	}

	public Object visit(Cast cast, Object param) {
		cast.getCasteo().accept(this, Funcion.VALOR);
		out(cast.getCasteo().getTipo().getSufijo() + "2"
				+ cast.getTipo().getSufijo());
		return null;
	}

	public Object visit(Comparacion comparacion, Object param) {
		comparacion.getIzquierda().accept(this, Funcion.VALOR);
		comparacion.getDerecha().accept(this, Funcion.VALOR);
		out(operation.get(comparacion.getOperador()));
		return null;
	}

	public Object visit(LlamadaFuncion llamadaFuncion, Object param) {
		for (Expresion e : llamadaFuncion.getExpresiones()) {
			e.accept(this, Funcion.VALOR);
		}
		out("call " + llamadaFuncion.getNombre());
		return null;
	}

	public Object visit(LlamadaFuncionSent llamadaFuncionSent, Object param) {
		for (Expresion e : llamadaFuncionSent.getExpresiones()) {
			e.accept(this, Funcion.VALOR);
		}
		out("call " + llamadaFuncionSent.getNombre());
		if (llamadaFuncionSent.getDeclaracion().getRetorno() != null) {
			out("pop" + llamadaFuncionSent.getDeclaracion().getRetorno().getSufijo());
		}
		return null;
	}

	public Object visit(NotLogico not, Object param) {
		not.getExpresion().accept(this, Funcion.VALOR);
		operation.get("not" + not.getTipo().getSufijo());
		return null;
	}

	public Object visit(AccesoCampo campo, Object param) {
		if (param.equals(Funcion.VALOR)) {
			visit(campo, Funcion.DIRECCION);
			out("load" + campo.getTipo().getSufijo());
		} else {
			campo.getStruct().accept(this, null);
			out("pushi "
					+ ((DeclaracionStruct) campo.getTipo()).getCampo(
							campo.getCampo()).getDireccion());
			out("addi");
		}
		return null;
	}

	public Object visit(OperacionAritmetica operacion, Object param) {
		operacion.getIzquierda().accept(this, Funcion.VALOR);
		operacion.getDerecha().accept(this, Funcion.VALOR);
		out(operation.get(operacion.getOperador())
				+ operacion.getTipo().getSufijo());
		return null;
	}

	public Object visit(OperacionLogica logica, Object param) {
		logica.getIzquierda().accept(this, Funcion.VALOR);
		logica.getDerecha().accept(this, Funcion.VALOR);
		out(operation.get(logica.getOperador()) + logica.getTipo().getSufijo());
		return null;
	}

	public Object visit(Asignacion asignacion, Object param) {
		asignacion.getIzquierda().accept(this, Funcion.DIRECCION);
		asignacion.getDerecha().accept(this, Funcion.VALOR);
		out("store" + asignacion.getIzquierda().getTipo().getSufijo());
		return null;
	}

	public Object visit(If sentIf, Object param) {
		getTag(2);
		sentIf.getExpresion().accept(this, Funcion.VALOR);
		out("jz " + tag);
		for (Sentencia s : sentIf.getSentencias()) {
			s.accept(this, null);
		}
		out("jump " + (tag + 1));
		out(tag + ":");
		for (Sentencia s : sentIf.getAlternativas()) {
			s.accept(this, null);
		}
		out((tag + 1) + ":");
		return null;
	}

	public Object visit(Print print, Object param) {
		print.getExpresion().accept(this, Funcion.VALOR);
		out("out" + print.getExpresion().getTipo().getSufijo());
		return null;
	}

	public Object visit(Read read, Object param) {
		read.getExpresion().accept(this, Funcion.DIRECCION);
		out("in" + read.getExpresion().getTipo().getSufijo());
		out("store" + read.getExpresion().getTipo().getSufijo());
		return null;
	}

	public Object visit(Return senReturn, Object param) {
		out("#RET " + senReturn.getExpresion().getTipo().getMAPLname());
		int locales = 0;
		for (DeclaracionVariable local : senReturn.getDeclaracionFuncion()
				.getDeclaraciones()) {
			locales += local.getTipo().size();
		}
		int parametros = 0;
		for (DeclaracionVariable parametro : senReturn.getDeclaracionFuncion()
				.getParametros()) {
			parametros += parametro.getTipo().size();
		}
		out("RET " + senReturn.getDeclaracionFuncion().getRetorno().size()
				+ ", " + locales + ", " + parametros);
		return null;
	}

	public Object visit(While sentWhile, Object param) {
		getTag(2);
		sentWhile.getExpresion().accept(this, Funcion.VALOR);
		out(tag + ":");
		out("jz " + (tag + 1));
		for (Sentencia s : sentWhile.getSentencias()) {
			s.accept(this, null);
		}
		out("jmp " + tag);
		out((tag + 1) + ":");
		return null;
	}

	public Object visit(ConstanteChar charr, Object param) {
		out("pushb " + charr.getValor());
		return null;
	}

	public Object visit(ConstanteEntera enter, Object param) {
		out("pushi " + enter.getValor());
		return null;
	}

	public Object visit(ConstanteReal real, Object param) {
		out("pushf " + real.getValor());
		return null;
	}

	private void out(String text) {
		System.out.println(text);
		writer.println(text);
	}

	public int getTag(int numero) {
		int temp = this.tag;
		this.tag += numero;
		return temp;
	}
}
