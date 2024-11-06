package eventos.modelo;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import repositorio.Identificable;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class EspacioFisico implements Identificable {
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private String id;
	private String nombre;
	private String propietario;
	private int capacidad;
	private String direccion;
	private double longitud;
	private double latitud;
	@Lob
	private List<PuntoInteres> puntosCercanos;
	private String descripcion;
	private boolean activo;

	public EspacioFisico(String nombre, String propietario, int capacidad, String direccion, double lat, double lng,
			List<PuntoInteres> lista, String descripcion) {
		this.nombre = nombre;
		this.propietario = propietario;
		this.capacidad = capacidad;
		this.direccion = direccion;
		this.latitud = lat;
		this.longitud = lng;
		this.puntosCercanos = lista;
		this.descripcion = descripcion;
		this.activo = true;
	}
	
	public EspacioFisico() {

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

	public String getPropietario() {
		return propietario;
	}

	public void setPropietario(String propietario) {
		this.propietario = propietario;
	}

	public int getCapacidad() {
		return capacidad;
	}

	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public double getLongitud() {
		return longitud;
	}

	public void setLongitud(double longitud) {
		this.longitud = longitud;
	}

	public double getLatitud() {
		return latitud;
	}

	public void setLatitud(double latitud) {
		this.latitud = latitud;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public List<PuntoInteres> getPuntosCercanos() {
		return puntosCercanos;
	}

	public void setPuntosCercanos(List<PuntoInteres> puntosCercanos) {
		this.puntosCercanos = puntosCercanos;
	}



}
