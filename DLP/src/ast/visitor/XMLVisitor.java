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
		System.out.println("<DeclaracionCampo name='" + node.getVariable()
				+ "'>");
		Object ret = super.visit(node);
		System.out.println("</DeclaracionCampo>");
		return ret;
	}

	public Object visit(DeclaracionFuncion node) {
		System.out.println("<DeclaracionFuncion name='" + node.getNombre()
				+ "'>");
		Object ret = super.visit(node);
		System.out.println("</DeclaracionFuncion>");
		return ret;
	}

	public Object visit(DeclaracionStruct node) {
		System.out.println("<DeclaracionStruct name='" + node.getNombre()
				+ "'>");
		Object ret = super.visit(node);
		System.out.println("</DeclaracionStruct>");
		return ret;
	}

	public Object visit(DeclaracionVariable node) {
		System.out.println("<DeclaracionVariable name='" + node.getNombre()
				+ "'>");
		Object ret = super.visit(node);
		System.out.println("</DeclaracionVariable>");
		return ret;
	}

	public Object visit(AccesoArray node) {
		System.out.println("<AccesoArray>");
		Object ret = super.visit(node);
		System.out.println("</AccesoArray>");
		return ret;
	}

	public Object visit(AccesoCampo node) {
		System.out.println("<AccesoCampo>");
		Object ret = super.visit(node);
		System.out.println("</AccesoCampo>");
		return ret;
	}

	public Object visit(Cast node) {
		System.out.println("<Cast>");
		Object ret = super.visit(node);
		System.out.println("</Cast>");
		return ret;
	}

	public Object visit(Comparacion node) {
		System.out.println("<Comparacion  name='" + node.getOperador() + "'>");
		Object ret = super.visit(node);
		System.out.println("</Comparacion>");
		return ret;
	}

	public Object visit(ConstanteChar node) {
		System.out.println("<ConstanteChar>");
		Object ret = super.visit(node);
		System.out.println("</ConstanteChar>");
		return ret;
	}

	public Object visit(ConstanteEntera node) {
		System.out.println("<ConstanteEntera>");
		Object ret = super.visit(node);
		System.out.println("</ConstanteEntera>");
		return ret;
	}

	public Object visit(ConstanteReal node) {
		System.out.println("<ConstanteReal>");
		Object ret = super.visit(node);
		System.out.println("</ConstanteReal>");
		return ret;
	}

	public Object visit(LlamadaFuncion node) {
		System.out.println("<LlamadaFuncionExpresion name='" + node.getNombre()
				+ "'>");
		Object ret = super.visit(node);
		System.out.println("</LlamadaFuncionExpresion>");
		return ret;
	}

	public Object visit(OperacionAritmetica node) {
		System.out.println("<OperacionAritmetica name='" + node.getOperador()
				+ "'>");
		Object ret = super.visit(node);
		System.out.println("</OperacionAritmetica>");
		return ret;
	}

	public Object visit(Variable node) {
		System.out.println("<Variable>");
		Object ret = super.visit(node);
		System.out.println("</Variable>");
		return ret;
	}

	public Object visit(NotLogico node) {
		System.out.println("<NotLogico>");
		Object ret = super.visit(node);
		System.out.println("</NotLogico>");
		return ret;
	}

	public Object visit(Asignacion node) {
		System.out.println("<Asignacion>");
		Object ret = super.visit(node);
		System.out.println("</Asignacion>");
		return ret;
	}

	public Object visit(If node) {
		System.out.println("<If>");
		Object ret = super.visit(node);
		System.out.println("</If>");
		return ret;
	}

	public Object visit(LlamadaFuncionSent node) {
		System.out.println("<LlamadaFuncionSentencia name='" + node.getNombre()
				+ "'>");
		Object ret = super.visit(node);
		System.out.println("</LlamadaFuncionSentencia>");
		return ret;
	}

	public Object visit(Print node) {
		System.out.println("<Print>");
		Object ret = super.visit(node);
		System.out.println("</Print>");
		return ret;
	}

	public Object visit(Read node) {
		System.out.println("<Read>");
		Object ret = super.visit(node);
		System.out.println("</Read>");
		return ret;
	}

	public Object visit(Return node) {
		System.out.println("<Return>");
		Object ret = super.visit(node);
		System.out.println("</Return>");
		return ret;
	}

	public Object visit(While node) {
		System.out.println("<While>");
		Object ret = super.visit(node);
		System.out.println("</While>");
		return ret;
	}

	public Object visit(TipoArray node) {
		System.out.println("<TipoArray>");
		Object ret = super.visit(node);
		System.out.println("</TipoArray>");
		return ret;
	}

	public Object visit(TipoEntero node) {
		System.out.println("<TipoEntero>");
		Object ret = super.visit(node);
		System.out.println("</TipoEntero>");
		return ret;
	}

	public Object visit(TipoStruct node) {
		System.out.println("<TipoStruct>");
		Object ret = super.visit(node);
		System.out.println("</TipoStruct>");
		return ret;
	}

}
