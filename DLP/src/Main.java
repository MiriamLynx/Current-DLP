import java.io.IOException;

public class Main {

	public static void main(String[] args) {
		Yylex lex = new Yylex(System.in);
		try {
			lex.yylex();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
