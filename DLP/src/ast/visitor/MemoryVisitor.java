package ast.visitor;

import ast.declaracion.DeclaracionFuncion;
import ast.declaracion.DeclaracionStruct;
import ast.declaracion.DeclaracionVariable;

public class MemoryVisitor extends AbstractVisitor {

	private int siguienteLibre = 0;

	public Object visit(DeclaracionVariable variableGlobal, Object param) {
		variableGlobal.setDireccion(siguienteLibre);
		siguienteLibre += variableGlobal.getTipo().size();
		return null;
	}

	public Object visit(DeclaracionStruct struct, Object param) {
		int camposBP = 0;
		if (struct.getDeclaraciones().size() > 0) {
			for (int i = 0; i < struct.getDeclaraciones().size(); i++) {
				struct.getDeclaraciones().get(i).setDireccion(camposBP);
				camposBP += struct.getDeclaraciones().get(i).getTipo().size();
			}
		}
		return null;
	}

	public Object visit(DeclaracionFuncion funcion, Object param) {

		int parametrosBP = 4;

		if (funcion.getParametros().size() > 0) {
			for (int i = funcion.getParametros().size() - 1; i >= 0; i--) {
				funcion.getParametros().get(i).setDireccion(parametrosBP);
				funcion.getParametros().get(i).setAmbito("param");
				parametrosBP += funcion.getParametros().get(i).getTipo().size();
			}
		}

		int localesBP = 0;

		if (funcion.getDeclaraciones().size() > 0) {
			for (int i = 0; i < funcion.getDeclaraciones().size(); i++) {
				localesBP -= funcion.getDeclaraciones().get(i).getTipo().size();
				funcion.getDeclaraciones().get(i).setDireccion(localesBP);
				funcion.getDeclaraciones().get(i).setAmbito("local");
			}
		}
		return null;
	}
}
