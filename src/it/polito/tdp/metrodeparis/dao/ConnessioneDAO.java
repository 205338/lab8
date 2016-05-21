package it.polito.tdp.metrodeparis.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

import it.polito.tdp.metrodeparis.model.Connessione;



public class ConnessioneDAO {

	public Map<Integer, Connessione> getAllConnections() {
		
		Map<Integer, Connessione> allConnections = new LinkedHashMap<Integer, Connessione>();
		
		
		try {
			Connection conn = DBConnect.getInstance().getConnection();
			String sql = "SELECT * FROM fermata";
			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				int idConnessione = rs.getInt(1); 
				int idLinea = rs.getInt(2);
				int idStazioneP = rs.getInt(3);
				int idStazioneA = rs.getInt(4);
				
				Connessione c = new Connessione(idConnessione, idLinea, idStazioneP, idStazioneA);
				
				allConnections.put(idConnessione, c);
				
			}

		return allConnections;
		
		} catch (SQLException e) {
			System.out.println("Errore connessione al database");
			throw new RuntimeException("Error Connection Database");
		}
		 
	}
	
	public int getIdConnection(int idStazioneP, int idStazioneA) {
		
		try {
			Connection conn = DBConnect.getInstance().getConnection();
			String sql = "SELECT id_connessione FROM connesione WHERE (id_stazP=? AND id_stazA=?)";
			PreparedStatement st = conn.prepareStatement(sql);

			st.setString(1, ""+idStazioneP);
			st.setString(2, ""+idStazioneA);
			
			ResultSet rs = st.executeQuery();
			
			int idConnessione = 0;
			if (rs.next()) {
				idConnessione = rs.getInt(1); 	
			}

		return idConnessione;
		
		} catch (SQLException e) {
			System.out.println("Errore connessione al database");
			throw new RuntimeException("Error Connection Database");
		}
	}
	
}
