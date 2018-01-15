package hauskontenverwaltung;

import java.io.Serializable;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * Klasse beschreibt einen Kostenkonto-Objekt
 * Ein Kostenkonto wird durch folgende Eigenschaften beschrieben:
 * <ul><li>Kontonummer</li>
 * <li>Bezeichnung</li> 
 * <li>Kontostand</li></ul>  
 * @author opodlubnaja
 */
public class Kostenkonto implements Serializable, Konstanten {
    private String kontonummer;
    private String bezeichnung;
    private double kontostand;    
    private double saldo = 0;    
    
    /**
    * Standard Konstruktor
    */
    public Kostenkonto()
    { 
        this.kontostand = saldo;
    }
    /**
     * überladener Konstruktor
     * @param kontonummer String
     * @param bezeichnung String
     */
    public Kostenkonto(String kontonummer, String bezeichnung)
    {
        this.kontonummer = kontonummer;
        this.bezeichnung = bezeichnung;
        this.kontostand = saldo;
    }
    // --- Kontonummer ---
    /**
     * Methode setzt den Kontonummer des Kostenkonto 
     * @param kontonummer Kontonummer
     */
    public void setKontonummer(String kontonummer)
    {
        this.kontonummer = kontonummer;
    }
    /**
     * Methode liefert Kontonummer des Kostenkonto
     * @return kontonummer String
     */
    public String getKontonummer()
    {
        return this.kontonummer;
    }
    
    /**
     * Methode liefert die Property der Kontonummer
     * @return SimpleStringProperty
     */
     public SimpleStringProperty kontonummerProperty()
    {
        return new SimpleStringProperty(this.kontonummer);
    }
     
    // --- Bezeichnung ---
    /**
     * Methode setztt die Bezeichnung des Kostenkonto
     * @param bezeichnung Bezeichnung
     */
    public void setBezeichnung(String bezeichnung)
    {
        this.bezeichnung = bezeichnung;
    }
    /**
     * Methode liefert Bezeichnung des Kostenkonto
     * @return bezeichnung string
     */
    public String getBezeichnung()
    {
        return this.bezeichnung;
    }
    
    /**
     * Methode liefert die Property der Bezeichnung
     * @return SimpleStringProperty
     */
    public SimpleStringProperty bezeichnungProperty()
    {
        return new SimpleStringProperty(this.bezeichnung);
    }
 
    // --- Kontostand ---
    /**
     * Methode liefert aktuellen Kontostand des Kostenkonto
     * @return kontostand double
     */
    public double getKontostand()
    {
        return this.kontostand;
    }
    
    /**
     * Methode setzt den Kontostand des Eigentümers bei
     * der Einzahlung
     * @param buchung double
     */
    public void setEinzahlen(double buchung)
    {
        this.kontostand +=buchung;
    }
     /**
     * Methode setzt den Kontostand des Eigentümers
     * bei der Auszahlung
     * @param buchung double
     */
    public void setAuszahlen(double buchung)
    {
        this.kontostand -=buchung;
    }
    
    /**
     * Methode liefert die Property des Kontostandes
     * @return SimpleDoubleProperty
     */
    public SimpleDoubleProperty kontostandProperty()
    {
        return new SimpleDoubleProperty(this.kontostand);
    }
    
    /**
     * Methode liefert boolean-Wert bei Kontoabgleich
     * true - Kontonummer exisitiert
     * @param zahl String
     * @return boolean
     */
    public boolean isKonto(String zahl)
    {
        return this.getKontonummer().equals(zahl);
    }
    
    /**
     * Methode gibt eine Zeichenkette mit den 
     * Kostenkontoinformationen zurück
     * @return Zeichenkette
     */
    @Override
    public String toString()
    {
        return "Kontonummer: " + this.getKontonummer() + ", "
               + "Bezeichnung: " + this.getBezeichnung() + ", "
               + "Kontostand: " + DF.format(this.getKontostand());
    } // Ende toString()
} // Ende der Klasse Kostenkonto
