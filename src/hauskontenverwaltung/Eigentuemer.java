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
 * @author opodlubnaja
 */
public class Eigentuemer implements Serializable
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
        //System.out.println("Nummer aus getID: " + nummer);
        this.kontostand = saldo;
        
    }
    
    public void setKonto(int nm)
    {
        this.id= nm;
    }
    
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
    public void setEinzahlen(double buchung)
    {
        this.kontostand +=buchung;
        //this.kontostand = saldo;
        
    }
     /**
     * Methode setzt den Kontostand des Eigentümers
     * @param buchung double
     */
    public void setAuszahlen(double buchung)
    {
        this.kontostand -=buchung;
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
                + ", Eigentümername: " + getNachname() + ", "
                + getVorname() + ", Wohnungsnummer: "
                + getWohnungsnummer() + ", Kontostand: " 
                + getKontostand() + " €";
    }
    
    // Methoden liefern die Eigenschaften
    
    public SimpleStringProperty kontonummerProperty()
    {
        return new SimpleStringProperty(this.kontonummer);
    }
    
    public SimpleStringProperty vornameProperty()
    {
        return new SimpleStringProperty(this.vorname);
    }
    
    public SimpleStringProperty nachnameProperty()
    {
        return new SimpleStringProperty(this.nachname);
    } 
    
    public SimpleStringProperty wohnungsnumerProperty()
    {
        return new SimpleStringProperty(this.wohnungsnummer);
    } 
    
    public SimpleDoubleProperty kontostandProperty()
    {
        return new SimpleDoubleProperty(this.kontostand);
    }
    
        public boolean isKonto(String zahl)
    {
        return this.getKontonummer().equals(zahl);
    }
}