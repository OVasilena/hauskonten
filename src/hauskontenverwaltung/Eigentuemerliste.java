package hauskontenverwaltung;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

/**
 * Klasse beschreibt eine Liste für Eigentümern, welche überwacht
 * wird. Das heißt, bei Änderungen an einem Eigentümer werden
 * diese sofort in die Liste übernommen.
 *
 * @author opodlubnaja
 */
public class Eigentuemerliste {

   
    // ÜberwachbareListe von Typ Eigentuemer    
    private ObservableList<Eigentuemer> eigentuemerListe;
    private boolean test = false;       
    private int letztenummer;
    
    public Eigentuemerliste() {
        // erzeugen des Listenobjektes
        // ArrayList -> indexorientierte überwachbare Liste
        this.eigentuemerListe = FXCollections.observableArrayList();  
        // testdaten nur beim ersten Datei erstellung speichern
        if(!test)
        {
        testdaten();
        test = true;
        }
        
    }

    /**
     * Methode zum Anlegen der Testdaten
     */
    private void testdaten() {
        // Testdaten aus der Dokumentation

        eigentuemerListe.add(new Eigentuemer("Gabriele", "Müller", "SFL07"));
        eigentuemerListe.add(new Eigentuemer("Cordula", "Schmidt", "VH11"));
        eigentuemerListe.add(new Eigentuemer("Sibille", "Lehmann", "SFR04"));
        eigentuemerListe.add(new Eigentuemer("Sabine", "Meyer", "GH06"));
        eigentuemerListe.add(new Eigentuemer("Irene", "Weiß", "VHL08"));
        eigentuemerListe.add(new Eigentuemer("Alex", "Braun", "VHL09"));
        eigentuemerListe.add(new Eigentuemer("Anton", "Kraft", "SFL02"));
        eigentuemerListe.add(new Eigentuemer("Thomas", "Geert", "SFL03"));
        eigentuemerListe.add(new Eigentuemer("Yurf", "Aure", "SFR01"));
        eigentuemerListe.add(new Eigentuemer("Mary", "Belkin", "SFR05"));

    }

    // Zufügen einen Eigentümer
    public void addEigentuemer(String vorname, String nachname, String whg) {
        Eigentuemer eig = new Eigentuemer();
        String nummer = eig.getKontonummer();
        System.out.println("Neue Eigentümer Kontonummer: " + nummer);
        eig.setVorname(vorname);
        eig.setNachname(nachname);
        eig.setWohnungsnummer(whg);
        System.out.println(eig);
        System.out.println("**** Ende neue Eigentümer ****");
        eigentuemerListe.add(eig);

    }
    
    /**
     * Methode merkt letzte Kontonummer
     * @param nummer int
     */
    public void setLetztenummer(int nummer)
    {
        this.letztenummer = nummer;
    }
    public int getLetztenummer()
    {
        return this.letztenummer;
    }

    
    public void addEigentuemer(Eigentuemer e)
    {
         eigentuemerListe.add(e);
    }
    
    /**
     * Methode berechnen Gesamtkontostand 
     * @return 
     */
    public double getGesamtStand()
    {
        double gstand = 0;
        for(Eigentuemer eg: eigentuemerListe)
        {
           gstand += eg.getKontostand();            
            
        }
        
        return gstand;   
    }
    
    /**
     * Methode liefert eine überwachbare Liste von Typ Eigentuemer
     *
     * @return liste ObservableList
     */
    public ObservableList<Eigentuemer> getListe() {
        return eigentuemerListe;
    }

    public int sizeListe() {
        return eigentuemerListe.size();
    }

    public boolean isEmpty() {
        return eigentuemerListe.isEmpty();
    }

    // Serialisierung eines Objektes
    public void speichernListe(File file) throws IOException {
        //testdaten();
        ObjectOutputStream aus = new ObjectOutputStream(new FileOutputStream(file));
        // Konvertieren ObservableList to ArrayList
        ArrayList<Eigentuemer> eigListe = new ArrayList<Eigentuemer>(eigentuemerListe);
        //System.out.println("**** gespeicherte Objekte als ArrayList ****");
        //for(Eigentuemer eg : eigListe) System.out.println(eg);
        aus.writeObject(eigListe);
        //System.out.println("************* ENDE SpeichernListe() *****************");
        aus.close();
    }

    // Deserialisierung eines Objektes
    public void auslesenDatei(File file) throws IOException, ClassNotFoundException {
        ObjectInputStream ein = new ObjectInputStream(new FileInputStream(file));
        ArrayList<Eigentuemer> eig_neu = (ArrayList<Eigentuemer>) ein.readObject();
        //  Konvertieren ArrayList to ObservableList
        eigentuemerListe = FXCollections.observableArrayList(eig_neu);
        //System.out.println("***** auslesenDatei als ObservableListe ******");
        //for (Eigentuemer eg : eigentuemerListe) {
        //    System.out.println(eg);
        //}
        
        
        ein.close();

    }  
    
    /**
     * Methode gibt einen Eigentümer an der Index-Stelle zurück
     * @param index int
     * @return Person
     */
    public Eigentuemer getEigentuemer(int index)
    {
        return eigentuemerListe.get(index);
    }
    
    public boolean removeEigentuemer(Eigentuemer e)
    {
        return this.eigentuemerListe.remove(e);
    }
    
    /**
     * Methode ersetzt einen Eigentümer an der Index-Stelle
     * @param index int
     * @param e Eigentuemer
     * @return Eigentuemer
     */
    public Eigentuemer setEigentuemer(int index, Eigentuemer e)
    {
        return this.eigentuemerListe.set(index, e);
    }


}
