package eventos.tests;

import java.time.LocalDateTime;
import java.util.List;

import eventos.dto.EspacioDTO;
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

		String idEspacio = servEspacios.alta("Restaurante", "Juan", 200, "Plaza zzz", 37.99, -1.13, "blablabla123");
		System.out.println(idEspacio);
		
		servEspacios.modificar(idEspacio, null, 200, "descripción 2");
		servEspacios.asignarPuntos(idEspacio, ListaPuntosCercanos);
		//servEspacios.cerrar("701");
		servEspacios.activar(idEspacio);
		List<EspacioDTO> disponibles =servEspacios.buscarEspacios(LocalDateTime.now(), LocalDateTime.now().plusDays(7), 1000);
		System.out.println("Espacios disponibles esta semana:");
		for (EspacioDTO e : disponibles){
			System.out.println(e.getNombre());
		}
		
		List<EspacioDTO> espaciosPablo = servEspacios.verEspaciosCreados("Pablo");
		System.out.println("Espacios creados por Pablo:");
		for (EspacioDTO e : espaciosPablo){
			System.out.println(e.getNombre());
		}
	}
}
