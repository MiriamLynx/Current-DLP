package ast.visitor;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import ast.Programa;
import ast.declaracion.DeclaracionCampo;
import ast.declaracion.DeclaracionFuncion;
import ast.declaracion.DeclaracionStruct;
import ast.declaracion.DeclaracionVariable;
import ast.expresion.LlamadaFuncion;
import ast.expresion.Variable;
import ast.sentencia.If;
import ast.sentencia.LlamadaFuncionSent;
import ast.sentencia.Return;
import ast.sentencia.Sentencia;
import ast.sentencia.While;
import ast.tipo.Tipo;
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

	public Object visit(Programa programa) {
		set();
		Object ret = super.visit(programa);
		if (funciones.get("main") == null) {
			GestorErrores.addError(new TipoError(programa,
					"El programa no contiene funcion main"));
		}
		reset();
		return ret;
	}

	public Object visit(DeclaracionCampo declaracionCampo) {
		if (campos.get(declaracionCampo.getNombre()) != null) {
			GestorErrores.addError(new TipoError(declaracionCampo, "El campo '"
					+ declaracionCampo.getNombre() + "' ya ha sido declarado"));
		} else {
			campos.put(declaracionCampo.getNombre(), declaracionCampo);
		}
		return super.visit(declaracionCampo);
	}

	public Object visit(DeclaracionFuncion declaracionFuncion) {
		if (funciones.get(declaracionFuncion.getNombre()) != null) {
			GestorErrores.addError(new TipoError(declaracionFuncion,
					"La funcion '" + declaracionFuncion.getNombre()
							+ "' ya ha sido declarada"));
		} else {
			assertTipoPrimitivo(declaracionFuncion.getRetorno());
			funciones.put(declaracionFuncion.getNombre(), declaracionFuncion);
			int sentReturn = 0;
			for (Sentencia s : declaracionFuncion.getSentencias()) {
				if (s instanceof While) {
					((While) s).setFuncion(declaracionFuncion);
				} else if (s instanceof If) {
					((If) s).setFuncion(declaracionFuncion);
				} else if (s instanceof Return) {
					((Return) s).setFuncion(declaracionFuncion);
					sentReturn++;
				}
			}
			if (declaracionFuncion.getRetorno() != null && sentReturn == 0) {
				GestorErrores
						.addError(new TipoError(
								declaracionFuncion,
								"La funcion '"
										+ declaracionFuncion.getNombre()
										+ "' no contiene ninguna sentencia de retorno"));
			}
		}

		set();
		Object ret = super.visit(declaracionFuncion);
		reset();

		return ret;
	}

	public Object visit(While whil) {
		for (Sentencia s : whil.getSentencias()) {
			if (s instanceof If) {
				((If) s).setFuncion(whil.getFuncion());
			} else if (s instanceof Return) {
				((Return) s).setFuncion(whil.getFuncion());
			}
		}
		return super.visit(whil);
	}

	public Object visit(If sentIf) {
		for (Sentencia s : sentIf.getSentencias()) {
			if (s instanceof While) {
				((While) s).setFuncion(sentIf.getFuncion());
			} else if (s instanceof Return) {
				((Return) s).setFuncion(sentIf.getFuncion());
			}
		}
		return super.visit(sentIf);
	}

	public Object visit(DeclaracionStruct declaracionStruct) {
		if (structs.get(declaracionStruct.getNombre()) != null) {
			GestorErrores.addError(new TipoError(declaracionStruct,
					"El struct '" + declaracionStruct.getNombre()
							+ "' ya ha sido declarado"));
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
					"La variable '" + declaracionVariable.getNombre()
							+ "' ya ha sido declarada"));
		} else {
			if (declaracionVariable.getTipo() instanceof TipoStruct) {
				if (structs.get(((TipoStruct) declaracionVariable.getTipo())
						.getNombre()) == null) {
					GestorErrores.addError(new TipoError(declaracionVariable,
							"El struct '"
									+ ((TipoStruct) declaracionVariable
											.getTipo()).getNombre()
									+ "' no ha sido declarado"));
				} else {
					putVariable(declaracionVariable.getNombre(),
							declaracionVariable);
				}
			} else {
				putVariable(declaracionVariable.getNombre(),
						declaracionVariable);
			}
		}
		return super.visit(declaracionVariable);
	}

	public Object visit(TipoArray array) {
		if (array.getTipo() instanceof TipoStruct) {
			if (structs.get(((TipoStruct) array.getTipo()).getNombre()) == null) {
				GestorErrores.addError(new TipoError(array, "El struct '"
						+ ((TipoStruct) array.getTipo()).getNombre()
						+ "' no ha sido declarado"));
			}
		}
		return super.visit(array);
	}

	public Object visit(LlamadaFuncion llamadaFuncion) {
		if (funciones.get(llamadaFuncion.getNombre()) == null) {
			GestorErrores.addError(new TipoError(llamadaFuncion, "La funcion '"
					+ llamadaFuncion.getNombre() + "' no ha sido declarada"));
		} else {
			llamadaFuncion.setDeclaracion(funciones.get(llamadaFuncion
					.getNombre()));
		}
		return super.visit(llamadaFuncion);
	}

	public Object visit(LlamadaFuncionSent llamadaFuncion) {
		if (funciones.get(llamadaFuncion.getNombre()) == null) {
			GestorErrores.addError(new TipoError(llamadaFuncion, "La funcion '"
					+ llamadaFuncion.getNombre() + "' no ha sido declarada"));
		} else {
			llamadaFuncion.setDeclaracion(funciones.get(llamadaFuncion
					.getNombre()));
		}
		return super.visit(llamadaFuncion);
	}

	public Object visit(Variable variable) {
		if (findDeclaracion(variable.getNombre()) == null) {
			GestorErrores.addError(new TipoError(variable, "La variable '"
					+ variable.getNombre() + "' no ha sido declarada"));
		} else {
			DeclaracionVariable declaracion = findDeclaracion(variable
					.getNombre());
			if (declaracion.getTipo() instanceof TipoStruct) {
				if (structs.get(((TipoStruct) declaracion.getTipo())
						.getNombre()) == null) {
					GestorErrores.addError(new TipoError(variable,
							"El struct '"
									+ ((TipoStruct) declaracion.getTipo())
											.getNombre()
									+ "' no ha sido declarado"));
				} else {
					declaracion.setTipo(structs.get(((TipoStruct) declaracion
							.getTipo()).getNombre()));
					variable.setDeclaracion(declaracion);
				}
			} else {
				variable.setDeclaracion(declaracion);
			}
		}
		return super.visit(variable);
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

	private DeclaracionVariable findDeclaracion(String nombreVariable) {
		for (int i = contextos.size() - 1; i >= 0; i--) {
			DeclaracionVariable decl = contextos.get(i).get(nombreVariable);
			if (decl != null) {
				return decl;
			}
		}
		return null;
	}

	private void assertTipoPrimitivo(Tipo tipo) {
		if (!(tipo instanceof TipoEntero) && !(tipo instanceof TipoChar)
				&& !(tipo == null) && !(tipo instanceof TipoReal)) {
			GestorErrores.addError(new TipoError(tipo,
					"Las funciones solo pueden retornar tipos primitivos"));
		}
	}

}
