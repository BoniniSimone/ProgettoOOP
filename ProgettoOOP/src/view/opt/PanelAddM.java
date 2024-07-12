package view.opt;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

/**
 * Pannello per l'inserimento di un nuovo esame composto.
 * @author Simone Bonini
 */

public class PanelAddM extends JPanel{
	private static final long serialVersionUID = 1L;
	private JTextField nomeField;
	private JTextField esameField;
	private JSpinner[] spinnerVoti;
    private JSpinner[] spinnerPesi;
    private JSpinner spinnerCfu;
	
	public PanelAddM(int num) {
		setLayout(new GridLayout(0, 2, 10, 10));
		
        add(new JLabel("Nome studente:"));
        nomeField = new JTextField();
        add(nomeField);

        add(new JLabel("Corso:"));
        esameField = new JTextField();
        add(esameField);
        
        spinnerVoti = new JSpinner[num];
        spinnerPesi = new JSpinner[num];
        
        for (int i = 0; i < num; i++) {
        	spinnerVoti[i] = new JSpinner(new SpinnerNumberModel(24, 18, 30, 1)); 
        	spinnerPesi[i] = new JSpinner(new SpinnerNumberModel(1.0/num, 0.01, 0.99, 0.05)); 

            add(new JLabel("Voto esame " + (i + 1)));
            add(spinnerVoti[i]);
            add(new JLabel("Peso esame " + (i + 1)));
            add(spinnerPesi[i]);
        }
        
        add(new JLabel("Cfu:"));
        spinnerCfu = new JSpinner();
        SpinnerNumberModel modelSpinner2 = new SpinnerNumberModel(9, 1, 12, 1);
        spinnerCfu.setModel(modelSpinner2);
        add(spinnerCfu);
        
	}

	public String getNome() {
		return nomeField.getText();
	}
	
	public String getCorso() {
		return esameField.getText();
	}
	
	 public int[] getVoti() {
	        int[] voti = new int[spinnerVoti.length];
	        for (int i = 0; i < spinnerVoti.length; i++) {
	            voti[i] = (int) spinnerVoti[i].getValue();
	        }
	        return voti;
	    }
	 
	 public double[] getPesi() {
	        double[] pesi = new double[spinnerPesi.length];
	        for (int i = 0; i < spinnerPesi.length; i++) {
	            pesi[i] = (double) spinnerPesi[i].getValue();
	        }
	        return pesi;
	    }
	 
	 public int getCfu() {
		 return (int) spinnerCfu.getValue();
	 }
	 
	 /**
	  * Verifica che la somma dei pesi sia corretta
	  * @return true se la somma dei pesi è corretta
	  */
	 public boolean checkPesi() {
		 double [] pesi = getPesi();
		 double sum = 0;
		 for(double peso : pesi) {
			 sum+=peso;
		 }
		 if(sum == 1) return true;
		 return false;
	 }
	 
	 /**
	 * Verifica che sia stato inserito il nome
	 * @return false se non è stato inserito il nome
	 */
	 public boolean checkNome() {
		 if(nomeField.getText().isEmpty()) return false;
		 return true;
	 }
	 
	 /**
	 * Verifica che sia stato inserito il corso
	 * @return false se non è stato inserito il corsos
	 */	 
	 public boolean checkCorso() {
	    	if(esameField.getText().isEmpty()) return false;
	    	return true;
	    }
	 
}
