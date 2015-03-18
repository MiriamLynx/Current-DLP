package error;

import java.util.ArrayList;
import java.util.List;

import ast.tipo.TipoError;

public class GestorErrores {

	private List<TipoError> errores = new ArrayList<TipoError>();

	public void aņadirError(TipoError e) {
		errores.add(e);
	}

	public void mostrarErrores() {
		for (TipoError e : errores) {
			System.out.println(e.toString());
		}
	}

	public boolean hayErrores() {
		return errores.size() != 0;
	}

	public List<TipoError> getErrores() {
		return errores;
	}

	public void setErrores(List<TipoError> errores) {
		this.errores = errores;
	}
}