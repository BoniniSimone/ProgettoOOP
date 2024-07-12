package view.opt;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

/**
 * Pannello per la scelta del numero di prove intermedie
 * @author Simone Bonini
 */

public class PanelNumEs extends JPanel{
	private static final long serialVersionUID = 1L;
	private JSpinner spinnerNumEs;
	
	public PanelNumEs() {
		add(new JLabel("Inserire numero di prove intermedie:"));
		spinnerNumEs = new JSpinner();
		SpinnerNumberModel modelSpinner = new SpinnerNumberModel(2, 2, 10, 1);
        spinnerNumEs.setModel(modelSpinner);
		add(spinnerNumEs);
	}
	
	public int getNumEs() {
		return (int) spinnerNumEs.getValue();
	}
}
