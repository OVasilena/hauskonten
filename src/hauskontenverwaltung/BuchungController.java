package hauskontenverwaltung;

import java.time.LocalDate;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

/**
 * FXML Controller class enthält alle Ereignisroutinen, um auf 
 * Aktionen in der Benutzeroberfäche zu reagieren. 
 * @author Olga Podlubnaja
 */
public class BuchungController implements Konstanten{

    @FXML private TextField tfKontonr;
    @FXML private TextField tfBeschreibung;
    @FXML private DatePicker dpDate;
    @FXML private TextField tfBetrag;
    @FXML private ToggleGroup myToggleGroup;
    @FXML private RadioButton rbtnE, rbtnA;
 
    @FXML private Label lblStatus;

    @FXML private TableView<Buchung> tblAnzeige;
    @FXML private TableColumn<Buchung, LocalDate> colDatum;
    @FXML private TableColumn<Buchung, Double> colBetrag;
    @FXML private TableColumn<Buchung, String> colBuchungsart;
    @FXML private TableColumn<Buchung, String> colBeschreibung;
    @FXML private TableColumn<Buchung, String> colKontonummer;

    // Referenzen auf die Listen
    private Buchungsliste bliste;
    private Eigentuemerliste egliste;
    private Kostenkontenliste kkliste;

    /**
     * Methode setzt die Referenz
     * @param egl Eigentuemerliste
     */
    public void setEigentListe(Eigentuemerliste egl) {
        this.egliste = egl;
    }
    
    /**
     * Methode setzt die Referenz
     * @param bl Buchungsliste
     */
    public void setBListe(Buchungsliste bl)
    {
        this.bliste = bl;
        lblStatus.setText("Bereit für eine neue Buchung");
        tblAnzeige.setItems(bliste.getListe());
    }
    
    /**
     * Methode setzt die Referenz
     * @param kkl Kostenkontenliste
     */
    public void setKListe(Kostenkontenliste kkl)
    {
        this.kkliste = kkl;        
    }
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

    /**
     * Methode initialisiert die Daten
     */
    public void initialize() {
        
        colDatum.setCellValueFactory(cellData
                -> cellData.getValue().buchungProperty());

        colBuchungsart.setCellValueFactory(cellData
                -> cellData.getValue().vorgangProperty());

        colBeschreibung.setCellValueFactory(cellData
                -> cellData.getValue().beschreibungProperty());

        colBetrag.setCellValueFactory(cellData
                -> cellData.getValue().betragProperty()
                                      .asObject());
        colKontonummer.setCellValueFactory(cellData
                -> cellData.getValue().kontonummerProperty());
        
    }
    
    /**
     * Methode zeigt die Daten der Buchung in den Textfeldern 
     * @param bg Buchung
     */
    public void anzeigeBuchungInfo(Buchung bg)
    {
        if(bg != null)
        {            
            tfKontonr.setText(bg.getKontonummer());
            tfBeschreibung.setText(bg.getBeschreibung());
            dpDate.setValue(bg.getBuchungstag());
            tfBetrag.setText(DF.format(bg.getBetrag()));
            if(bg.getVorgang()) rbtnE.setSelected(true);
            else rbtnA.setSelected(true);
                     
        }
    }

    /**
     * Ereignismethode nach Klick auf Button "Übernehmen".
     */
    public void handleUebernehmen() {
        try {
            lblStatus.setText("Daten wurden übernommen");
            Buchung buchung = getEingabeBuchung();
            this.hkVerwaltung.setAenderung(true);
            this.bliste.addBuchung(buchung);
            for(Eigentuemer eg: egliste.getListe()) 
            {
                if (buchung.getKontonummer()
                                    .equals(eg.getKontonummer()))
                {
                    if(buchung.getVorgang()) 
                        eg.setEinzahlen(buchung.getBetrag());
                    else 
                        eg.setAuszahlen(buchung.getBetrag());
                }                
            }
            for(Kostenkonto kk: kkliste.getListe())
            {
                if(buchung.getKontonummer()
                                    .equals(kk.getKontonummer()))
                {
                    if(buchung.getVorgang()) 
                        kk.setEinzahlen(buchung.getBetrag());
                    else 
                        kk.setAuszahlen(buchung.getBetrag());
                }
            }            
            
            tblAnzeige.getSelectionModel().select(buchung);
            tblAnzeige.setItems(bliste.getListe());
            leerenTextfelder();

        } catch (Exception e) {
            lblStatus.setText("Fehler aufgetretten: " 
                               + e.getMessage());
        }
    } // Ende handleUebernehmen()

    /**
     * Hilfsmethode leert die Textfelder. 
     * Methode entfernt die Anzeige in allen Textfeldern und 
     * zeigt aktuelles Datum
     */
    private void leerenTextfelder() {
        tfKontonr.setText("");
        tfBeschreibung.setText("");
        tfBetrag.setText("");
        lblStatus.setText("Bereit für einen  neuen Eitrag");
        dpDate.setVisible(true);
        dpDate.setValue(LocalDate.now());
        
    }

    /**
     * Methode übernimmt alle Eingabewerte und prüft nach 
     * Richtigkeit und Vollständigkeit und trägt den
     * Buchungseintrag in die Liste ein.
     * @return buchung Buchung
     * @throws Exception wenn Eingaben falsch sind
     */
    private Buchung getEingabeBuchung() throws Exception {
        Buchung bhg = new Buchung();
       
        // **** Eingabe Kontonummer ****
        String tempKtn ="";
        String strNummer = tfKontonr.getText().trim();
        for (Eigentuemer liste : egliste.getListe()) 
        {
            if (liste.isKonto(strNummer)) 
            {                
                tempKtn = liste.getKontonummer();                
            } 
        }
        for(Kostenkonto liste : kkliste.getListe())
        {
            if (liste.isKonto(strNummer)) 
            {               
                tempKtn = liste.getKontonummer();                
            } 
        }
        if(strNummer.isEmpty())
        {
            tfKontonr.requestFocus();
            throw new Exception("Bitte Kontonummer eintragen");
        }
        if(!strNummer.equals(tempKtn))
        {
            tfKontonr.requestFocus();
            throw new Exception(strNummer 
                        + " - Kontonummer exisistiert nicht!");
        }
        bhg.setKontonummer(strNummer);
        // **** Eingabe Buchungsdatum ****
        LocalDate buchdatum = dpDate.getValue();        
        if (buchdatum == null || buchdatum
                                       .isAfter(LocalDate.now())) 
        {
            dpDate.requestFocus();
            throw new Exception("Bitte ein Datum in der " 
                                + "Vergangenheit wählen!");
        }
        bhg.setBuchungstag(buchdatum);

        // **** Eingabe Beschreibung ****        
        String beschreibung = tfBeschreibung.getText().trim();        
        if (beschreibung.isEmpty()) {
            tfBeschreibung.requestFocus();
            throw new Exception("Bitte Beschreibung eingeben!");
        }
        bhg.setBeschreibung(beschreibung);

        // **** Eingabe Betrag ****
        Double zahl = Double.valueOf(tfBetrag.getText().trim()
                                             .replace(",", "."));        
        if (zahl < 0) {
            tfBetrag.requestFocus();
            throw new Exception("Bitte nur positive Zahlen " 
                                + "eingeben!");
        }        
        bhg.setBetrag(zahl);
                
        if(myToggleGroup.getSelectedToggle().equals(rbtnE))         
            bhg.setVorgang(true);           
                
        if(myToggleGroup.getSelectedToggle().equals(rbtnA))         
            bhg.setVorgang(false);        
        
        return bhg;
    } // Ende getEingabeBuchung()
} // ende der Klasse BuchungController
