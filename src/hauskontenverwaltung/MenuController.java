package hauskontenverwaltung;

import java.io.File;
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
import javafx.scene.control.Alert;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class enthält alle Ereignisroutinen, um auf 
 * Aktionen in der Benutzeroberfäche zu reagieren.
 *
 * @author opodlubnaja
 */
public class MenuController implements Konstanten{        
    
    
    AnchorPane centerPane;
    //private Hauskontenverwaltung hkv;
    private EigentuemerController egcontroller;
    private BuchungController bcontroller;
    private KostenkontenController kkcontroller;
    private Hauskontenverwaltung hkverwaltung;
    BorderPane border;
    boolean isliste = false;
    @FXML
    //private MenuBar menuBar;  
    
    public void initialize()
    {
    }
    // Referenz auf PersonenVerwaltung
    
    /**
     * Methode setzt die Referenz
     * @param pv PersonenVerwaltung
     */
    public void setVerwaltung(Hauskontenverwaltung hv)
    {
        this.hkverwaltung = hv;
    }
    private Eigentuemerliste eliste;
    public void setEigentListe(Eigentuemerliste el)
    {
        this.eliste = el;
    }
    private Buchungsliste buchungsliste;
    public void setBuchungsListe(Buchungsliste bl)
    {
        this.buchungsliste = bl;
    }
    private Kostenkontenliste kostenliste;
    public void setKostenliste(Kostenkontenliste kl)
    {
        this.kostenliste = kl;
    }

    /**
     * Methode 
     * @param actionEvent
     * @throws IOException 
     */
    public void handleEigentuemerverwaltung(ActionEvent actionEvent) throws Exception   {  
        
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Eigentuemer.fxml"));        
        centerPane = loader.load();        
        border = hkverwaltung.getRoot();        
        border.setCenter(centerPane);
        hkverwaltung.getFenster().setTitle("Eigentümerverwaltung");
        
        // Die liste an Controller übergeben
        egcontroller = loader.getController();
        
        if(eliste.isEmpty()) 
        {
            egcontroller.setZustand(LEER);
            
        } 
        // Liste aktualisieren zB nach der Buchung
        
        egcontroller.setEigentListe(eliste);
        
    }

    public void handleKostenkonten(ActionEvent actionEvent) throws IOException {
        System.out.println("Kostenkonten anzeigen");
       // Stage stage = (Stage) menuBar.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Kostenkonten.fxml"));        
        centerPane = loader.load();      
        border = hkverwaltung.getRoot();
        border.setCenter(centerPane);
        hkverwaltung.getFenster().setTitle("Kostenkontenverwaltung");
        
        kkcontroller = loader.getController();
        if(kostenliste.isEmpty()) 
        {
            kkcontroller.setZustand(LEER);            
        } 
        
        kkcontroller.setKostenliste(kostenliste);
        
        
        
    }
    
    public void handleBuchung(ActionEvent actionEvent) throws IOException {
        System.out.println("Buchung anzeigen");
       // Stage stage = (Stage) menuBar.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Buchung.fxml"));
        
        //URL paneBuchhaltung = getClass().getResource("Buchung.fxml");
        centerPane = loader.load();
        //System.out.println("Eigenschaften loader: " +loader.toString());
        border = hkverwaltung.getRoot();
        border.setCenter(centerPane);
        bcontroller = loader.getController();
        
         
        bcontroller.setBListe(buchungsliste);
        bcontroller.setEigentListe(eliste);
        bcontroller.setKListe(kostenliste);
        hkverwaltung.getFenster().setTitle("Buchungsvorgang");
    }
  

}

