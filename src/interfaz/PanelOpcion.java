package interfaz;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

@SuppressWarnings("serial")
public class PanelOpcion extends JPanel implements ActionListener{

	private JButton boton;
	
	@SuppressWarnings("unused")
	private Principal principal;
	
	public PanelOpcion(Principal Pprincipal) {
		principal = Pprincipal;
		
		boton = new JButton("Hacer Reporte");
		boton.addActionListener(this);
		setBorder(new CompoundBorder(new EmptyBorder(0, 0, 0, 0),new TitledBorder("Opciones")));
		add(boton);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == boton) {
			principal.crear_reporte();
		}
		
	}
	
}
