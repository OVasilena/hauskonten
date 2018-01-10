/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hauskontenverwaltung;

import java.time.LocalDate;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author opodlubnaja
 */
public class Buchungsliste {
    // ÜberwachbareListe von Typ Buchung
    private ObservableList<Buchung> buchungsListe;
        
    
    public Buchungsliste()
    {
        // erzeugen des Listenobjektes
        //ArrayList -> indexorientierte überwachbare Liste
        this.buchungsListe = FXCollections.observableArrayList();
        
        testdaten();
        //for(Buchung bhg: buchungsListe) 
        //    System.out.println(bhg);
    }
    
    public void testdaten()
    {
        buchungsListe.add(new Buchung
        (LocalDate.of(2017,07,01),265.00,true,"Hausgeld Frau Meyer GH06","EK106"));
        buchungsListe.add(new Buchung
        (LocalDate.of(2017,07,12),356.78,false,"Rechnung Firma \"Ungeziefer Ex\"","K123"));
        buchungsListe.add(new Buchung
        (LocalDate.of(2017,07,20),1286.25,false,"Rechnung Straßenreinigung","K076"));
        buchungsListe.add(new Buchung
        (LocalDate.of(2017,8,3),265, true,"Hausgeld Frau Müller","EK103"));
        buchungsListe.add(new Buchung
        (LocalDate.of(2017,8,03),265.00,true,"Hausgeld Frau Schmidt","EK104"));
       
    }
          
    
    public boolean addBuchung(Buchung b)
    {
        return buchungsListe.add(b);
    }
    
    /**
     * Methode liefert eine überwachbare Liste von Typ Person
     * @return liste ObservableList
     */
    public ObservableList<Buchung> getListe()
    {
        return buchungsListe;
    }
    

    
}
