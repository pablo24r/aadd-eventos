package eventos.servicio;

import java.util.List;

import eventos.modelo.PuntoInteres;

public interface IServicioPuntoInteres {

	List<PuntoInteres> puntosCercanos(double lat, double lon) throws Exception;
}
