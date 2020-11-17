package interfaz;

import java.awt.*;
import java.awt.event.*;
import java.io.File;

import javax.swing.*;
import mundo.*;

@SuppressWarnings("serial")
public class VentanaAgregar extends JFrame implements ActionListener {

	private JLabel dia, hora, evento, lugar, descripcion, label_imagen;

	@SuppressWarnings("rawtypes")
	private JComboBox combo1, combo2;

	private JTextField text1, text2, text3;

	private JTextArea area;

	private JButton boton1, boton2, boton3;

	private Principal principal;

	private File elegida;

	private String imagen;

	public VentanaAgregar(Principal Pprincipal) {
		principal = Pprincipal;
		iniciar();
		alinear();
		setIconImage(principal.getIconImage());
		setVisible(true);
		setSize(450, 400);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
	}

	public void iniciar() {
		dia = new JLabel(" Dia");
		hora = new JLabel(" Hora");
		evento = new JLabel(" Evento");
		lugar = new JLabel(" Lugar");
		label_imagen = new JLabel("Imagen");
		descripcion = new JLabel("Descripcion Del Evento");
		combo1 = new JComboBox<>();
		combo2 = new JComboBox<>();
		combobox();
		text1 = new JTextField();
		text2 = new JTextField();
		text3 = new JTextField();
		text3.setEditable(false);
		area = new JTextArea();
		boton1 = new JButton("Confirmar");
		boton1.addActionListener(this);
		boton2 = new JButton("Salir");
		boton2.addActionListener(this);
		boton3 = new JButton("Seleccionar");
		boton3.addActionListener(this);
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
		JPanel panel1 = new JPanel(new GridLayout(5, 2));
		JPanel panel2 = new JPanel(new BorderLayout());
		JPanel panel3 = new JPanel(new GridLayout(1, 2));
		JPanel panel4 = new JPanel(new GridLayout(0, 2));

		panel1.add(dia);
		panel1.add(combo1);
		panel1.add(hora);
		panel1.add(combo2);
		panel1.add(evento);
		panel1.add(text1);
		panel1.add(lugar);
		panel1.add(text2);
		panel1.add(label_imagen);
		panel4.add(text3);
		panel4.add(boton3);
		panel1.add(panel4);

		panel2.add(descripcion, BorderLayout.NORTH);
		panel2.add(area, BorderLayout.CENTER);

		panel3.add(boton2);
		panel3.add(boton1);

		add(panel1, BorderLayout.NORTH);
		add(panel2, BorderLayout.CENTER);
		add(panel3, BorderLayout.SOUTH);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == boton1) {
			if (combo1.getSelectedItem().toString() == " " || combo2.getSelectedItem().toString() == " "
					|| text1.getText().toString().isEmpty() || text2.getText().toString().isEmpty()
					|| area.getText().toString().isEmpty()) {
				JOptionPane.showMessageDialog(null, "no puede dejar espacios vacios");
			} else {
				principal.crear_evento(Integer.parseInt(combo1.getSelectedItem().toString()), combo2.getSelectedIndex(),
						new Evento(text1.getText(), text2.getText(), area.getText(), imagen));
				principal.actualizar_lista();
				dispose();
			}
		} else if (boton2 == e.getSource()) {
			dispose();
		} else if (boton3 == e.getSource()) {

			JFileChooser jfc = new JFileChooser("./data/imagenes");
			int resultado = jfc.showOpenDialog(null);
			if (resultado == JFileChooser.APPROVE_OPTION) {
				elegida = jfc.getSelectedFile();
				if (elegida != null) {
					imagen = "./data/imagenes/" + elegida.getName();
					text3.setText(elegida.getName());
				}
			}
		}
	}
}
