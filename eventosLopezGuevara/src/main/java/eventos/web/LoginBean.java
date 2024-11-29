package eventos.web;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import eventos.web.locale.ActiveLocale;

@SuppressWarnings("serial")
@Named
@SessionScoped
public class LoginBean implements Serializable {

	private String nombreUsuario; // Nueva propiedad para el nombre de usuario
	private String rol;

	@Inject
	private FacesContext facesContext;

	@Inject
	private ActiveLocale localeConfig;

	public void iniciarSesion(String rol) {
		if (nombreUsuario.isBlank()) {
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "",
					localeConfig.getBundle().getString("errorFaltaUsuario")));
			return;

		}

		this.rol = rol;
		System.out.println("Nombre de usuario: " + nombreUsuario);
		System.out.println("Sesión iniciada como: " + rol);

		// Redirigir según el rol
		if ("propietario".equals(rol)) {
			redirect("propietario/home.xhtml");
		} else if ("organizador".equals(rol)) {
			redirect("organizadorHome.xhtml");
		}

	}

	private void redirect(String pagina) {
		try {
			javax.faces.context.FacesContext.getCurrentInstance().getExternalContext().redirect(pagina);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getRol() {
		return rol;
	}
}
