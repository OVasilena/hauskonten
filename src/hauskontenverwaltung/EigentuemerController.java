package hauskontenverwaltung;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 *
 * @author opodlubnaja
 */
public class EigentuemerController {
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
        System.out.println("Referenz auf Hauskontenverwaltung");
    }
    
    // Referenz auf Eigentümerliste
    private Eigentuemerliste eigentListe;
    public void setEigentListe(Eigentuemerliste egl)
    {
        this.eigentListe = egl;
        System.out.println("Referenz auf Eigentümerliste");
        //Liste an die Tabelle knüpfen
        System.out.println("Anzeige tabelle: " + eigentListe.getListe());
        tblAnzeige.setItems(eigentListe.getListe());
        System.out.println("getListe " + eigentListe.getListe().size());
        
        lblStatus.setText(eigentListe.sizeListe() + " Eigentümer in der Liste");
        
        lblStand.setText("Gesatmkontostand: " + Double.toString(eigentListe.getGesamtStand())+ " EURO");
        if(!eigentListe.isEmpty())
        {
            anzeigeEigentuemerInfo(eigentListe.getPerson(eigentListe.sizeListe()-1));
            Eigentuemer eigent = eigentListe.getPerson(eigentListe.sizeListe()-1);
            int index = eigentListe.sizeListe()-1;
            tblAnzeige.getSelectionModel().select(index);
            
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
            case 0:
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
            case 1:
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
            case 2:
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
        }
    }
    
    public void handleNeu()
    {
        if(this.zustand == 0|| this.zustand == 2)
        {
            lblStatus.setText("Neuen Eingetümer anlegen");           
            this.setZustand(1);
            
        }
        else if(this.zustand == 1)
        {
            try
            {
                // Eigentümer übernehmen  mit Eingabeprüfung
                Eigentuemer eig_neu = getEingabeEigentuemer();
                this.eigentListe.addEigentuemer(eig_neu);
                System.out.println("*****/// " + eig_neu.getKontonummer() +"///**** ");
                tblAnzeige.getSelectionModel().select(eig_neu);
                this.setZustand(2);
                lblStatus.setText(eig_neu.toString() + " zugefügt");
                
            }
            catch(Exception e)
            {
                lblStatus.setText(e.getMessage());
            }
        }
        
    }
    
    private Eigentuemer getEingabeEigentuemer() throws Exception
    {
        System.out.println("Get letzte Kontonummer: " + eigentListe.getPerson(eigentListe.sizeListe()-1).getKontonummer());
        Eigentuemer eigentuemer= new Eigentuemer();
        System.out.println("Neue KN Nr " + eigentuemer.getKontonummer());
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
