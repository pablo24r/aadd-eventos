package eventos.servicio;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

import eventos.dto.EspacioDTO;
import eventos.modelo.EspacioFisico;
import eventos.modelo.Evento;
import eventos.modelo.PuntoInteres;
import repositorio.EntidadNoEncontrada;
import repositorio.FactoriaRepositorios;
import repositorio.Repositorio;
import repositorio.RepositorioException;

@SuppressWarnings("serial")
public class ServicioEspacios implements IServicioEspacios, Serializable {
	private Repositorio<EspacioFisico, String> repoEspacios = FactoriaRepositorios.getRepositorio(EspacioFisico.class);
	private Repositorio<Evento, String> repoEventos = FactoriaRepositorios.getRepositorio(Evento.class);

	@Override
	public String alta(String nombre, String propietario, int capacidad, String direccion, double lat, double lng,
			String descripcion) throws RepositorioException {

		// Precondiciones
		if (nombre == null || nombre.isEmpty()) {
			throw new IllegalArgumentException("El nombre del espacio no puede ser nulo o vacío.");
		}
		if (propietario == null || propietario.isEmpty()) {
			throw new IllegalArgumentException("El propietario del espacio no puede ser nulo o vacío.");
		}
		if (capacidad <= 0) {
			throw new IllegalArgumentException("La capacidad debe ser un valor positivo mayor que cero.");
		}
		if (direccion == null || direccion.isEmpty()) {
			throw new IllegalArgumentException("La dirección del espacio no puede ser nula o vacía.");
		}
		if (descripcion == null || descripcion.isEmpty()) {
			throw new IllegalArgumentException("La descripción no puede ser nula o vacía.");
		}

		// Crear espacio físico
		List<PuntoInteres> puntos = new LinkedList<PuntoInteres>();
		EspacioFisico espacio = new EspacioFisico(nombre, propietario, capacidad, direccion, lat, lng, puntos,
				descripcion);
		return repoEspacios.add(espacio);
	}

	@Override
	public void asignarPuntos(String idEspacio, List<PuntoInteres> puntosCercanos)
			throws RepositorioException, EntidadNoEncontrada {

		// Precondiciones
		if (idEspacio == null || idEspacio.isEmpty()) {
			throw new IllegalArgumentException("El ID del espacio no puede ser nulo o vacío.");
		}
		if (puntosCercanos == null) {
			throw new IllegalArgumentException("La lista de puntos cercanos no puede ser nula.");
		}

		// Asignar puntos al espacio
		EspacioFisico espacio = repoEspacios.getById(idEspacio);
		if (espacio == null) {
			throw new EntidadNoEncontrada("El espacio con ID " + idEspacio + " no existe.");
		}
		espacio.setPuntosCercanos(puntosCercanos);
		repoEspacios.update(espacio);
	}

	@Override
	public EspacioFisico modificar(String idEspacio, String nombre, int capacidad, String descripcion)
			throws RepositorioException, EntidadNoEncontrada {

		// Precondiciones
		if (idEspacio == null || idEspacio.isEmpty()) {
			throw new IllegalArgumentException("El ID del espacio no puede ser nulo o vacío.");
		}
		if (capacidad <= 0) {
			throw new IllegalArgumentException("La capacidad debe ser un valor positivo mayor que cero.");
		}

		// Modificar espacio físico
		EspacioFisico espacio = repoEspacios.getById(idEspacio);
		if (espacio == null) {
			throw new EntidadNoEncontrada("El espacio con ID " + idEspacio + " no existe.");
		}
		if (nombre != null && !nombre.isEmpty()) {
			espacio.setNombre(nombre);
		}
		if (capacidad >= 0) {
			espacio.setCapacidad(capacidad);
		}
		if (descripcion != null && !descripcion.isEmpty()) {
			espacio.setDescripcion(descripcion);
		}
		repoEspacios.update(espacio);
		return espacio;
	}

	@Override
	public boolean cerrar(String idEspacio) throws RepositorioException, EntidadNoEncontrada {

		// Precondiciones
		if (idEspacio == null || idEspacio.isEmpty()) {
			throw new IllegalArgumentException("El ID del espacio no puede ser nulo o vacío.");
		}

		// Cerrar el espacio
		EspacioFisico espacio = repoEspacios.getById(idEspacio);
		if (espacio == null) {
			throw new EntidadNoEncontrada("El espacio con ID " + idEspacio + " no existe.");
		}

		for (Evento evento : repoEventos.getAll()) {
			EspacioFisico espacioEvento = evento.getOcupacion().getEspacio();
			if (espacioEvento.equals(espacio) && evento.getOcupacion().isActiva()) {
				return false; // No se puede cerrar si el espacio está ocupado por un evento activo
			}
		}
		espacio.setActivo(false);
		repoEspacios.update(espacio);
		return true;
	}

	@Override
	public void activar(String idEspacio) throws RepositorioException, EntidadNoEncontrada {

		// Precondiciones
		if (idEspacio == null || idEspacio.isEmpty()) {
			throw new IllegalArgumentException("El ID del espacio no puede ser nulo o vacío.");
		}

		// Activar el espacio
		EspacioFisico espacio = repoEspacios.getById(idEspacio);
		if (espacio == null) {
			throw new EntidadNoEncontrada("El espacio con ID " + idEspacio + " no existe.");
		}
		espacio.setActivo(true);
		repoEspacios.update(espacio);
	}

	@Override
	public List<EspacioFisico> buscarEspacios(LocalDateTime inicio, LocalDateTime fin, int capacidad)
			throws RepositorioException, EntidadNoEncontrada {

		// Precondiciones
		if (inicio == null || fin == null) {
			throw new IllegalArgumentException("Las fechas de inicio y fin no pueden ser nulas.");
		}
		if (capacidad <= 0) {
			throw new IllegalArgumentException("La capacidad debe ser mayor que cero.");
		}

		List<EspacioFisico> espaciosDisponibles = new LinkedList<EspacioFisico>();
		List<Evento> eventos = repoEventos.getAll();
		List<EspacioFisico> espacios = repoEspacios.getAll();

		for (EspacioFisico esp : espacios) {
			boolean flag = true;
			// Si tiene suficiente capacidad y está activo
			if (esp.getCapacidad() >= capacidad && esp.isActivo()) {
				for (Evento ev : eventos) {
					EspacioFisico espEvento = ev.getOcupacion().getEspacio();
					LocalDateTime inicioEvento = ev.getOcupacion().getInicio();
					LocalDateTime finEvento = ev.getOcupacion().getFin();
					// Si el espacio es el mismo que el del evento actual, miro si coinciden fechas
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
				if (flag) {
					espaciosDisponibles.add(esp);
				}
			}
		}
		return espaciosDisponibles;
	}

	@Override
	public List<EspacioDTO> verEspaciosCreados(String usuario) throws RepositorioException {
		List<EspacioDTO> espacios = new LinkedList<EspacioDTO>();
		for (EspacioFisico ef : repoEspacios.getAll()) {
			if (ef.getPropietario().equals(usuario))
				espacios.add(ef.toDTO());
		}
		return espacios;
	}
	
	@Override
	public EspacioFisico getEspacio(String id) throws RepositorioException, EntidadNoEncontrada {
		return repoEspacios.getById(id);
	}
}