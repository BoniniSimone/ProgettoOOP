package view.opt;

import java.awt.GridLayout;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * Pannello per l'inserimento di un nuovo esame semplice.
 * @author Simone Bonini
 */

public class PanelAdd extends JPanel{
	private static final long serialVersionUID = 1L;
	private JTextField nomeField;
	private JTextField esameField;
	private JSpinner spinnerVoto;
	private JCheckBox checkLode;
	private JSpinner spinnerCfu;
	 
	public PanelAdd() {
       setLayout(new GridLayout(0, 1));
        
        add(new JLabel("Nome studente:"));
        nomeField = new JTextField();
        add(nomeField);

        add(new JLabel("Corso:"));
        esameField = new JTextField();
        add(esameField);

        add(new JLabel("Voto:"));
        spinnerVoto = new JSpinner();
        SpinnerNumberModel modelSpinner = new SpinnerNumberModel(24, 18, 30, 1); //Si imposta lo Spinner in modo che possa assumere valori da 18 a 30
        spinnerVoto.setModel(modelSpinner);
        add(spinnerVoto);
    
        
        //Si evita venga selezionata la lode se il voto è inferiore a 30
        spinnerVoto.addChangeListener((ChangeListener) new ChangeListener() {
        	@Override
            public void stateChanged(ChangeEvent e) {
                int value = (int) spinnerVoto.getValue();
                if (value == 30) {
                    checkLode.setEnabled(true);
                } else {
                    checkLode.setEnabled(false);
                    checkLode.setSelected(false); // Deseleziona la checkbox quando viene disabilitata
                }
            }
        });
        
        add(new JLabel("Lode:"));
        checkLode = new JCheckBox();
        checkLode.setEnabled(false);
        add(checkLode);
        
        add(new JLabel("Cfu:"));
        spinnerCfu = new JSpinner();
        SpinnerNumberModel modelSpinner2 = new SpinnerNumberModel(9, 1, 12, 1); //Si imposta lo Spinner in modo che possa assumere valori da 1 a 12
        spinnerCfu.setModel(modelSpinner2);
        add(spinnerCfu);
        
        
	}
	
	public String getNome() {
        return nomeField.getText();
    }

    public String getEsame() {
        return esameField.getText();
    }

    public int getVoto() {
        return (int) spinnerVoto.getValue();
    }
    
    public boolean getLode() {
    	return checkLode.isSelected();
    }
    
    public int getCfu() {
    	return (int) spinnerCfu.getValue();
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
