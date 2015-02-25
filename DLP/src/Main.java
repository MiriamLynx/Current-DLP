import java.io.FileNotFoundException;
import java.io.FileReader;

public class Main {

	public static void main(String[] args) {

		final String source = "ejemplo.txt";

		Lexico lex;
		try {

			lex = new Lexico(new FileReader(source));

			Parser parser = new Parser(lex);

			parser.yyparse();

		} catch (FileNotFoundException e) {

		}

	}

}
