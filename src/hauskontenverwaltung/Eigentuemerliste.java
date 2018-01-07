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


/**
 * Klasse beschreibt eine Liste für Eigentümern, welche überwacht 
 * wird. Das heißt, bei Änderungen an einem Eigentümer werden 
 * diese sofort in die Liste übernommen.
 * @author opodlubnaja
 */
public class Eigentuemerliste {
    
    // ÜberwachbareListe von Typ Eigentuemer
    //private ObservableList<Eigentuemer> eigentuemerListe;
    private ArrayList<Eigentuemer> eigentuemerListe;
    public Eigentuemerliste()
    {
        // erzeugen des Listenobjektes
        // ArrayList -> indexorientierte überwachbare Liste
        //this.eigentuemerListe = FXCollections.observableArrayList();
        this.eigentuemerListe = new ArrayList();
        testdaten();
        //for(Eigentuemer eigen:eigentuemerListe) 
        //    System.out.println(eigen);
        try
        {
            speichernListe();
        }
        catch (IOException e)
        {
            System.out.println("Fehlermeldung");
        }
        
        System.out.println("********ausgeben Datei*******");
        try
        {
            auslesenDatei();
            
        }
        catch (IOException e)
        {
            System.out.println("Ausgabe nicht möglich\n " +e.getMessage());
        }
        catch (ClassNotFoundException ce)
        {
            System.out.println("Klass nicht gefunden" + ce.getMessage());
        }
                
    }
    
     /**
     * Methode zum Anlegen der Testdaten
     */
    private void testdaten()
    {
        // Testdaten aus der Dokumentation
        
        eigentuemerListe.add(new Eigentuemer
                                ("Gabriele", "Müller","SFL07"));
        eigentuemerListe.add(new Eigentuemer
                                ("Cordula", "Schmidt", "VH11"));
        eigentuemerListe.add(new Eigentuemer
                                ("Sibille", "Lehmann", "SFR04"));
        eigentuemerListe.add(new Eigentuemer
                                ("Sabine", "Meyer", "GH06"));
        eigentuemerListe.add(new Eigentuemer
                                ("Irene", "Weiß", "VHL08"));
        eigentuemerListe.add(new Eigentuemer
                                ("Alex", "Braun", "VHL09"));
        eigentuemerListe.add(new Eigentuemer
                                ("Anton", "Kraft", "SFL02"));
        eigentuemerListe.add(new Eigentuemer
                                ("Thomas", "Geert", "SFL03"));
        eigentuemerListe.add(new Eigentuemer
                                ("Yurf", "Aure", "SFR01"));
        eigentuemerListe.add(new Eigentuemer
                                ("Mary", "Belkin", "SFR05"));
        
        
       
        
    }
    
    // Serialisierung eines Objektes
    public void speichernListe() throws IOException
    {
        ObjectOutputStream aus = new ObjectOutputStream(
                                 new FileOutputStream("test2.stm"));
        aus.writeObject(eigentuemerListe);
        aus.close();
    }
    
    // Deserialisierung eines Objektes
    public void auslesenDatei() throws IOException, ClassNotFoundException
    {
        ObjectInputStream ein = new ObjectInputStream( new FileInputStream("test2.stm"));
        ArrayList<Eigentuemer> eig_neu = (ArrayList<Eigentuemer>)ein.readObject();
        
       for(Eigentuemer eg: eig_neu)
            System.out.println(eg);
        ein.close();
        
    }
    
    
    
}
