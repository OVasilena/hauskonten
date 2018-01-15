package hauskontenverwaltung;

import java.io.Serializable;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;


/**
 * Klasse beschreibt einen Eigentümerobjekt
 * Ein Eigentümer wird durch folgende Eigenschaften beschrieben:
 * <ul><li>Kontonummer</li>
 * <li>Vorname</li>
 * <li>Nachname</li>
 * <li>Wohnungsnummer</li>
 * <li>Kontostand</li></ul>  
 * @author Olga Podlubnaja
 */
public class Eigentuemer implements Serializable, Konstanten
{
    
    private String wohnungsnummer;
    private String vorname, nachname;
    private double kontostand =0;    
    private String kontonummer;
    private final double saldo = 0;
    private static int id = 102;
    private int nummer;
    

    /**
    * Standard Konstruktor
    */
    public Eigentuemer() 
    { 
             
        nummer = ++id;
        this.kontonummer = "EK" + Integer.toString(nummer);           
        this.kontostand = saldo;
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
    
    // --- Kontonummer ---
    /**
     * Methode liefert die Kontonummer des Eigentümers
     * @return kontonummer String
     */
    public String getKontonummer()
    {
        return this.kontonummer;
    }  
    
    /**
     * Methode liefert die Property der Kontonummer
     * @return SimpleStringProperty kontonummer
     */  
     public SimpleStringProperty kontonummerProperty()
    {
        return new SimpleStringProperty(this.kontonummer);
    }
     
    /**
     * Methode setzt neue id für Kontonummerberechnung
     * @param nm int
     */
    public void setKonto(int nm)
    {
        this.id= nm;
    }
    
    /**
     * Methode liefert nummer
     * @return nummer int
     */
    public int getID()
    {
        return nummer;
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
    
    /**
     * Methode liefert die Property des Vornamens des Eigentümers
     * @return SimpleStringProperty vorname
     */    
     public SimpleStringProperty vornameProperty()
    {
        return new SimpleStringProperty(this.vorname);
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
    
    /**
     * Methode liefert die Property des Nachnamens 
     * @return SimpleStringProperty nachname
     */
    public SimpleStringProperty nachnameProperty()
    {
        return new SimpleStringProperty(this.nachname);
    } 
    
    // --- Wohnungsnummer ---
    /**
     * Methode setzt die Wohnungsnummer des Eigentümers
     * @param whgnummer  String
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
    
    /**
     * Methode liefert die Property der Wihnungsnummer
     * @return SimpleStringProperty wohnungsnummer
     */
    public SimpleStringProperty wohnungsnumerProperty()
    {
        return new SimpleStringProperty(this.wohnungsnummer);
    } 

    // --- Kontostand ---
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
     * Methode setzt den Kontostand des Eigentümers bei
     * der Auszahlung
     * @param buchung double
     */
    public void setAuszahlen(double buchung)
    {
        this.kontostand -=buchung;
    }
    /**
     * Methode liefert aktuellen Kontostand des Eigentümers
     * @return kontostand double
     */
    public double getKontostand()
    {
        return this.kontostand;
    }
    
    /**
     * Methode liefert die Property der Kontonummer
     * @return SimpleStringProperty kontonummer
     */  
    public SimpleDoubleProperty kontostandProperty()
    {
        return new SimpleDoubleProperty(this.kontostand);
    }
  
    /**
     * Methode liefert boolean-Wert bei Kontovergleich
     * true - Kontonummer existiert
     * @param zahl String
     * @return boolean
     */
    public boolean isKonto(String zahl)
    {
        return this.getKontonummer().equals(zahl);
    } // Ende isKonto()
    
    /**
     * Methode gibt eine Zeichnenkette mit den 
     * Eigentümerinformationen zurück.
     * @return Zeichenkette
     */
    @Override
    public String toString()
    {
        return "Kontonummer: " + getKontonummer()
                + ", Eigentümername: " + getNachname() + ", "
                + getVorname() + ", Wohnungsnummer: "
                + getWohnungsnummer() + ", Kontostand: " 
                + DF.format(getKontostand()) + " €";
    } // Ende toString()
} // Ende der Klasse Eigentuemer