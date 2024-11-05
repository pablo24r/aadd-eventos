package eventos.servicio;

import java.time.LocalDateTime;
import java.util.List;

import eventos.modelo.Categoria;
import eventos.modelo.Evento;
import repositorio.EntidadNoEncontrada;
import repositorio.RepositorioException;

public interface IServicioEventos {

	String alta(String nombre, String descripcion, String organizador, Categoria categoria, LocalDateTime inicio,
			LocalDateTime fin, int numPlazas, String idEspacio) throws RepositorioException, EntidadNoEncontrada;

	Evento modificar(String id, String descripcion, LocalDateTime inicio, LocalDateTime fin, int numPlazas,
			String idEspacio)  throws RepositorioException, EntidadNoEncontrada;

	void cancelar(String id) throws RepositorioException, EntidadNoEncontrada ;

	List<EventoResumen> eventosMes(int month, int year) throws RepositorioException;

}
