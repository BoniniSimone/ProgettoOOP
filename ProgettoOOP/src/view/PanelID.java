package view;

import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.NumberFormat;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.SwingUtilities;

/**
 * Pannello per l'iserimento dell'id.
 * @author Simone Bonini
 */

public class PanelID extends JPanel{
	private static final long serialVersionUID = 1L;
	private JFormattedTextField fieldCanc; //Si dichiara un JFormattedTextField perchè si vuole inserire un numero all'interno di un textField
	
	public PanelID(String text) {
		setLayout(new GridLayout(0, 2, 10, 10));
		add(new JLabel(text));
		fieldCanc = new JFormattedTextField(NumberFormat.getNumberInstance());
		add(fieldCanc);
		
		fieldCanc.addKeyListener(new KeyAdapter() {  //Il KeyListener permette di ascoltare gli eventi da tastiera
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    JRootPane rootPane = SwingUtilities.getRootPane(PanelID.this);
                    if (rootPane != null) {
                        rootPane.getDefaultButton().doClick();
                    }
                }
            }
        });
	}
	
	public int getID() {
		Number value = (Number) fieldCanc.getValue();
        if (value != null) {
            return value.intValue();
        }
		return -1;
	}
	
	
	/**
	 * Metodo che controlla che l'ID sia positivo
	 * @return true se l'id è positivo
	 */
	public boolean checkID() {
		if(getID()<0) return false;
		return true;
	}
}