package interfaz;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class AcercaDe extends JFrame{

	Label1 label;
	
	private Principal principal;
	
	public AcercaDe(Principal Pprincipal) {
		principal = Pprincipal;
		setIconImage(principal.getIconImage());
		Label1 label = new Label1();
		label.setLocation(50, 50);
		setLayout(null);
		this.add(label);
		setVisible(true);
		setSize(500, 500);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		label.setBounds(0,0,300,20);
		add(label);
	}
	
}
