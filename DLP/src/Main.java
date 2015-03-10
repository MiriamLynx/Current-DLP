import introspector.model.IntrospectorModel;
import introspector.view.IntrospectorTree;

import java.io.FileNotFoundException;
import java.io.FileReader;

import ast.AST;

public class Main {
	public static void main(String[] args) {
		String nombreFichero = "ejemplo.txt";
		Lexico lex;
		try {
			// GestorErrores gestor = new GestorErrores();
			lex = new Lexico(new FileReader("src\\" + nombreFichero));
			Parser parser = new Parser(lex);
			parser.yyparse();
			AST root = parser.getAst();
			// if (!gestor.hayErrores()) {
			showTree(root);
			// System.out.println(">> Programa correcto sint�cticamente.");
			// }
		} catch (FileNotFoundException e) {
			System.out.println("Fichero no encontrado.");
			e.printStackTrace();
		}

	}

	public static void showTree(Object root) {
		IntrospectorModel modelo = new IntrospectorModel("Ra�z", root);
		new IntrospectorTree("Introspector", modelo);
	}

}
