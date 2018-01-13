package hauskontenverwaltung;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class enthält alle Ereignisroutinen, um auf 
 * Aktionen in der Benutzeroberfäche zu reagieren. 
 * @author opodlubnaja
 */
public class MenuController implements Konstanten{        
    
    
    private AnchorPane centerPane;    
    private EigentuemerController egcontroller;
    private BuchungController bcontroller;
    private KostenkontenController kkcontroller;
    private Hauskontenverwaltung hkverwaltung;
    private BorderPane border;
    boolean isliste = false;    
        
    public void initialize()
    {
    }
        
    /**
     * Methode setzt die Referenz
     * @param hv Hauskontenverwaltung
     */
    public void setVerwaltung(Hauskontenverwaltung hv)
    {
        this.hkverwaltung = hv;
    }
    
    private Eigentuemerliste eliste;
    /**
     * Methode setzt die Referenz
     * @param el Eigentuemerliste
     */
    public void setEigentListe(Eigentuemerliste el)
    {
        this.eliste = el;
    }
    private Buchungsliste buchungsliste;
    /**
     * Methode setzt die Referenz
     * @param bl Buchungsliste
     */
    public void setBuchungsListe(Buchungsliste bl)
    {
        this.buchungsliste = bl;
    }
    private Kostenkontenliste kostenliste;
    /**
     * Methode setzt die Referenz 
     * @param kl Kostenkontenliste
     */
    public void setKostenliste(Kostenkontenliste kl)
    {
        this.kostenliste = kl;
    }

    /**
     * Ereignismethode nach Klick auf Menüpunkt Stammdaten-> Eigentümer
     * @param actionEvent
     * @throws IOException 
     */
    @FXML
    public void handleEigentuemerverwaltung(ActionEvent actionEvent) throws Exception   {  
        
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Eigentuemer.fxml"));        
        centerPane = loader.load();        
        border = hkverwaltung.getRoot();        
        border.setCenter(centerPane);
        hkverwaltung.getFenster().setTitle("Eigentümerverwaltung");        
        
        egcontroller = loader.getController();        
        if(eliste.isEmpty()) egcontroller.setZustand(LEER);   
        egcontroller.setEigentListe(eliste);
        egcontroller.setHausVerwaltung(hkverwaltung);
    }
    
    /**
     * Ereignismethode nach Klick auf den Menüpunkt Stammdaten -> Kostenkonten
     * @param actionEvent
     * @throws IOException 
     */
    @FXML
    public void handleKostenkonten(ActionEvent actionEvent) throws IOException {
              
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Kostenkonten.fxml"));        
        centerPane = loader.load();      
        border = hkverwaltung.getRoot();
        border.setCenter(centerPane);
        hkverwaltung.getFenster().setTitle("Kostenkontenverwaltung");
        
        kkcontroller = loader.getController();
        if(kostenliste.isEmpty()) kkcontroller.setZustand(LEER);          
        kkcontroller.setKostenliste(kostenliste);
        kkcontroller.setHausVerwaltung(hkverwaltung);
    }
    /**
     * Ereignismethode nach Klick auf den Menüpunkt Buchhaltung -> Buchen
     * @param actionEvent
     * @throws IOException 
     */
    @FXML
    public void handleBuchung(ActionEvent actionEvent) throws IOException {
        
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Buchung.fxml"));
        centerPane = loader.load();        
        border = hkverwaltung.getRoot();
        border.setCenter(centerPane);
        hkverwaltung.getFenster().setTitle("Buchungsvorgang");
        bcontroller = loader.getController();        
         
        bcontroller.setBListe(buchungsliste);
        bcontroller.setEigentListe(eliste);
        bcontroller.setKListe(kostenliste);
        bcontroller.setHausVerwaltung(hkverwaltung);
    }
}

