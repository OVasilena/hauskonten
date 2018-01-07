package hauskontenverwaltung;

import java.io.Serializable;


/**
 * Klasse beschreibt einen Eigentümerobjekt
 * Ein Eigentümer wird durch folgende Eigenschaften beschrieben:
 * <ul><li>Kontonummer</li>
 * <li>Vorname</li>
 * <li>Nachname</li>
 * <li>Wohnungsnummer</li>
 * <li>Kontostand</li></ul>  
 * @author opodlubnaja
 */
public class Eigentuemer implements Serializable
{
    
    private String wohnungsnummer;
    private String vorname, nachname;
    private double kontostand;    
    private String kontonummer;
    private final double saldo = 0;
    private static int id = 102;
    private int nummer;
   
    /**
    * Standard Konstruktor
    */

    public Eigentuemer() 
    {      
    }

    
    /**
    * überladener Konstruktor
    * @param vorname Vorname
    * @param nachname Nachname
    * @param whgnummer Wohnungsnummer 
    */
    public Eigentuemer(String vorname, String nachname, 
                       String whgnummer) 
    {
        nummer = ++id;
        this.vorname = vorname;
        this.nachname = nachname;
        this.wohnungsnummer = whgnummer;
        this.kontonummer = "EK" + Integer.toString(nummer);           
        this.kontostand = saldo;
        
    }

    // --- Vorname ---
    /**
     * Methode setzt den Vornamen des Eigentümers
     * @param vorname String
     */
    public void setVorname(String vorname)
    {
        this.vorname = vorname;
    }
    /**
     * Methode liefert den Vornamen des Eigentümers
     * @return vorname String
     */
    public String getVorname()
    {
        return this.vorname;
    }

    // --- Nachname ---
    /**
     * Methode setzt den Nachnamen des Eigentümers
     * @param nachname String
     */
    public void setNachname(String nachname)
    {
        this.nachname = nachname;
    }
    /**
     * Methode liefert den Nachnamen des Eigentümers
     * @return Nachname String
     */
    public String getNachname()
    {
        return this.nachname;
    }

    // --- Wohnungsnummer ---
    /**
     * Methode setzt die Wohnungsnummer des Eigentümers
     * @param whgnummer 
     */
    public void setWohnungsnummer(String whgnummer)
    {
        this.wohnungsnummer = whgnummer;
    }
    /**
     * Methode liefert die Wohnungsnummer des Eigentümers
     * @return Wohnungsnummer String
     */
    public String getWohnungsnummer()
    {
        return this.wohnungsnummer;
    }

    // --- Kontonummer ---
    /**
     * Methode liefert den Kontostand des Eigentümers
     * @return kontonummer String
     */
    public String getKontonummer()
    {
        return this.kontonummer;
    }

    // --- Kontostand ---
    /**
     * Methode setzt den Kontostand des Eigentümers
     * @param buchung double
     */
    public void setKontostand(double buchung)
    {
        this.kontostand +=buchung;
        //this.kontostand = saldo;
        
    }
    /**
     * Methode liefert aktuellen Kontostand des Eigentümers
     * @return kontostand double
     */
    public double getKontostand()
    {
        return this.kontostand;
    }
  
    @Override
    public String toString()
    {
        return "Kontonummer: " + getKontonummer()
                + "\nEigentümername: " + getNachname() + ", "
                + getVorname() + "\nWohnungsnummer: "
                + getWohnungsnummer() + "\nKontostand: " 
                + getKontostand() + " €\n";
    }
    
}