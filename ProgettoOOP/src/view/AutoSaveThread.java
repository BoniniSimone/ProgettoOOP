package view;

import java.io.File;
import java.io.IOException;
import controller.Controller;

/**
 * Thread per il salvataggio automatico su file dei dati (salvataggio ad intervalli regolari).
 * @author Simone Bonini
 */

public class AutoSaveThread extends Thread{
	private Controller controller;
	private PanelFile panelFile;
	private boolean running = true;
	private int intervallo;
	
	public AutoSaveThread(Controller controller, PanelFile panelFile, int intervallo) {
		this.controller = controller;
		this.panelFile = panelFile;
		this.intervallo = intervallo;
	}
	
	@Override
	public void run() {
		while (running) {
			 try {
	                File mainFile = panelFile.getMainFile();
	                if (mainFile != null) {
	                    controller.salvaSuFile(mainFile);
	                }
	                Thread.sleep(intervallo); //Il metodo sleep crea l'intervallo di tempo tra un salvataggio e l'altro 
	            } catch (IOException e) {
	                e.printStackTrace(); // Gestione errore
	            } catch (InterruptedException e) {
	                Thread.currentThread().interrupt(); 
	            }
		}
	}
	
	public void stopAutoSave() {
		running = false;
	}
}
