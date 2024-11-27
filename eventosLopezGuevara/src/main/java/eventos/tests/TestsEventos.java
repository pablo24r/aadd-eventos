package eventos.tests;

import java.time.LocalDateTime;
import java.util.List;

import eventos.modelo.Categoria;
import eventos.servicio.EventoResumen;
import eventos.servicio.IServicioEventos;
import servicio.FactoriaServicios;

public class TestsEventos {

	public static void main(String[] args) throws Exception {

		IServicioEventos servicio = FactoriaServicios.getServicio(IServicioEventos.class);

		String id = servicio.alta("Graduación", "curso 2024/25", "Decano", Categoria.DEPORTES, LocalDateTime.now(),
				LocalDateTime.now().plusDays(3), 1000, "3001");
		System.out.println(id);
		
		
		//servicio.cancelar(id);
		
		//servicio.modificar("1951", "descripcion 23123213", null, null, 666, null);
		
		List<EventoResumen> eventosMes = servicio.eventosMes(11, 2024);
		for(EventoResumen er : eventosMes)
			System.out.println(er.toString());
	}
}
