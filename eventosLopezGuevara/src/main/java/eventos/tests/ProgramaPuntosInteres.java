package eventos.tests;

import java.util.List;

import eventos.modelo.PuntoInteres;
import eventos.servicio.IServicioPuntoInteres;
import servicio.FactoriaServicios;

public class ProgramaPuntosInteres {
	public static void main(String[] args) throws Exception {
		IServicioPuntoInteres servicio = FactoriaServicios.getServicio(IServicioPuntoInteres.class);
		List<PuntoInteres> puntos = servicio.puntosCercanos(37.9838, -1.1283);
		for (PuntoInteres pi : puntos) {
			System.out.println(pi.getNombre());
			System.out.println(pi.getDescripcion());
			System.out.println(pi.getUrlWiki());
			System.out.println();
		}
	}

}
