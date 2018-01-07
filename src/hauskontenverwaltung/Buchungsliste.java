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
    private ArrayList<Buchung> buchungsListe;
        
    
    public Buchungsliste()
    {
        // erzeugen des Listenobjektes
        //ArrayList -> indexorientierte überwachbare Liste
        //this.buchungsListe = FXCollections.observableArrayList();
        buchungsListe = new ArrayList(); 
        testdaten();
        for(Buchung bhg: buchungsListe) 
            System.out.println(bhg);
    }
    
    public void testdaten()
    {
        buchungsListe.add(new Buchung
        (LocalDate.of(2017,07,01),265.00,"E","Hausgeld Frau Meyer GH06","EK106"));
        buchungsListe.add(new Buchung
        (LocalDate.of(2017,07,12),356.78,"A","Rechnung Firma \"Ungeziefer Ex\"","K123"));
        buchungsListe.add(new Buchung
        (LocalDate.of(2017,07,20),1286.25,"A","Rechnung Straßenreinigung","K076"));
        buchungsListe.add(new Buchung
        (LocalDate.of(2017,8,3),265, "E","Hausgeld Frau Müller","EK103"));
        buchungsListe.add(new Buchung
        (LocalDate.of(2017,8,03),265.00,"E","Hausgeld Frau Schmidt","EK104"));
       
    }
    
    public void kontonummerWahl()
    {
        
            
    }
    
}
