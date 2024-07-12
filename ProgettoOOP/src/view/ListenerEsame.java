package view;

import java.util.List;
import model.Esame;

/**
 * Interfaccia per la definizione dei metodi che verranno utilizzati per notificare eventi specifici.
 * @author Simone Bonini
 */

public interface ListenerEsame {
	public void aggiorna();
	public void filtroNome(String nome);
	public void filtroCorso(String corso);
	public List<Esame> listaTable();
}
