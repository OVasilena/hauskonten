package hauskontenverwaltung;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Klasse beschreibt eine Liste für Kostenkonten, welche
 * überwacht wird. Das heißt, bei Änderungen an einem Eigentümer
 * werden diese sofort in die Liste übernommen.
 *
 * @author opodlubnaja
 */
public class Kostenkontenliste {

    // Liste von Typ Kostenkonto
    private ArrayList<Kostenkonto> kostenkontoListe;

    public Kostenkontenliste() {
        // erzeugen des Listenobjektes
        // ArrayList -> indexorientierte überwachbare Liste
        this.kostenkontoListe = new ArrayList();
        testdaten();
        //for (Kostenkonto kkonten : kostenkontoListe) {
        //    System.out.println(kkonten);
        //}
        try
        {
            speichernListe();
        }
        catch (IOException e)
        {
            System.out.println("Fehlermeldung" +e.getMessage());
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
            System.out.println("Klasse nicht gefunden" + ce.getMessage());
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
        
        Scanner scan = new Scanner(System.in);
        System.out.println("Konto: ");
        String kontonr = scan.next();
        System.out.println("Bezeichnung: ");
        String bezeichnung = scan.next();
        
        addKonto(kontonr, bezeichnung);
        scan.close();

    }
    // Zufügen eine Kostenstelle
    public void addKonto(String kontonr, String bezeichnung) {
        kostenkontoListe.add(new Kostenkonto
                                        (kontonr, bezeichnung));
    }
    

    // Serialisierung eines Objektes
    public void speichernListe() throws IOException {
        ObjectOutputStream aus = new ObjectOutputStream(
                new FileOutputStream("test2.stm"));
        aus.writeObject(kostenkontoListe);
        aus.close();
    }

    // Deserialisierung eines Objektes
    public void auslesenDatei() throws IOException, ClassNotFoundException {
        ObjectInputStream ein = new ObjectInputStream(new FileInputStream("test2.stm"));
        ArrayList<Kostenkonto> kosten_neu = (ArrayList<Kostenkonto>) ein.readObject();

        for (Kostenkonto kkonto : kosten_neu) {
            System.out.println(kkonto);
        }
        ein.close();

    }
}
