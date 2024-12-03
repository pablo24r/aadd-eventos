package eventos.dto;

public class EspacioDTO {
	private String id;
	private String nombre;
	private String descripcion;
	private String capacidad;
	private String direccion;
	private String propietario;
	private boolean estado;

	public EspacioDTO(String id, String nombre, String descripcion, String capacidad, String direccion,
			String propietario, boolean estado) {
		super();
		this.setId(id);
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.capacidad = capacidad;
		this.direccion = direccion;
		this.propietario = propietario;
		this.estado = estado;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCapacidad() {
		return capacidad;
	}

	public void setCapacidad(String capacidad) {
		this.capacidad = capacidad;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public boolean getEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getPropietario() {
		return propietario;
	}

	public void setPropietario(String propietario) {
		this.propietario = propietario;
	}

}
