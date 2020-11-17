package interfaz;

import java.awt.*;
import java.awt.event.*;
import java.net.*;

import javax.swing.JLabel;

@SuppressWarnings("serial")
public class Label1 extends JLabel implements MouseListener {

	public Label1() {

		setText("<html><a href=" + "http://www.google.com.co/" + ">Pagina Universida Santiago De Cali</a></html>");
		this.setCursor(new Cursor(Cursor.HAND_CURSOR));
		this.addMouseListener(this);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		try {
			if (Desktop.isDesktopSupported()) {
				Desktop desktop = Desktop.getDesktop();
				if (desktop.isSupported(Desktop.Action.BROWSE)) {
					desktop.browse(
							new URI("http://usc.edu.co/"));
				}
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
