package hauskontenverwaltung;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

/**
 *
 * @author olechka
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
    
     @FXML
    public void handleNeu()
    {
        System.out.println("Button NEU");
    }
    @FXML
    public void handleLaden() throws IOException 
    {
        
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("StartPane.fxml"));
        menuPane  = loader.load();
        border = hkverwaltung.getRoot();
        border.setTop(menuPane);
        this.mcontroller = loader.getController();
        mcontroller.setVerwaltung(this.hkverwaltung);
        this.hkverwaltung.setMController(mcontroller);
        this.hkverwaltung.datenLaden();
        
    }
    @FXML
    public void handleBeenden()
    {
        this.hkverwaltung.stop();
    }
    
    @FXML
    public void handleInfo()
    {
        
    }
    
}
