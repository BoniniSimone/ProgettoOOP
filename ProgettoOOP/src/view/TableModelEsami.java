package view;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import controller.Controller;
import model.Esame;

/**
 * Modello per la getione dei dati che verranno visualizzati nella tabella.
 * @author Simone Bonini
 */

public class TableModelEsami extends AbstractTableModel{
	private static final long serialVersionUID = 1L;
	private List<Esame> listaEsamiFiltrata;
	private List<Esame> listaEsami;
	private Controller controller;
	private String[] nomiColonne = {"ID", "Nome studente", "Corso", "Voto", "Lode", "Cfu"};
	
	public TableModelEsami(Controller controller) {
		this.controller=controller;
	}
	
	public void setData(List<Esame> listaEsami) {
		this.listaEsami = listaEsami;
	}
	
	@Override
	public String getColumnName(int column) {
		return nomiColonne[column];
	}
	
	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return listaEsami.size();
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 6;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Esame esame = listaEsami.get(rowIndex);
		
		switch(columnIndex) {
			case 0:
				return esame.getId();
			case 1:
				return esame.getNomeStud();
			case 2:
				return esame.getCorso();
			case 3:
				return esame.getVoto();
			case 4:
				if (esame.getLode()) return "lode"; //getLode() ritorna un valore booleano quindi per migliorare la visualizzazione stampiamo la scritta "lode"
				return null;
			case 5:
				return esame.getCfu();
			default:
				return null;
		}
		
	}
	
	/**
	 * Metodo per filtrare la tabella per il nome dello studente
	 * @param nome dello studente per cui si vuole filtrare
	 */
	 public void filtroNomeStudente(String nomeStudente) {
		 	//Prima di filtrare ci si assicura che la tabella sia completa di tutti gli esami e senza filtri
		 	setData(controller.getEsami());
		 	
	        if (nomeStudente == null || nomeStudente.isEmpty()) {
	        	//No filtro
	        } else {
	        	listaEsamiFiltrata = new ArrayList<>();
	            for (Esame esame : listaEsami) {
	                if (esame.getNomeStud().toLowerCase().contains(nomeStudente.toLowerCase())) { //Effettuiamo il confronto portando tutto al lower case così da essere case insensitive
	                    listaEsamiFiltrata.add(esame);
	                }
	            }
	            setData(listaEsamiFiltrata);
	        }
	        fireTableDataChanged(); //Si aggiorna la tabella
	    }
	 
	 /**
	  * Metodo per filtrare la tabella per il nome del corso
	  * @param nome del corso
	  */
	 public void filtroCorso(String corso) {
		 	//Prima di filtrare ci si assicura che la tabella sia completa di tutti gli esami e senza filtri
		 	setData(controller.getEsami());
	        if (corso == null || corso.isEmpty()) {
	        	//No filtro
	        } else {
	        	listaEsamiFiltrata = new ArrayList<>();
	            for (Esame esame : listaEsami) {
	                if (esame.getCorso().toLowerCase().contains(corso.toLowerCase())) {
	                    listaEsamiFiltrata.add(esame);
	                }
	            }
	            setData(listaEsamiFiltrata);
	        }
	        fireTableDataChanged(); //Si aggiorna la tabella
	    }
	 
	 public List<Esame> listaTable(){
		 return this.listaEsami;
	 }
}
