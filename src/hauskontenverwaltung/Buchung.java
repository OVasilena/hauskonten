/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hauskontenverwaltung;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


/**
 * Klasse beschreibt einen Buchvorgang-Objekt
 * Eine Buchung wird durch folgende Eigenschaften beschrieben:
 * <ul><li>Datum</li>
 * <li>Betrag</li>
 * <li>Vorgangsart</li>
 * <li>Beschreibung</li>
 * <li>Kontonummer</li></ul>  
 * @author opodlubnaja
 */
public class Buchung implements Serializable {
    private LocalDate buchDatum;
    private Double betrag;
    private String vorgangsart;
    private String beschreibung;
    private String kontonummer;
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    java.text.DecimalFormat df = new java.text.DecimalFormat("#,##0.00");     
   public Buchung()
   {
   }
    
   public Buchung(LocalDate datum, double betrag, 
                   String vorgangsart,String beschreibung, 
                   String kontonummer)
   {
       this.buchDatum = datum;
       this.betrag = betrag;
       this.vorgangsart = vorgangsart;
       this.beschreibung = beschreibung;
       this.kontonummer = kontonummer;
   }
   
   // --- Buchnungsdatum ---   
    /**
     * Methode setzt das Buchungstagobjekt des Eigentümers/Kostenkonto
     * @param datum LocalDate
     */
    public void setBuchungstag(LocalDate datum)
    {
     this.buchDatum = datum;
    }
    
    /**
     * Methode liefert das Buchungstagobjekt des Eigentümers/Kostenkonto
     * @return buchDatum - LocalDate
     */
    public LocalDate getBuchungstag()
    {
        return this.buchDatum;
    }
    

    // --- Betrag ---
    public void setBetrag(double betrag)
    {
        this.betrag = betrag;
    }
    public double getBetrag()
    {
        return this.betrag;
    }

    // --- Vorgangsart ---
    public void setVorgang(String vorgang)
    {
        this.vorgangsart = vorgang;
    }
    public String getVorgang()
    {
        return this.vorgangsart;
    }
 
    // --- Beschreibung ---
    public void setBeschreibung(String beschreibung)
    {
        this.beschreibung = beschreibung;
    }
    public String getBeschreibung()
    {
        return this.beschreibung;
    }

    
    // --- Kontonummer ---
    public void setKontonummer(String kontonummer)
    {
        this.kontonummer = kontonummer;
    }
    public String getKontonummer()
    {
        return this.kontonummer;
    }
 
    
    @Override
    public String toString()
    {
        return "Datum\t\tBetrag\tE/A\tBeschreibung\tKontonummer\n"
                + this.getBuchungstag().format(dtf) + "\t" 
                + df.format(this.getBetrag())+ "\t" 
                + this.getVorgang() + "\t" 
                + this.getBeschreibung() + "\t" 
                + this.getKontonummer();
    }
    
}
