package hauskontenverwaltung;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Popup;

/**
 * FXML Controller enthält alle Ereignisroutinen, um auf Aktionen
 * in der Benutzeroberfläche bei Start der Anwendung zu reagieren
 * @author Olga Podlubnaja
 */
public class StartMenuController {
    
    private BorderPane border;
    private AnchorPane menuPane; 
    private Hauskontenverwaltung hkverwaltung;
    private MenuController mcontroller;
    
     /**
     * Methode setzt die Referenz
     * @param hv Hauskontenverwaltung
     */
    public void setVerwaltung(Hauskontenverwaltung hv)
    {
        this.hkverwaltung = hv;
    }
    
    /**
     * Ereignismethode nach Klick auf den Menüpunkt 
     * Objekt - Laden
     * @throws IOException wenn Fehler beim FXML-Datei laden
     */
    @FXML
    public void handleLaden() throws IOException 
    {        
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass()
                           .getResource("StartPane.fxml"));
        FXMLLoader loaderobject = new FXMLLoader();
        loaderobject.setLocation(getClass()
                            .getResource("ObjectPane.fxml"));
        menuPane  = loader.load();
        AnchorPane ap = loaderobject.load();
        border = hkverwaltung.getRoot();                
        border.setTop(menuPane);
        border.setCenter(ap);
        
        this.mcontroller = loader.getController();
        mcontroller.setVerwaltung(this.hkverwaltung);
        this.hkverwaltung.setMController(mcontroller);
        this.hkverwaltung.datenLaden();        
    } // Ende handleLaden()
    
    /**
     * Ereignismethode nach Klick auf Menüpunkt 
     * Objekt - Beenden
     */
    @FXML
    public void handleBeenden()
    {
        this.hkverwaltung.stop();
    }
    
    /**
     * Ereignismethode nach Klick auf Menüpunkt
     * Hilfe - Info
     * @throws IOException wenn Fehler beim FXML-Datei laden
     */
    @FXML
    public void handleInfo() throws IOException
    {
    Popup popup = new Popup();    
    popup.setAutoHide(true); 
    FXMLLoader loader = new FXMLLoader(getClass()
                                .getResource("Info.fxml"));    
    popup.getContent().add((Parent)loader.load());        
    popup.setAutoFix(true);
    popup.show(this.hkverwaltung.getFenster());
    } // Ende handleInfo()    
} // Ende der Klasse StartMenuController
