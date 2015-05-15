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
import ast.tipo.TipoBool;
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

	public Object visit(Programa programa, Object param) {
		set();
		Object ret = super.visit(programa, null);
		if (funciones.get("main") == null) {
			GestorErrores.addError(new TipoError(programa,
					"El programa no contiene funcion main"));
		}
		reset();
		return ret;
	}

	public Object visit(DeclaracionCampo declaracionCampo, Object param) {
		super.visit(declaracionCampo, null);
		if (campos.get(declaracionCampo.getNombre()) != null) {
			GestorErrores.addError(new TipoError(declaracionCampo, "El campo '"
					+ declaracionCampo.getNombre() + "' ya ha sido declarado"));
		} else {
			if (declaracionCampo.getTipo() instanceof TipoStruct) {
				if (structs.get(((TipoStruct) declaracionCampo.getTipo())
						.getNombre()) == null) {
					GestorErrores.addError(new TipoError(declaracionCampo,
							"El struct '"
									+ ((TipoStruct) declaracionCampo.getTipo())
											.getNombre()
									+ "' no ha sido declarado"));
				} else {
					declaracionCampo.setTipo(structs
							.get(((TipoStruct) declaracionCampo.getTipo())
									.getNombre()));
				}
			}
			campos.put(declaracionCampo.getNombre(), declaracionCampo);
		}
		return null;
	}

	public Object visit(DeclaracionFuncion declaracionFuncion, Object param) {
		if (funciones.get(declaracionFuncion.getNombre()) != null) {
			GestorErrores.addError(new TipoError(declaracionFuncion,
					"La funcion '" + declaracionFuncion.getNombre()
							+ "' ya ha sido declarada"));
		} else {
			for (DeclaracionVariable c : declaracionFuncion.getParametros()) {
				assertParametroPrimitivo(c);
			}
			assertRetornoPrimitivo(declaracionFuncion.getRetorno());
			funciones.put(declaracionFuncion.getNombre(), declaracionFuncion);
			int sentReturn = count(declaracionFuncion);
			for (Sentencia s : declaracionFuncion.getSentencias()) {
				s.setDeclaracionFuncion(declaracionFuncion);
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
		Object ret = super.visit(declaracionFuncion, null);
		reset();

		return ret;
	}

	private int count(DeclaracionFuncion declaracion) {
		int count = 0;
		for (Sentencia s : declaracion.getSentencias()) {
			if (s instanceof While) {
				count += count((While) s);
			} else if (s instanceof If) {
				count += count((If) s);
			} else if (s instanceof Return) {
				count += 1;
			}
		}
		return count;
	}

	private int count(While whil) {
		int count = 0;
		for (Sentencia s : whil.getSentencias()) {
			if (s instanceof While) {
				count += count((While) s) + count;
			} else if (s instanceof If) {
				count += count((If) s);
			} else if (s instanceof Return) {
				count += 1;
			}
		}
		return count;
	}

	private int count(If sentIf) {
		int count = 0;
		for (Sentencia s : sentIf.getSentencias()) {
			if (s instanceof While) {
				count += count((While) s) + count;
			} else if (s instanceof If) {
				count += count((If) s);
			} else if (s instanceof Return) {
				count += 1;
			}
		}
		return count;
	}

	public Object visit(While whil, Object param) {
		for (Sentencia s : whil.getSentencias()) {
			s.setDeclaracionFuncion(whil.getDeclaracionFuncion());
		}
		return super.visit(whil, null);
	}

	public Object visit(If sentIf, Object param) {
		for (Sentencia s : sentIf.getSentencias()) {
			s.setDeclaracionFuncion(sentIf.getDeclaracionFuncion());
		}
		if (sentIf.getAlternativas().size() > 0) {
			for (Sentencia s : sentIf.getAlternativas()) {
				s.setDeclaracionFuncion(sentIf.getDeclaracionFuncion());
			}
		}
		return super.visit(sentIf, null);
	}

	public Object visit(DeclaracionStruct declaracionStruct, Object param) {
		if (structs.get(declaracionStruct.getNombre()) != null) {
			GestorErrores.addError(new TipoError(declaracionStruct,
					"El struct '" + declaracionStruct.getNombre()
							+ "' ya ha sido declarado"));
		} else {
			structs.put(declaracionStruct.getNombre(), declaracionStruct);
		}

		campos.clear();
		Object ret = super.visit(declaracionStruct, null);

		return ret;
	}

	public Object visit(DeclaracionVariable declaracionVariable, Object param) {
		super.visit(declaracionVariable, null);
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
					declaracionVariable.setTipo(structs
							.get(((TipoStruct) declaracionVariable.getTipo())
									.getNombre()));
				}
			} else {
				putVariable(declaracionVariable.getNombre(),
						declaracionVariable);
			}
		}
		return null;
	}

	public Object visit(TipoArray array, Object param) {
		super.visit(array, null);
		if (array.getTipoBase() instanceof TipoStruct) {
			if (structs.get(((TipoStruct) array.getTipoBase()).getNombre()) == null) {
				GestorErrores.addError(new TipoError(array, "El struct '"
						+ ((TipoStruct) array.getTipoBase()).getNombre()
						+ "' no ha sido declarado"));
			} else {
				array.setTipoBase(structs.get(((TipoStruct) array.getTipoBase())
						.getNombre()));
			}
		}
		return null;
	}

	public Object visit(LlamadaFuncion llamadaFuncion, Object param) {
		if (funciones.get(llamadaFuncion.getNombre()) == null) {
			GestorErrores.addError(new TipoError(llamadaFuncion, "La funcion '"
					+ llamadaFuncion.getNombre() + "' no ha sido declarada"));
		} else {
			llamadaFuncion.setDeclaracion(funciones.get(llamadaFuncion
					.getNombre()));
		}
		return super.visit(llamadaFuncion, null);
	}

	public Object visit(LlamadaFuncionSent llamadaFuncion, Object param) {
		if (funciones.get(llamadaFuncion.getNombre()) == null) {
			GestorErrores.addError(new TipoError(llamadaFuncion, "La funcion '"
					+ llamadaFuncion.getNombre() + "' no ha sido declarada"));
		} else {
			llamadaFuncion.setDeclaracion(funciones.get(llamadaFuncion
					.getNombre()));
		}
		return super.visit(llamadaFuncion, null);
	}

	public Object visit(Variable variable, Object param) {
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
					variable.setDeclaracion(declaracion);
				}
			} else {
				variable.setDeclaracion(declaracion);
			}
		}
		return super.visit(variable, null);
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

	private void assertRetornoPrimitivo(Tipo tipo) {
		if (!(tipo instanceof TipoEntero) && !(tipo instanceof TipoChar)
				&& !(tipo == null) && !(tipo instanceof TipoReal)
				&& !(tipo instanceof TipoBool)) {
			GestorErrores.addError(new TipoError(tipo,
					"Las funciones solo pueden retornar tipos primitivos"));
		}
	}

	private void assertParametroPrimitivo(DeclaracionVariable declaracion) {
		if (!(declaracion.getTipo() instanceof TipoEntero)
				&& !(declaracion.getTipo() instanceof TipoChar)
				&& !(declaracion.getTipo() == null)
				&& !(declaracion.getTipo() instanceof TipoReal)
				&& !(declaracion.getTipo() instanceof TipoBool)) {
			GestorErrores.addError(new TipoError(declaracion, "El parametro "
					+ declaracion.getNombre() + " no es de tipo primitivo"));
		}
	}
}
