package fr.iut.saeterraria.sae.Controller;

import fr.iut.saeterraria.sae.Modele.Jeu;
import fr.iut.saeterraria.sae.Modele.Personnages.Joueur;

import fr.iut.saeterraria.sae.Vue.Sprite;
import javafx.animation.AnimationTimer;

import fr.iut.saeterraria.sae.Vue.Fond;

import javafx.application.Platform;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private TilePane fond;
    @FXML
    private TilePane joueur;
    @FXML
    private Pane screen;
    @FXML
    private Button mapButton;
    @FXML
    private Button scene1;
    @FXML
    private Button scene2;
    @FXML
    private Fond scene;

    private Jeu jeu;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        scene = new Fond(10, 5, fond);
        initialiseTile();
        jeu = new Jeu("Joueur", 1024, 1024);
        Platform.runLater(() -> fond.requestFocus()); // Permet de faire fonctionner la méthode mouvement
        fond.setOnKeyPressed(Insert -> mouvement(Insert));

        fond.setOnKeyReleased(Insert -> stopmouvement(Insert));
        Sprite vuejoueur = new Sprite(jeu, fond); // Appelle la classe de la vue pour l'initialiser
        vuejoueur.creerSpriteJoueur(jeu.getJoueur()); // Appelle la méthode de la vue pour créer le visuel du joueur, et le lier au fond
        AnimationTimer timer = new AnimationTimer() { // classe qui sert pour faire des animations fluides car dans sa méthode handle ,ce qui est écrit dedans est effectué toutes les frames
            private long lastUpdate = 0;
            private final long frameInterval = 16_666_666; // Conversion nano secondes en secondes = 60 FPS

            @Override
            public void handle(long now) {
                if (now - lastUpdate >= frameInterval) {
                    jeu.getJoueur().mettreAJour();
                    lastUpdate = now;
                }
            }
        };
        timer.start();  // frameInterval est l'intervalle entre 2 màj graphiques
        // lastUpdate stocke le temps de la dernière màj graphique enregistré
        // La méthode vérifie si entre la dernière update et maintenant il s'est passé 1/60 ème de seconde ( 1 frame), si oui on actualise graphiquement
        afficheCarte();
    }


    private void stopmouvement(KeyEvent event) {
        switch (event.getCode()) {
            case D:
                jeu.getJoueur().setMarcheDroite(false);
                break;
            case RIGHT:
                jeu.getJoueur().setMarcheDroite(false);
                break;
            case LEFT:
                jeu.getJoueur().setMarcheGauche(false);
                break;
            case Q:
                jeu.getJoueur().setMarcheGauche(false);
        }
    }


    //public void afficherMap()
    public void mouvement(KeyEvent event) {
        switch (event.getCode()) {
            case UP: // Saute
                jeu.getJoueur().sauter();
                System.out.println("Saute");
                break;
            case DOWN: // Descend d'une plateforme
                jeu.getJoueur().descendre();
                System.out.println("Descend");
                break;
            case LEFT: // Déplace à gauche
                jeu.getJoueur().setMarcheGauche(true);
                System.out.println("Gauche");
                break;
            case RIGHT: // Déplace à droite
                jeu.getJoueur().setMarcheDroite(true);
                System.out.println("Droite");
                break;
            case SPACE: // Saute
                jeu.getJoueur().sauter();
                System.out.println("Saute");
                break;
            case Q: // Déplace à gauche
                jeu.getJoueur().setMarcheGauche(true);
                System.out.println("Gauche");
                break;
            case D: // Déplace à droite
                jeu.getJoueur().setMarcheDroite(true);
                System.out.println("Droite");
                break;
            case S: // Descend d'une plateforme
                jeu.getJoueur().descendre();
                System.out.println("Descend");
                break;
            default:
                break;
        }
    }


    private void initialiseTile() {
        scene.ajoutTile("/Tiles/Fond_noir.png");
        scene.ajoutTile("/Tiles/Dirt_1.png");
        scene.ajoutTile("/Tiles/Dirt_2.png");
        scene.ajoutTile("/Tiles/Ciel.png");
    }

    private void afficheCarte() {
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 32; j++) {
                scene.afficherCarte(i, j);
            }
        }
    }
}
