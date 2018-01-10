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
public class MenuController {    
    
    private String dateiName;
    private File datei;
    private File dateiPath;
    private Eigentuemerliste liste;
    AnchorPane centerPane;
    //private Hauskontenverwaltung hkv;
    private EigentuemerController egcontroller;
    private BuchungController bcontroller;
    private Hauskontenverwaltung hkverwaltung;
    BorderPane border;
    boolean isliste = false;
    @FXML
    private MenuBar menuBar;  
    
    public void initialize()
    {
      liste = new Eigentuemerliste();
      
        
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
        //border.getCenter().setDisable(false);
        border.setCenter(centerPane);
        hkverwaltung.getFenster().setTitle("Eigentümerverwaltung");
        
        if(!isliste) getListe();
        
        // Die liste an Controller übergeben
        egcontroller = loader.getController();
        System.out.println("Controller setzen Eigentümerliste");
        //hkverwaltung.getEC().setEigentListe(liste);
        if(liste.isEmpty()) egcontroller.setZustand(0); 
        else egcontroller.setEigentListe(liste);
        
    }

    public void handleKostenkonten(ActionEvent actionEvent) throws IOException {
        System.out.println("Kostenkonten anzeigen");
       // Stage stage = (Stage) menuBar.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Kostenkonten.fxml"));
        //URL paneKosten = getClass().getResource("Kostenkonten.fxml");
        centerPane = loader.load();
      
        border = hkverwaltung.getRoot();
        border.setCenter(centerPane);
        hkverwaltung.getFenster().setTitle("Kostenkontenverwaltung");
        
    }
    
    public void handleBuchung(ActionEvent actionEvent) throws IOException {
        System.out.println("Buchung anzeigen");
       // Stage stage = (Stage) menuBar.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Buchung.fxml"));
        
        //URL paneBuchhaltung = getClass().getResource("Buchung.fxml");
        centerPane = loader.load();
        System.out.println("Eigenschaften loader: " +loader.toString());
        border = hkverwaltung.getRoot();
        border.setCenter(centerPane);
        bcontroller = loader.getController();
        getListe();
        bcontroller.setEigentListe(liste);
        hkverwaltung.getFenster().setTitle("Buchungsvorgang");
    }
    
    public void getListe()
    {
        
        File file = getDatei(true);       
        try
        { // wenn eine Datei gewählt wurde
            if(file != null)                      
                // Datei einlesen           
                liste.auslesenDatei(file);
        }
        catch(IOException ioe)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Einlesefehler");
            alert.setHeaderText(ioe.getMessage());
            alert.showAndWait();
        }
        catch (ClassNotFoundException cnfe)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Datei nicht gefunden");
            alert.setHeaderText(cnfe.getMessage());
            alert.showAndWait();
        }
        isliste = true;
    }
    
 /**
     * Methode gibt File zurück.
     * Fenster ist als Modale-Dialog aufgebaut
     * true - Datei öffen
     * false - Datei speichern
     * @param oeffnen boolean
     * @return Datei
     */      
    
    public File getDatei(boolean oeffnen)
    {
        dateiPath = new File(System.getProperty("user.dir"));
        FileChooser fc = new FileChooser(); 
        fc.setInitialDirectory(dateiPath);        

        // Dialog anzeigen
        if(oeffnen) datei = fc.showOpenDialog(hkverwaltung.getFenster());       
        else datei = fc.showSaveDialog(hkverwaltung.getFenster());
       
        return datei;
    }

}

