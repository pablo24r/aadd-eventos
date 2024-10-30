package eventos.servicio;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

import eventos.modelo.Categoria;
import eventos.modelo.Evento;
import repositorio.EntidadNoEncontrada;
import repositorio.FactoriaRepositorios;
import repositorio.Repositorio;
import repositorio.RepositorioException;

public class ServicioEventos implements IServicioEventos {

	private Repositorio<Evento, String> repositorio = FactoriaRepositorios.getRepositorio(Evento.class);

	@Override
	public String alta(String nombre, String descripcion, String organizador, Categoria categoria, LocalDateTime inicio,
			LocalDateTime fin, int numPlazas, String idEspacio) throws RepositorioException {
		Evento evento = new Evento(nombre, descripcion, organizador, numPlazas, categoria, inicio, fin, idEspacio);
		return repositorio.add(evento);
	}

	@Override
	public Evento modificar(String id, String descripcion, LocalDateTime inicio, LocalDateTime fin, int numPlazas,
			String idEspacio) throws RepositorioException, EntidadNoEncontrada {
		Evento evento = repositorio.getById(id);

		if (descripcion != null)
			evento.setDescripcion(descripcion);
		if (inicio != null)
			evento.setInicio(inicio);
		if (fin != null)
			evento.setFin(fin);
		if (numPlazas != -1)
			evento.setPlazas(numPlazas);
//TODO	if(idEspacio != null) evento.set ;
		repositorio.update(evento);
		return evento;
	}

	@Override
	public void cancelar(String id) throws RepositorioException, EntidadNoEncontrada {
		Evento evento = repositorio.getById(id);
		evento.setCancelado(true);
		repositorio.update(evento);
	}

	@Override
	public List<EventoResumen> eventosMes(int month, int year) throws RepositorioException {
		List<EventoResumen> lista = new LinkedList<EventoResumen>();
		for (Evento e : repositorio.getAll()) {
			if (e.getInicio().getMonth().getValue() == month && e.getInicio().getYear() == year) {
				EventoResumen eRes = new EventoResumen(e.getNombre(), e.getDescripcion(), e.getInicio(), e.getFin());
				lista.add(eRes);
			}
		}
		return lista;
	}

}
