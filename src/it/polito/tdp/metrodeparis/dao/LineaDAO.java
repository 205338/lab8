package it.polito.tdp.metrodeparis.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

import it.polito.tdp.metrodeparis.model.Linea;


public class LineaDAO {

	public Map<Integer, Linea> getAllLines() {
		
		Map<Integer, Linea> tutteLeLinee = new LinkedHashMap<Integer, Linea>();
		
		
		try {
			Connection conn = DBConnect.getInstance().getConnection();
			String sql = "SELECT * FROM linea";
			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				
				int idLinea = rs.getInt("id_fermata");
				String nome = rs.getString("nome");
				double velocita = rs.getDouble("coordX");
				double intervallo = rs.getDouble("coordY");
				String colore = rs.getString("colore");

				Linea l = new Linea(idLinea, nome, velocita, intervallo, colore);
				tutteLeLinee.put(idLinea, l);
			}
			
			return tutteLeLinee;
			
		} catch (SQLException e) {
			System.out.println("Errore connessione al database");
			throw new RuntimeException("Error Connection Database");
		}
		
	}
	
}
