package ast.visitor;

import ast.Programa;

public class CodeVisitor extends AbstractVisitor {

	public Object visit(Programa programa) {
		System.out.println("#source\"" + "NombreFicheroFuente" + "\"");
		System.out.println("callmain");
		System.out.println("halt");
		return super.visit(programa);
	}
}
