package hauskontenverwaltung;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 *
 * @author opodlubnaja
 */
public class Hauskontenverwaltung extends Application{
    
    // Objekte der Hauskontenverwaltung
    private Stage fenster;
    private BorderPane root = new BorderPane();
    private Eigentuemerliste eigliste;
    private Kostenkontenliste kkliste;
    private Buchungsliste buchungsliste;
    private File dateiPath = new File(System.getProperty("user.dir"));
    private MenuController mcontroller;
    public FXMLLoader loader;

    /**
     * Konstruktor diefiniert zu verwaltenden Listen
     */    
    public Hauskontenverwaltung()
    {
        
        eigliste = new Eigentuemerliste();
        //kkliste = new Kostenkontenliste();
        buchungsliste = new Buchungsliste();
        
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
    
   
    @Override
    public void start(Stage primaryStage) throws Exception {
        fenster = primaryStage;
        loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("StartPane.fxml"));
        root = loader.load();   
        Scene scene = new Scene(root);
        
        // Fenstereigenschaften
        fenster.setScene(scene);
        fenster.setTitle("Hauskontenverwaltung");
       
        this.mcontroller = loader.getController();        
        mcontroller.setVerwaltung(this);
        
        
        fenster.show();        
    }    
    
    
    
}
