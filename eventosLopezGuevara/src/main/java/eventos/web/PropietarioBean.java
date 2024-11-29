package eventos.web;

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

	private List<EspacioDTO> espacios;
	private String usuario;

	// Método para cargar los espacios creados por el propietario
	public void cargarEspacios(String usuario) {
		this.usuario = usuario;
		try {
			espacios = servicioEspacios.verEspaciosCreados(usuario);
		} catch (RepositorioException e) {
			facesContext.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudieron cargar los espacios."));
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
			cargarEspacios(usuario); // Cambia esto con el usuario actual
		} catch (RepositorioException | EntidadNoEncontrada e) {
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
					"No se pudo cambiar el estado del espacio."));
		}
	}
}
