package ast.visitor;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import ast.declaracion.DeclaracionCampo;
import ast.declaracion.DeclaracionFuncion;
import ast.declaracion.DeclaracionStruct;
import ast.declaracion.DeclaracionVariable;
import ast.tipo.TipoArray;
import ast.tipo.TipoChar;
import ast.tipo.TipoEntero;
import ast.tipo.TipoError;
import ast.tipo.TipoReal;
import ast.tipo.TipoStruct;
import error.GestorErrores;

public class IdentificationVisitor extends AbstractVisitor {

	Map<String, DeclaracionStruct> structs = new HashMap<String, DeclaracionStruct>();
	Map<String, DeclaracionFuncion> funciones = new HashMap<String, DeclaracionFuncion>();
	Map<String, DeclaracionCampo> campos = new HashMap<String, DeclaracionCampo>();
	Stack<Map<String, DeclaracionVariable>> contextos = new Stack<Map<String, DeclaracionVariable>>();

	public Object visit(DeclaracionCampo declaracionCampo) {
		if (campos.get(declaracionCampo.getNombre()) != null) {
			GestorErrores.addError(new TipoError(declaracionCampo,
					"Ya existe un campo con ese nombre"));
		} else {
			campos.put(declaracionCampo.getNombre(), declaracionCampo);
		}
		return super.visit(declaracionCampo);
	}

	public Object visit(DeclaracionFuncion declaracionFuncion) {
		if (funciones.get(declaracionFuncion.getNombre()) != null) {
			GestorErrores.addError(new TipoError(declaracionFuncion,
					"Ya existe una funcion con ese nombre"));
		} else {
			funciones.put(declaracionFuncion.getNombre(), declaracionFuncion);
		}

		set();
		Object ret = super.visit(declaracionFuncion);
		reset();

		return ret;
	}

	public Object visit(DeclaracionStruct declaracionStruct) {
		if (structs.get(declaracionStruct.getNombre()) != null) {
			GestorErrores.addError(new TipoError(declaracionStruct,
					"Ya existe un Struct con ese nombre"));
		} else {
			structs.put(declaracionStruct.getNombre(), declaracionStruct);
		}

		campos.clear();
		Object ret = super.visit(declaracionStruct);

		return ret;
	}

	public Object visit(DeclaracionVariable declaracionVariable) {
		if (getVariable(declaracionVariable.getNombre()) != null) {
			GestorErrores.addError(new TipoError(declaracionVariable,
					"Ya existe una variable con ese nombre"));
		} else {
			putVariable(declaracionVariable.getNombre(), declaracionVariable);
		}
		return super.visit(declaracionVariable);
	}

	public Object visit(TipoArray tipoArray) {
		// TODO Auto-generated method stub
		return null;
	}

	public Object visit(TipoChar tipoChar) {
		// TODO Auto-generated method stub
		return null;
	}

	public Object visit(TipoEntero tipoEntero) {
		// TODO Auto-generated method stub
		return null;
	}

	public Object visit(TipoError tipoError) {
		// TODO Auto-generated method stub
		return null;
	}

	public Object visit(TipoReal tipoReal) {
		// TODO Auto-generated method stub
		return null;
	}

	public Object visit(TipoStruct tipoStruct) {
		// TODO Auto-generated method stub
		return null;
	}

	private void set() {
		contextos.push(new HashMap<String, DeclaracionVariable>());
	}

	private void reset() {
		contextos.pop();
	}

	private DeclaracionVariable getVariable(String nombreVariable) {
		return contextos.peek().get(nombreVariable);
	}

	private void putVariable(String nombreVariable, DeclaracionVariable node) {
		contextos.peek().put(nombreVariable, node);
	}

	private DeclaracionVariable encuentraDeclaracion(String nombreVariable) {
		for (int i = contextos.size() - 1; i >= 0; i--) {
			DeclaracionVariable decl = contextos.get(i).get(nombreVariable);
			if (decl != null) {
				return decl;
			}
		}
		return null;
	}

}
