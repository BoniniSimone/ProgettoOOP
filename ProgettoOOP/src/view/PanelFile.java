package view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;
import controller.Controller;

/**
 * Pannello per il salvataggio o caricamento degli esami da un file.
 * @author Simone Bonini
 */

public class PanelFile extends JPanel{
	private static final long serialVersionUID = 1L;
	private ListenerEsame esameListener;
	private JButton saveButton;
	private JButton loadButton;
	private JFileChooser fileChooser;
	private File mainFile;
	private AutoSaveThread autoSaveThread;
	private static final int INTERVALLO = 60000; //300000 -> 5 minuti in millisecondi
																					//60000 -> 1 minuto in millisecondi
	
	public PanelFile(Controller controller) {
		setPreferredSize(new Dimension(300, 100));
		setLayout(new GridBagLayout());
		
		Border bordoInterno = BorderFactory.createTitledBorder("Salva/Carica");
		Border bordoEsterno = BorderFactory.createEmptyBorder(0, 5, 5, 5);
		Border bordoFinale = BorderFactory.createCompoundBorder(bordoEsterno, bordoInterno);
		setBorder(bordoFinale);
		
		//Componenti
		saveButton = new JButton("Salva");
		loadButton = new JButton("Carica esami");
		fileChooser = new JFileChooser();
		
		//Layout
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
		gbc.anchor = GridBagConstraints.CENTER; // Centra i componenti all'interno della cella
		gbc.fill = GridBagConstraints.BOTH;
		gbc.insets = new Insets(2, 10, 2, 10); //Si imposta un margine per migliorare la visualizzazione
		        
		add(saveButton, gbc);
		        
		gbc.gridy = 1;

		add(loadButton, gbc);
		
		//Event bottone "Salva"
		saveButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (fileChooser.showSaveDialog(PanelFile.this) == JFileChooser.APPROVE_OPTION) {
					File selectedFile = fileChooser.getSelectedFile();
					if (selectedFile.exists()) { //Se il file sul quale si vuole effettuare il salvataggio esiste già, si chiede conferma di sovrascrittura
						int response = JOptionPane.showConfirmDialog(
							PanelFile.this,
							"Il file esiste già. Vuoi sovrascriverlo?",
							"Conferma Sovrascrittura",
							JOptionPane.YES_NO_OPTION,
							JOptionPane.WARNING_MESSAGE
						);
						if (response != JOptionPane.YES_OPTION) {
							return;
						}
					}
					try {
						controller.salvaSuFile(selectedFile);
						mainFile = selectedFile;
						if(autoSaveThread != null) {
							 stopAutoSaveThread(); //In caso fosse avviato un thread per il salvataggio automatico, viene fermato
						 }
						 autoSaveThread = new AutoSaveThread(controller, PanelFile.this, INTERVALLO);
					     autoSaveThread.start(); //Viene invece fatto partire un altro thread per il salvataggio automatico con il nuovo file di destianzione impostato
						aggiornaTable();
					} catch (IOException e1) {
						JOptionPane.showMessageDialog(
							PanelFile.this, 
							"Impossibile esportare i dati su file", 
							"Errore", 
							JOptionPane.ERROR_MESSAGE
						);
					}
				}
			}
		});
		
		//Event bottone "Carica esami"
		loadButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(fileChooser.showOpenDialog(PanelFile.this) == JFileChooser.APPROVE_OPTION){
					try {
						File selectedFile = fileChooser.getSelectedFile();
						controller.caricaDaFile(selectedFile);
						 mainFile = selectedFile;
						 if(autoSaveThread != null) {
							 stopAutoSaveThread(); //In caso fosse avviato un thread per il salvataggio automatico, viene fermato
						 }
						 autoSaveThread = new AutoSaveThread(controller, PanelFile.this, INTERVALLO);
					     autoSaveThread.start(); //Viene invece fatto partire un altro thread per il salvataggio automatico con il nuovo file di destianzione impostato
						aggiornaTable();
					} 
					catch (IOException e1) {
						JOptionPane.showMessageDialog(PanelFile.this, "Impossibile importare i dati da file", "Errore", JOptionPane.ERROR_MESSAGE);
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
	 * Metodo per l'aggiornamento della tabella
	 */
    private void aggiornaTable() {
        if (esameListener != null) {
        	esameListener.aggiorna();
        }
    }
    
    /**
     * Metodo per fermare un thread di salvataggio automatico
     */
    public void stopAutoSaveThread() {
        if (autoSaveThread != null) {
            autoSaveThread.stopAutoSave();
        }
    }

    /**
     * Metodo per ottenere il file principale.
     * Synchronized assicura che il metodo sia eseguito da un solo thread alla volta.
     * @return file usato per la memorizzazione
     */
    public synchronized File getMainFile() {
        return mainFile;
    }
}
