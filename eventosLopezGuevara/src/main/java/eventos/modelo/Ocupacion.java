package eventos.modelo;

import java.time.LocalDateTime;

import javax.persistence.Embeddable;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import utils.LocalDateTimeAdapter;

@Embeddable
public class Ocupacion {
	@XmlJavaTypeAdapter(value = LocalDateTimeAdapter.class)
	private LocalDateTime inicio;
	@XmlJavaTypeAdapter(value = LocalDateTimeAdapter.class)
	private LocalDateTime fin;
	private EspacioFisico espacio;

	public Ocupacion(LocalDateTime inicio, LocalDateTime fin, EspacioFisico espacio) {
		super();
		this.inicio = inicio;
		this.fin = fin;
		this.espacio = espacio;
	}

	public Ocupacion() { // POJO
	
	}

	public LocalDateTime getInicio() {
		return inicio;
	}

	public void setInicio(LocalDateTime inicio) {
		this.inicio = inicio;
	}

	public LocalDateTime getFin() {
		return fin;
	}

	public void setFin(LocalDateTime fin) {
		this.fin = fin;
	}

	public EspacioFisico getEspacio() {
		return espacio;
	}

	public void setEspacio(EspacioFisico espacio) {
		this.espacio = espacio;
	}

	// Una ocupación se considera activa si su fecha de fin es posterior a la fecha
	// actual.
	public boolean isActiva() {
		return fin.isAfter(LocalDateTime.now());
	}

}
