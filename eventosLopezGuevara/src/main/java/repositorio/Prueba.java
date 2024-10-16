package repositorio;

import eventos.modelo.Evento;

public class Prueba {

	public static void main(String[] args) throws Exception {
		
		Evento encuesta = new Evento("CUMPLE", null, null, 0, false, null, null, null, null);
		
		Repositorio<Evento, String> repositorio = FactoriaRepositorios.getRepositorio(Evento.class);
		
		String id = repositorio.add(encuesta);
		
		Evento encuesta2 = repositorio.getById(id);
		
		System.out.println(encuesta2.getId());
		
		System.out.println(repositorio.getAll().size());
		System.out.println("evento 1:" + repositorio.getById("1").getNombre());
		
	}
}
