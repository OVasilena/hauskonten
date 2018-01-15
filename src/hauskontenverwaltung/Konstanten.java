/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hauskontenverwaltung;

import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;

/**
 * Das Interface legt eine Reihe von Konstanten und Formaten
 * fest, welche im Projekt Hauskontenverwaltung anzuwenden sind.
 * @author Olga Podlubnaja
 */
public interface Konstanten {
    // Zustandsarten
    final int LEER = 0;
    final int NEU = 1;
    final int BASIS = 2;
    final int AENDERN = 3;
    // Datei-Namen
    final String EDATEI = "eigentuemer";
    final String KDATEI = "kosten";
    final String BDATEI = "buchung";
    
    final DateTimeFormatter DTF = 
                    DateTimeFormatter.ofPattern("dd.MM.yyyy");
    final DecimalFormat DF = new DecimalFormat("#,##0.00");  
}
