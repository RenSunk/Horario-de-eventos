package interfaz;

import java.awt.*;
import java.awt.event.*;
import java.io.*;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.*;

import mundo.Evento;

@SuppressWarnings({ "serial", "unused" })
public class PanelModificar extends JPanel implements ActionListener {

	private JLabel evento, lugar, dia, hora, descripcion;

	private JTextField text1, text2, text3, text4;

	@SuppressWarnings("rawtypes")
	private JComboBox combo1, combo2;

	private JLabel imagen;

	private JTextArea area;

	private JButton boton1, boton2, boton3,boton4;

	private Principal principal;

	private int dia1, hora1;

	private String imag;

	private JScrollPane scrollPane;

	File elegida;

	boolean accion = false;

	private JPanel panel1 = new JPanel(new GridLayout(4, 2, 10, 10));

	public PanelModificar(Principal Pprincipal) {
		principal = Pprincipal;
		setBorder(new CompoundBorder(new EmptyBorder(0, 0, 0, 0), new TitledBorder("Modificar")));
		iniciar();
		alinear();
	}

	public void iniciar() {

		imagen = new JLabel(new ImageIcon(""));
		evento = new JLabel("evento:");
		lugar = new JLabel("lugar");
		dia = new JLabel("dia");
		hora = new JLabel("hora");
		descripcion = new JLabel("descripcion");
		text1 = new JTextField();
		text1.setEditable(false);
		text2 = new JTextField();
		text2.setEditable(false);
		text3 = new JTextField();
		text3.setEditable(false);
		text4 = new JTextField();
		text4.setEditable(false);
		combo1 = new JComboBox<>();
		combo2 = new JComboBox<>();
		area = new JTextArea();
		area.setEditable(false);
		boton1 = new JButton("Modificar");
		boton1.addActionListener(this);
		boton2 = new JButton("imagen");
		boton2.addActionListener(this);
		boton3 = new JButton("Aceptar");
		boton3.addActionListener(this);
		boton4 = new JButton("eliminar");
		boton4.addActionListener(this);
		scrollPane = new JScrollPane(area);
	}

	public void actualizar_componentes(int dia, int hora, String evento, String lugar, String descripcion,
			String imagen) {
		dia1 = dia;
		hora1 = hora;
		text1.setText(evento);
		text2.setText(lugar);
		text3.setText("" + dia);
		if (hora > 12) {
			text4.setText((hora - 12) + ":00 PM");
		} else {
			text4.setText(hora + ":00 AM");
		}
		area.setText(descripcion);
		this.imagen.setIcon(new ImageIcon(imagen));
	}

	public void limpiar_componentes() {
		combo1.setSelectedIndex(0);
		combo2.setSelectedIndex(0);
		text1.setText("");
		text2.setText("");
		text3.setText("");
		text4.setText("");
		area.setText("");
		imagen.setIcon(new ImageIcon(""));
	}

	@SuppressWarnings("unchecked")
	public void combobox() {
		String pm = ":00 Pm", am = ":00 Am";
		combo1.addItem(" ");
		for (int i = 0; i < principal.mandardia(); i++) {
			combo1.addItem((i + 1));
		}
		combo2.addItem(" ");
		for (int i = 0; i < (principal.mandarhora() / 2) - 1; i++) {
			combo2.addItem((i + 1) + am);
		}
		combo2.addItem("12" + pm);
		for (int i = 0; i < (principal.mandarhora() / 2) - 1; i++) {
			combo2.addItem((i + 1) + pm);
		}
		combo2.addItem("12" + am);
	}

	public void alinear() {
		setLayout(new BorderLayout());

		panel1.add(evento);
		panel1.add(lugar);
		panel1.add(text1);
		panel1.add(text2);
		panel1.add(dia);
		panel1.add(hora);
		panel1.add(text3);
		panel1.add(text4);

		add(panel1, BorderLayout.NORTH);
		JPanel panel2 = new JPanel(new BorderLayout());
		panel2.add(descripcion, BorderLayout.NORTH);
		JPanel panel3 = new JPanel(new GridLayout(1, 1));
		panel3.add(scrollPane);
		panel3.add(imagen);
		panel2.add(panel3, BorderLayout.CENTER);
		JPanel panel4 = new JPanel(new GridLayout(0, 4, 0, 0));

		panel4.add(boton1);
		panel4.add(boton3);
		panel4.add(boton2);
		panel4.add(boton4);
		panel2.add(panel4, BorderLayout.SOUTH);
		add(panel2, BorderLayout.CENTER);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == boton1) {
			if (text1.getText().isEmpty()) {
				JOptionPane.showMessageDialog(this, "Seleccione Un evento");
			} else {
				text1.setEditable(true);
				text2.setEditable(true);
				area.setEditable(true);
				panel1.remove(text3);
				panel1.remove(text4);
				panel1.add(combo1);
				panel1.add(combo2);
				combobox();
				combo1.setSelectedIndex(dia1);
				combo2.setSelectedIndex(hora1);
				panel1.revalidate();
				accion = true;
				// boton2.addActionListener(this);
			}
		}

		if (e.getSource() == boton3) {

			if (text1.getText().isEmpty()) {
				JOptionPane.showMessageDialog(this, "Seleccione Un evento");
			} else  if (accion == false) {
				JOptionPane.showMessageDialog(this, "Tienes Que Habilitar La Modificacion");
			}else{
				principal.modificar(dia1, hora1, Integer.parseInt(combo1.getSelectedItem().toString()),
						combo2.getSelectedIndex(), new Evento(text1.getText(), text2.getText(), area.getText(), imag));
				principal.actualizar_lista();
				JOptionPane.showMessageDialog(null, "Modificacion Exitosa");
				text1.setEditable(false);
				text2.setEditable(false);
				area.setEditable(false);
				panel1.remove(combo1);
				panel1.remove(combo2);
				panel1.add(text3);
				panel1.add(text4);
				panel1.revalidate();
				limpiar_componentes();
				accion = false;
			}
		}
		
		if(e.getSource() == boton4) {
			if (text1.getText().isEmpty()) {
				JOptionPane.showMessageDialog(this, "Seleccione Un evento");
			} else if (accion == false) {
				JOptionPane.showMessageDialog(this, "Tienes Que Habilitar La Modificacion");
			}else{
				principal.eliminar(Integer.parseInt(combo1.getSelectedItem().toString()), combo2.getSelectedIndex());
				principal.actualizar_lista();
				text1.setEditable(false);
				text2.setEditable(false);
				area.setEditable(false);
				panel1.remove(combo1);
				panel1.remove(combo2);
				panel1.add(text3);
				panel1.add(text4);
				panel1.revalidate();
				limpiar_componentes();
				accion = false;
			}
		}
		
		if (e.getSource() == boton2) {

			if (text1.getText().isEmpty()) {
				JOptionPane.showMessageDialog(this, "Seleccione Un evento");
			} else if (accion == false) {
				JOptionPane.showMessageDialog(this, "Tienes Que Habilitar La Modificacion");
			} else {

				JFileChooser jfc = new JFileChooser("./data/imagenes");
				int resultado = jfc.showOpenDialog(null);
				if (resultado == JFileChooser.APPROVE_OPTION) {
					elegida = jfc.getSelectedFile();
					if (elegida != null) {
						imagen.setIcon(new ImageIcon("./data/imagenes/" + elegida.getName()));
						imag = "./data/imagenes/" + elegida.getName();
						accion = false;
					}
				}
			}
		}
	}

}
