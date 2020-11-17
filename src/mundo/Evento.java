package mundo;

public class Evento {
	
	private String evento;
	
	private String lugar;
	
	private String descripcion;

	private String imagen;
	
	public Evento(String evento, String lugar, String descripcion, String imagen) {
		this.imagen = imagen;
		this.evento = evento;
		this.lugar = lugar;
		this.descripcion = descripcion;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public String getEvento() {
		return evento;
	}

	public void setEvento(String evento) {
		this.evento = evento;
	}

	public String getLugar() {
		return lugar;
	}

	public void setLugar(String lugar) {
		this.lugar = lugar;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	public String toString() {
		return "Evento: "+evento + " \nLugar: " + lugar + "\nDescripcion:" + descripcion+"\n";
	}

	
}
