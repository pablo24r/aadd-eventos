package eventos.servicio;

import java.time.LocalDateTime;
import java.util.List;

import eventos.modelo.Categoria;

public class EventoResumen {

	String nombre, descripcion, nombreEspacio, dirEspacio;
	LocalDateTime inicio;
	Categoria categoria;
	List<String> puntosCercanos;

	public EventoResumen(String nombre, String descripcion, String nombreEspacio, String dirEspacio,
			LocalDateTime inicio, Categoria categoria, List<String> puntosCercanos) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.nombreEspacio = nombreEspacio;
		this.dirEspacio = dirEspacio;
		this.inicio = inicio;
		this.categoria = categoria;
		this.puntosCercanos = puntosCercanos;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getNombreEspacio() {
		return nombreEspacio;
	}

	public void setNombreEspacio(String nombreEspacio) {
		this.nombreEspacio = nombreEspacio;
	}

	public String getDirEspacio() {
		return dirEspacio;
	}

	public void setDirEspacio(String dirEspacio) {
		this.dirEspacio = dirEspacio;
	}

	public LocalDateTime getInicio() {
		return inicio;
	}

	public void setInicio(LocalDateTime inicio) {
		this.inicio = inicio;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public List<String> getPuntosCercanos() {
		return puntosCercanos;
	}

	public void setPuntosCercanos(List<String> puntosCercanos) {
		this.puntosCercanos = puntosCercanos;
	}

	@Override
	public String toString() {
		return "EventoResumen [nombre=" + nombre + ", descripcion=" + descripcion + ", nombreEspacio=" + nombreEspacio
				+ ", dirEspacio=" + dirEspacio + ", inicio=" + inicio + ", categoria=" + categoria + ", puntosCercanos="
				+ puntosCercanos + "]";
	}

}
