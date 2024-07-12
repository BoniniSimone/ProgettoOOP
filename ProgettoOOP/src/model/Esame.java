package model;
import java.io.Serializable;

/**
 * Classe per la getione  degli esami.
 * @author Simone Bonini
 */

public abstract class Esame implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private String nomeStud;
	private String corso;
	private int cfu;
	private int voto;
	
	public Esame(int id, String nomeStud,  String corso, int cfu) {
		this.id = id;
		this.setNomeStud(nomeStud);
		this.setCorso(corso);
		this.setCfu(cfu);
		
	}

	public int getId() {
		return id;
	}
	
	public String getNomeStud() {
		return nomeStud;
	}

	public void setNomeStud(String nomeStud) {
		this.nomeStud = nomeStud;
	}

	public String getCorso() {
		return corso;
	}

	public void setCorso(String corso) {
		this.corso = corso;
	}

	public int getCfu() {
		return cfu;
	}

	public void setCfu(int cfu) {
		this.cfu = cfu;
	}
	
	public void setVoto(int voto) {
		this.voto = voto;
	}
	
	public int getVoto() {
		return voto;
	}
	
	public boolean getLode() {
		return false;
	}
	
}
