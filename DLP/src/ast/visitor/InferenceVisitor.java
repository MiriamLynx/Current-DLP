package ast.visitor;

import ast.expresion.ConstanteChar;
import ast.expresion.ConstanteEntera;
import ast.expresion.ConstanteReal;
import ast.expresion.Expresion;
import ast.expresion.OperacionAritmetica;
import ast.expresion.Variable;
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
		variable.setLvalue(true);
		variable.setTipo(variable.getDeclaracion().getTipo());
		return super.visit(variable);
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
