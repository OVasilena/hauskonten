package hauskontenverwaltung;

import static hauskontenverwaltung.Konstanten.AENDERN;
import static hauskontenverwaltung.Konstanten.BASIS;
import static hauskontenverwaltung.Konstanten.LEER;
import static hauskontenverwaltung.Konstanten.NEU;
import java.util.Optional;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 *
 * @author opodlubnaja
 */
public class KostenkontenController implements Konstanten{
    @FXML private TextField tfNummer, tfBezeichnung;
    
    @FXML private Button btnNeu, btnAendern, btnLoeschen;
    @FXML private Label lblStatus;
    
    @FXML private Label lblStand;    
    @FXML private TableView<Kostenkonto> tblAnzeige;
    
    @FXML private TableColumn<Kostenkonto, String> colNummer;
    @FXML private TableColumn<Kostenkonto, String> colBezeichnung;    
    @FXML private TableColumn<Kostenkonto, String> colKontostand;
    
    // Referenz auf Haukontenverwaltung
    private Hauskontenverwaltung hkVerwaltung;
    /**
     * Methode setzt die Referenz 
     * @param hkv Hauskontenverwaltung
     */
    public void setHausVerwaltung(Hauskontenverwaltung hkv)
    {
        this.hkVerwaltung = hkv;
        
    }
    
    // Referenz auf Kostenkontenliste
    private Kostenkontenliste kostenListe;
    public void setKostenliste(Kostenkontenliste kl)
    {
        this.kostenListe = kl;
        tblAnzeige.setItems(kostenListe.getListe());
        //System.out.println("getKostenListe " + kostenListe.getListe().size());
        lblStatus.setText(kostenListe.sizeListe() + " Kostenkonten in der Liste");
        
        
         if(!kostenListe.isEmpty())
        {
            anzeigeKontoInfo(kostenListe.getKostenkonto(kostenListe.sizeListe()-1));            
            int index = kostenListe.sizeListe()-1;
            tblAnzeige.getSelectionModel().select(index);                 
            lblStand.setText("Gesatmkontostand: " + Double.toString(kostenListe.getGesamtStand())+ " EURO");
            
        }
        else lblStatus.setText("Die Liste ist leer");
        
    }
    
     /**
     * Methode initialisiert die Daten
     */
    public void initialize()
    {
        // Verbinden der Tabellenspalten mit den Properties
        colNummer.setCellValueFactory(cellData 
                    -> cellData.getValue().kontonummerProperty());
        colBezeichnung.setCellValueFactory(cellData 
                    -> cellData.getValue().bezeichnungProperty()); 
        colKontostand.setCellValueFactory(cellData 
                -> cellData.getValue().kontostandProperty().asObject().asString().concat(" €"));        
        
        // Klickereignis: Klick auf Eigentümer in Tabelle
        // -> Anzeige dieser Daten
        
        tblAnzeige.getSelectionModel().selectedItemProperty()
                  .addListener((observable, oldValue, newValue)
                       -> anzeigeKontoInfo(newValue));
        // Auswahl des ersten DS
        
        
        
    } // Ende Methode initialize()
    
    public void anzeigeKontoInfo(Kostenkonto konto)
    {
         if(konto != null)
        {            
            tfNummer.setText(konto.getKontonummer());
            tfBezeichnung.setText(konto.getBezeichnung());
                     
        }
    }
    
    private Kostenkonto getEingabeKostenkonto() throws Exception
    {
        Kostenkonto kostenkonto = new Kostenkonto();
        String konto = tfNummer.getText().trim();
        if(konto.length() > 5)
        {
            tfNummer.selectAll();
            tfNummer.requestFocus();
            throw new Exception("Kostenkonto falsch eingetragen. Konto hat nicht mehr als 5 Zeichen");
        }
        kostenkonto.setKontonummer(konto);
        String bezeichnung = tfBezeichnung.getText().trim();
        kostenkonto.setBezeichnung(bezeichnung);
        
        return kostenkonto;
    }
    
    @FXML 
    public void handleNeu()
    {
        if(this.zustand == LEER|| this.zustand == BASIS)
        {
            lblStatus.setText("Neue Kostenkonto anlegen");           
            this.setZustand(NEU);
            
            
        }
        else if(this.zustand == NEU)
        {
            try
            {
                // Konto übernehmen  mit Eingabeprüfung
                Kostenkonto kkonto = this.getEingabeKostenkonto();
                this.kostenListe.addKonto(kkonto);
                
                tblAnzeige.getSelectionModel().select(kkonto);
                this.setZustand(BASIS);
                lblStatus.setText("Zugefügt: " + kkonto.toString());
                this.hkVerwaltung.setAenderung(true);
            }
            catch(Exception e)
            {
                lblStatus.setText(e.getMessage());
            }
        }
        
    }
    
     @FXML 
    public void handleAendern()
    {
        if(this.zustand == BASIS)
        {
            setZustand(AENDERN);
            int index = tblAnzeige.getSelectionModel().getSelectedIndex();
            lblStatus.setText("aktuell gewählt: " + kostenListe.getKostenkonto(index));
        }
        else if(this.zustand == AENDERN)
        {
            try
            {
                Kostenkonto kk = this.getEingabeKostenkonto();
                int index = tblAnzeige.getSelectionModel()
                                      .getSelectedIndex();
                this.kostenListe.setKostenkonto(index, kk);
                
                lblStatus.setText(kk.toString() 
                                  + " wurde aktualisiert.");
                setZustand(BASIS);
                this.hkVerwaltung.setAenderung(true);
                // wählen aktuellen Datensatz
                tblAnzeige.getSelectionModel().select(index);
            }
            catch(Exception e)
            {
                lblStatus.setText(e.getMessage());
            }
        }
    }
    
    @FXML
    public void handleLoeschen()
    {
        if(this.zustand == NEU || this.zustand == AENDERN)
        {
            lblStatus.setText("Vorgang abgebrochen");
            aktZustand();
            
        }
        else
        {
        Kostenkonto kk = tblAnzeige.getSelectionModel().getSelectedItem();
        Alert con = new Alert(Alert.AlertType.CONFIRMATION );
        con.setTitle("Löschabfrage");
        con.setHeaderText("Wollen Sie die Person löschen?");
        con.setContentText(kk.toString());
        con.getButtonTypes().setAll(ButtonType.YES,
                                    ButtonType.NO);
        Optional erg = con.showAndWait();
        if ( erg.get() == ButtonType.YES)
        {
            this.kostenListe.removeKostenkonto(kk);
            lblStatus.setText(kk.toString() 
                              + " wurde gelöscht.");
            //this.hkVerwaltung.setAenderung(true);
        }       
        else
        {
            // Methode verlassen
            lblStatus.setText("Vorgang abgebrochen.");
            return;
        }
        if(this.kostenListe.sizeListe() == 0) 
        {
            this.setZustand(LEER);
            leerenTextfelder();
            
        }
        else this.setZustand(BASIS);
        }
    }
    
    private int zustand;
    public void setZustand(int zustand)
    {
        this.zustand = zustand;
        switch(zustand)
        {
            case LEER:
                // LEER -Zustand
                this.btnNeu.setDisable(false); // Button aktiv
                this.btnNeu.setText("NEU");
                this.btnAendern.setDisable(true);
                this.btnAendern.setText("Ändern");
                this.btnLoeschen.setDisable(true);
                this.btnLoeschen.setText("Löschen");
                this.tblAnzeige.setDisable(true);
                eingabeEnabled(false);
                break;
            case NEU:
                // NEU -Zustand
                
                this.btnNeu.setDisable(false);
                this.btnNeu.setText("DS Speichern");
                this.btnAendern.setDisable(true);
                this.btnAendern.setText("Ändern");
                this.btnLoeschen.setDisable(false);
                this.btnLoeschen.setText("Abbrechen");
                eingabeEnabled(true);
                leerenTextfelder();
                break;
            case BASIS:
                // BASIS -Zustand
                this.btnNeu.setDisable(false);
                this.btnNeu.setText("NEU");
                this.btnAendern.setDisable(false);
                this.btnAendern.setText("Ändern");
                this.btnLoeschen.setDisable(false);
                this.btnLoeschen.setText("Löschen");
                eingabeEnabled(false);
                this.tblAnzeige.setDisable(false);
                break;
            case AENDERN:
                // ÄNDERN -Zustand
                this.btnNeu.setDisable(true);
                this.btnNeu.setText("NEU");
                this.btnAendern.setDisable(false);
                this.btnAendern.setText("DS Speichern");
                this.btnLoeschen.setDisable(false);
                this.btnLoeschen.setText("Abbrechen");
                eingabeEnabled(true);
                this.tblAnzeige.setDisable(true);
                break;            
        }
    }
    
    public void aktZustand()
    {
        if(!this.kostenListe.isEmpty())
        {
            int index = tblAnzeige.getSelectionModel()
                              .getSelectedIndex();
            Kostenkonto kkonto = tblAnzeige.getSelectionModel()
                                  .getSelectedItem();
            tblAnzeige.getSelectionModel().select(index);            
            setZustand(BASIS);
            this.anzeigeKontoInfo(kkonto);
        }
        else
        {
            setZustand(LEER);
            leerenTextfelder();
        }
    }
    
    private void leerenTextfelder()
    {
        this.tfNummer.setText("");
        this.tfBezeichnung.setText("");
           
    }
    private void eingabeEnabled(boolean bool)
    {        
        this.tfNummer.setEditable(bool);
        this.tfBezeichnung.setEditable(bool);
       
    }
    
}
