package it.polito.tdp.metrodeparis.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import it.polito.tdp.metrodeparis.model.Fermata;

public class FermataDAO {

	public Map<Integer, Fermata> getTutteLeFermate() {
	
		Map<Integer, Fermata> tutteLeFermate = new LinkedHashMap<Integer, Fermata>();
		
		
		try {
			Connection conn = DBConnect.getInstance().getConnection();
			String sql = "SELECT * FROM fermata";
			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				
				int idFermata = rs.getInt("id_fermata");
				String nome = rs.getString("nome");
				double coordX = rs.getDouble("coordX");
				double coordY = rs.getDouble("coordY");
				
				Fermata f = new Fermata(idFermata, nome, coordX, coordY);
				tutteLeFermate.put(idFermata, f);
			}
			
			return tutteLeFermate;
			
		} catch (SQLException e) {
			System.out.println("Errore connessione al database");
			throw new RuntimeException("Error Connection Database");
		}
		
	}
	
	public List<String> getNomiTutteLeFermate() {
		List<String> nomeTutteLeFermate = new LinkedList<String>();
		
		
		try {
			Connection conn = DBConnect.getInstance().getConnection();
			String sql = "SELECT nome FROM fermata";
			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				
				String nome = rs.getString("nome");

				nomeTutteLeFermate.add(nome);
			}
			
			return nomeTutteLeFermate;
			
		} catch (SQLException e) {
			System.out.println("Errore connessione al database");
			throw new RuntimeException("Error Connection Database");
		}
		
	}
	
}
