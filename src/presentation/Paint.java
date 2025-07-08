package presentation;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Clase que pinta las imagenes en componetes visuales.
 * 
 * @version 0.1
 */
public class Paint {

	public static Paint printer;

	/**
	 * Constructor de la impresorsa
	 */
	public Paint() {
	}

	/**
	 * Metodo para obtener la impresora.
	 * 
	 * @return Paint para pintar imagenes en componentes.
	 */
	public static Paint getPrinter() {
		if (printer == null) {
			printer = new Paint();
		}
		return printer;
	}

	/**
	 * Pinta imagenes en componete Jlabel con un tamanno especifico.
	 * 
	 * @param label Componente a pintar
	 * @param ruta  Localizacion de la imagne en el direcorio.
	 * @param ancho Pixeles de ancho de la imagen.
	 * @param alto  Pixeles de alto de la imagen.
	 */
	public void pintarImagen(JLabel label, String ruta, int ancho, int alto) {
		ImageIcon imagen = new ImageIcon(ruta);
		ImageIcon icon = new ImageIcon(imagen.getImage().getScaledInstance(ancho, alto, Image.SCALE_DEFAULT));
		label.setIcon(icon);
	}

	/**
	 * Pinta imagenes en componete JButton con un tamanno especifico.
	 * 
	 * @param button Componente a pintar
	 * @param ruta   Localizacion de la imagne en el direcorio.
	 * @param ancho  Pixeles de ancho de la imagen.
	 * @param alto   Pixeles de alto de la imagen.
	 */
	public void pintarImagen(JButton button, String ruta, int ancho, int alto) {
		ImageIcon imagen = new ImageIcon(ruta);
		ImageIcon icon = new ImageIcon(imagen.getImage().getScaledInstance(ancho, alto, Image.SCALE_DEFAULT));
		button.setIcon(icon);
	}
}