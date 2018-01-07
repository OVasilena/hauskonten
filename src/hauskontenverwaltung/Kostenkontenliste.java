package hauskontenverwaltung;

import java.util.ArrayList;



/**
 * Klasse beschreibt eine Liste für Kostenkonten, welche 
 * überwacht wird. Das heißt, bei Änderungen an einem Eigentümer 
 * werden diese sofort in die Liste übernommen.
 * @author opodlubnaja
 */
public class Kostenkontenliste {
    // Liste von Typ Kostenkonto
    private ArrayList<Kostenkonto> kostenkontoListe;
    
    public Kostenkontenliste()
    {
        // erzeugen des Listenobjektes
        // ArrayList -> indexorientierte überwachbare Liste
        this.kostenkontoListe = new ArrayList();
        testdaten();
        for(Kostenkonto kkonten: kostenkontoListe) 
            System.out.println(kkonten);
    }
    /**
     * Methode zum Anlegen der Testdaten
     */
    public void testdaten()
    {
        kostenkontoListe.add(new Kostenkonto
                                    ("K104","Aufzugskosten"));
        kostenkontoListe.add(new Kostenkonto
                                    ("K099","Hausstrom"));
        kostenkontoListe.add(new Kostenkonto
                                    ("K123","Ungezieferbekämpfung"));
        kostenkontoListe.add(new Kostenkonto
                                    ("K076","Straßenreinigung"));
        kostenkontoListe.add(new Kostenkonto
                                    ("K183","Aufzugskosten"));     
        kostenkontoListe.add(new Kostenkonto
                                    ("K206","Hausmeister"));
        kostenkontoListe.add(new Kostenkonto
                            ("K600","Diverse Reparaturkosten"));
        kostenkontoListe.add(new Kostenkonto
                                    ("K209","Schornsteinfeger"));
     
    }
}
