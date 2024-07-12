package view;

import java.awt.BorderLayout;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import controller.Controller;
import model.Esame;
import view.opt.PanelOpt;

/**
 * Classe della finestra principale per la gestione degli esami.
 * @author Simone Bonini
 */

public class FrameGE extends Frame implements ListenerEsame{  //è implementata l'interfaccia ListenerEsame, questa servirà per aggiornare altre parti dell'applicazione
	private static final long serialVersionUID = 1L;
	private PanelOpt optPanel;
	private PanelFiltro filtPanel;
	private PanelFile filePanel;
	private JPanel panel;
	private PanelTable tablePanel;
	private Controller controller;
	
	public FrameGE() {
		super("Gestione degli esami");
		
		controller = new Controller();
		
		setLayout(new BorderLayout());
		panel = new JPanel(); //Pannello base in cui inseriremo gli altri pannelli, questo è stato fatto perchè vogliamo tre pannelli differenti a sinistre (LINE_START) della finestra.
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		filtPanel = new PanelFiltro();
		tablePanel = new PanelTable(controller);
		optPanel = new PanelOpt(controller);
		filePanel = new PanelFile(controller);
		
		//Si setta per i vari pannelli, questo frame come listener.
		optPanel.setExamListener(this);
		filtPanel.setExamListener(this);
		filePanel.setExamListener(this);
		
		panel.add(optPanel);
		panel.add(filtPanel);
		panel.add(filePanel);
		add(panel, BorderLayout.LINE_START);
		
		
		tablePanel.setData(controller.getEsami());
		add(tablePanel, BorderLayout.CENTER);
		
		
	}

	/**
	 * Aggiorna i dati all'interno della tabella
	 */
	@Override
	public void aggiorna() {
        tablePanel.aggiorna();
	}
	
	/**
	 * Filtra i dati della tabella per nome dello studente
	 */
	@Override
	public void filtroNome(String nome) {
		tablePanel.filtroNome(nome);
	}
	
	/**
	 * Filtra i dati della tabella per corso
	 */
	 @Override
	 public void filtroCorso(String corso) {
		 tablePanel.filtroNome(null);
		 tablePanel.filtroCorso(corso);
	 }
	 
	 /**
	  * Ritorna i dati degli esami presenti nella tabella
	  */
	 @Override
	 public List<Esame> listaTable(){
		 return tablePanel.listaTable();
	 }
}
