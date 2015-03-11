package ast.visitor;

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
import ast.expresion.Variable;
import ast.sentencia.Asignacion;
import ast.sentencia.If;
import ast.sentencia.LlamadaFuncionSent;
import ast.sentencia.Print;
import ast.sentencia.Read;
import ast.sentencia.Return;
import ast.sentencia.While;
import ast.tipo.TipoArray;
import ast.tipo.TipoEntero;
import ast.tipo.TipoStruct;

public class XMLVisitor extends AbstractVisitor {

	public Object visit(Programa node) {
		System.out.println("<Programa>");
		Object ret = super.visit(node);
		System.out.println("</Programa>");
		return ret;
	}

	public Object visit(DeclaracionCampo node) {
		formatDeclaracion();
		System.out.println("<DeclaracionCampo name='" + node.getVariable()
				+ "'>");
		Object ret = super.visit(node);
		formatDeclaracion();
		System.out.println("</DeclaracionCampo>");
		return ret;
	}

	public Object visit(DeclaracionFuncion node) {
		formatDeclaracion();
		System.out.println("<DeclaracionFuncion name='" + node.getNombre()
				+ "'>");
		Object ret = super.visit(node);
		formatDeclaracion();
		System.out.println("</DeclaracionFuncion>");
		return ret;
	}

	public Object visit(DeclaracionStruct node) {
		formatDeclaracion();
		System.out.println("<DeclaracionStruct name='" + node.getNombre()
				+ "'>");
		Object ret = super.visit(node);
		formatDeclaracion();
		System.out.println("</DeclaracionStruct>");
		return ret;
	}

	public Object visit(DeclaracionVariable node) {
		formatDeclaracion();
		System.out.println("<DeclaracionVariable name='" + node.getNombre()
				+ "'>");
		Object ret = super.visit(node);
		formatDeclaracion();
		System.out.println("</DeclaracionVariable>");
		return ret;
	}

	public Object visit(AccesoArray node) {
		formatExpresion();
		System.out.println("<AccesoArray>");
		Object ret = super.visit(node);
		formatExpresion();
		System.out.println("</AccesoArray>");
		return ret;
	}

	public Object visit(AccesoCampo node) {
		formatExpresion();
		System.out.println("<AccesoCampo>");
		Object ret = super.visit(node);
		formatExpresion();
		System.out.println("</AccesoCampo>");
		return ret;
	}

	public Object visit(Cast node) {
		formatExpresion();
		System.out.println("<Cast>");
		Object ret = super.visit(node);
		formatExpresion();
		System.out.println("</Cast>");
		return ret;
	}

	public Object visit(Comparacion node) {
		formatExpresion();
		System.out.println("<Comparacion  name='" + node.getOperador() + "'>");
		Object ret = super.visit(node);
		formatExpresion();
		System.out.println("</Comparacion>");
		return ret;
	}

	public Object visit(ConstanteChar node) {
		formatExpresion();
		System.out.println("<ConstanteChar>");
		Object ret = super.visit(node);
		formatExpresion();
		System.out.println("</ConstanteChar>");
		return ret;
	}

	public Object visit(ConstanteEntera node) {
		formatExpresion();
		System.out.println("<ConstanteEntera>");
		Object ret = super.visit(node);
		formatExpresion();
		System.out.println("</ConstanteEntera>");
		return ret;
	}

	public Object visit(ConstanteReal node) {
		formatExpresion();
		System.out.println("<ConstanteReal>");
		Object ret = super.visit(node);
		formatExpresion();
		System.out.println("</ConstanteReal>");
		return ret;
	}

	public Object visit(LlamadaFuncion node) {
		formatExpresion();
		System.out.println("<LlamadaFuncionExpresion name='" + node.getNombre()
				+ "'>");
		Object ret = super.visit(node);
		formatExpresion();
		System.out.println("</LlamadaFuncionExpresion>");
		return ret;
	}

	public Object visit(OperacionAritmetica node) {
		formatExpresion();
		System.out.println("<OperacionAritmetica name='" + node.getOperador()
				+ "'>");
		Object ret = super.visit(node);
		formatExpresion();
		System.out.println("</OperacionAritmetica>");
		return ret;
	}

	public Object visit(Variable node) {
		formatExpresion();
		System.out.println("<Variable>");
		Object ret = super.visit(node);
		formatExpresion();
		System.out.println("</Variable>");
		return ret;
	}

	public Object visit(NotLogico node) {
		formatExpresion();
		System.out.println("<NotLogico>");
		Object ret = super.visit(node);
		formatExpresion();
		System.out.println("</NotLogico>");
		return ret;
	}

	public Object visit(Asignacion node) {
		formatSentencia();
		System.out.println("<Asignacion>");
		Object ret = super.visit(node);
		System.out.println("</Asignacion>");
		formatSentencia();
		return ret;
	}

	public Object visit(If node) {
		formatSentencia();
		System.out.println("<If>");
		Object ret = super.visit(node);
		System.out.println("</If>");
		formatSentencia();
		return ret;
	}

	public Object visit(LlamadaFuncionSent node) {
		formatSentencia();
		System.out.println("<LlamadaFuncionSentencia name='" + node.getNombre()
				+ "'>");
		Object ret = super.visit(node);
		formatSentencia();
		System.out.println("</LlamadaFuncionSentencia>");
		return ret;
	}

	public Object visit(Print node) {
		formatSentencia();
		System.out.println("<Print>");
		Object ret = super.visit(node);
		formatSentencia();
		System.out.println("</Print>");
		return ret;
	}

	public Object visit(Read node) {
		formatSentencia();
		System.out.println("<Read>");
		Object ret = super.visit(node);
		formatSentencia();
		System.out.println("</Read>");
		return ret;
	}

	public Object visit(Return node) {
		formatSentencia();
		System.out.println("<Return>");
		Object ret = super.visit(node);
		formatSentencia();
		System.out.println("</Return>");
		return ret;
	}

	public Object visit(While node) {
		formatSentencia();
		System.out.println("<While>");
		Object ret = super.visit(node);
		formatSentencia();
		System.out.println("</While>");
		return ret;
	}

	public Object visit(TipoArray node) {
		formatTipo();
		System.out.println("<TipoArray>");
		Object ret = super.visit(node);
		formatTipo();
		System.out.println("</TipoArray>");
		return ret;
	}

	public Object visit(TipoEntero node) {
		formatTipo();
		System.out.println("<TipoEntero>");
		Object ret = super.visit(node);
		formatTipo();
		System.out.println("</TipoEntero>");
		return ret;
	}

	public Object visit(TipoStruct node) {
		formatTipo();
		System.out.println("<TipoStruct>");
		Object ret = super.visit(node);
		formatTipo();
		System.out.println("</TipoStruct>");
		return ret;
	}

	private void formatDeclaracion() {
		System.out.print("\t");
	}

	private void formatSentencia() {
		System.out.print("\t\t");
	}

	private void formatExpresion() {
		System.out.print("\t\t\t");
	}

	private void formatTipo() {
		System.out.print("\t\t\t\t");
	}

}