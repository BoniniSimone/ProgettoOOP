package model;

/**
 * Classe per la getione di un esame semplice.
 * @author Simone Bonini
 */

public class EsameSemplice extends Esame{
	private static final long serialVersionUID = 1L;
	private boolean lode;
	
	public EsameSemplice(int id, String nomeStud, String corso, int voto, boolean lode, int cfu) {
		super(id, nomeStud, corso, cfu);
		this.setLode(lode);
		this.setVoto(voto);
	}

	public boolean isLode() {
		return lode;
	}

	public void setLode(boolean lode) {
		this.lode = lode;
	}
	
	
	@Override
	public boolean getLode() {
		if(lode) return true;
		return false;
	}
	
}
