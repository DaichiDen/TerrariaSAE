package fr.iut.saeterraria.sae.Controller;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.*;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private Pane fond;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Platform.runLater(()->fond.requestFocus()); // Permet de faire fonctionner la méthode mouvement
        fond.setOnKeyPressed(Insert -> mouvement(Insert));
    }


    public void mouvement(KeyEvent event) {
        switch (event.getCode()){
            case UP: // Saute
                System.out.println("Saute");
                break;
            case DOWN: // Descend d'une plateforme
                System.out.println("Descend");
                break;
            case LEFT: // Déplace à gauche
                System.out.println("Gauche");
                break;
            case RIGHT: // Déplace à droite
                System.out.println("Droite");
                break;
            case SPACE: // Saute
                System.out.println("Saute");
                break;
            case Q: // Déplace à gauche
                System.out.println("Gauche");
                break;
            case D: // Déplace à droite
                System.out.println("Droite");
                break;
            case S: // Descend d'une plateforme
                System.out.println("Descend");
                break;
            default:
                break;
        }
    }

    private void creerSpriteJoueur(String nom){
    }



    private void afficherSpriteJoueur(){
    }
}