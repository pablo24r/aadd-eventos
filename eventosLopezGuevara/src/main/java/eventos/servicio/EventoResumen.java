package eventos.servicio;

import java.time.LocalDateTime;

public class EventoResumen {

	String nombre, descripcion;
	LocalDateTime inicio, fin;

	public EventoResumen(String nombre, String descripcion, LocalDateTime inicio, LocalDateTime fin) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.inicio = inicio;
		this.fin = fin;
	}

	@Override
	public String toString() {
		return "EventoResumen [nombre=" + nombre + ", descripcion=" + descripcion + ", inicio=" + inicio + ", fin="
				+ fin + "]";
	}
	
	

}
