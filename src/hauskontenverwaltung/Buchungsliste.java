package hauskontenverwaltung;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
        
        //testdaten();
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
    
       // Serialisierung eines Objektes
    public void speichernListe(File file) throws IOException {
        
        ObjectOutputStream aus = new ObjectOutputStream(new FileOutputStream(file));
        // Konvertieren ObservableList to ArrayList
        ArrayList<Buchung> bListe = new ArrayList<Buchung>(buchungsListe);        
        aus.writeObject(bListe);        
        aus.close();
    }

    // Deserialisierung eines Objektes
    public void auslesenDatei(File file) throws IOException, ClassNotFoundException {
        ObjectInputStream ein = new ObjectInputStream(new FileInputStream(file));
        ArrayList<Buchung> bhg_neu = (ArrayList<Buchung>) ein.readObject();
        //  Konvertieren ArrayList to ObservableList
        buchungsListe = FXCollections.observableArrayList(bhg_neu);        
        ein.close();

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
    
    public boolean isEmpty() {
        return buchungsListe.isEmpty();
    }
    
}
