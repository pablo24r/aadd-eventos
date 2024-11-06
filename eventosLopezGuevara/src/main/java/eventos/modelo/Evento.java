package eventos.modelo;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import eventos.servicio.EventoResumen;
import repositorio.Identificable;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class Evento implements Identificable {
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private String id;
	private String nombre;
	private String descripcion;
	private String organizador;
	private int plazas;
	private boolean cancelado = false;
	private Categoria categoria;
	@Embedded  
	private Ocupacion ocupacion;

	public Evento(String nombre, String descripcion, String organizador, int plazas, Categoria categoria,
			LocalDateTime inicio, LocalDateTime fin, String idEspacioFisico) {

		this.nombre = nombre;
		this.descripcion = descripcion;
		this.organizador = organizador;
		this.plazas = plazas;
		this.categoria = categoria;
	}

	public Evento() {

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getOrganizador() {
		return organizador;
	}

	public void setOrganizador(String organizador) {
		this.organizador = organizador;
	}

	public int getPlazas() {
		return plazas;
	}

	public void setPlazas(int plazas) {
		this.plazas = plazas;
	}

	public boolean isCancelado() {
		return cancelado;
	}

	public void setCancelado(boolean cancelado) {
		this.cancelado = cancelado;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public LocalDateTime getInicio() {
		return ocupacion.getInicio();
	}

	public void setInicio(LocalDateTime inicio) {
		ocupacion.setInicio(inicio);
	}

	public LocalDateTime getFin() {
		return ocupacion.getFin();
	}

	public void setFin(LocalDateTime fin) {
		ocupacion.setFin(fin);
	}

	public Ocupacion getOcupacion() {
		return ocupacion;
	}

	public void setOcupacion(Ocupacion ocupacion) {
		this.ocupacion = ocupacion;
	}
	
	public static EventoResumen toResumen(Evento evento) {
	    if (evento == null) {
	        throw new IllegalArgumentException("El evento no puede ser nulo");
	    }

	    // Extraer los datos básicos del evento
	    String nombre = evento.getNombre();
	    String descripcion = evento.getDescripcion();
	    LocalDateTime inicio = evento.getInicio();
	    Categoria categoria = evento.getCategoria();

	    // Extraer los datos del espacio físico desde la ocupación
	    String nombreEspacio = evento.getOcupacion().getEspacio().getNombre();
	    String direccionEspacio = evento.getOcupacion().getEspacio().getDireccion();

	    // Obtener los puntos de interés y ordenarlos por distancia ascendente
	    List<PuntoInteres> puntosCercanos = evento.getOcupacion().getEspacio().getPuntosCercanos();

	    // Asumimos que PuntoInteres tiene un método 'getDistancia()' que devuelve la distancia.
	    puntosCercanos.sort((p1, p2) -> Double.compare(p1.getDistancia(), p2.getDistancia()));

	    // Crear la lista de nombres de los puntos de interés
	    List<String> puntosCercanosNombres = puntosCercanos.stream()
	            .map(PuntoInteres::getNombre) 
	            .collect(Collectors.toList());

	    // Crear y devolver el resumen del evento
	    return new EventoResumen(nombre, descripcion, nombreEspacio, direccionEspacio, inicio, categoria, puntosCercanosNombres);
	}

}
