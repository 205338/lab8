package it.polito.tdp.metrodeparis.model;

import com.javadocmd.simplelatlng.LatLng;

public class Fermata {

	int idFermata;
	String nome;
	double coordX, coordY;

	LatLng coord;

//	int idLinea;

	public Fermata(int idFermata, String nome, double coordX, double coordY) {
		super();
		this.idFermata = idFermata;
		this.nome = nome;
		this.coordX = coordX;
		this.coordY = coordY;
		this.coord = new LatLng(coordX, coordY);
	}

	public int getIdFermata() {
		return idFermata;
	}

	public void setIdFermata(int idFermata) {
		this.idFermata = idFermata;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getCoordX() {
		return coordX;
	}

	public void setCoordX(double coordX) {
		this.coordX = coordX;
	}

	public double getCoordY() {
		return coordY;
	}

	public void setCoordY(double coordY) {
		this.coordY = coordY;
	}

	public LatLng getCoord() {
		return coord;
	}

	public void setCoord(LatLng coord) {
		this.coord = coord;
	}

//	public int getIdLinea() {
//		return idLinea;
//	}
//
//	public void setIdLinea(int idLinea) {
//		this.idLinea = idLinea;
//	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idFermata;
//		result = prime * result + idLinea;
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
		Fermata other = (Fermata) obj;
		if (idFermata != other.idFermata)
			return false;
//		if (idLinea != other.idLinea)
//			return false;
		return true;
	}

	@Override
	public String toString() {
		return nome;
	}
	
	

}
