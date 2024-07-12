package view;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Pannello per la visualizzazione degli esami intermedi di un esame composto.
 * @author Simone Bonini
 */

public class PanelDettagliEsame extends JPanel{
	private static final long serialVersionUID = 1L;

	public PanelDettagliEsame(int numEs, String nome, String corso, int[] voti, double[] pesi, int cfu) {
		setLayout(new GridLayout(0, 2, 10, 10));
		
        add(new JLabel("Nome studente:"));
        add(new JLabel(nome));

        add(new JLabel("Corso:"));
        add(new JLabel(corso));
        
        for (int i = 0; i < numEs; i++) {
        	add(new JLabel("Voto esame " + (i + 1)));
        	add(new JLabel(String.valueOf(voti[i])));

            add(new JLabel("Peso esame " + (i + 1)));
            add(new JLabel(String.valueOf(pesi[i])));
        }
        
        add(new JLabel("Cfu:"));
        add(new JLabel(String.valueOf(cfu)));
        
	}
	
}
