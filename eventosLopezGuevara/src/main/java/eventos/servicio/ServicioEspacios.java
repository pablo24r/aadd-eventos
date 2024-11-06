package eventos.servicio;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

import eventos.modelo.EspacioFisico;
import eventos.modelo.Evento;
import eventos.modelo.PuntoInteres;
import repositorio.EntidadNoEncontrada;
import repositorio.FactoriaRepositorios;
import repositorio.Repositorio;
import repositorio.RepositorioException;

public class ServicioEspacios implements IServicioEspacios {
	private Repositorio<EspacioFisico, String> repoEspacios = FactoriaRepositorios.getRepositorio(EspacioFisico.class);
	private Repositorio<Evento, String> repoEventos = FactoriaRepositorios.getRepositorio(Evento.class);

	@Override
	public String alta(String nombre, String propietario, int capacidad, String direccion, double lat, double lng,
			String descripcion) throws RepositorioException {
		List<PuntoInteres> puntos = new LinkedList<PuntoInteres>();
		EspacioFisico espacio = new EspacioFisico(nombre, propietario, capacidad, direccion, lat, lng, puntos,
				descripcion);
		return repoEspacios.add(espacio);
	}

	@Override
	public void asignarPuntos(String idEspacio, List<PuntoInteres> puntosCercanos)
			throws RepositorioException, EntidadNoEncontrada {
		EspacioFisico espacio = repoEspacios.getById(idEspacio);
		espacio.setPuntosCercanos(puntosCercanos);
		repoEspacios.update(espacio);
	}

	@Override
	public EspacioFisico modificar(String idEspacio, String nombre, int capacidad, String descripcion)
			throws RepositorioException, EntidadNoEncontrada {
		EspacioFisico espacio = repoEspacios.getById(idEspacio);
		if (nombre != null)
			espacio.setNombre(nombre);
		if (capacidad >= 0)
			espacio.setCapacidad(capacidad);
		if (descripcion != null)
			espacio.setDescripcion(descripcion);
		repoEspacios.update(espacio);
		return espacio;
	}

	@Override
	public boolean cerrar(String idEspacio) throws RepositorioException, EntidadNoEncontrada {
		EspacioFisico espacio = repoEspacios.getById(idEspacio);
		for (Evento evento : repoEventos.getAll()) {
			EspacioFisico espacioEvento = evento.getOcupacion().getEspacio();
			if (espacioEvento.equals(espacio) && evento.getOcupacion().isActiva())
				return false;
		}
		espacio.setActivo(false);
		repoEspacios.update(espacio);
		return true;
	}

	@Override
	public void activar(String idEspacio) throws RepositorioException, EntidadNoEncontrada {
		EspacioFisico espacio = repoEspacios.getById(idEspacio);
		espacio.setActivo(true);
		repoEspacios.update(espacio);
	}

	@Override
	public List<EspacioFisico> buscarEspacios(LocalDateTime inicio, LocalDateTime fin, int capacidad)
			throws RepositorioException, EntidadNoEncontrada {
		List<EspacioFisico> espaciosDisponibles = new LinkedList<EspacioFisico>();
		List<Evento> eventos = repoEventos.getAll();
		List<EspacioFisico> espacios = repoEspacios.getAll();

		for (EspacioFisico esp : espacios) {
			boolean flag = true;
			// Si tiene suficiente capacidad y esta activo
			if (esp.getCapacidad() >= capacidad && esp.isActivo()) { 
				for (Evento ev : eventos) {
					EspacioFisico espEvento = ev.getOcupacion().getEspacio();
					LocalDateTime inicioEvento = ev.getOcupacion().getInicio();
					LocalDateTime finEvento = ev.getOcupacion().getFin();
					 // Si el espacio es el mismo que el del evento actual  miro si coinciden fechas
					if (espEvento.equals(esp)) {
						if (inicioEvento.isAfter(inicio) && inicioEvento.isBefore(fin))
							flag = false;
						else if (finEvento.isAfter(inicio) && finEvento.isBefore(fin))
							flag = false;
						else if (inicioEvento.isBefore(inicio) && finEvento.isAfter(fin))
							flag = false;
						else if (inicioEvento.isAfter(inicio) && finEvento.isBefore(fin))
							flag = false;
						else
							flag = true;

					}
				}
				if (flag)
					espaciosDisponibles.add(esp);
			}
		}

		return espaciosDisponibles;
	}
}
