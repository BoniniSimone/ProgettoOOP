package model;

/**
 * Classe per la gestione di un esame composto
 * @author Simone Bonini
 */

public class EsameComposto extends Esame{
	private static final long serialVersionUID = 1L;
	double[] pesiProve;
	private int[] votiProve;
	
	public EsameComposto(int id, String nomeStud, String corso, int[] votiProve, double[] pesiProve, int cfu) {
		super(id, nomeStud, corso, cfu);
		setPesiProve(pesiProve);
		setVotiProve(votiProve);
	}

	public int getNumProve() {
		return pesiProve.length;
	}


	public double[] getPesiProve() {
		return pesiProve;
	}


	public void setPesiProve(double[] pesiProve) {
		this.pesiProve = pesiProve;
	}

	public int[] getVotiProve() {
		return votiProve;
	}

	public void setVotiProve(int[] votiProve) {
		this.votiProve = votiProve;
	}
	
	@Override
	public int getVoto() {
		double sum = 0;
		for (int i=0; i<pesiProve.length; i++) {
			sum= sum+(pesiProve[i]*votiProve[i]);
		}
		return (int) Math.round(sum);
	}

	
	
}
