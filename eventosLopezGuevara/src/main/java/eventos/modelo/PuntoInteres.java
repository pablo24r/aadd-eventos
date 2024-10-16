package eventos.modelo;

public class PuntoInteres {

	private String nombre;

	private String descripcion;

	private int distancia;

	private String urlWiki;

	public PuntoInteres(String nombre, String descripcion, int distancia, String urlWiki) {
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

	public int getDistancia() {
		return distancia;
	}

	public void setDistancia(int distancia) {
		this.distancia = distancia;
	}

	public String getUrlWiki() {
		return urlWiki;
	}

	public void setUrlWiki(String urlWiki) {
		this.urlWiki = urlWiki;
	}

}
