package ast.visitor;

import java.util.List;

import ast.AST;
import ast.declaracion.DeclaracionCampo;
import ast.declaracion.DeclaracionStruct;
import ast.declaracion.DeclaracionVariable;
import ast.expresion.AccesoArray;
import ast.expresion.AccesoCampo;
import ast.expresion.Cast;
import ast.expresion.Comparacion;
import ast.expresion.ConstanteChar;
import ast.expresion.ConstanteEntera;
import ast.expresion.ConstanteReal;
import ast.expresion.Expresion;
import ast.expresion.LlamadaFuncion;
import ast.expresion.MenosUnario;
import ast.expresion.NotLogico;
import ast.expresion.OperacionAritmetica;
import ast.expresion.OperacionLogica;
import ast.expresion.Variable;
import ast.sentencia.Asignacion;
import ast.sentencia.If;
import ast.sentencia.LlamadaFuncionSent;
import ast.sentencia.Print;
import ast.sentencia.Read;
import ast.sentencia.Return;
import ast.sentencia.While;
import ast.tipo.Tipo;
import ast.tipo.TipoArray;
import ast.tipo.TipoChar;
import ast.tipo.TipoEntero;
import ast.tipo.TipoError;
import ast.tipo.TipoReal;
import error.GestorErrores;

public class InferenceVisitor extends AbstractVisitor {

	public Object visit(ConstanteEntera constanteEntera, Object param) {
		constanteEntera.setLvalue(false);
		constanteEntera.setTipo(TipoEntero.getInstance(
				constanteEntera.getLinea(), constanteEntera.getColumna()));
		return super.visit(constanteEntera, null);
	}

	public Object visit(ConstanteReal constanteReal, Object param) {
		constanteReal.setLvalue(false);
		constanteReal.setTipo(TipoReal.getInstance(constanteReal.getLinea(),
				constanteReal.getColumna()));
		return super.visit(constanteReal, null);
	}

	public Object visit(ConstanteChar constanteChar, Object param) {
		constanteChar.setLvalue(false);
		constanteChar.setTipo(TipoChar.getInstance(constanteChar.getLinea(),
				constanteChar.getColumna()));
		return super.visit(constanteChar, null);
	}

	public Object visit(Variable variable, Object param) {
		if (variable.getDeclaracion() != null) {
			super.visit(variable, null);
			variable.setLvalue(true);
			variable.setTipo(variable.getDeclaracion().getTipo());
		}
		return null;
	}

	public Object visit(OperacionAritmetica operacionAritmetica, Object param) {
		super.visit(operacionAritmetica, null);
		assertTiposIguales(operacionAritmetica.getIzquierda(),
				operacionAritmetica.getDerecha());
		assertTipoPrimitivo(operacionAritmetica.getIzquierda());
		assertTipoPrimitivo(operacionAritmetica.getDerecha());
		operacionAritmetica.setLvalue(false);
		operacionAritmetica.setTipo(operacionAritmetica.getIzquierda()
				.getTipo());
		return null;
	}

	public Object visit(LlamadaFuncion llamadaFuncion, Object param) {
		if (llamadaFuncion.getDeclaracion() != null) {
			super.visit(llamadaFuncion, null);
			llamadaFuncion.setLvalue(false);
			llamadaFuncion
					.setTipo(llamadaFuncion.getDeclaracion().getRetorno());
			assertParametrosCorrectos(llamadaFuncion, llamadaFuncion
					.getDeclaracion().getParametros(),
					llamadaFuncion.getExpresiones());
		}
		return null;
	}

	public Object visit(LlamadaFuncionSent llamadaFuncionSent, Object param) {
		if (llamadaFuncionSent.getDeclaracion() != null) {
			super.visit(llamadaFuncionSent, null);
			assertParametrosCorrectos(llamadaFuncionSent, llamadaFuncionSent
					.getDeclaracion().getParametros(),
					llamadaFuncionSent.getExpresiones());
		}
		return null;
	}

	public Object visit(AccesoArray accesoArray, Object param) {
		Object ret = super.visit(accesoArray, null);
		accesoArray.setLvalue(true);
		if (assertTipoArray(accesoArray.getArray())) {
			accesoArray.setTipo((((TipoArray) accesoArray.getArray().getTipo())
					.getTipoBase()));
			if (accesoArray.getIndex().size() != ((TipoArray) accesoArray
					.getArray().getTipo()).getSizes().size()) {
				TipoError error = new TipoError(accesoArray,
						"Acceso al array con un numero de dimensiones incorrecto");
				GestorErrores.addError(error);
				accesoArray.setTipo(error);
			}
		}
		assertTipoEntero(accesoArray);
		return ret;
	}

	public Object visit(AccesoCampo accesoCampo, Object param) {
		Object ret = super.visit(accesoCampo, null);
		if (accesoCampo.getStruct().getTipo() != null) {
			accesoCampo.setLvalue(true);
			if (assertTipoStruct(accesoCampo)) {
				boolean encontrado = false;
				for (DeclaracionCampo dc : ((DeclaracionStruct) accesoCampo
						.getStruct().getTipo()).getDeclaraciones()) {
					if (dc.getNombre().equals(accesoCampo.getCampo())) {
						accesoCampo.setTipo(dc.getTipo());
						encontrado = true;
					}
				}
				if (!encontrado) {
					TipoError error = new TipoError(accesoCampo, "El campo '"
							+ accesoCampo.getCampo() + "' no ha sido declarado");
					GestorErrores.addError(error);
					accesoCampo.setTipo(error);
				}
			}
		}
		return ret;
	}

	public Object visit(Return ret, Object param) {
		Object rett = super.visit(ret, null);
		if (ret.getDeclaracionFuncion().getRetorno() != null) {
			if (ret.getExpresion() != null) {
				if (ret.getDeclaracionFuncion().getRetorno().getClass() != ret
						.getExpresion().getTipo().getClass()) {
					TipoError error = new TipoError(ret,
							"El tipo de retorno no coincide con el tipo de la funcion");
					GestorErrores.addError(error);
					ret.getExpresion().setTipo(error);
				}
			} else {
				TipoError error = new TipoError(ret,
						"Una funcion no puede retornar null");
				GestorErrores.addError(error);
			}
		} else {
			if (ret.getExpresion() != null) {
				TipoError error = new TipoError(ret,
						"Esta funcion no puede tener sentencia de retorno");
				GestorErrores.addError(error);
				ret.getExpresion().setTipo(error);
			}
		}
		return rett;
	}

	public Object visit(If sentIf, Object param) {
		Object ret = super.visit(sentIf, null);
		assertTipoEntero(sentIf);
		return ret;
	}

	public Object visit(While whil, Object param) {
		Object ret = super.visit(whil, null);
		assertTipoEntero(whil);
		return ret;
	}

	public Object visit(Asignacion asignacion, Object param) {
		super.visit(asignacion, null);
		assertTipoPrimitivo(asignacion.getIzquierda());
		if (!asignacion.getIzquierda().isLvalue()) {
			GestorErrores
					.addError(new TipoError(asignacion,
							"La parte izquierda de una asignacion debe ser modificable"));
		}
		assertTiposIguales(asignacion.getIzquierda(), asignacion.getDerecha());
		return null;
	}

	public Object visit(Print print, Object param) {
		Object ret = super.visit(print, null);
		assertPrintPrimitivo(print.getExpresion());
		return ret;
	}

	public Object visit(Read read) {
		Object ret = super.visit(read, null);
		assertTipoPrimitivo(read.getExpresion());
		if (!read.getExpresion().isLvalue()) {
			GestorErrores.addError(new TipoError(read,
					"La expresion de un read debe de ser modificable"));
		}
		return ret;
	}

	public Object visit(Comparacion comparacion, Object param) {
		super.visit(comparacion, null);
		comparacion.setTipo(TipoEntero.getInstance(comparacion.getLinea(),
				comparacion.getColumna()));
		assertTiposIguales(comparacion.getIzquierda(), comparacion.getDerecha());
		assertTipoPrimitivo(comparacion.getIzquierda());
		assertTipoPrimitivo(comparacion.getDerecha());
		return null;
	}

	public Object visit(OperacionLogica logica, Object param) {
		super.visit(logica, null);
		logica.setTipo(TipoEntero.getInstance(logica.getLinea(),
				logica.getColumna()));
		assertTipoEntero(logica.getIzquierda());
		assertTipoEntero(logica.getDerecha());
		return null;
	}

	public Object visit(Cast casteo, Object param) {
		super.visit(casteo, null);
		assertCastPrimitivo(casteo);
		assertTipoPrimitivo(casteo.getCasteo());
		casteo.setTipo(casteo.getTipoBase());
		assertTiposDistintos(casteo.getTipoBase(), casteo.getCasteo());
		return null;
	}

	public Object visit(NotLogico not, Object param) {
		super.visit(not, null);
		assertTipoEntero(not);
		not.setTipo(not.getExpresion().getTipo());
		not.setLvalue(false);
		return null;
	}

	public Object visit(MenosUnario menos, Object param) {
		super.visit(menos, null);
		assertTipoEntero(menos);
		menos.setTipo(menos.getExpresion().getTipo());
		menos.setLvalue(false);
		return null;
	}

	private void assertTipoEntero(Expresion expresion) {
		if (!(expresion.getTipo() instanceof TipoEntero)) {
			GestorErrores.addError(new TipoError(expresion,
					"La expresion debe ser de tipo entero"));
		}
	}

	private void assertTipoEntero(While whil) {
		if (!(whil.getExpresion().getTipo() instanceof TipoEntero)) {
			GestorErrores.addError(new TipoError(whil,
					"La expresion de un while debe ser de tipo entero"));
		}
	}

	private void assertTipoEntero(If sentIf) {
		if (!(sentIf.getExpresion().getTipo() instanceof TipoEntero)) {
			GestorErrores.addError(new TipoError(sentIf,
					"La expresion de un if debe ser de tipo entero"));
		}
	}

	private void assertTipoEntero(AccesoArray accesoArray) {
		for (Expresion e : accesoArray.getIndex()) {
			if (!(e.getTipo() instanceof TipoEntero)) {
				GestorErrores
						.addError(new TipoError(accesoArray,
								"El indice de acceso a un array debe ser de tipo entero"));
			}
		}
	}

	private void assertTipoEntero(NotLogico not) {
		if (!(not.getExpresion().getTipo() instanceof TipoEntero)) {
			TipoError error = new TipoError(not,
					"La expresion de una negacion debe ser de tipo entero");
			GestorErrores.addError(error);
			not.setTipo(error);
		}
	}

	private void assertTipoEntero(MenosUnario menos) {
		if (!(menos.getExpresion().getTipo() instanceof TipoEntero)) {
			TipoError error = new TipoError(menos,
					"La expresion de un menos unario debe ser de tipo entero");
			GestorErrores.addError(error);
			menos.setTipo(error);
		}
	}

	private boolean assertTipoStruct(AccesoCampo accesoCampo) {
		if (!(accesoCampo.getStruct().getTipo() instanceof DeclaracionStruct)) {
			TipoError error = new TipoError(accesoCampo,
					"La variable debe ser un struct");
			GestorErrores.addError(error);
			accesoCampo.setTipo(error);
			return false;
		}
		return true;
	}

	private boolean assertTipoArray(Expresion array) {
		if (!(array.getTipo() instanceof TipoArray)) {
			GestorErrores.addError(new TipoError(array,
					"La variable debe ser un array"));
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

	private void assertTiposDistintos(Tipo tipobase, Expresion casteo) {
		if (tipobase.getClass() == casteo.getTipo().getClass()) {
			GestorErrores.addError(new TipoError(casteo,
					"La expresion del casteo es del mismo tipo"));
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

	private void assertPrintPrimitivo(Expresion expresion) {
		if (!(expresion.getTipo() instanceof TipoEntero)
				&& !(expresion.getTipo() instanceof TipoChar)
				&& !(expresion.getTipo() instanceof TipoReal)) {
			GestorErrores.addError(new TipoError(expresion,
					"La expresion de un print debe ser de un tipo primitivo"));
		}
	}

	private void assertCastPrimitivo(Cast casteo) {
		if (!(casteo.getTipoBase() instanceof TipoEntero)
				&& !(casteo.getTipoBase() instanceof TipoChar)
				&& !(casteo.getTipoBase() instanceof TipoReal)) {
			GestorErrores.addError(new TipoError(casteo,
					"El tipo base de un casteo debe ser primitivo"));
		}
	}

}
