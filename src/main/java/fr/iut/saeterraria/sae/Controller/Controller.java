package fr.iut.saeterraria.sae.Controller;

import fr.iut.saeterraria.sae.Modele.Jeu;

import fr.iut.saeterraria.sae.Vue.SpriteJoueur;
import javafx.animation.AnimationTimer;

import fr.iut.saeterraria.sae.Vue.Fond;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private TilePane fond;
    @FXML
    private Pane screen;
    @FXML
    private Button mapButton;
    @FXML
    private Fond scene;

    private Jeu jeu;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        scene = new Fond(fond); // Initialise le fond (décor du jeu)
        scene.initialiseTile(); // Associe les images des blocs au décor
        jeu = new Jeu("Joueur", 1024, 1024);
        Platform.runLater(() -> fond.requestFocus()); // Permet de faire fonctionner la méthode mouvement
        SpriteJoueur vuejoueur = new SpriteJoueur(jeu, screen); // Appelle la classe de la vue pour l'initialiser
        vuejoueur.creerSpriteJoueur(jeu.getJoueur()); // Appelle la méthode de la vue pour créer le visuel du joueur, et le lier au pane
        fond.setOnKeyPressed(Insert -> vuejoueur.mouvement(Insert));
        fond.setOnKeyReleased(Insert -> vuejoueur.stopmouvement(Insert));
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
        scene.afficherCarte(); // Affiche le décor dans la vue
    }



}
