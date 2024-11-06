package eventos.tests;

import java.time.LocalDateTime;

import eventos.modelo.Categoria;
import eventos.servicio.IServicioEventos;
import servicio.FactoriaServicios;

public class TestsEventos {

	public static void main(String[] args) throws Exception {

//		Evento ev = new Evento("Apertura curso 24/25", "blablablabablabal", "Pablo L", 100, Categoria.ACADEMICO,
//				LocalDateTime.now(), LocalDateTime.now().plusDays(3), "patio");
//
//		Repositorio<Evento, String> repositorio = FactoriaRepositorios.getRepositorio(Evento.class);
//
//		String id = repositorio.add(ev);
//
//		Evento ev2 = repositorio.getById(id);
//
//		System.out.println(ev2.getId());
//
//		System.out.println(repositorio.getAll().size());
//		System.out.println("ids:" + repositorio.getIds().toString());

		IServicioEventos servicio = FactoriaServicios.getServicio(IServicioEventos.class);

		String id = servicio.alta("Graduación", "curso 2024/25", "Decano", Categoria.ACADEMICO, LocalDateTime.now(),
				LocalDateTime.now().plusDays(3), 1000, "701");
		System.out.println(id);
	}
}
