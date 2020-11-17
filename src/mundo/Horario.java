package mundo;

import java.io.*;

import javax.swing.JOptionPane;

public class Horario {

	private Evento eventos[][];

	private final int dias = 31;

	private final int horas = 24;

	public Horario() {

		eventos = new Evento[dias][horas];
		leerdatos();
	}

	public void crear_evento(int dia, int hora, Evento evento) {
		eventos[dia - 1][hora - 1] = evento;
	}

	public boolean leerdatos() {
		try {
			BufferedReader br = new BufferedReader(new FileReader("./data/datos.txt"));
			String hola = br.readLine();
			while (hola != null) {
				boolean pasa = true;
				String descripcion = "";
				do {
					if (hola.equals("**")) {
						pasa = false;
					} else {
						if (hola.equals("*")) {
						} else {
							if (descripcion.equals("")) {
								descripcion = hola;
							} else {
								descripcion = descripcion + "\n" + hola;
							}
						}
						hola = br.readLine();
					}
				} while (pasa);
				hola = br.readLine();
				String evento = hola.split(",")[0];
				String lugar = hola.split(",")[1];
				String imagen = hola.split(",")[2];
				int dia = Integer.valueOf(hola.split(",")[3]);
				int hora = Integer.valueOf(hola.split(",")[4]);
				crear_evento(dia, hora, new Evento(evento, lugar, descripcion,imagen));
				hola = br.readLine();
			}
			br.close();
			return true;
		} catch (IOException e) {
		} catch (NoSuchFieldError e) {
		}
		return false;
	}

	public void guardardatos() {
		try {
			PrintWriter escribir = new PrintWriter(new File("./data/datos.txt"));
			for (int i = 1; i < dias + 1; i++) {
				for (int j = 1; j < horas + 1; j++) {
					try {
						escribir.println(para_el_guardado(i, j) + "," + (i) + "," + (j));
					} catch (NullPointerException e) {
					}

				}
			}
			escribir.close();
		} catch (IOException e) {

		}
	}

	public int cuantos_eventos_día(int i) {
		int contar = 0;
		for (int j = 0; j < horas; j++) {
			if (eventos[i][j] != null) {
				contar++;
			}
		}
		return contar;
	}

	public int cuantos_eventos_hora(int j) {

		int contar = 0;
		for (int i = 0; i < dias; i++) {
			if (eventos[i][j] != null) {
				contar++;
			}
		}
		return contar;
	}

	public int total_eventos() {
		int contar = 0;
		for (int i = 0; i < dias; i++) {
			for (int j = 0; j < horas; j++) {
				if (eventos[i][j] != null) {
					contar++;
				}
			}
		}
		return contar;
	}

	public int día_mes_menos_eventos() {
		int menor2 = 10000, k = 0, menor = 0;
		for (int i = 0; i < dias; i++) {
			menor = cuantos_eventos_día(i);
			if (menor < menor2) {
				menor = menor2;
				k = i;
			}
		}
		return k + 1;
	}

	public void registro() {
		
		File archivo = new File("./data/reporte.txt");
		
		try {
			PrintWriter escribir = new PrintWriter(archivo);
			escribir.println("Eventos por dias: ");
			for (int i = 0; i < dias; i++) {
				escribir.println("Eventos En El Dia " + (i + 1) + ": " + cuantos_eventos_día(i));
			}
			escribir.println(
					"---------------------------------------------------------------------------\nEventos por hora: ");
			for (int j = 0; j < horas; j++) {
				escribir.println("Eventos En La Hora " + (j + 1) + ": " + cuantos_eventos_hora(j));
			}
			escribir.println(
					"---------------------------------------------------------------------------\nEventos totales: "
							+ total_eventos());
			escribir.println(
					"---------------------------------------------------------------------------\nDia Del Mes Con Menos Eventos: "
							+ día_mes_menos_eventos());
			escribir.close();
		} catch (IOException e) {
			System.out.println(e);
		}
	}

	public Evento buscar(int dia, int hora) {
		try {
			return eventos[dia - 1][hora - 1];
		} catch (NullPointerException e) {

		}
		return null;
	}

	public void eliminar_evento(int dia, int hora) {
		try {
			eventos[dia - 1][hora - 1] = null;
			JOptionPane.showMessageDialog(null, "Eliminacion hecha");
		} catch (NullPointerException e) {
			JOptionPane.showMessageDialog(null, "No Hay Evento Creado En Esta Fecha, Vuelva A Intentar",
					"Este evento no existe", JOptionPane.ERROR_MESSAGE);
		}
	}

	public String para_el_guardado(int dia, int hora) {
		return "*\n" + eventos[dia - 1][hora - 1].getDescripcion() + "\n**\n" + eventos[dia - 1][hora - 1].getEvento()
				+ "," + eventos[dia - 1][hora - 1].getLugar()+","+eventos[dia - 1][hora - 1].getImagen();
	}

	public String ver_eventos_prueba(int dia, int hora) {
		try {
			return eventos[dia - 1][hora - 1].getEvento();
		}catch(NullPointerException  e){
			
		}
		return "";
	}
	
	public String ver_eventos(int dia, int hora) {
		return eventos[dia - 1][hora - 1].toString();
	}

	public void modificar(int dia, int hora, int dia1, int hora1, Evento evento1) {
		eventos[dia - 1][hora - 1].setEvento(evento1.getEvento());
		eventos[dia - 1][hora - 1].setLugar(evento1.getLugar());
		eventos[dia - 1][hora - 1].setDescripcion(evento1.getDescripcion());
		eventos[dia - 1][hora - 1].setImagen(evento1.getImagen());
		if (dia != dia1 || hora1 != hora) {
			eventos[dia - 1][hora - 1] = null;
			eventos[dia1 - 1][hora1 - 1] = evento1;
		}
	}

	public int getDia() {
		return dias;
	}

	public int getHora() {
		return horas;
	}
}
