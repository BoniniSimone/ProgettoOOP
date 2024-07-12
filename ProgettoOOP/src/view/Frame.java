package view;
import javax.swing.*;

/**
 * Classe per la creazione di un Frame personalizzato.
 * <p>
 * Tale classe non è indispensabile per il programma, ma è stata implementata in caso si abbia la necesità di creare
 * più frame con la stessa impostazione.
 * </p>
 * @author Simone Bonini
 */

public class Frame extends JFrame{
	private static final long serialVersionUID = 1L;
	/**
	 * Costruttore senza parametri.
	 */
	public Frame() {
		this("");
	}
	
	/**
	 * Costruttore a cui viene passato un parametro.
	 * @param Titolo della finestra
	 */
	public Frame(String titolo) {
		super(titolo);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
}
