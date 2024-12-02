package eventos.web;

import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import eventos.dto.EspacioDTO;
import eventos.modelo.Categoria;
import eventos.modelo.EspacioFisico;
import eventos.servicio.ServicioEspacios;
import eventos.servicio.ServicioEventos;
import eventos.web.locale.ActiveLocale;
import repositorio.EntidadNoEncontrada;
import repositorio.RepositorioException;

@SuppressWarnings("serial")
@Named
@SessionScoped
public class OrganizadorBean implements Serializable {

	@Inject
	private ServicioEspacios servicioEspacios;

	@Inject
	private ServicioEventos servicioEventos;

	@Inject
	private FacesContext facesContext;

	@Inject
	private ActiveLocale localeConfig;

	private LocalDateTime fechaInicio;
	private LocalDateTime fechaFin;
	private String capacidadMinima = "1";
	private List<EspacioFisico> espacios;

	private EspacioDTO espacioElegido;

	private String nombreEvento;
	private String descripcionEvento;
	private String nombreOrganizador;
	private LocalDateTime fechaInicioEvento;
	private LocalDateTime fechaFinEvento;
	private String[] categorias = { "ACADEMICO", "CULTURAL", "ENTRETENIMIENTO", "DEPORTES", "OTROS" };
	private String categoriaEvento;
	private String numPlazasEvento;

	private boolean mostrarPanel;

	public void comprobarRol(String usuario, String rol) {
		if (!rol.equals("organizador")) {
			try {
				// Flash Scope para mantener el mensaje tras la redirección
				FacesContext facesContext = FacesContext.getCurrentInstance();
				facesContext.getExternalContext().getFlash().setKeepMessages(true);
				facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
						localeConfig.getBundle().getString("errorRol2")));
				facesContext.getExternalContext().redirect("/index.xhtml");
			} catch (IOException e) {
				facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
						localeConfig.getBundle().getString("errorGen")));
			}
		}
		nombreOrganizador = usuario;
	}

	public void buscarEspacios() {
		mostrarPanel = true;
		try {
			espacios = servicioEspacios.buscarEspacios(fechaInicio, fechaFin, Integer.parseInt(capacidadMinima));
			if (espacios.isEmpty()) {
				facesContext.addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_INFO, "No se encontraron espacios disponibles.", ""));
			}
		} catch (Exception e) {
			facesContext.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al buscar espacios: " + e.getMessage(), ""));
		}
	}

	public void formularioEvento(String idEspacio) {
		try {
			espacioElegido = servicioEspacios.getEspacio(idEspacio).toDTO();
			javax.faces.context.FacesContext.getCurrentInstance().getExternalContext()
					.redirect("/organizador/nuevoEvento.xhtml");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void crearEvento() {
		Categoria categoria = null;
		switch (categoriaEvento) {
		case "ACADEMICO": {
			categoria = Categoria.ACADEMICO;
			break;
		}
		case "CULTURAL": {
			categoria = Categoria.CULTURAL;
			break;
		}
		case "ENTRETENIMIENTO": {
			categoria = Categoria.ENTRETENIMIENTO;
			break;
		}
		case "DEPORTES": {
			categoria = Categoria.DEPORTES;
			break;
		}
		case "OTROS": {
			categoria = Categoria.OTROS;
			break;
		}
		}
		try {
			System.out.println(numPlazasEvento);
			servicioEventos.alta(nombreEvento, descripcionEvento, nombreOrganizador, categoria, fechaInicio, fechaFin,
					Integer.parseInt(numPlazasEvento), espacioElegido.getId());
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito", "EVENTO CREADO"));
		} catch (NumberFormatException | RepositorioException | EntidadNoEncontrada e) {
			facesContext.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "ERROR AL CREAR EL EVENTO"));
			
		}
	}

	public void volver() {
		setMostrarPanel(false);
		try {
			javax.faces.context.FacesContext.getCurrentInstance().getExternalContext()
					.redirect("/organizador/home.xhtml");
		} catch (Exception e) {
			e.printStackTrace();
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

	public String getCapacidadMinima() {
		return capacidadMinima;
	}

	public void setCapacidadMinima(String capacidadMinima) {
		this.capacidadMinima = capacidadMinima;
	}

	public List<EspacioFisico> getEspacios() {
		return espacios;
	}

	public void setEspacios(List<EspacioFisico> espaciosDisponibles) {
		this.espacios = espaciosDisponibles;
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


	public boolean isMostrarPanel() {
		return mostrarPanel;
	}

	public void setMostrarPanel(boolean mostrarPanel) {
		this.mostrarPanel = mostrarPanel;
	}

	public String[] getCategorias() {
		return categorias;
	}

	public void setCategorias(String[] categorias) {
		this.categorias = categorias;
	}

	public String getCategoriaEvento() {
		return categoriaEvento;
	}

	public void setCategoriaEvento(String categoriaEvento) {
		this.categoriaEvento = categoriaEvento;
	}

	public String getNumPlazasEvento() {
		return numPlazasEvento;
	}

	public void setNumPlazasEvento(String numPlazasEvento) {
		this.numPlazasEvento = numPlazasEvento;
	}

	public EspacioDTO getEspacioElegido() {
		return espacioElegido;
	}

	public void setEspacioElegido(EspacioDTO espacioElegido) {
		this.espacioElegido = espacioElegido;
	}

}
