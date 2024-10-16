package eventos.modelo;

import java.time.LocalDateTime;

import repositorio.Identificable;

public class Ocupacion implements Identificable{

	private LocalDateTime inicio;
	private LocalDateTime fin;
	private EspacioFisico espacio;
	private boolean activa = true;

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

	public boolean isActiva() {
		return activa;
	}

	public void setActiva(boolean activa) {
		this.activa = activa;
	}

	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setId(String id) {
		// TODO Auto-generated method stub
		
	}

}
