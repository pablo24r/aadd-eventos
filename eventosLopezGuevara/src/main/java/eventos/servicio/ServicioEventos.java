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

		// Comprobaciones
		if (espacio.getCapacidad() < numPlazas) {
			System.err
					.println("El número de plazas del evento excede la capacidad del espacio: " + espacio.getNombre());
			return null;
		} else if (!fechaDisponible(idEspacio, inicio) || !fechaDisponible(idEspacio, fin)) {
			System.err
					.println("El espacio " + espacio.getNombre() + " no está disponible en las fechas seleccionadas.");
			return null;
		} else if (!espacio.isActivo()) {
			System.err.println("El espacio " + espacio.getNombre() + " está cerrado temporalmente.");
			return null;
		} else {
			Evento evento = new Evento(nombre, descripcion, organizador, numPlazas, categoria, inicio, fin, idEspacio);
			Ocupacion oc = new Ocupacion(inicio, fin, espacio);
			evento.setOcupacion(oc);
			return repoEventos.add(evento);
		}
	}

	@Override
	public Evento modificar(String id, String descripcion, LocalDateTime inicio, LocalDateTime fin, int numPlazas,
			String idEspacio) throws RepositorioException, EntidadNoEncontrada {

		Evento evento = repoEventos.getById(id);
		if (descripcion != null)
			evento.setDescripcion(descripcion);
		if (idEspacio != null) {
			EspacioFisico espacio = repoEspacios.getById(idEspacio);
			Ocupacion oc = new Ocupacion(evento.getInicio(), evento.getFin(), espacio);
			evento.setOcupacion(oc);
		}
		if (inicio != null && fechaDisponible(evento.getOcupacion().getEspacio().getNombre(), inicio)) {
			evento.setInicio(inicio);
		}
		if (fin != null && fechaDisponible(evento.getOcupacion().getEspacio().getNombre(), fin)) {
			evento.setFin(fin);
		}
		if (numPlazas >= 0 && numPlazas <= evento.getOcupacion().getEspacio().getCapacidad())
			evento.setPlazas(numPlazas);

		repoEventos.update(evento);
		return evento;
	}

	@Override
	public void cancelar(String id) throws RepositorioException, EntidadNoEncontrada {
		Evento evento = repoEventos.getById(id);
		evento.setCancelado(true);
		evento.setOcupacion(null);
		repoEventos.update(evento);
	}

	@Override
	public List<EventoResumen> eventosMes(int month, int year) throws RepositorioException {
		List<EventoResumen> lista = new LinkedList<EventoResumen>();
		for (Evento e : repoEventos.getAll()) {
			if (e.getOcupacion() != null) {
				if ((e.getInicio().getMonth().getValue() == month && e.getInicio().getYear() == year)
						|| e.getFin().getMonth().getValue() == month && e.getFin().getYear() == year) {
					EventoResumen eRes = Evento.toResumen(e);
					lista.add(eRes);
				}
			}
		}
		return lista;
	}

	public boolean fechaDisponible(String idEspacio, LocalDateTime fecha) throws RepositorioException {
		List<Evento> eventos = repoEventos.getAll();
		for (Evento e : eventos) {
			if (!e.isCancelado()) {
				if (fecha.isAfter(e.getInicio()) && fecha.isBefore(e.getFin())) {
					return false;
				}
			}
		}
		return true;
	}

}
