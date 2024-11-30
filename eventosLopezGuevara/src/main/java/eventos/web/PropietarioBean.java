package eventos.web;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import eventos.dto.EspacioDTO;
import eventos.modelo.EspacioFisico;
import eventos.servicio.ServicioEspacios;
import eventos.web.locale.ActiveLocale;
import repositorio.EntidadNoEncontrada;
import repositorio.RepositorioException;

@SuppressWarnings("serial")
@Named
@ViewScoped
public class PropietarioBean implements Serializable {

	@Inject
	protected FacesContext facesContext;

	@Inject
	private ServicioEspacios servicioEspacios;
	
	@Inject
	private ActiveLocale localeConfig;

	private List<EspacioDTO> espacios;
	private String usuario;
	private String rol;

	// Método para cargar los espacios creados por el propietario
	public void cargarEspacios(String usuario, String rol) {
		if (rol.equals("propietario")) {
			this.usuario = usuario;
			this.rol = rol;
			try {
				espacios = servicioEspacios.verEspaciosCreados(usuario);
			} catch (RepositorioException e) {
				facesContext.addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", localeConfig.getBundle().getString("errorEspacios")));
			}
		} else {
			try {
				// Flash Scope para mantener el mensaje tras la redirección
				FacesContext facesContext = FacesContext.getCurrentInstance();
				facesContext.getExternalContext().getFlash().setKeepMessages(true);
				facesContext.addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", localeConfig.getBundle().getString("errorRol1")));
				facesContext.getExternalContext().redirect("/index.xhtml");
			} catch (IOException e) {
				facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
						localeConfig.getBundle().getString("errorGen")));
			}
		}
	}

	// Getter para acceder a los espacios en la vista
	public List<EspacioDTO> getEspacios() {
		return espacios;
	}

	public void cambiarEstado(String idEspacio) {
		System.out.println("Cerrar espacio con id:" + idEspacio);
		try {
			EspacioFisico espacio = servicioEspacios.getEspacio(idEspacio);
			if (espacio.isActivo()) {
				servicioEspacios.cerrar(idEspacio);
			} else {
				servicioEspacios.activar(idEspacio);
			}
			// Recargar la lista de espacios para reflejar los cambios
			cargarEspacios(usuario, rol); // Cambia esto con el usuario actual
		} catch (RepositorioException | EntidadNoEncontrada e) {
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
					localeConfig.getBundle().getString("errorEstado")));
		}
	}
}
