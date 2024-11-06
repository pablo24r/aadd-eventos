package eventos.modelo;

import java.io.Serializable;

public class PuntoInteres implements Serializable {

	private static final long serialVersionUID = 1L;
	private String nombre; // Suponemos que el nombre es unico
	private String descripcion;
	private double distancia;
	private String urlWiki;

	public PuntoInteres(String nombre, String descripcion, double distancia, String urlWiki) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.distancia = distancia;
		this.urlWiki = urlWiki;
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

	public double getDistancia() {
		return distancia;
	}

	public void setDistancia(double distancia) {
		this.distancia = distancia;
	}

	public String getUrlWiki() {
		return urlWiki;
	}

	public void setUrlWiki(String urlWiki) {
		this.urlWiki = urlWiki;
	}

	@Override
	public String toString() {
		return "PuntoInteres [nombre=" + nombre + ", descripcion=" + descripcion + ", distancia=" + distancia
				+ ", urlWiki=" + urlWiki + "]";
	}

}
