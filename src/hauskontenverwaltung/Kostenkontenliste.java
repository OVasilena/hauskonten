package hauskontenverwaltung;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Klasse beschreibt eine Liste für Kostenkonten, welche
 * überwacht wird. Das heißt, bei Änderungen an einem Eigentümer
 * werden diese sofort in die Liste übernommen.
 *
 * @author opodlubnaja
 */
public class Kostenkontenliste {
   
   
    // Liste von Typ Kostenkonto
    // ÜberwachbareListe von Typ Kostenkonto   
    private ObservableList<Kostenkonto> kostenkontoListe;
    private boolean test = false;       

    public Kostenkontenliste() {
        // erzeugen des Listenobjektes
        // ArrayList -> indexorientierte überwachbare Liste
        this.kostenkontoListe = FXCollections.observableArrayList();  
        if(!test)
        {
        testdaten();
        test = true;
        }    
       
    }

    /**
     * Methode zum Anlegen der Testdaten
     */
    public void testdaten() {
        kostenkontoListe.add(new Kostenkonto("K104", "Aufzugskosten"));
        kostenkontoListe.add(new Kostenkonto("K099", "Hausstrom"));
        kostenkontoListe.add(new Kostenkonto("K123", "Ungezieferbekämpfung"));
        kostenkontoListe.add(new Kostenkonto("K076", "Straßenreinigung"));
        kostenkontoListe.add(new Kostenkonto("K183", "Aufzugskosten"));
        kostenkontoListe.add(new Kostenkonto("K206", "Hausmeister"));
        kostenkontoListe.add(new Kostenkonto("K600", "Diverse Reparaturkosten"));
        kostenkontoListe.add(new Kostenkonto("K209", "Schornsteinfeger"));
        

    }
    // Zufügen eine Kostenstelle
    public void addKonto(String kontonr, String bezeichnung) {
        kostenkontoListe.add(new Kostenkonto
                                        (kontonr, bezeichnung));
    }
    
    public void addKonto(Kostenkonto k)
    {
        kostenkontoListe.add(k);
    }
    
    /**
     * Methode berechnen Gesamtkontostand 
     * @return 
     */
    public double getGesamtStand()
    {
        double gstand = 0;
        for(Kostenkonto kk: kostenkontoListe)
        {
           gstand += kk.getKontostand();
           
        }
        
        return gstand;   
    }
    

    // Serialisierung eines Objektes
    public void speichernListe(File file) throws IOException {
        
        ObjectOutputStream aus = new ObjectOutputStream(new FileOutputStream(file));
        // Konvertieren ObservableList to ArrayList
        ArrayList<Kostenkonto> kListe = new ArrayList<Kostenkonto>(kostenkontoListe);
        
        aus.writeObject(kListe);
        aus.close();
    }

    // Deserialisierung eines Objektes
    public void auslesenDatei(File file) throws IOException, ClassNotFoundException {
        ObjectInputStream ein = new ObjectInputStream(new FileInputStream(file));
        ArrayList<Kostenkonto> kosten_neu = (ArrayList<Kostenkonto>) ein.readObject();
        kostenkontoListe = FXCollections.observableArrayList(kosten_neu);
        //for (Kostenkonto kkonto : kosten_neu) {
        //    System.out.println(kkonto);
        //}
        ein.close();

    }
    
    /**
     * Methode liefert eine überwachbare Liste von Typ Kostenkonto
     *
     * @return liste ObservableList
     */
    public ObservableList<Kostenkonto> getListe() {
        return kostenkontoListe;
    }

    public int sizeListe() {
        return kostenkontoListe.size();
    }

    public boolean isEmpty() {
        return kostenkontoListe.isEmpty();
    }
    
     
    /**
     * Methode gibt einen Eigentümer an der Index-Stelle zurück
     * @param index int
     * @return Person
     */
    public Kostenkonto getKostenkonto(int index)
    {
        return kostenkontoListe.get(index);
    }
    
    public boolean removeKostenkonto(Kostenkonto k)
    {
        return this.kostenkontoListe.remove(k);
    }
    
    /**
     * Methode ersetzt einen Eigentümer an der Index-Stelle
     * @param index int
     * @param e Eigentuemer
     * @return Eigentuemer
     */
    public Kostenkonto setKostenkonto(int index, Kostenkonto k)
    {
        return this.kostenkontoListe.set(index, k);
    }
    
}
