package eventos.tests;

import java.time.LocalDateTime;
import java.util.List;

import eventos.modelo.Categoria;
import eventos.modelo.EspacioFisico;
import eventos.modelo.PuntoInteres;
import eventos.servicio.IServicioEspacios;
import eventos.servicio.IServicioEventos;
import eventos.servicio.IServicioPuntoInteres;
import servicio.FactoriaServicios;

public class Tests {
	public static void main(String[] args) throws Exception {
		IServicioPuntoInteres servPI = FactoriaServicios.getServicio(IServicioPuntoInteres.class);
		IServicioEspacios servEspacios = FactoriaServicios.getServicio(IServicioEspacios.class);
		IServicioEventos servEventos = FactoriaServicios.getServicio(IServicioEventos.class);

//		 ---- Test PuntoInteres ----
		List<PuntoInteres> ListaPuntosCercanos = servPI.puntosCercanos(37.99, -1.13);
		for (PuntoInteres pi : ListaPuntosCercanos) {
			System.out.println(pi.toString());
		}

//		----	Tests Espacios Físicos ----

//		String idEspacio = servEspacios.alta("Auditorio", "Ayto. Murcia", 5000, "Calle mayor 123", 37.99, -1.13, "blablabla");
//		System.out.println(idEspacio);

		EspacioFisico espacio = servEspacios.modificar("501", null, 3500, "blabla2");
		System.out.println(espacio.getNombre() + "-> capacidad modificada: " + espacio.getCapacidad());

		servEspacios.asignarPuntos("501", ListaPuntosCercanos);
		ListaPuntosCercanos = espacio.getPuntosCercanos();
		for (PuntoInteres pi : ListaPuntosCercanos) {
			System.out.println(pi.toString());
		}

		servEspacios.activar("501");

		List<EspacioFisico> espaciosDisponibles = servEspacios.buscarEspacios(LocalDateTime.now(),
				LocalDateTime.now().plusDays(7), 2000);
		System.out.println("Espacios disponibles para esta semana:");
		for (EspacioFisico ef : espaciosDisponibles) {
			System.out.println("\t" + ef.getNombre() + " -> " + ef.getDescripcion());
		}

		servEspacios.cerrar("501");
		System.out.println(espacio.getNombre() + " cerrado temporalmente.");

		espaciosDisponibles = servEspacios.buscarEspacios(LocalDateTime.now(), LocalDateTime.now().plusDays(7), 2000);
		System.out.println("Espacios disponibles para esta semana:");
		for (EspacioFisico ef : espaciosDisponibles) {
			System.out.println("\t" + ef.getNombre() + " -> " + ef.getDescripcion());
		}

//		---- Tests Eventos ----
		
		String id = servEventos.alta("Graduación", "curso 2024/25", "Decano", Categoria.ACADEMICO, LocalDateTime.now(),
				LocalDateTime.now().plusDays(3), 1000, "501");

		
	}

}
