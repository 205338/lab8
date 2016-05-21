package it.polito.tdp.metrodeparis;

import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import it.polito.tdp.metrodeparis.model.Fermata;
import it.polito.tdp.metrodeparis.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;

public class MetroDeParisController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ChoiceBox<Fermata> choiceBoxPartenza;

    @FXML
    private ChoiceBox<Fermata> choiceBoxArrivo;

    @FXML
    private Button btnCalcola;

    @FXML
    private TextArea txtRisultato;

    Model model;
    
    @FXML
    void calcolaPercorso(ActionEvent event) {
    	Fermata stazioneP = choiceBoxPartenza.getValue();
    	Fermata stazioneA = choiceBoxArrivo.getValue();
    	String text = model.percosoTempoMinimo(stazioneP, stazioneA);
    	txtRisultato.setText(text);
    }
    public void setModel(Model model) {
		this.model = model;
		
		Map<Integer, Fermata> tutteLeFermate = model.getTutteLeFermate();
		
		choiceBoxArrivo.getItems().addAll(tutteLeFermate.values());
		choiceBoxPartenza.getItems().addAll(tutteLeFermate.values());
		
	}
    
    @FXML
    void initialize() {
        assert choiceBoxPartenza != null : "fx:id=\"choiceBoxPartenza\" was not injected: check your FXML file 'MetroDeParis.fxml'.";
        assert choiceBoxArrivo != null : "fx:id=\"choiceBoxArrivo\" was not injected: check your FXML file 'MetroDeParis.fxml'.";
        assert btnCalcola != null : "fx:id=\"btnCalcola\" was not injected: check your FXML file 'MetroDeParis.fxml'.";
        assert txtRisultato != null : "fx:id=\"txtRisultato\" was not injected: check your FXML file 'MetroDeParis.fxml'.";

    }
}
