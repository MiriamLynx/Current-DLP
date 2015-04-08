package ast.visitor;

import ast.declaracion.DeclaracionFuncion;
import ast.declaracion.DeclaracionStruct;
import ast.declaracion.DeclaracionVariable;

public class MemoryVisitor extends AbstractVisitor {

	private int siguienteLibre = 0;
	private int parametrosBP = 4;
	private int localesBP = 0;
	private int camposBP = 4;

	public Object visit(DeclaracionVariable variableGlobal) {
		variableGlobal.setDireccion(siguienteLibre);
		siguienteLibre += variableGlobal.getTipo().size();
		return null;
	}

	public Object visit(DeclaracionStruct struct) {
		struct.setDireccion(siguienteLibre);
		siguienteLibre += struct.size();
		if (struct.getDeclaraciones().size() > 0) {
			for (int i = 0; i < struct.getDeclaraciones().size(); i++) {
				struct.getDeclaraciones().get(i).setDireccion(camposBP);
				camposBP += struct.getDeclaraciones().get(i).getTipo().size();
			}
		}
		return null;
	}

	public Object visit(DeclaracionFuncion funcion) {
		if (funcion.getParametros().size() > 0) {
			for (int i = funcion.getParametros().size() - 1; i >= 0; i--) {
				funcion.getParametros().get(i).setDireccion(parametrosBP);
				parametrosBP += funcion.getParametros().get(i).getTipo().size();
			}
		}

		if (funcion.getDeclaraciones().size() > 0) {
			localesBP = funcion.getDeclaraciones().get(0).getTipo().size();
			for (int i = 0; i < funcion.getDeclaraciones().size(); i++) {
				funcion.getDeclaraciones().get(i).setDireccion(localesBP);
				localesBP -= funcion.getDeclaraciones().get(i).getTipo().size();
			}
		}
		return null;
	}
}
