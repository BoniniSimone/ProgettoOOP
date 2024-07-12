package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import controller.Controller;
import model.Esame;
import model.EsameComposto;

/**
 * Pannello per la visualizzazione della tabella
 * @author Simone Bonini
 */

public class PanelTable extends JPanel{
	private static final long serialVersionUID = 1L;
	private JTable table;
	private TableModelEsami tableModelEsami;
	private Controller controller;
	
	public PanelTable(Controller controller) {
		this.controller = controller;
		setPreferredSize(new Dimension(600, 400));
		tableModelEsami = new TableModelEsami(controller);
		table = new JTable(tableModelEsami); //Si passa il modello della tabella al costruttore della tabella
		
		//Si centrano le colonne ID, voto, lode e cfu, con un render
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
        table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer); // ID
        table.getColumnModel().getColumn(3).setCellRenderer(centerRenderer); // Voto
        table.getColumnModel().getColumn(4).setCellRenderer(centerRenderer); // Lode
        table.getColumnModel().getColumn(5).setCellRenderer(centerRenderer); // Cfu
		
		
		setLayout(new BorderLayout());
		add(new JScrollPane(table), BorderLayout.CENTER);
		
		//Aggiungiamo un evento del mouse per il click su un esame 
		 table.addMouseListener(new MouseAdapter() {
	            public void mouseClicked(MouseEvent e) {
	            	
	                if (e.getClickCount() == 2  ) { // 2 perchè sarà il doppio click ad aprire i dettagli degli esami
	                    JTable target = (JTable)e.getSource();
	                    int row = target.getSelectedRow();
	                    int id = (int) tableModelEsami.getValueAt(row, 0);
	                    Esame esame = controller.getEsame(id);
	                    if(esame instanceof EsameComposto) {
	                    	EsameComposto esameC = (EsameComposto) esame;
	                    	PanelDettagliEsame panelDE = new PanelDettagliEsame(esameC.getNumProve(), esameC.getNomeStud(), esameC.getCorso(), esameC.getVotiProve(), esameC.getPesiProve(), esameC.getCfu());
	                    	JOptionPane.showMessageDialog(null, panelDE, "Dettagli Esame", JOptionPane.INFORMATION_MESSAGE);
	                    }
	                }
	            }
	        });
	}
	
	public JTable getTable() {
        return table;
    }
	
	public void setData(List<Esame> listaEsami) {
		tableModelEsami.setData(listaEsami);
	}
	
	/**
	 * Metodo per aggiornare la tabella
	 */
	public void aggiorna() {
		tableModelEsami.setData(controller.getEsami());
		tableModelEsami.fireTableDataChanged();
	}
	
	/**
	 * Metodo per filtrare gli esami per nome dello studente
	 * @param nome dello studente
	 */
	public void filtroNome(String nome) {
		tableModelEsami.filtroNomeStudente(nome);
	}
	
	/**
	 * Metodo per filtrare gli esami per il nome del corso
	 * @param nome del corso
	 */
	public void filtroCorso(String corso) {
		tableModelEsami.filtroCorso(corso);
	}
	
	/**
	 * Metodo che restituisce la lista degli esami visulizzati (quindi eventualmente filtrati)
	 * @return esami visualizzati 
	 */
	public List<Esame> listaTable(){
		return tableModelEsami.listaTable();
	}
	
}
