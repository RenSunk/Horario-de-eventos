package interfaz;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class PanelImagen extends JPanel{

	public PanelImagen() {
		JLabel imagen = new JLabel( );
        ImageIcon icono = new ImageIcon( "./data/imagenes/imagen.png" );
        imagen = new JLabel( "" );
        imagen.setIcon( icono );
        add( imagen );
	}
	
}
