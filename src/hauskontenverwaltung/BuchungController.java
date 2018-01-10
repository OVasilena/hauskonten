/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hauskontenverwaltung;

import java.time.LocalDate;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

/**
 *
 * @author opodlubnaja
 */
public class BuchungController {

    @FXML
    private TextField tfKontonr;
    @FXML
    private TextField tfBeschreibung;
    @FXML
    private DatePicker dpDate;
    @FXML
    private TextField tfBetrag;
    @FXML
    private ToggleGroup myToggleGroup;
    @FXML
    private RadioButton rbtnE, rbtnA;
    @FXML
    private Button btnUebernehmen;
    @FXML
    private Label lblStatus;

    @FXML
    private TableView<Buchung> tblAnzeige;
    @FXML
    private TableColumn<Buchung, LocalDate> colDatum;
    @FXML
    private TableColumn<Buchung, Double> colBetrag;
    @FXML
    private TableColumn<Buchung, String> colBuchungsart;
    @FXML
    private TableColumn<Buchung, String> colBeschreibung;
    @FXML
    private TableColumn<Buchung, String> colKontonummer;

    private Buchungsliste bliste;
    private Eigentuemerliste egliste;
    private Kostenkontenliste kkliste;

    public void setEigentListe(Eigentuemerliste egl) {
        this.egliste = egl;
    }

    public void initialize() {
        bliste = new Buchungsliste();

        //kkliste = new Kostenkontenliste();
        colDatum.setCellValueFactory(cellData
                -> cellData.getValue().buchungProperty());

        colBuchungsart.setCellValueFactory(cellData
                -> cellData.getValue().vorgangProperty());

        colBeschreibung.setCellValueFactory(cellData
                -> cellData.getValue().beschreibungProperty());

        colBetrag.setCellValueFactory(cellData
                -> cellData.getValue().betragProperty().asObject());
        colKontonummer.setCellValueFactory(cellData
                -> cellData.getValue().kontonummerProperty());
    }

    /**
     * Ereignismethode nach Klick auf Button "Übernehmen".
     */
    public void handleUebernehmen() {

        System.out.println("Hier soll Label angezeigt werden");

        try {
            lblStatus.setText("Daten wurden übernommen");
            Buchung buchung = getEingabeBuchung();

            this.bliste.addBuchung(buchung);

            tblAnzeige.getSelectionModel().select(buchung);
            tblAnzeige.setItems(bliste.getListe());

        } catch (Exception e) {
            lblStatus.setText("Fehler aufgetretten: " + e.getMessage());
        }

    }

    /**
     * Hilfsmethode leert die Textfelder. Methode entfernt die anzeige in allen Textfeldern.
     */
    private void leerenTextfelder() {
        tfKontonr.setText("");
        tfBeschreibung.setText("");
        tfBetrag.setText("");
        lblStatus.setText("");
        dpDate.setValue(LocalDate.now());
    }

    /**
     *
     * Methode übernimmt alle Eingabewerte und prüft nach Richtigkeit und Vollständigkeit und gibt
     * Buchungseintrag zurück
     *
     * @return buchung Buchung
     * @throws Exception
     */
    private Buchung getEingabeBuchung() throws Exception {
        Buchung bhg = new Buchung();
        Eigentuemer egm = new Eigentuemer();
        // **** Eingabe Kontonummer ****
        String strNummer = tfKontonr.getText().trim();
        for (Eigentuemer eg : egliste.getListe()) {

            if (eg.isKonto(strNummer)) {
                System.out.println("Konto gefunden: " + eg.getKontonummer());
                egm = eg;
                break;
            } else {                
                lblStatus.setText("Kontonummer exisitiert nicht oder falsch geschrieben");
                
            }
        }
        
        bhg.setKontonummer(strNummer);
        // **** Eingabe Buchungsdatum ****
        LocalDate buchdatum = dpDate.getValue();
        System.out.println("Datum: " + buchdatum);
        if (buchdatum == null || buchdatum.isAfter(LocalDate.now())) {
            dpDate.requestFocus();
            throw new Exception("Bitte ein Datum in der Vergangenheit wählen!");
        }
        bhg.setBuchungstag(buchdatum);

        // **** Eingabe Beschreibung ****
        // ************ TODO REGEX überprüfen ********************
        String beschreibung = tfBeschreibung.getText().trim();
        
        if (beschreibung.isEmpty()) {
            tfBeschreibung.requestFocus();
            throw new Exception("Bitte eine Beschreibung eingeben!");
        }
        bhg.setBeschreibung(beschreibung);

        // **** Eingabe Betrag ****
        Double zahl = Double.valueOf(tfBetrag.getText().trim());
        System.out.println("Betrag: " + zahl);
        if (zahl < 0) {
            tfBetrag.requestFocus();
            throw new Exception("Bitte richriten Betrag eingeben!");
        }
        
        
        
        bhg.setBetrag(zahl);
                
        if(myToggleGroup.getSelectedToggle().equals(rbtnE)) 
        {
            bhg.setVorgang(true);
            egm.setEinzahlen(zahl);
            System.out.println("neue kontostand: " + egm.getKontostand());
        }        
        if(myToggleGroup.getSelectedToggle().equals(rbtnA)) 
        {
            bhg.setVorgang(false);
            egm.setAuszahlen(zahl);
            System.out.println("neue kontostand: " + egm.getKontostand());
        }         
        System.out.println(bhg);
        return bhg;
    }

}
