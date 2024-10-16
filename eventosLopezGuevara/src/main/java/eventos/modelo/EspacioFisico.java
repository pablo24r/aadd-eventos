package eventos.modelo;

import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class EspacioFisico {
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private String id;

	private String nombre;
	private String propietario;
	private int capacidad;
	private String direccion;
	private float longitud;
	private float latitud;
	private List<PuntoInteres> PuntosInteres;
	private String descripcion;
	private boolean estado; // 0=cerrado 1=activo

	public EspacioFisico(String nombre, String propietario, int capacidad, String direccion, float longitud,
			float latitud, String descripcion) {
		this.nombre = nombre;
		this.propietario = propietario;
		this.capacidad = capacidad;
		this.direccion = direccion;
		this.longitud = longitud;
		this.latitud = latitud;
		this.descripcion = descripcion;
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

	public float getLongitud() {
		return longitud;
	}

	public void setLongitud(float longitud) {
		this.longitud = longitud;
	}

	public float getLatitud() {
		return latitud;
	}

	public void setLatitud(float latitud) {
		this.latitud = latitud;
	}

	public List<PuntoInteres> getPuntosInteres() {
		return PuntosInteres;
	}

	public void setPuntosInteres(List<PuntoInteres> puntosInteres) {
		PuntosInteres = puntosInteres;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}
	
	

}
