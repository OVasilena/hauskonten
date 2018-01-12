package hauskontenverwaltung;

import java.util.Optional;
import javafx.beans.property.SimpleDoubleProperty;
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
public class EigentuemerController implements Konstanten {
    @FXML private TextField tfNummer;
    @FXML private TextField tfNachname;
    @FXML private TextField tfVorname;
    @FXML private TextField tfWhgnummer;
    
    @FXML private Button btnNeu;
    @FXML private Button btnAendern;
    @FXML private Button btnLoeschen;
    
    @FXML private Label lblStatus;
    @FXML private Label lblStand;
    
    @FXML private TableView<Eigentuemer> tblAnzeige;
    @FXML private TableColumn<Eigentuemer, String> colNummer;
    @FXML private TableColumn<Eigentuemer, String> colName;
    @FXML private TableColumn<Eigentuemer, String> colWhgnummer;
    @FXML private TableColumn<Eigentuemer, String> colKontostand;
    
    // Referenz auf Haukontenverwaltung
    private Hauskontenverwaltung hkVerwaltung;
    /**
     * Methode setzt die Referenz 
     * @param hkv Hauskontenverwaltung
     */
    public void setHausVerwaltung(Hauskontenverwaltung hkv)
    {
        this.hkVerwaltung = hkv;
        //System.out.println("Referenz auf Hauskontenverwaltung");
    }
    
    // Referenz auf Eigentümerliste
    private Eigentuemerliste eigentListe;
    public void setEigentListe(Eigentuemerliste egl)
    {
        this.eigentListe = egl;
        //System.out.println("Referenz auf Eigentümerliste");
        //Liste an die Tabelle knüpfen
        //System.out.println("Anzeige tabelle: " + eigentListe.getListe());
        tblAnzeige.setItems(eigentListe.getListe());
        //System.out.println("getListe " + eigentListe.getListe().size());
        
        
        //System.out.println("Gesatmkontostand: " + eigentListe.getGesamtStand()+ " EURO");
        //for(Eigentuemer el: eigentListe.getListe()) System.out.println("Kontostand:" + el.getKontostand());
       
        if(!eigentListe.isEmpty())
        {
            lblStatus.setText(eigentListe.sizeListe() + " Eigentümer in der Liste");
            anzeigeEigentuemerInfo(eigentListe.getEigentuemer(eigentListe.sizeListe()-1));
            Eigentuemer eigent = eigentListe.getEigentuemer(eigentListe.sizeListe()-1);
            int index = eigentListe.sizeListe()-1;
            tblAnzeige.getSelectionModel().select(index);
            
            String konto = eigent.getKontonummer();
            int nummer = Integer.valueOf(konto.substring(2));
            
            eigent.setKonto(nummer);
             lblStand.setText("Gesatmkontostand: " + Double.toString(eigentListe.getGesamtStand())+ " EURO");
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
        colName.setCellValueFactory(cellData 
                    -> cellData.getValue().vornameProperty().concat(", ").concat(cellData.getValue().nachnameProperty()));
        
        colWhgnummer.setCellValueFactory(cellData 
                    -> cellData.getValue().wohnungsnumerProperty());
        
        colKontostand.setCellValueFactory(cellData 
                -> cellData.getValue().kontostandProperty().asObject().asString().concat(" €"));        
        
        // Klickereignis: Klick auf Eigentümer in Tabelle
        // -> Anzeige dieser Daten
        
        tblAnzeige.getSelectionModel().selectedItemProperty()
                  .addListener((observable, oldValue, newValue)
                       -> anzeigeEigentuemerInfo(newValue));
        // Auswahl des ersten DS
        
        
        
    } // Ende Methode initialize()
    
    /**
     * Methode zeigt die Daten der Person in den Textfeldern 
     * und DatePicker an.
     * @param person Personen
     */
    public void anzeigeEigentuemerInfo(Eigentuemer person)
    {
        if(person != null)
        {            
            tfNummer.setText(person.getKontonummer());
            tfVorname.setText(person.getVorname());
            tfNachname.setText(person.getNachname());
            tfWhgnummer.setText(person.getWohnungsnummer());          
                     
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
    @FXML 
    public void handleNeu()
    {
        if(this.zustand == LEER|| this.zustand == BASIS)
        {
            lblStatus.setText("Neuen Eingetümer anlegen");           
            this.setZustand(NEU);
            
            
        }
        else if(this.zustand == NEU)
        {
            try
            {
                // Eigentümer übernehmen  mit Eingabeprüfung
                Eigentuemer eig_neu = getEingabeEigentuemer();
                this.eigentListe.addEigentuemer(eig_neu);
                System.out.println("*****/// " + eig_neu.getKontonummer() +"///**** ");
                tblAnzeige.getSelectionModel().select(eig_neu);
                this.setZustand(BASIS);
                lblStatus.setText("Zugefügt: " + eig_neu.toString());
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
            lblStatus.setText("aktuell gewählt: " + eigentListe.getEigentuemer(index));
        }
        else if(this.zustand == AENDERN)
        {
            try
            {
                Eigentuemer egm = this.getEingabeEigentuemer();
                int index = tblAnzeige.getSelectionModel()
                                      .getSelectedIndex();
                this.eigentListe.setEigentuemer(index, egm);
                
                lblStatus.setText(egm.toString() 
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
        Eigentuemer egm = tblAnzeige.getSelectionModel().getSelectedItem();
        Alert con = new Alert(Alert.AlertType.CONFIRMATION );
        con.setTitle("Löschabfrage");
        con.setHeaderText("Wollen Sie die Person löschen?");
        con.setContentText(egm.toString());
        con.getButtonTypes().setAll(ButtonType.YES,
                                    ButtonType.NO);
        Optional erg = con.showAndWait();
        if ( erg.get() == ButtonType.YES)
        {
            this.eigentListe.removeEigentuemer(egm);
            lblStatus.setText(egm.toString() 
                              + " wurde gelöscht.");
            //this.hkVerwaltung.setAenderung(true);
        }       
        else
        {
            // Methode verlassen
            lblStatus.setText("Vorgang abgebrochen.");
            return;
        }
        if(this.eigentListe.sizeListe() == 0) 
        {
            this.setZustand(LEER);
            leerenTextfelder();
            
        }
        else this.setZustand(BASIS);
        }
    }
    
    public void aktZustand()
    {
        if(!this.eigentListe.isEmpty())
        {
            int index = tblAnzeige.getSelectionModel()
                              .getSelectedIndex();
            Eigentuemer eigentuemer = tblAnzeige.getSelectionModel()
                                  .getSelectedItem();
            tblAnzeige.getSelectionModel().select(index);            
            setZustand(BASIS);
            this.anzeigeEigentuemerInfo(eigentuemer);
        }
        else
        {
            setZustand(LEER);
            leerenTextfelder();
        }
    }
    
    private Eigentuemer getEingabeEigentuemer() throws Exception
    {
        Eigentuemer eigentuemer = new Eigentuemer();;
     
        if(this.zustand == AENDERN)
        {
             eigentuemer = tblAnzeige.getSelectionModel().getSelectedItem();
        }                    
         
         
        // *** Eingabe Vorname ***
        String vorname = tfVorname.getText().trim();
        if(!vorname.matches("[A-Za-z]+"))
        {
            tfVorname.selectAll();
            tfVorname.requestFocus();
            throw new Exception("Bitte nur Buchstaben eigeben");
        }
        eigentuemer.setVorname(vorname);
        // *** Eingabe Nachname ***
        String nachname = tfNachname.getText().trim();
        if(!nachname.matches("[A-Za-z]+"))
        {
            tfNachname.selectAll();
            tfNachname.requestFocus();
            throw new Exception("Bitte nur Buchstaben eigeben");
        }
        eigentuemer.setNachname(nachname);
        // *** Eingabe Wohnungsnummer ***
        String whg = tfWhgnummer.getText().trim();
        if(whg.length() > 5)
        {
            tfWhgnummer.selectAll();
            tfWhgnummer.requestFocus();
            throw new Exception("Wohnungsnummer hat nur 5 Zeichen");
        }
        eigentuemer.setWohnungsnummer(whg);
        return eigentuemer;
    }
    
    private void leerenTextfelder()
    {
        this.tfNummer.setText("");
        this.tfVorname.setText("");
        this.tfNachname.setText("");
        this.tfWhgnummer.setText("");     
    }
    
    private void eingabeEnabled(boolean bool)
    {        
        this.tfVorname.setEditable(bool);
        this.tfNachname.setEditable(bool);
        this.tfWhgnummer.setEditable(bool);
    }
}
