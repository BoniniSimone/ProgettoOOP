package controller;

/**
 * Classe adibita a controller, ovvero svolge il ruolo di intermediario tra la View e il Model.
 * @author Simone Bonini
 */

import java.io.File;
import java.io.IOException;
import java.util.List;
import model.Database;
import model.Esame;
import model.EsameComposto;
import model.EsameSemplice;

public class Controller {
	private Database<Esame> database = new Database<>();
	private Esame esame;
	
	/**
	 * Aggiunge un esame singolo al database
	 * @param nome dello studente
	 * @param corso
	 * @param voto
	 * @param lode
	 * @param cfu
	 */
	public void addEsameSingolo(String nome, String corso, int voto, boolean lode, int cfu) {
		esame = new EsameSemplice(database.nuovoIndice(), nome, corso, voto, lode, cfu);
		database.addEsame(esame);
	}
	
	/**
	 * Aggiunge un esame composto al database
	 * @param nome dello studente
	 * @param corso
	 * @param voti
	 * @param pesi
	 * @param cfu
	 */
	public void addEsameComposto(String nome, String corso, int[] voti, double[] pesi, int cfu) {
		esame = new EsameComposto(database.nuovoIndice(), nome, corso, voti, pesi, cfu);
		database.addEsame(esame);
	}
	
	/**
	 * Restituisce la lista degli esami presenti nel database
	 * @return esami presenti nel database
	 */
	public List<Esame> getEsami(){
		return database.getEsami();
	}
	
	/**
	 * Metodo per la cancellazione di un esame
	 * @param id
	 * @return true se la cancellazione è avvenuta con successo
	 */
	public boolean cancEsame(int id) {
		return database.cancEsame(id);
	}
	
	/**
	 * Metodo per la ricerca di un esame
	 * @param id 
	 * @return true se l'esame è presente
	 */
	public boolean findEsame(int id) {
		return database.findEsame(id);
	}
	
	/**
	 * Metodo che restituisce un esame dato il suo id
	 * @param id
	 * @return esame
	 */
	public Esame getEsame(int id) {
		return database.getEsame(id);
	}
	
	/**
	 * Metodo per la modifica di un esame semplice
	 * @param id
	 * @param nome dello studente
	 * @param corso
	 * @param voto
	 * @param lode
	 * @param cfu
	 */
	public void modificaEsame(int id, String nome, String corso, int voto, boolean lode, int cfu) {
		database.modificaEsame(id, nome, corso, voto, lode, cfu);
	}
	
	/**
	 * Metodo per la modifica di un esame composto
	 * @param id
	 * @param nome dello studente
	 * @param corso
	 * @param voti
	 * @param pesi
	 * @param cfu
	 */
	public void modificaEsameComposto(int id, String nome, String corso, int[] voti, double[] pesi, int cfu) {
		database.modificaEsameComposto(id, nome, corso, voti, pesi, cfu);
	}
	
	/**
	 * Metodo per il salvataggio degli esami su file
	 * @param file
	 * @throws IOException
	 */
	public void salvaSuFile(File file) throws IOException{
		database.salvaSuFile(file);
	}
	
	/**
	 * Metodo per il caricamento di esami da file
	 * @param file
	 * @throws IOException
	 */
	public void caricaDaFile(File file) throws IOException{
		database.caricaDaFile(file);
	}
	
}
