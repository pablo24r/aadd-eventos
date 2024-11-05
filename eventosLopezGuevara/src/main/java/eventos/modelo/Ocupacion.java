package eventos.modelo;

import java.time.LocalDateTime;

public class Ocupacion {

	private LocalDateTime inicio;
	private LocalDateTime fin;
	private EspacioFisico espacio;

	public Ocupacion(LocalDateTime inicio, LocalDateTime fin, EspacioFisico espacio) {
		super();
		this.inicio = inicio;
		this.fin = fin;
		this.espacio = espacio;
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
	
	//  Una ocupación se considera activa si su fecha de fin es posterior a la fecha actual.
	public boolean isActiva() {
		return fin.isAfter(LocalDateTime.now()); 
	}


}
