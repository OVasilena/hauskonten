package hauskontenverwaltung;

import java.time.LocalDate;
import java.time.Month;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 *
 * @author opodlubnaja
 */
public class Hauskontenverwaltung extends Application{
    
    // Objekte der Hauskontenverwaltung
    private static Stage fenster;
    private static BorderPane root = new BorderPane();
    private Eigentuemerliste eigliste;
    private Kostenkontenliste kkliste;
    private Buchungsliste buchungsliste;

    /**
     * @param args the command line arguments
     */
    
    public Hauskontenverwaltung()
    {
        eigliste = new Eigentuemerliste();
        kkliste = new Kostenkontenliste();
       buchungsliste = new Buchungsliste();
    }
    
    public static void main(String[] args) {
        launch();
        new Hauskontenverwaltung();
        
    }
    
    public static BorderPane getRoot() {
        return root;
    }
    
    public static Stage getFenster() {
        return fenster;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        fenster = primaryStage;
        
        // loading MenuBar, StartPane FXML              
        MenuBar menuBar = FXMLLoader.load(getClass().getResource("Menubar.fxml"));
        AnchorPane startPane = FXMLLoader.load(getClass().getResource("StartPane.fxml"));
        
        // Konstruieren unsere Scene         
        root.setTop(menuBar);
        root.setCenter(startPane);        
        Scene scene = new Scene(root);
        
        // Fenstereigenschaften
        fenster.setScene(scene);
        fenster.setTitle("Hauskontenverwaltung");
        fenster.show();        
    }    
    
    
}
