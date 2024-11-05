package eventos.servicio;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

import eventos.modelo.EspacioFisico;
import eventos.modelo.Ocupacion;
import eventos.modelo.PuntoInteres;
import repositorio.EntidadNoEncontrada;
import repositorio.FactoriaRepositorios;
import repositorio.Repositorio;
import repositorio.RepositorioException;

public class ServicioEspacios implements IServicioEspacios {
	private Repositorio<EspacioFisico, String> repositorio = FactoriaRepositorios.getRepositorio(EspacioFisico.class);

	@Override
	public String alta(String nombre, String propietario, int capacidad, String direccion, double lat, double lng,
			String descripcion) throws RepositorioException {
		List<PuntoInteres> puntos = new LinkedList<PuntoInteres>();
		EspacioFisico espacio = new EspacioFisico(nombre, propietario, capacidad, direccion, lat, lng, puntos,
				descripcion);
		return repositorio.add(espacio);
	}

	@Override
	public void asignarPuntos(String idEspacio, List<PuntoInteres> puntosCercanos)
			throws RepositorioException, EntidadNoEncontrada {
		EspacioFisico espacio = repositorio.getById(idEspacio);
		espacio.setPuntosCercanos(puntosCercanos);
		repositorio.update(espacio);
	}

	@Override
	public EspacioFisico modificar(String idEspacio, String nombre, int capacidad, String descripcion)
			throws RepositorioException, EntidadNoEncontrada {
		EspacioFisico espacio = repositorio.getById(idEspacio);
		if (nombre != null)
			espacio.setNombre(nombre);
		if (capacidad >= 0)
			espacio.setCapacidad(capacidad);
		if (descripcion != null)
			espacio.setDescripcion(descripcion);
		repositorio.update(espacio);
		return espacio;
	}

	@Override
	public boolean cerrar(String idEspacio) throws RepositorioException, EntidadNoEncontrada {
		EspacioFisico espacio = repositorio.getById(idEspacio);
		for (Ocupacion o : espacio.getOcupaciones()) {
			if (o.isActiva())
				return false;
		}
		espacio.setActivo(false);
		repositorio.update(espacio);
		return true;
	}

	@Override
	public void activar(String idEspacio) throws RepositorioException, EntidadNoEncontrada {
		EspacioFisico espacio = repositorio.getById(idEspacio);
		espacio.setActivo(true);
		repositorio.update(espacio);
	}

	@Override
	public List<EspacioFisico> buscarEspacios(LocalDateTime inicio, LocalDateTime fin, int capacidad)
			throws RepositorioException, EntidadNoEncontrada {
		List<EspacioFisico> espacios = new LinkedList<EspacioFisico>();
		for (EspacioFisico e : repositorio.getAll()) {
			if (e.getCapacidad() >= capacidad && e.estaLibre(inicio, fin) && e.isActivo())
				espacios.add(e);
		}
		return espacios;
	}
}
