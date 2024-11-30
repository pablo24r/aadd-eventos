package eventos.web;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import eventos.modelo.Categoria;
import eventos.modelo.EspacioFisico;
import eventos.servicio.ServicioEspacios;
import eventos.servicio.ServicioEventos;
import repositorio.EntidadNoEncontrada;
import repositorio.RepositorioException;

@SuppressWarnings("serial")
@Named
@ViewScoped
public class OrganizadorBean implements Serializable {

	@Inject
	private ServicioEspacios servicioEspacios;

	@Inject
	private ServicioEventos servicioEventos;

	@Inject
	private FacesContext facesContext;

	private LocalDateTime fechaInicio;
	private LocalDateTime fechaFin;
	private int capacidadMinima;
	private List<EspacioFisico> espaciosDisponibles;

	private String nombreEvento;
	private String descripcionEvento;
	private String nombreOrganizador;
	private LocalDateTime fechaInicioEvento;
	private LocalDateTime fechaFinEvento;
	private String numPlazas;
	private String idEspacio;

	// Métodos para buscar espacios disponibles
	public void buscarEspacios() throws RepositorioException, EntidadNoEncontrada {
		espaciosDisponibles = servicioEspacios.buscarEspacios(fechaInicio, fechaFin, capacidadMinima);
		System.out.println(espaciosDisponibles.size());
	}

	// Métodos para crear evento
	public void crearEvento() {
		try {
			servicioEventos.alta(nombreEvento, descripcionEvento, nombreOrganizador, Categoria.ACADEMICO,
					fechaInicioEvento, fechaFinEvento, Integer.parseInt(numPlazas), idEspacio);
			facesContext.addMessage(null, new FacesMessage("Evento creado exitosamente."));
		} catch (Exception e) {
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage()));
		}
	}

	public ServicioEspacios getServicioEspacios() {
		return servicioEspacios;
	}

	public void setServicioEspacios(ServicioEspacios servicioEspacios) {
		this.servicioEspacios = servicioEspacios;
	}

	public ServicioEventos getServicioEventos() {
		return servicioEventos;
	}

	public void setServicioEventos(ServicioEventos servicioEventos) {
		this.servicioEventos = servicioEventos;
	}

	public FacesContext getFacesContext() {
		return facesContext;
	}

	public void setFacesContext(FacesContext facesContext) {
		this.facesContext = facesContext;
	}

	public LocalDateTime getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(LocalDateTime fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public LocalDateTime getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(LocalDateTime fechaFin) {
		this.fechaFin = fechaFin;
	}

	public int getCapacidadMinima() {
		return capacidadMinima;
	}

	public void setCapacidadMinima(int capacidadMinima) {
		this.capacidadMinima = capacidadMinima;
	}

	public List<EspacioFisico> getEspaciosDisponibles() {
		return espaciosDisponibles;
	}

	public void setEspaciosDisponibles(List<EspacioFisico> espaciosDisponibles) {
		this.espaciosDisponibles = espaciosDisponibles;
	}

	public String getNombreEvento() {
		return nombreEvento;
	}

	public void setNombreEvento(String nombreEvento) {
		this.nombreEvento = nombreEvento;
	}

	public String getDescripcionEvento() {
		return descripcionEvento;
	}

	public void setDescripcionEvento(String descripcionEvento) {
		this.descripcionEvento = descripcionEvento;
	}

	public String getNombreOrganizador() {
		return nombreOrganizador;
	}

	public void setNombreOrganizador(String nombreOrganizador) {
		this.nombreOrganizador = nombreOrganizador;
	}

	public LocalDateTime getFechaInicioEvento() {
		return fechaInicioEvento;
	}

	public void setFechaInicioEvento(LocalDateTime fechaInicioEvento) {
		this.fechaInicioEvento = fechaInicioEvento;
	}

	public LocalDateTime getFechaFinEvento() {
		return fechaFinEvento;
	}

	public void setFechaFinEvento(LocalDateTime fechaFinEvento) {
		this.fechaFinEvento = fechaFinEvento;
	}

	public String getNumPlazas() {
		return numPlazas;
	}

	public void setNumPlazas(String numPlazas) {
		this.numPlazas = numPlazas;
	}

	public String getIdEspacio() {
		return idEspacio;
	}

	public void setIdEspacio(String idEspacio) {
		this.idEspacio = idEspacio;
	}

}
