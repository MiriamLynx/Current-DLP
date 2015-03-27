package ast.visitor;

import java.util.List;

import ast.AST;
import ast.declaracion.DeclaracionCampo;
import ast.declaracion.DeclaracionStruct;
import ast.declaracion.DeclaracionVariable;
import ast.expresion.AccesoArray;
import ast.expresion.AccesoCampo;
import ast.expresion.ConstanteChar;
import ast.expresion.ConstanteEntera;
import ast.expresion.ConstanteReal;
import ast.expresion.Expresion;
import ast.expresion.LlamadaFuncion;
import ast.expresion.OperacionAritmetica;
import ast.expresion.Variable;
import ast.sentencia.Asignacion;
import ast.sentencia.LlamadaFuncionSent;
import ast.sentencia.Return;
import ast.tipo.Tipo;
import ast.tipo.TipoChar;
import ast.tipo.TipoEntero;
import ast.tipo.TipoError;
import ast.tipo.TipoReal;
import error.GestorErrores;

public class InferenceVisitor extends AbstractVisitor {

	public Object visit(ConstanteEntera constanteEntera) {
		constanteEntera.setLvalue(false);
		constanteEntera.setTipo(TipoEntero.getInstance(
				constanteEntera.getLinea(), constanteEntera.getColumna()));
		return super.visit(constanteEntera);
	}

	public Object visit(ConstanteReal constanteReal) {
		constanteReal.setLvalue(false);
		constanteReal.setTipo(TipoReal.getInstance(constanteReal.getLinea(),
				constanteReal.getColumna()));
		return super.visit(constanteReal);
	}

	public Object visit(ConstanteChar constanteChar) {
		constanteChar.setLvalue(false);
		constanteChar.setTipo(TipoChar.getInstance(constanteChar.getLinea(),
				constanteChar.getColumna()));
		return super.visit(constanteChar);
	}

	public Object visit(Variable variable) {
		if (variable.getDeclaracion() != null) {
			super.visit(variable);
			variable.setLvalue(true);
			variable.setTipo(variable.getDeclaracion().getTipo());
		}
		return null;
	}

	public Object visit(OperacionAritmetica operacionAritmetica) {
		super.visit(operacionAritmetica);
		assertTiposIguales(operacionAritmetica.getIzquierda(),
				operacionAritmetica.getDerecha());
		assertTipoPrimitivo(operacionAritmetica.getIzquierda());
		assertTipoPrimitivo(operacionAritmetica.getDerecha());
		operacionAritmetica.setLvalue(false);
		operacionAritmetica.setTipo(operacionAritmetica.getIzquierda()
				.getTipo());
		return null;
	}

	public Object visit(LlamadaFuncion llamadaFuncion) {
		if (llamadaFuncion.getDeclaracion() != null) {
			super.visit(llamadaFuncion);
			llamadaFuncion.setLvalue(false);
			llamadaFuncion
					.setTipo(llamadaFuncion.getDeclaracion().getRetorno());
			assertParametrosCorrectos(llamadaFuncion, llamadaFuncion
					.getDeclaracion().getParametros(),
					llamadaFuncion.getExpresiones());
		}
		return null;
	}

	public Object visit(LlamadaFuncionSent llamadaFuncionSent) {
		if (llamadaFuncionSent.getDeclaracion() != null) {
			super.visit(llamadaFuncionSent);
			assertParametrosCorrectos(llamadaFuncionSent, llamadaFuncionSent
					.getDeclaracion().getParametros(),
					llamadaFuncionSent.getExpresiones());
		}
		return null;
	}

	public Object visit(AccesoArray accesoArray) {
		super.visit(accesoArray);
		accesoArray.setLvalue(true);
		accesoArray.setTipo(accesoArray.getArray().getTipo());
		assertTipoEntero(accesoArray.getIndex().getTipo());
		return null;
	}

	public Object visit(AccesoCampo accesoCampo) {
		super.visit(accesoCampo);
		if (accesoCampo.getStruct().getTipo() != null) {
			accesoCampo.setLvalue(true);
			if (assertTipoStruct(accesoCampo.getStruct().getTipo())) {
				boolean encontrado = false;
				for (DeclaracionCampo dc : ((DeclaracionStruct) accesoCampo
						.getStruct().getTipo()).getDeclaraciones()) {
					if (dc.getNombre().equals(accesoCampo.getCampo())) {
						accesoCampo.setTipo(dc.getTipo());
						encontrado = true;
					}
				}
				if (!encontrado) {
					GestorErrores.addError(new TipoError(accesoCampo,
							"El campo '" + accesoCampo.getCampo()
									+ "' no ha sido declarado"));
				}
			}
		}
		return null;
	}

	public Object visit(Return ret) {
		super.visit(ret);
		if (ret.getExpresion() != null) {
			if (ret.getFuncion().getRetorno() != null) {
				if (ret.getFuncion().getRetorno().getClass() != ret
						.getExpresion().getTipo().getClass()) {
					GestorErrores
							.addError(new TipoError(ret,
									"El tipo de retorno no coincide con el tipo de la funcion"));
				}
			} else {
				GestorErrores
						.addError(new TipoError(ret,
								"Un procedimiento no puede tener sentencia de retorno"));
			}
		}
		return null;
	}

	public Object visit(Asignacion asignacion) {
		super.visit(asignacion);
		if (!asignacion.getIzquierda().isLvalue()) {
			GestorErrores
					.addError(new TipoError(asignacion,
							"La parte izquierda de una asignacion debe ser modificable"));
		}
		assertTiposIguales(asignacion.getIzquierda(), asignacion.getDerecha());
		return null;
	}

	private void assertTipoEntero(Tipo tipo) {
		if (!(tipo instanceof TipoEntero)) {
			GestorErrores.addError(new TipoError(tipo,
					"El indice de acceso a un array debe ser de tipo entero"));
		}
	}

	private boolean assertTipoStruct(Tipo tipo) {
		if (!(tipo instanceof DeclaracionStruct)) {
			GestorErrores.addError(new TipoError(tipo,
					"La variable debe ser un struct"));
			return false;
		}
		return true;
	}

	private void assertParametrosCorrectos(AST nodo,
			List<DeclaracionVariable> params, List<Expresion> call) {
		if (params.size() != call.size()) {
			GestorErrores
					.addError(new TipoError(nodo,
							"La llamada a la funcion no contiene el numero de parametros correcto"));
		} else {
			for (int i = 0; i < params.size(); i++) {
				if (params.get(i).getTipo() != call.get(i).getTipo()) {
					GestorErrores.addError(new TipoError(nodo,
							"El tipo del parametro en la posicion " + i
									+ " no es correcto"));
				}
			}
		}
	}

	private void assertTiposIguales(Expresion izquierda, Expresion derecha) {
		if (izquierda.getTipo().getClass() != derecha.getTipo().getClass()) {
			GestorErrores
					.addError(new TipoError(izquierda,
							"Las dos expresiones de la operacion no son del mismo tipo"));
		}

	}

	private void assertTipoPrimitivo(Expresion expresion) {
		if (!(expresion.getTipo() instanceof TipoEntero)
				&& !(expresion.getTipo() instanceof TipoChar)
				&& !(expresion.getTipo() instanceof TipoReal)) {
			GestorErrores.addError(new TipoError(expresion,
					"La expresion debe ser de un tipo primitivo"));
		}
	}

}
