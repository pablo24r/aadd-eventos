package eventos.servicio;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

import eventos.modelo.Categoria;
import eventos.modelo.EspacioFisico;
import eventos.modelo.Evento;
import eventos.modelo.Ocupacion;
import repositorio.EntidadNoEncontrada;
import repositorio.FactoriaRepositorios;
import repositorio.Repositorio;
import repositorio.RepositorioException;

public class ServicioEventos implements IServicioEventos {

	private Repositorio<Evento, String> repoEventos = FactoriaRepositorios.getRepositorio(Evento.class);
	private Repositorio<EspacioFisico, String> repoEspacios = FactoriaRepositorios.getRepositorio(EspacioFisico.class);

	@Override
	public String alta(String nombre, String descripcion, String organizador, Categoria categoria, LocalDateTime inicio,
			LocalDateTime fin, int numPlazas, String idEspacio) throws RepositorioException, EntidadNoEncontrada {

		EspacioFisico espacio = repoEspacios.getById(idEspacio);
		Evento evento = new Evento(nombre, descripcion, organizador, numPlazas, categoria, inicio, fin, idEspacio);

		

		System.err.println("El número de plazas del evento excede la capacidad del espacio: " + espacio.getNombre());
		return null;
	}

	@Override
	public Evento modificar(String id, String descripcion, LocalDateTime inicio, LocalDateTime fin, int numPlazas,
			String idEspacio) throws RepositorioException, EntidadNoEncontrada {
		Evento evento = repoEventos.getById(id);

		
		return evento;
	}

	@Override
	public void cancelar(String id) throws RepositorioException, EntidadNoEncontrada {
		Evento evento = repoEventos.getById(id);
		evento.setCancelado(true);
		repoEventos.update(evento);
	}

	@Override
	public List<EventoResumen> eventosMes(int month, int year) throws RepositorioException {
		List<EventoResumen> lista = new LinkedList<EventoResumen>();
		for (Evento e : repoEventos.getAll()) {
			if ((e.getInicio().getMonth().getValue() == month && e.getInicio().getYear() == year)
					|| e.getFin().getMonth().getValue() == month && e.getFin().getYear() == year) {
				EventoResumen eRes = new EventoResumen(e.getNombre(), e.getDescripcion(), e.getInicio(), e.getFin());
				lista.add(eRes);
			}
		}
		return lista;
	}

}
