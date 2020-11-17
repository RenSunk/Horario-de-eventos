package interfaz;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

@SuppressWarnings("serial")
public class PanelSeleccion extends JPanel implements ActionListener{

	private Principal principal;
	
	private JLabel dia,hora;
	
	@SuppressWarnings("rawtypes")
	private JComboBox combo1,combo2;
	
	private JButton boton;
	
	public PanelSeleccion(Principal Pprincipal) {
		principal = Pprincipal;
		setLayout(new BorderLayout());
		setBorder(new CompoundBorder(new EmptyBorder(0, 0, 0, 0),new TitledBorder("Seleccion")));
		iniciar();
		alinear();
	}
	
	public void iniciar() {
		dia = new JLabel("Dia");
		hora = new JLabel("hora");
		combo1 = new JComboBox<>();
		combo2 = new JComboBox<>();
		combobox();
		boton = new JButton("Buscar");
		boton.addActionListener(this);
	}
	
	@SuppressWarnings("unchecked")
	public void combobox() {
		String pm =":00 Pm",am = ":00 Am";
		combo1.addItem(" ");
		for (int i = 0; i < principal.mandardia(); i++) {
			combo1.addItem((i+1));
		}
		combo2.addItem(" ");
		for (int i = 0; i < (principal.mandarhora()/2)-1; i++) {
			combo2.addItem((i+1)+am);
		}
		combo2.addItem("12"+pm);
		for (int i = 0; i < (principal.mandarhora()/2)-1; i++) {
			combo2.addItem((i+1)+pm);
		}
		combo2.addItem("12"+am);
	}
	
	public void alinear() {
		JPanel panel1 = new JPanel( new GridLayout( 2, 2 , 10, 10) );
		panel1.add(dia);
		panel1.add(hora);
		panel1.add(combo1);
		panel1.add(combo2);
		JPanel panel2 = new JPanel();
		panel2.add(boton);
		add(panel1,BorderLayout.CENTER);
		add(panel2,BorderLayout.SOUTH);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(boton == e.getSource()) {
			if(combo1.getSelectedIndex() == 0 || combo2.getSelectedIndex() == 0) {
				JOptionPane.showMessageDialog(null,"Campos Vacios!!","Error",JOptionPane.ERROR_MESSAGE);
			}else {
				principal.actualizar_modificar(Integer.parseInt(combo1.getSelectedItem().toString()),combo2.getSelectedIndex());
			}
		}
	}
	
}
