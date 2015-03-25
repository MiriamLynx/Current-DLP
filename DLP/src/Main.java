import introspector.model.IntrospectorModel;
import introspector.view.IntrospectorTree;

import java.io.FileNotFoundException;
import java.io.FileReader;

import ast.AST;
import ast.visitor.IdentificationVisitor;
import ast.visitor.XMLVisitor;
import error.GestorErrores;

public class Main {
	public static void main(String[] args) {
		String nombreFichero = "pruebaIdentificacion.txt";
		Lexico lex;
		try {
			GestorErrores gestor = new GestorErrores();
			lex = new Lexico(new FileReader("src\\" + nombreFichero));
			Parser parser = new Parser(lex);
			parser.yyparse();
			AST root = parser.getAst();
			IdentificationVisitor iv = new IdentificationVisitor();
			parser.getAst().accept(iv);
			if (!gestor.hayErrores()) {
				showTree(root);
				System.out.println(">> Programa correcto sintácticamente.");
				XMLVisitor xmlv = new XMLVisitor();
				parser.getAst().accept(xmlv);
			} else {
				showTree(root);
				gestor.mostrarErrores();
			}
		} catch (FileNotFoundException e) {
			System.out.println("Fichero no encontrado.");
			e.printStackTrace();
		}

	}

	public static void showTree(Object root) {
		IntrospectorModel modelo = new IntrospectorModel("Raíz", root);
		new IntrospectorTree("Introspector", modelo);
	}

}
