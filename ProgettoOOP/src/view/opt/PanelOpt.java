package view.opt;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.Border;
import controller.Controller;
import model.Esame;
import model.EsameComposto;
import model.EsameSemplice;
import view.ListenerEsame;
import view.PanelID;

/**
 * Pannello per le opzioni sulla tabella (Aggiungi esami, Cancella esami, Modifica esami)
 * @author Simone Bonini
 */

public class PanelOpt extends JPanel{
	private static final long serialVersionUID = 1L;
	private ListenerEsame esameListener;
	private JButton addButton; //Bottone aggiungi nuovo esame
	private JButton cancButton; //Bottone cancella esame
	private JButton updButton; //Bottone per la modifica di un esame
	
	
	public PanelOpt(Controller controller) {
		setPreferredSize(new Dimension(300, 200));
		setLayout(new GridBagLayout()); //Si utilizza GridBagLayout per avere maggiore flessibilità
		
		Border bordoInterno = BorderFactory.createTitledBorder("Opzioni");
		Border bordoEsterno = BorderFactory.createEmptyBorder(0, 5, 5, 5);
		Border bordoFinale = BorderFactory.createCompoundBorder(bordoEsterno, bordoInterno);
		setBorder(bordoFinale);
		
		
		//Componenti
		addButton = new JButton("Aggiuingi");
		cancButton = new JButton("Cancella");
		updButton = new JButton("Modifica");
		
		
		
		//Layout
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0; //Il layout impostando weightx a 1 farà si che lo spazio extra disponibile venga tutto occupato dal componente (in questo caso in orizzontale)
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.CENTER; // Centra i componenti all'interno della cella
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(2, 10, 2, 10); // Si imposta un margine per migliorare la visualizzazione
        
        add(addButton, gbc);
        
        gbc.gridy = 1;
        add(cancButton, gbc);

        gbc.gridy = 2;
        add(updButton, gbc);
        
        
        
        /*----------------------------------------  JOptionPane.MESSAGE  ----------------------------------------
         * PLAIN_MESSAGE --> dialog con messaggio senza icone
         * WARNING_MESSAGE --> messaggio con icona di attenzione (si utilizza per i problemi)
         * ERROR_MESSAGE --> messaggio con iconea di erroe (si utilizza per gli errori)
         * INFORMATION_MESSAGE --> messaggio con icona informazione
         * --------------------------------------------------------------------------------------------------------------------
         */
        
        //Event bottone "Aggiungi"
        addButton.addActionListener(new ActionListener() {
        	@Override
        	 public void actionPerformed(ActionEvent e) {

                String[] options = {"Esame semplice", "Esame composto"}; //Nomi dei bottoni

                // Scelta "Esame semplice" o  "Esame composto"
                int choice = JOptionPane.showOptionDialog(null, "Scegli il tipo di esame:", "Selezione Esame", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0] );

                // ESAME SEMPLICE
                if (choice == 0) {
                	PanelAdd addPanel = new PanelAdd(); //Si inizializza il pannello dedicato all'aggiunta di un nuovo esame semplice
                    int result = JOptionPane.showConfirmDialog(null, addPanel, "Aggiungi esame", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE); 

                    if (result == JOptionPane.OK_OPTION) {
                        String nome = addPanel.getNome();
                        String corso = addPanel.getEsame();
                        int voto = addPanel.getVoto();
                        boolean lode = addPanel.getLode();
                        int cfu = addPanel.getCfu();
                        
                        //Si verifica che il nome dello studente e il nome del corso siano stati inseriti
                        if(addPanel.checkNome() && addPanel.checkCorso()) {
                        	controller.addEsameSingolo(nome, corso, voto, lode, cfu); //Per accedere al "database" degli esami utilizziamo il controller
                        	aggiornaTable();
                        }
                        else {
                    		JOptionPane.showMessageDialog(null, "Inserimento fallito \nCompilare sia il campo \"Nome studete\" che il campo \"Corso\".", "Errore", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
                //ESAME MULTIPLO
                else if (choice == 1) {
                    PanelNumEs numEsPanel = new PanelNumEs(); //Si inizializza il pannello dedicato alla selezione del numero di esami (parziali)
                    int result = JOptionPane.showConfirmDialog(null, numEsPanel, "Numero prove", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                    
                    if(result == JOptionPane.OK_OPTION) {
                    	int numEsami = numEsPanel.getNumEs();
                    	PanelAddM addMPanel = new PanelAddM(numEsami);  //Si inizializza il pannello dedicato all'aggiunta di un nuovo esame composto
                    	int result2 = JOptionPane.showConfirmDialog(null, addMPanel, "Aggiungi esame", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                    	if(result2 == JOptionPane.OK_OPTION) {
                    		if(addMPanel.checkPesi()) { //Si effettua un controllo per verificare che i pesi siano corretti
                    			//Si verifica che il nome dello studente e il nome del corso siano stati inseriti
                    			if(addMPanel.checkNome() && addMPanel.checkCorso()) {
                    				//Per accedere al "database" degli esami utilizziamo il controller
                    				controller.addEsameComposto(addMPanel.getNome(), addMPanel.getCorso(), addMPanel.getVoti(), addMPanel.getPesi(), addMPanel.getCfu());
                    				aggiornaTable();
                    			}
                    			else {
                            		JOptionPane.showMessageDialog(null, "Inserimento fallito \nCompilare sia il campo \"Nome studete\" che il campo \"Corso\".", "Errore", JOptionPane.ERROR_MESSAGE);
                    			}
                        	}
                        	else {
                        		JOptionPane.showMessageDialog(null, "Inserimento fallito \nLa somma dei  pesi degli esami intermedi non è corretta, ", "Errore", JOptionPane.ERROR_MESSAGE);
                        	}
                    	}              	
                    }
                }
        	}
        });
        
        //Event bottone "Cancella"
        cancButton.addActionListener((ActionListener) new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				PanelID idPanel = new PanelID("ID esame da cancellare: ");  //Si inizializza un pannello dedicato alla selezione dell'ID
                int result = JOptionPane.showConfirmDialog(null, idPanel, "Cancella esame", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                if(result == JOptionPane.OK_OPTION) {
                	if(idPanel.checkID()) { //Si controlla che l'ID sia valido
                		System.out.println(idPanel.getID());
                		if(controller.cancEsame(idPanel.getID())) {
                			JOptionPane.showMessageDialog(null, "Esame cancellato con successo", "Esame cancellato", JOptionPane.INFORMATION_MESSAGE);
                			aggiornaTable();
                		}
                		else {
                			JOptionPane.showMessageDialog(null, "ID non presente negli esami", "ID non trovato", JOptionPane.ERROR_MESSAGE);
                		}
                	}
                	else {
                		JOptionPane.showMessageDialog(null, "ID non valido", "Errore", JOptionPane.ERROR_MESSAGE);

                	}
                }	
			}      	
        });
        
        //Event bottone "Aggiorna"
        updButton.addActionListener((ActionListener) new ActionListener(){
        	
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		PanelID idPanel = new PanelID("ID esame da aggiornare: "); //Si inizializza un pannello dedicato alla selezione dell'ID
        		int result = JOptionPane.showConfirmDialog(null, idPanel, "Aggiorna esame", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                if(result == JOptionPane.OK_OPTION) {
                	if(idPanel.checkID()) { //Si controlla che l'ID sia valido
                		if(controller.findEsame(idPanel.getID())) {
                			Esame esame = controller.getEsame(idPanel.getID());
                			if(esame instanceof EsameSemplice) { //Si verifica se l'esame è semplice 
                				//Si inizializza un pannello per la modifica di un esame semplice
                				PanelUpd updPanel = new PanelUpd(esame.getNomeStud(), esame.getCorso(), esame.getVoto(), esame.getLode(), esame.getCfu());
                				int result2 = JOptionPane.showConfirmDialog(null, updPanel, "Aggiorna esame", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                				if(result2 == JOptionPane.OK_OPTION) {
                					if(updPanel.checkNome() && updPanel.checkCorso()) {
                						 controller.modificaEsame(idPanel.getID(), updPanel.getNome(), updPanel.getCorso(), updPanel.getVoto(), updPanel.getLode(), updPanel.getCfu());
                						 aggiornaTable();
                					}
                					 else {
                                 		JOptionPane.showMessageDialog(null, "Modifica fallita \nCompilare sia il campo \"Nome studete\" che il campo \"Corso\".", "Errore", JOptionPane.ERROR_MESSAGE);
                                     }
                				}
                			}
                			else {
                				EsameComposto esameC = (EsameComposto) esame;
                				//Si inizializza un pannello per la modifica di un esame composto
                				PanelUpdM updMPanel = new PanelUpdM(esameC.getNumProve(), esameC.getNomeStud(), esameC.getCorso(), esameC.getVotiProve(), esameC.getPesiProve(), esameC.getCfu());
                				int result2 = JOptionPane.showConfirmDialog(null, updMPanel, "Aggiorna esame", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                				if(result2 == JOptionPane.OK_OPTION) {
                            		if(updMPanel.checkPesi()) {
                            			if(updMPanel.checkNome() && updMPanel.checkCorso()) {
                            				controller.modificaEsameComposto(idPanel.getID(), updMPanel.getNome(), updMPanel.getCorso(), updMPanel.getVoti(), updMPanel.getPesi(), updMPanel.getCfu());
                        					aggiornaTable();
                            			}
                            			else {
                                    		JOptionPane.showMessageDialog(null, "Modifica fallita \nCompilare sia il campo \"Nome studete\" che il campo \"Corso\".", "Errore", JOptionPane.ERROR_MESSAGE);
                            			}
                                	}
                                	else {
                                		JOptionPane.showMessageDialog(null, "Modifica fallita \nLa somma dei  pesi degli esami intermedi non è corretta, ", "Errore", JOptionPane.ERROR_MESSAGE);
                                	}
                					
                				}
                			}
                			
                		}
                		else {
                			JOptionPane.showMessageDialog(null, "ID non presente negli esami", "ID non trovato", JOptionPane.ERROR_MESSAGE);
                		}
                	}
                	else {
                		JOptionPane.showMessageDialog(null, "ID non valido", "Errore", JOptionPane.ERROR_MESSAGE);

                	}
                }
        	}
        });
	}
	
	/**
	 * Metodo per impostare un listener
	 * @param listener
	 */
	public void setExamListener(ListenerEsame listener) {
        this.esameListener = listener;
    }
	
	/**
	 * Metodo che richiama la funzione di aggiornameto tabella.
	 */
    private void aggiornaTable() {
        if (esameListener != null) {
        	esameListener.aggiorna();
        }
    }
}
