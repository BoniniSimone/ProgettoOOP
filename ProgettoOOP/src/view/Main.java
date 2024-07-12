package view;

/**
 * Classe principale per l'avvio dell'applicazione di gestione degli esami
 * <p>
 * L'applicazione gestisce la visualizzazione e la gestione degli esami attraverso l'utilizzo di {@link FrameGE}.
 * </p>
 * @author Simone Bonini
 */
public class Main {

	public static void main(String[] args) {
		FrameGE geFrame = new FrameGE();
        geFrame.pack();
        geFrame.setLocationRelativeTo(null); // Centra il frame nella schermata.
        geFrame.setVisible(true);
	}
}
