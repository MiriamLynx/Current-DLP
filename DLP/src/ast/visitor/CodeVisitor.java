package ast.visitor;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import ast.Programa;

public class CodeVisitor extends AbstractVisitor {

	private String file;
	private PrintWriter writer;
	private Map<String, String> operation = new HashMap<String, String>();

	public CodeVisitor(String file, PrintWriter writer) {
		this.file = file;
		this.writer = writer;
		operation.put("+", "add");
		operation.put("-", "sub");
		operation.put("*", "mul");
		operation.put("/", "div");
	}

	public Object visit(Programa programa) {
		out("#source\"" + file + "\"");
		out("callmain");
		out("halt");
		return super.visit(programa);
	}

	private void out(String text) {
		writer.println(text);
	}
}
