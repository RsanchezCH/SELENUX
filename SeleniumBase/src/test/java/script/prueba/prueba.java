package script.prueba;

import org.junit.Test;
import common.Tool;

public class prueba {
	Tool tool=new Tool();

	@Test
	public void script_prueba() {
		tool.enviarcorreo("rg.sanchezchavarria@gmail.com", "Rcba1991G", "eric.manuel.mardones.vidal@everis.com;rodrigo.guillermo.sanchez.chavarria@everis.com;ignacio.andres.sagredo.bravo@everis.com", "exito en mensaje", "envio de prueba final");
	}
}
