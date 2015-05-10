import introspector.model.IntrospectorModel;
import introspector.view.IntrospectorTree;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;

import ast.AST;
import ast.visitor.CodeVisitor;
import ast.visitor.IdentificationVisitor;
import ast.visitor.InferenceVisitor;
import ast.visitor.MemoryVisitor;
import ast.visitor.XMLVisitor;
import error.GestorErrores;

public class Main {
	public static void main(String[] args) {
		String nombreFichero = "generacion1.txt";
		Lexico lex;
		try {
			GestorErrores gestor = new GestorErrores();
			lex = new Lexico(new FileReader("src\\" + nombreFichero));
			Parser parser = new Parser(lex);
			parser.yyparse();
			AST root = parser.getAst();
			IdentificationVisitor iv = new IdentificationVisitor();
			InferenceVisitor inv = new InferenceVisitor();
			MemoryVisitor mv = new MemoryVisitor();
			PrintWriter writer = new PrintWriter("src\\salida.txt");
			CodeVisitor cv = new CodeVisitor(nombreFichero, writer);
			parser.getAst().accept(iv, null);
			parser.getAst().accept(inv, null);
			parser.getAst().accept(mv, null);
			if (!gestor.hayErrores()) {
				// showTree(root);
				System.out.println(">> Programa correcto sintácticamente.");
				XMLVisitor xmlv = new XMLVisitor();
				parser.getAst().accept(xmlv, null);
				parser.getAst().accept(cv, null);
				writer.close();
			} else {
				// showTree(root);
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
