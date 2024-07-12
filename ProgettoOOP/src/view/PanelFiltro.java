package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import model.Esame;
import java.util.HashMap;
import java.util.List;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 * Pannello per filtrare gli esami.
 * @author Simone Bonini
 */

public class PanelFiltro extends JPanel{
	private static final long serialVersionUID = 1L;
	private ListenerEsame esameListener;
	private JLabel labelStud;
	private JTextField fieldStud;
	private JLabel labelCorso;
	private JTextField fieldCorso;
	private JButton filtroNomeButton;
	private JButton filtroCorsoButton;
	private JButton statNButton;
	private JButton statCButton;
	
	
	public PanelFiltro() {
		setPreferredSize(new Dimension(300, 200));
		setLayout(new GridBagLayout());
		
		Border bordoInterno = BorderFactory.createTitledBorder("Filtro");
		Border bordoEsterno = BorderFactory.createEmptyBorder(0, 5, 5, 5);
		Border bordoFinale = BorderFactory.createCompoundBorder(bordoEsterno, bordoInterno);
		setBorder(bordoFinale);
		
		labelStud = new JLabel("Nome studente: ");
		fieldStud =new JTextField(20);
		filtroNomeButton = new JButton("Filtra");
		statNButton = new JButton("Statistiche");
		statNButton.setEnabled(false);
		
		labelCorso = new JLabel("Nome corso: ");
		fieldCorso =new JTextField(20);
		filtroCorsoButton = new JButton("Filtra");
		statCButton = new JButton("Statistiche");
		statCButton.setEnabled(false);
		
		
		
		//LAYOUT
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(10, 5, 3, 5);

		//----Filtro per nome studente----
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weighty = 0.01;
		gbc.weightx = 0.1;
		add(labelStud, gbc);

		gbc.gridx = 1;
		gbc.weightx = 1.0; // Peso in orizzontale
		gbc.fill = GridBagConstraints.HORIZONTAL;
		add(fieldStud, gbc);

		gbc.insets = new Insets(3, 5, 3, 5);

		//----Botone filtro nome----
		gbc.gridx = 0;
		gbc.gridy = 1; // seconda riga
		gbc.weighty = 0.01;
		gbc.gridwidth = 1;  // quante colonne il componente può occupare
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.PAGE_START;
		add(filtroNomeButton, gbc);

		gbc.gridx = 1; // seconda colonna
		gbc.gridwidth = 1;  // quante colonne il componente può occupare
		gbc.fill = GridBagConstraints.HORIZONTAL;
		add(statNButton, gbc);

		//----Filtro per nome esame----
		gbc.gridy = 2; // terza riga
		gbc.gridx = 0;
		gbc.weightx = 0.01;
		gbc.weighty = 0.01;
		gbc.gridwidth = 1;
		gbc.fill = GridBagConstraints.CENTER;
		gbc.anchor = GridBagConstraints.PAGE_START;
		gbc.insets = new Insets(3, 5, 0, 5); 
		add(labelCorso, gbc);

		gbc.gridx = 1;
		gbc.weightx = 1.0;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		add(fieldCorso, gbc);

		gbc.insets = new Insets(3, 5, 3, 5); 

		//----Botone filtro corso----
		gbc.gridx = 0;
		gbc.gridy = 3; // quarta riga
		gbc.weighty = 0.01;
		gbc.gridwidth = 1;  // quante colonne il componente può occupare
		gbc.fill = GridBagConstraints.HORIZONTAL;
		add(filtroCorsoButton, gbc);

		gbc.gridx = 1;
		gbc.gridwidth = 1;  // quante colonne il componente può occupare
		gbc.fill = GridBagConstraints.HORIZONTAL;
		add(statCButton, gbc);
		
		
		//EVENTI
		
		//Event bottone filtoNome
		filtroNomeButton.addActionListener((ActionListener) new ActionListener() {
			@Override
       	 public void actionPerformed(ActionEvent e) {
				fieldCorso.setText(""); //Si resetta il field dell'altro filtro siccome non si permette il doppio filtraggio
				statCButton.setEnabled(false); //Si disattiva il bottone per visualizzare le statistiche rispetto ad un filtro sul corso
				filtroNome(fieldStud.getText());
				if(fieldStud.getText()==null || fieldStud.getText().isEmpty()) statNButton.setEnabled(false); 
				else statNButton.setEnabled(true);
			}
		});
		
		//Event bottone filtroCorso
		filtroCorsoButton.addActionListener((ActionListener) new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				fieldStud.setText("");//Si resetta il field dell'altro filtro siccome non si permette il doppio filtraggio
				statNButton.setEnabled(false); //Si disattiva il bottone per visualizzare le statistiche rispetto ad un filtro sul nome
				filtroCorso(fieldCorso.getText());
				if(fieldCorso.getText()==null || fieldCorso.getText().isEmpty()) statCButton.setEnabled(false);
				else statCButton.setEnabled(true);
			}
		});
		
		//Event bottone statNButton
		statNButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFreeChart chart = ChartFactory.createBarChart("Andamento studenti", "Valutazioni", "Numero di esami", buildDataset(buildHM(listaTable())), PlotOrientation.VERTICAL, false, false, false);
				ChartFrame cFrame = new ChartFrame("Tabella esami", chart);
				cFrame.pack();
		        cFrame.setLocationRelativeTo(null);
				cFrame.setVisible(true);
				
			}
		});
		
		//Event bottone statCButton
		statCButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFreeChart chart = ChartFactory.createBarChart("Andamento corsi", "Valutazioni", "Numero di esami", buildDataset(buildHM(listaTable())), PlotOrientation.VERTICAL, false, false, false);
				ChartFrame cFrame = new ChartFrame("Tabella esami", chart);
				cFrame.pack();
		        cFrame.setLocationRelativeTo(null);
				cFrame.setVisible(true);
				
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
	 * Metodo per filtrare gli esami per nome dello studente
	 * @param nome dello studente
	 */
    private void filtroNome(String nome) {
        if (esameListener != null) {
        	esameListener.filtroNome(nome);
        }
    }
    
    /**
	 * Metodo per filtrare gli esami per il nome del corso
	 * @param nome del corso
	 */
    public void filtroCorso(String corso) {
    	if(esameListener != null) {
    		esameListener.filtroCorso(corso);
    	}
    }
    
    /**
	 * Metodo che restituisce la lista degli esami visualizzati nella tabella (quindi eventualmente filtrati)
	 * @return esami visualizzati 
	 */
    public List<Esame> listaTable(){
    	if(esameListener != null) {
    		return esameListener.listaTable();
    	}
    	return null;
    }
    
    /**
     * Metodo per creare un hashmap che associ ad ogni voto il numero di esami con tale risultato
     * @param lista degli esami visualizzati nella tabella
     * @return hashmap
     */
    public HashMap<Integer, Integer> buildHM(List<Esame> esami) {
    	HashMap<Integer, Integer> votiCount = new HashMap<>();
    	for(int i = 18; i<=30; i++) {
    		votiCount.put(i, 0);
    	}
    	for(Esame esame : esami) {
    		int voto = esame.getVoto();
    		votiCount.put(voto, votiCount.getOrDefault(voto, 0)+1);  //getOrDeafult: restituisce il valore associato ad una chiave data o un valore di default specificato (in questo caso 0)
    	}
    	return votiCount;
    }
    
    
    /**
     * Metodo per la creazione di un dataset da passare alla funzione ChartFactory.createBarChart
     * @param hashmap con voti e numero di esami con tali voti
     * @return dataset
     */
    public DefaultCategoryDataset buildDataset(HashMap<Integer, Integer> votiCount) {
    	DefaultCategoryDataset dataset = new DefaultCategoryDataset();
    	for(Integer i=18; i<=30; i++) {
    		dataset.addValue(votiCount.getOrDefault(i,0), "Numero esami", i);
    	}
    	return dataset;
    }
}
