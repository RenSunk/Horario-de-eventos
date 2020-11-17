package interfaz;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import mundo.*;

@SuppressWarnings("serial")
public class Principal extends JFrame implements ActionListener{

	private Horario miHorario;
	
	private JMenu menu, menu1;

	private JMenuBar barra;

	private JMenuItem crear, salir, acerca,guardar;
	
	private PanelImagen imagen;
	
	private PanelLista lista;
	
	private PanelModificar modificar;
	
	private PanelOpcion opcion;
	
	private PanelSeleccion seleccion;
	
	public Principal() {
		super("Horario De Eventos");
		iniciar();
		alinear();
		setIconImage(new ImageIcon("./data/imagenes/icono.png").getImage());
		setVisible(true);
		setSize(950, 650);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(true);
		setLocationRelativeTo(null);
		
		}	
	
	public void iniciar() {
		miHorario = new Horario();
		crear = new JMenuItem("Crear Evento");
		salir = new JMenuItem("salir");
		acerca = new JMenuItem("Acerca de...");
		menu = new JMenu("Archivo");
		menu1 = new JMenu("Ayuda");
		guardar = new JMenuItem("Guardar Cambios");
		menu.add(crear);
		menu.add(guardar);
		menu1.add(acerca);
		menu.add(salir);
		
		crear.addActionListener(this);
		salir.addActionListener(this);
		acerca.addActionListener(this);
		guardar.addActionListener(this);
		
		barra = new JMenuBar();
		barra.add(menu);
		barra.add(menu1);
		seleccion = new PanelSeleccion(this);
		modificar = new PanelModificar(this);
		imagen = new PanelImagen();
		opcion = new PanelOpcion(this);
		lista = new PanelLista(this);		
		actualizar_lista();
		
	}
	
	public void modificar(int dia,int hora,int dia1, int hora1, Evento evento1) {
		miHorario.modificar(dia, hora, dia1, hora1, evento1);
	}
	
	public void actualizar_lista() {
		lista.limpiar_texto();
		for (int i = 1; i < miHorario.getDia()+1; i++) {
			for (int j = 1; j < (miHorario.getHora()+1)/2; j++) {
				try {
					lista.actualizar_lista("Dia:"+i+"\nHora:"+j+":00 Am"+"\n"+miHorario.ver_eventos(i, j));
				} catch (NullPointerException e) {
					
				}
			}
			for (int j = 1; j < (miHorario.getHora()+1)/2; j++) {
				try {
					lista.actualizar_lista("Dia:"+i+"\nHora:"+j+":00 Pm"+"\n"+miHorario.ver_eventos(i, j+12));
				} catch (NullPointerException e) {
					
				}
			}
		}
	}
	
	public void actualizar_modificar(int dia,int hora) {
		Evento evento = miHorario.buscar(dia, hora);
		try {
			String evento1 = evento.getEvento();
			String lugar = evento.getLugar();
			String descripcion = evento.getDescripcion();
			String imagen = evento.getImagen();
			modificar.actualizar_componentes(dia, hora, evento1, lugar, descripcion,imagen);
		}catch(NullPointerException e) {
			 JOptionPane.showMessageDialog(null, "No Hay Evento Creado En Esta Fecha, Vuelva A Intentar", "Este evento no existe", JOptionPane.ERROR_MESSAGE );
		}
	}
	public void crear_reporte() {
		miHorario.registro();
	}
	
	public void eliminar(int dia,int hora) {
		miHorario.eliminar_evento(dia, hora);
	}
	
	public void crear_evento(int dia,int hora,Evento evento) {
		miHorario.crear_evento(dia, hora, evento);
	}
	
	public int mandardia() {
		return miHorario.getDia();
	}
	
	public int mandarhora() {
		return miHorario.getHora();
	}

	
	public void alinear() {
		setLayout(new BorderLayout());
		
		JPanel panelAux1 = new JPanel(new BorderLayout());
		JPanel panelAux2 = new JPanel(new BorderLayout());
		JPanel panelCentral = new JPanel(new GridLayout(1, 1));
		JPanel todo = new JPanel(new BorderLayout());
		JScrollPane panel = new JScrollPane(todo);
		panelAux1.add(seleccion,BorderLayout.NORTH);
		panelAux1.add(modificar,BorderLayout.CENTER);
		
		panelAux2.add(imagen,BorderLayout.NORTH);
		panelAux2.add(lista,BorderLayout.CENTER);
		panelAux2.add(opcion,BorderLayout.SOUTH);
		
		panelCentral.add(panelAux1);
		panelCentral.add(panelAux2);
		
		add(barra,BorderLayout.NORTH);
		todo.add(panelCentral,BorderLayout.CENTER);
		add(panel,BorderLayout.CENTER);
	}
	
	public static void main(String[] args) {
		new Principal();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == crear) {
			new VentanaAgregar(this);
		}else if(e.getSource() == salir) {
			dispose();
		}else if(e.getSource() == acerca) {
			new AcercaDe(this);
		}else if(e.getSource() == guardar) {
			JOptionPane.showMessageDialog(null,"Los Datos Se An Guardado Con Exito en el archivo (data.txt)");
			miHorario.guardardatos();
		}
		
	}
}
