import java.io.IOException;

public class Main {

	public static void main(String[] args) {
		Lexico lex = new Lexico(System.in);

		int token;

		try {

			while ((token = lex.yylex()) != 0) {
				System.out.println("Token: " + token);
			}

			lex.yylex();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
