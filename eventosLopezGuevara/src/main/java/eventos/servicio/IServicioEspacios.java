package eventos.servicio;

import java.time.LocalDateTime;
import java.util.List;

import eventos.modelo.EspacioFisico;
import eventos.modelo.PuntoInteres;
import repositorio.EntidadNoEncontrada;
import repositorio.RepositorioException;

public interface IServicioEspacios {

	String alta(String nombre, String propietario, int capacidad, String direccion, double lat, double lng,
			String descripcion) throws RepositorioException;

	void asignarPuntos(String idEspacio, List<PuntoInteres> puntosCercanos) throws RepositorioException, EntidadNoEncontrada ;
	
	EspacioFisico modificar (String idEspacio, String nombre, int capacidad, String descripcion) throws RepositorioException, EntidadNoEncontrada ;
	
	boolean cerrar(String idEspacio) throws RepositorioException, EntidadNoEncontrada ;
	
	void activar(String idEspacio) throws RepositorioException, EntidadNoEncontrada ;
	
	List<EspacioFisico> buscarEspacios(LocalDateTime inicio, LocalDateTime fin, int capacidad) throws RepositorioException, EntidadNoEncontrada ;
}
