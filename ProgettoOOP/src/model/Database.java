package model;

import java.io.*;
import java.util.*;

/**
 * Classe che funge da database per gli esami
 * @param <E>
 */

public class Database <E extends Esame>{
	private ArrayList<E> esami;
	
	public Database() {
		esami = new ArrayList<>();
	}
	
	public void addEsame(E esame) {
		 esami.add(esame);
	}
	
	public List<E> getEsami(){
		return new ArrayList<>(esami);
	}
	
	/**
	 * Metodo per la cancellazione di un esame
	 * @param id
	 * @return true se la cancellazione è avvenuta con successo
	 */
	public boolean cancEsame(int id) {
		for (int i = 0; i<esami.size(); i++) {
			E esame = esami.get(i);
			
			if(esame.getId()==id) {
				esami.remove(i);
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Metodo per la ricerca di un esame
	 * @param id 
	 * @return true se l'esame è presente
	 */
	public boolean findEsame(int id) {
		for (int i = 0; i<esami.size(); i++) {
			E esame = esami.get(i);
			
			if(esame.getId()==id) {
				return true;
			}
		}
		return false;
	}
	
	
	/**
	 * Metodo che restituisce un esame dato il suo id
	 * @param id
	 * @return esame
	 */
	public E getEsame(int id) {
		for (int i = 0; i<esami.size(); i++) {
			E esame = esami.get(i);
			
			if(esame.getId()==id) {
				return esame;
			}
		}
		return null;
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
		for (int i = 0; i<esami.size(); i++) {
			E esame = esami.get(i);
			
			if(esame.getId()==id) {
				EsameSemplice esameS = (EsameSemplice) esame;
				esameS.setNomeStud(nome);
				esameS.setCorso(corso);
				esameS.setVoto(voto);
				esameS.setLode(lode);
				esameS.setCfu(cfu);
			}
		}
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
		for (int i = 0; i<esami.size(); i++) {
			Esame esame = esami.get(i);
			
			if(esame.getId()==id) {
				EsameComposto esameC = (EsameComposto) esame;
				esameC.setNomeStud(nome);
				esameC.setCorso(corso);
				esameC.setVotiProve(voti);
				esameC.setPesiProve(pesi);
				esameC.setCfu(cfu);
			}
		}
	}
	
	/**
	 * Metodo per il salvataggio degli esami su file
	 * @param file
	 * @throws IOException
	 */
	public void salvaSuFile(File file) throws IOException{
		FileOutputStream fos = new FileOutputStream(file);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		
		Esame[] arrayEsami = esami.toArray(new Esame[esami.size()]);  //Si converte l'arrayList in un Array normale per evitare problemi di conversione successivamente, l'array a differenza del arrayList, quando caricato, non va a perdere il tipo
		
		oos.writeObject(arrayEsami); 
		
		oos.close();
		fos.close();
	}
	
	/**
	 * Metodo per il caricamento di esami da file
	 * @param file
	 * @throws IOException
	 */
	public void caricaDaFile(File file) throws IOException{
		try(FileInputStream fis = new FileInputStream(file);
			ObjectInputStream ois = new ObjectInputStream(fis);) {
			E[] esamiCaricati= (E[]) ois.readObject(); //Con ois.readObject si legge un oggetto precedentemente serializzato
			esami.clear(); //Si svuota l'arrayList
			esami.addAll(Arrays.asList(esamiCaricati));
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		} 
	}
	
	/**
	 * Metodo per trovare un nuovo indice
	 * @return nuovo indice
	 */
	public int nuovoIndice() {
		if(esami.isEmpty()) {
			return 0;
		}
		E esame = esami.get(esami.size()-1);
		return esame.getId()+1;
	}
}
