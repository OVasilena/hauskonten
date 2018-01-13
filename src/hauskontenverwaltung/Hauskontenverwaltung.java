package hauskontenverwaltung;

import java.io.File;
import java.io.IOException;
import java.util.Optional;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.BorderPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

/**
 *
 * @author opodlubnaja
 */
public class Hauskontenverwaltung extends Application implements Konstanten {

    // Objekte der Hauskontenverwaltung
    private Stage fenster;
    private BorderPane root = new BorderPane();
    private Eigentuemerliste eigliste;
    private Kostenkontenliste kkliste;
    private Buchungsliste buchungsliste;
    private File dateiPath;
    private MenuController mcontroller;
    private StartMenuController smcontroller;
    private FXMLLoader loader;
    private File datei;
    private File gefundenEDatei;
    private File gefundenKDatei;
    private File gefundenBDatei;    
    private boolean aenderung;

    /**
     * Konstruktor diefiniert zu verwaltenden Listen
     */
    public Hauskontenverwaltung() {

        eigliste = new Eigentuemerliste();
        kkliste = new Kostenkontenliste();
        buchungsliste = new Buchungsliste();
        aenderung = false;
    }

    public static void main(String[] args) {
        launch();

    }

    public BorderPane getRoot() {
        return root;
    }

    public Stage getFenster() {
        return fenster;
    }
    /**
     * Methode starten GUI
     * @param primaryStage
     * @throws Exception 
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        fenster = primaryStage;
        loader = new FXMLLoader();
        //loader.setLocation(getClass().getResource("StartPane.fxml"));
        loader.setLocation(getClass().getResource("StartMenu.fxml"));
        root = loader.load();
        Scene scene = new Scene(root);

        // Fenstereigenschaften
        fenster.setScene(scene);
        fenster.setTitle("Hauskontenverwaltung");

        //this.mcontroller = loader.getController();
        //mcontroller.setVerwaltung(this);
        this.smcontroller = loader.getController();
        smcontroller.setVerwaltung(this);
/*
        File ordner = this.getDatei();
        datei = ordner.getAbsoluteFile();

        // ***** Eigentümerliste ****
        gefundenEDatei = this.searchFile(datei, EDATEI);        
        eigliste = ladenEDatei(gefundenEDatei);
        mcontroller.setEigentListe(eigliste);
        // ***** Kostenkontenliste *****
        gefundenKDatei = this.searchFile(datei, KDATEI);
        kkliste = ladenKDatei(gefundenKDatei);
        mcontroller.setKostenliste(kkliste);
        // ***** Buchungsliste *****
        gefundenBDatei = this.searchFile(datei, BDATEI);
        buchungsliste = ladenBDatei(gefundenBDatei);
        mcontroller.setBuchungsListe(buchungsliste);
*/
        fenster.show();
    }

    /**
     * Methode schließt das Fenster
     */
    public void stop() {
        if(!this.aenderung)
        {
            System.exit(0);
        }
        Alert con = new Alert(Alert.AlertType.CONFIRMATION);
        con.setHeaderText("Datenänderung");
        con.setContentText("Sollen die Änderungen gespeichert werden?");
        con.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);
        Optional erg = con.showAndWait();
        if (erg.get() == ButtonType.YES) {
            try {
                if (gefundenEDatei == null) {
                    gefundenEDatei = new File(datei + "\\" + EDATEI + "_" + datei.getName() + ".stm");
                }
                if (gefundenBDatei == null) {
                    gefundenBDatei = new File(datei + "\\" + BDATEI + "_" + datei.getName() + ".dat");
                }
                if (gefundenKDatei == null) {
                    gefundenKDatei = new File(datei + "\\" + KDATEI + "_" + datei.getName() + ".stm");
                }

                eigliste.speichernListe(gefundenEDatei);
                buchungsliste.speichernListe(gefundenBDatei);
                kkliste.speichernListe(gefundenKDatei);

            } catch (IOException ioe) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Datei kann nich gespeichert werden");
                alert.setHeaderText(ioe.getMessage());
                alert.showAndWait();
            }
        } else {
            System.exit(0);
        }

    }
    public void setMController(MenuController mc)
    {
        this.mcontroller = mc;
    }

    /**
     * Methode liefert die eingelesenen Daten der Datei in der Eigentümerliste
     * @param file File
     * @return Eigentuemerliste
     */
    public Eigentuemerliste ladenEDatei(File file) {
        try { // wenn eine Datei gewählt wurde
            if (file != null) // Datei einlesen           
            {
                eigliste.auslesenDatei(file);
            }
        } catch (IOException ioe) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Einlesefehler");
            alert.setHeaderText(ioe.getMessage());
            alert.showAndWait();
            System.exit(0);
        } catch (ClassNotFoundException cnfe) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Datei nicht gefunden");
            alert.setHeaderText(cnfe.getMessage());
            alert.showAndWait();
        }
        return eigliste;
    }

    /**
     * Methode liefert die eingelesenen Daten der Datei in der Kostenkontenliste     
     * @param file File
     * @return Kostenkontenliste
     */
    public Kostenkontenliste ladenKDatei(File file) {
        try { // wenn eine Datei gewählt wurde
            if (file != null) // Datei einlesen           
            {
                kkliste.auslesenDatei(file);
            }
        } catch (IOException ioe) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Einlesefehler Kostenkonten-Datei");
            alert.setHeaderText(ioe.getMessage());
            alert.showAndWait();
            System.exit(0);
        } catch (ClassNotFoundException cnfe) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Datei nicht gefunden");
            alert.setHeaderText(cnfe.getMessage());
            alert.showAndWait();
        }
        return kkliste;
    }

    /**
     * Methode liefert die eingelesenen Daten der Datei in der Buchungsliste     
     * @param file File
     * @return Buchungsliste
     */
    public Buchungsliste ladenBDatei(File file) {
        try { // wenn eine Datei gewählt wurde
            if (file != null) // Datei einlesen           
            {
                buchungsliste.auslesenDatei(file);
            }
        } catch (IOException ioe) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Einlesefehler");
            alert.setHeaderText(ioe.getMessage());
            alert.showAndWait();
            System.exit(0);
        } catch (ClassNotFoundException cnfe) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Datei nicht gefunden");
            alert.setHeaderText(cnfe.getMessage());
            alert.showAndWait();
        }
        return buchungsliste;
    }

    /**
     * Methode sucht nach einer Datei und gibt Dateipfad zurück     
     * @param dir File
     * @param find String
     * @return File
     */
    public File searchFile(File dir, String find) {
        File matches = null;
        File[] files = dir.listFiles();

        String temp = "";
        if (files != null) {
            for (int i = 0; i < files.length; i++) {
                temp = files[i].getName();
                //if (files[i].getName().equalsIgnoreCase(find)) { 
                if (temp.contains(find)) {
                    matches = files[i];
                    System.out.println(files[i].getName());
                    break;

                }

            }
        }

        return matches;
    }

    /**
     * Methode liefert Ordnerpfad ausgewälten Verzeichnises     
     * @return selectedDirectory File
     */
    public File getDatei() {
        dateiPath = new File(System.getProperty("user.dir"));
        DirectoryChooser fc = new DirectoryChooser();
        fc.setInitialDirectory(dateiPath);

        File selectedDirectory
                = fc.showDialog(this.getFenster());

        if (selectedDirectory == null) {
            System.out.println("No Directory selected");
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Ordner nicht gewählt");
            alert.setHeaderText("Bitte wählen Sie einen Verzeichnis: ");
            alert.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);
            Optional erg = alert.showAndWait();
            if (erg.get() == ButtonType.YES) {
                getDatei();

            }
            if (erg.get() == ButtonType.NO) {
                System.exit(0);
            }
            alert.showAndWait();
        } else {
            System.out.println(selectedDirectory.getAbsolutePath());
        }

        return selectedDirectory;
    }

    /**
     * Methode setzt die Änderung
     * @param bool boolean
     */
    public void setAenderung(boolean bool) {
        this.aenderung = bool;
    }

    /**
     * Methode liefert die Änderung     
     * @return aenderung
     */
    public boolean getAenderung() {
        return aenderung;
    }
    
    public void datenLaden()
    {
        
        File ordner = this.getDatei();
        datei = ordner.getAbsoluteFile();

        // ***** Eigentümerliste ****
        gefundenEDatei = this.searchFile(datei, EDATEI);        
        eigliste = ladenEDatei(gefundenEDatei);
        mcontroller.setEigentListe(eigliste);
        // ***** Kostenkontenliste *****
        gefundenKDatei = this.searchFile(datei, KDATEI);
        kkliste = ladenKDatei(gefundenKDatei);
        mcontroller.setKostenliste(kkliste);
        // ***** Buchungsliste *****
        gefundenBDatei = this.searchFile(datei, BDATEI);
        buchungsliste = ladenBDatei(gefundenBDatei);
        mcontroller.setBuchungsListe(buchungsliste);

    }

}
