package hauskontenverwaltung;

import java.io.Serializable;
import java.time.LocalDate;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * Klasse beschreibt einen Buchvorgang-Objekt Eine Buchung 
 * wird durch folgende Eigenschaften beschrieben:
 * <ul><li>Datum</li>
 * <li>Betrag</li>
 * <li>Vorgangsart</li>
 * <li>Beschreibung</li>
 * <li>Kontonummer</li></ul>
 *
 * @author Olga Podlubnaja
 */
public class Buchung implements Serializable, Konstanten {

    private LocalDate buchDatum;
    private Double betrag;
    private boolean vorgangsart;
    private String beschreibung;
    private String kontonummer;

    /**
     * Standard-Konstruktor
     */
    public Buchung() {
    }

    public Buchung(LocalDate datum, double betrag,
            boolean vorgangsart, String beschreibung,
            String kontonummer) {
        this.buchDatum = datum;
        this.betrag = betrag;
        this.vorgangsart = vorgangsart;
        this.beschreibung = beschreibung;
        this.kontonummer = kontonummer;
    }

    // --- Buchnungsdatum ---   
    /**
     * Methode setzt das Buchungstagobjekt des 
     * Eigentümers/Kostenkonto
     * @param datum LocalDate
     */
    public void setBuchungstag(LocalDate datum) {
        this.buchDatum = datum;
    }

    /**
     * Methode liefert das Buchungstagobjekt des 
     * Eigentümers/Kostenkonto
     * @return buchDatum - LocalDate
     */
    public LocalDate getBuchungstag() {
        return this.buchDatum;
    }

    /**
     * Methode liefert das Property-Objekt für den Buchungstag
     * @return SimpleObjectProperty@lt;LocalDate@gt;
     */
    public SimpleObjectProperty<LocalDate> buchungProperty() {
        return new SimpleObjectProperty(this.buchDatum);
    }

    // --- Betrag ---
    /**
     * Methode setzt den Betrag der Buchung
     * @param betrag double
     */
    public void setBetrag(double betrag) {
        this.betrag = betrag;
    }

    /**
     * Methode liefert den Betrag der Buchung
     * @return betrag double
     */
    public double getBetrag() {
        return this.betrag;
    }

    /**
     * Methode liefert die Property des Betrages
     * @return SimpleDoubleProperty betrag
     */
    public SimpleDoubleProperty betragProperty() {
        return new SimpleDoubleProperty(this.betrag);
    }

    // --- Vorgangsart ---
    /**
     * Methode setzt das Vorgangsart der Buchung
     * @param vorgang boolean
     */
    public void setVorgang(boolean vorgang) {
        this.vorgangsart = vorgang;
    }

    /**
     * Methode liefert das Vorgangsart der buchung
     * @return vorgangsart boolean
     */
    public boolean getVorgang() {
        return this.vorgangsart;
    }

    /**
     * Methode liefert die Property des Vorgangsartes
     * <ul>
     * <li>true - "E" für Einzahlung</li>
     * <li>false - "A" für Auszahlung</li>
     * </ul>
     * @return SimpleStringProperty
     */
    public SimpleStringProperty vorgangProperty() {
        if (this.vorgangsart == true) {
            return new SimpleStringProperty("E");
        } else {
            return new SimpleStringProperty("A");
        }
    }

    // --- Beschreibung ---
    /**
     * Methode setzt die Beschreibung der Buchung
     * @param beschreibung String
     */
    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }

    /**
     * Methode liefert die Beschreibung der Buchung
     * @return beschreibung String
     */
    public String getBeschreibung() {
        return this.beschreibung;
    }

    /**
     * Methode liefert die Property der Beschreibung
     * @return SimpleStringProperty beschreibung
     */
    public SimpleStringProperty beschreibungProperty() {
        return new SimpleStringProperty(this.beschreibung);
    }

    // --- Kontonummer ---
    /**
     * Methode setzt die Kontonummer der Buchung
     * @param kontonummer String
     */
    public void setKontonummer(String kontonummer) {
        this.kontonummer = kontonummer;
    }

    /**
     * Methode liefert die Kontonummer der Buchung
     * @return kontonummer string
     */
    public String getKontonummer() {
        return this.kontonummer;
    }

    /**
     * Methode liefert die Property der Kontonummer
     * @return SimpleStringProperty kontonummer
     */
    public SimpleStringProperty kontonummerProperty() {
        return new SimpleStringProperty(this.kontonummer);
    }

    /**
     * Methode gibt eine Zeichenkette mit den 
     * Buchungsinformationen zurück
     * @return Zeichenkette
     */
    @Override
    public String toString() {
        String zeichenkette;        
        zeichenkette = this.getBuchungstag().format(DTF) + ", "
                + DF.format(this.getBetrag());
        if (this.getVorgang()) {
            zeichenkette += ", E,";
        } else {
            zeichenkette += ", A,";
        }
        zeichenkette += this.getBeschreibung() + ", " 
                        + this.getKontonummer();
        return zeichenkette;
    } // Ende toString()
} // Ender der Klasse Buchung
