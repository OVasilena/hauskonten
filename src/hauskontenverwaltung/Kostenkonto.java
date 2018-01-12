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
public class Kostenkonto implements Serializable {
    private String kontonummer;
    private String bezeichnung;
    private double kontostand;    
    private double saldo = 0;
    java.text.DecimalFormat df = new java.text.DecimalFormat("#,##0.00"); 
    
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
    
    public SimpleStringProperty bezeichnungProperty()
    {
        return new SimpleStringProperty(this.bezeichnung);
    }
 
    // --- Kontostand ---
    /**
     * Methode setzt den Kontostand des Kostenkonto
     * @param buchung double
     */
    public void setKontostand(double buchung)
    {
        this.kontostand +=buchung;
    }
    /**
     * Methode liefert aktuellen Kontostand des Kostenkonto
     * @return kontostand double
     */
    public double getKontostand()
    {
        return this.kontostand;
    }
    
    // --- Kontostand ---
    /**
     * Methode setzt den Kontostand des Eigentümers
     * @param buchung double
     */
    public void setEinzahlen(double buchung)
    {
        this.kontostand +=buchung;
       
        
    }
     /**
     * Methode setzt den Kontostand des Eigentümers
     * @param buchung double
     */
    public void setAuszahlen(double buchung)
    {
        this.kontostand -=buchung;
        
        
    }
    
    public SimpleDoubleProperty kontostandProperty()
    {
        return new SimpleDoubleProperty(this.kontostand);
    }
    
    @Override
    public String toString()
    {
        return "Kontonummer: " + this.getKontonummer()
                + "\nBezeichnung: " + this.getBezeichnung()
                + "\nKontostand: " + df.format(this.getKontostand()) 
                + "\n";
    }
    
    public boolean isKonto(String zahl)
    {
        return this.getKontonummer().equals(zahl);
    }
    
}
