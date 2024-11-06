package eventos.tests;

import java.time.LocalDateTime;
import java.util.List;

import eventos.modelo.EspacioFisico;
import eventos.modelo.PuntoInteres;
import eventos.servicio.IServicioEspacios;
import eventos.servicio.IServicioPuntoInteres;
import servicio.FactoriaServicios;

public class TestsEspacios {
	public static void main(String[] args) throws Exception {
		IServicioPuntoInteres servPI = FactoriaServicios.getServicio(IServicioPuntoInteres.class);
		IServicioEspacios servEspacios = FactoriaServicios.getServicio(IServicioEspacios.class);
		
//		 ---- Test PuntoInteres ----
		List<PuntoInteres> ListaPuntosCercanos = servPI.puntosCercanos(37.99, -1.13);
		for (PuntoInteres pi : ListaPuntosCercanos) {
			System.out.println(pi.toString());
		}

//		----	Tests Espacios Físicos ----

//		String idEspacio = servEspacios.alta("Auditorio", "Ayto. Murcia", 5000, "Calle mayor 123", 37.99, -1.13, "blablabla");
//		System.out.println(idEspacio);
		
		servEspacios.modificar("701", null, 1000, "descripción 9");
		servEspacios.asignarPuntos("701", ListaPuntosCercanos);
		//servEspacios.cerrar("701");
		servEspacios.activar("701");
		List<EspacioFisico> disponibles =servEspacios.buscarEspacios(LocalDateTime.now(), LocalDateTime.now().plusDays(7), 1000);
		System.out.println("Espacios disponibles esta semana:");
		for (EspacioFisico e : disponibles){
			System.out.println(e.getNombre());
		}
	}
}
