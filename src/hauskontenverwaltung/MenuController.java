package hauskontenverwaltung;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * FXML Controller class enthält alle Ereignisroutinen, um auf 
 * Aktionen in der Benutzeroberfäche zu reagieren.
 *
 * @author opodlubnaja
 */
public class MenuController implements Initializable{
    
    @FXML
    private MenuBar menuBar;

    @Override
    public void initialize(java.net.URL arg0, ResourceBundle arg1) {

    }

    /**
     * Methode 
     * @param actionEvent
     * @throws IOException 
     */
    public void handleEigentuemerverwaltung(ActionEvent actionEvent) throws IOException {
        System.out.println("Eigentümer anzeigen");
        URL paneEigentum = getClass().getResource("Eigentuemer.fxml");
        AnchorPane eigentum = FXMLLoader.load( paneEigentum );
      
        BorderPane border = Hauskontenverwaltung.getRoot();
        border.setCenter(eigentum); 
        Hauskontenverwaltung.getFenster().setTitle("Eigentümerverwaltung");
    }

    public void handleKostenkonten(ActionEvent actionEvent) throws IOException {
        System.out.println("Kostenkonten anzeigen");
       // Stage stage = (Stage) menuBar.getScene().getWindow();
        URL paneKosten = getClass().getResource("Kostenkonten.fxml");
        AnchorPane kosten = FXMLLoader.load( paneKosten );
      
        BorderPane border = Hauskontenverwaltung.getRoot();
        border.setCenter(kosten);
        Hauskontenverwaltung.getFenster().setTitle("Kostenkontenverwaltung");
        
    }
    
    public void handleBuchung(ActionEvent actionEvent) throws IOException {
        System.out.println("Buchung anzeigen");
       // Stage stage = (Stage) menuBar.getScene().getWindow();
        URL paneBuchhaltung = getClass().getResource("Buchung.fxml");
        AnchorPane buchhaltung = FXMLLoader.load( paneBuchhaltung );
      
        BorderPane border = Hauskontenverwaltung.getRoot();
        border.setCenter(buchhaltung);
        
        Hauskontenverwaltung.getFenster().setTitle("Buchungsvorgang");
    }
 
    
}

