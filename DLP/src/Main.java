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
import error.GestorErrores;

public class Main {
	public static void main(String[] args) {
		String nombreFichero = "incremento.txt";
		Lexico lex;
		try {
			GestorErrores gestor = new GestorErrores();
			lex = new Lexico(new FileReader("src\\" + nombreFichero));
			Parser parser = new Parser(lex);
			parser.yyparse();
			IdentificationVisitor iv = new IdentificationVisitor();
			InferenceVisitor inv = new InferenceVisitor();
			MemoryVisitor mv = new MemoryVisitor();
			PrintWriter writer = new PrintWriter("src\\salida.txt");
			CodeVisitor cv = new CodeVisitor(nombreFichero, writer);
			AST root = parser.getAst();
			parser.getAst().accept(iv, null);
			parser.getAst().accept(inv, null);
			parser.getAst().accept(mv, null);
			if (!gestor.hayErrores()) {
				System.out.println(root.toString());
				parser.getAst().accept(cv, null);
				writer.close();
			} else {
				System.out.println(root.toString());
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
