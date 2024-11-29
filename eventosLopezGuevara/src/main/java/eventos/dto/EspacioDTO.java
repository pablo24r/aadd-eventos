package eventos.dto;

public class EspacioDTO {
	private String id;
	private String nombre;
	private String capacidad;
	private String direccion;
	private String estado;
	

	public EspacioDTO(String id,String nombre, String capacidad, String direccion, String estado) {
		super();
		this.setId(id);
		this.nombre = nombre;
		this.capacidad = capacidad;
		this.direccion = direccion;
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

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
