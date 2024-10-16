package eventos.modelo;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

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
	private boolean cancelado;
	private Categoria categoria;
	//private Ocupacion ocupacion;

	public Evento(String nombre, String descripcion, String organizador, int plazas, boolean cancelado,
			Categoria categoria, LocalDateTime inicio, LocalDateTime fin, String idEspacioFisico) {

		this.nombre = nombre;
		this.descripcion = descripcion;
		this.organizador = organizador;
		this.plazas = plazas;
		this.cancelado = cancelado;
		this.categoria = categoria;
		// this.ocupacion = new Ocupacion(inicio, fin, );
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
/*
	public Ocupacion getOcupacion() {
		return ocupacion;
	}

	public void setOcupacion(Ocupacion ocupacion) {
		this.ocupacion = ocupacion;
	}
*/
}
