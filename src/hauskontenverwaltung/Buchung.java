/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hauskontenverwaltung;

import java.io.Serializable;
import java.time.LocalDate;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * Klasse beschreibt einen Buchvorgang-Objekt Eine Buchung wird durch folgende
 * Eigenschaften beschrieben:
 * <ul><li>Datum</li>
 * <li>Betrag</li>
 * <li>Vorgangsart</li>
 * <li>Beschreibung</li>
 * <li>Kontonummer</li></ul>
 *
 * @author opodlubnaja
 */
public class Buchung implements Serializable, Konstanten {

    private LocalDate buchDatum;
    private Double betrag;
    private boolean vorgangsart;
    private String beschreibung;
    private String kontonummer;

    //DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    //java.text.DecimalFormat df = new java.text.DecimalFormat("#,##0.00");     
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
     * Methode setzt das Buchungstagobjekt des Eigentümers/Kostenkonto
     *
     * @param datum LocalDate
     */
    public void setBuchungstag(LocalDate datum) {
        this.buchDatum = datum;
    }

    /**
     * Methode liefert das Buchungstagobjekt des Eigentümers/Kostenkonto
     *
     * @return buchDatum - LocalDate
     */
    public LocalDate getBuchungstag() {
        return this.buchDatum;
    }

    /**
     * Methode liefert das Property-Objekt für den Buchungstag
     *
     * @return SimpleObjectProperty@lt;LocalDate@gt;
     */
    public SimpleObjectProperty<LocalDate> buchungProperty() {
        return new SimpleObjectProperty(this.buchDatum);

    }

    // --- Betrag ---
    public void setBetrag(double betrag) {
        this.betrag = betrag;
    }

    public double getBetrag() {
        return this.betrag;
    }

    public SimpleDoubleProperty betragProperty() {
        return new SimpleDoubleProperty(this.betrag);
    }

    // --- Vorgangsart ---
    public void setVorgang(boolean vorgang) {
        this.vorgangsart = vorgang;
    }

    public boolean getVorgang() {
        return this.vorgangsart;
    }

    public SimpleStringProperty vorgangProperty() {
        if (this.vorgangsart == true) {
            return new SimpleStringProperty("E");
        } else {
            return new SimpleStringProperty("A");
        }
    }

    // --- Beschreibung ---
    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }

    public String getBeschreibung() {
        return this.beschreibung;
    }

    public SimpleStringProperty beschreibungProperty() {
        return new SimpleStringProperty(this.beschreibung);
    }

    // --- Kontonummer ---
    public void setKontonummer(String kontonummer) {
        this.kontonummer = kontonummer;
    }

    public String getKontonummer() {
        return this.kontonummer;
    }

    public SimpleStringProperty kontonummerProperty() {
        return new SimpleStringProperty(this.kontonummer);
    }

    @Override
    public String toString() {
        String zeichenkette;
        //zeichenkette = "Datum\t\tBetrag\tE/A\tBeschreibung\tKontonummer\n";
        zeichenkette = this.getBuchungstag().format(DTF) + ", "
                + DF.format(this.getBetrag());
        if (this.getVorgang()) {
            zeichenkette += ", E,";
        } else {
            zeichenkette += ", A,";
        }
        zeichenkette += this.getBeschreibung() + ", " + this.getKontonummer();

        return zeichenkette;
    }

}
