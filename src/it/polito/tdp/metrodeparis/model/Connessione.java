package it.polito.tdp.metrodeparis.model;

public class Connessione {

	int idConnessione; 
	int idLinea; 
	int idStazioneP; 
	int idStazioneA;
	
	public Connessione(int idConnessione, int idLinea, int idStazioneP, int idStazioneA) {
		super();
		this.idConnessione = idConnessione;
		this.idLinea = idLinea;
		this.idStazioneP = idStazioneP;
		this.idStazioneA = idStazioneA;
	}

	public int getIdConnessione() {
		return idConnessione;
	}

	public void setIdConnessione(int idConnessione) {
		this.idConnessione = idConnessione;
	}

	public int getIdLinea() {
		return idLinea;
	}

	public void setIdLinea(int idLinea) {
		this.idLinea = idLinea;
	}

	public int getIdStazioneP() {
		return idStazioneP;
	}

	public void setIdStazioneP(int idStazioneP) {
		this.idStazioneP = idStazioneP;
	}

	public int getIdStazioneA() {
		return idStazioneA;
	}

	public void setIdStazioneA(int idStazioneA) {
		this.idStazioneA = idStazioneA;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idConnessione;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Connessione other = (Connessione) obj;
		if (idConnessione != other.idConnessione)
			return false;
		return true;
	}

}
