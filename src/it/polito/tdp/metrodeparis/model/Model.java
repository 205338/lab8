package it.polito.tdp.metrodeparis.model;

import java.util.List;
import java.util.Map;

import org.jgrapht.GraphPath;
import org.jgrapht.Graphs;
import org.jgrapht.WeightedGraph;
import org.jgrapht.alg.DijkstraShortestPath;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.graph.WeightedMultigraph;

import com.javadocmd.simplelatlng.LatLng;
import com.javadocmd.simplelatlng.LatLngTool;
import com.javadocmd.simplelatlng.util.LengthUnit;

import it.polito.tdp.metrodeparis.dao.ConnessioneDAO;
import it.polito.tdp.metrodeparis.dao.FermataDAO;
import it.polito.tdp.metrodeparis.dao.LineaDAO;


public class Model {

	FermataDAO fermataDAO;
	LineaDAO lineaDAO;
	ConnessioneDAO connessioneDAO;

	DefaultDirectedGraph<Integer, DefaultEdge> grafoMetro;

	Map<Integer, Fermata> tutteLeFermate;
	Map<Integer, Connessione> allConnections;
	Map<Integer, Linea> allLines;
	
	public Model() {
		fermataDAO = new FermataDAO();
		lineaDAO = new LineaDAO();
		connessioneDAO = new ConnessioneDAO();
		grafoMetro = new DefaultDirectedGraph<Integer, DefaultEdge>(DefaultEdge.class);
	}

	protected void generaGrafo() {
		tutteLeFermate = getTutteLeFermate();
		allConnections = getAllConnections();
		allLines = getAllLines();
		generaNodiGrafo();
	}

//	protected void popolaFermateConLinee() {
//		for (Fermata f : tutteLeFermate.values()) {
//			int idLinea;
//			f.setIdLinea(idLinea);
//		}
//	}

	public String percosoTempoMinimo(Fermata stazioneP, Fermata stazioneA) {
		String text = "Percorso:\n\n";
		Fermata startVertex = stazioneP; 
		Fermata endVertex = stazioneA;
		int idStazioneP = startVertex.getIdFermata();
		int idStazioneA = endVertex.getIdFermata();
		List<Integer> pathList = getShortestPath(idStazioneP, idStazioneA);
		
		
		DefaultEdge e;
		@SuppressWarnings("unused")
		double time = 0;
		int idConnessionePrecedente = 0;
		int idConnessioneAttuale = 0;
		
		for (int i = 0; i < pathList.size()-1; i++) {
			e = grafoMetro.getEdge(pathList.get(i), pathList.get(i)+1);
			time += grafoMetro.getEdgeWeight(e);
			idConnessioneAttuale = getIdConnection(idStazioneP, idStazioneA);
			if (idConnessioneAttuale != idConnessionePrecedente) {
				int idLinea = allConnections.get(idConnessioneAttuale).getIdLinea();				 
				text += "Prendo linea: " + allLines.get(idLinea).getNome() + "\n\n";
				time += 30;
			}
			 
			text += tutteLeFermate.get(pathList.get(i)).getNome() +", "+ tutteLeFermate.get(pathList.get(i+1)).getNome() +", ";  
		}
		
		return text;
	}
	
	protected void generaArchiGrafo() {
		for (Connessione c : allConnections.values()) {
			int idStazioneP = c.getIdStazioneP();
			int idStazioneA = c.getIdStazioneA();

			DefaultEdge e = grafoMetro.addEdge(idStazioneP, idStazioneA);
			LatLng coordP = tutteLeFermate.get(idStazioneP).getCoord();
			LatLng coordA = tutteLeFermate.get(idStazioneA).getCoord();

			int idLinea = c.getIdLinea();
			Linea line = allLines.get(idLinea);

			double distance = LatLngTool.distance(coordP, coordA, LengthUnit.KILOMETER);
			double speed = line.getVelocita();
			double time = (distance / speed) * 60 * 60;  // in seconds
			grafoMetro.setEdgeWeight(e, time);
		}
	}

	public List<Integer> getShortestPath(Integer startVertex, Integer endVertex) {
		
		DijkstraShortestPath<Integer, DefaultEdge> dijkstraShortestPath = new DijkstraShortestPath<Integer, DefaultEdge>(grafoMetro, startVertex, endVertex);
		GraphPath< Integer, DefaultEdge> path = dijkstraShortestPath.getPath();
		
		return Graphs.getPathVertexList(path);
	}
	
	protected void generaNodiGrafo() {
		for (Fermata f : tutteLeFermate.values()) {
			grafoMetro.addVertex(f.getIdFermata());
		}
	}

	public Map<Integer, Connessione> getAllConnections() {
		return connessioneDAO.getAllConnections();
	}

	public List<String> getNomiTutteLeFermate() {
		return fermataDAO.getNomiTutteLeFermate();
	}

	public Map<Integer, Fermata> getTutteLeFermate() {
		return fermataDAO.getTutteLeFermate();
	}

	public Map<Integer, Linea> getAllLines() {
		return lineaDAO.getAllLines();
	}

	public int getIdConnection(int idStazioneP, int idStazioneA) {
		return connessioneDAO.getIdConnection(idStazioneP, idStazioneA);
	}


	
}
