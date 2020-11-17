package interfaz;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.*;

@SuppressWarnings("serial")
public class PanelLista extends JPanel{

	private JTextArea area;
	
	@SuppressWarnings("unused")
	private Principal principal;
	
	private JScrollPane barra;
	
	public PanelLista(Principal Pprincipal) {
		principal = Pprincipal;
		area = new JTextArea();
		barra = new JScrollPane(area);
		area.setEditable(false);
		setBorder(new CompoundBorder(new EmptyBorder(0, 0, 0, 0),new TitledBorder("Lista")));
		setLayout(new BorderLayout());
		add(barra,BorderLayout.CENTER);
	}
	
	public void actualizar_lista(String texto) {
		area.append(texto+"\n");
	}
	
	public void limpiar_texto() {
		area.setText("");
	}
}
