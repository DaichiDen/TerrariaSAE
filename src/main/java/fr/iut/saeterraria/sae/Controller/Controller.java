package fr.iut.saeterraria.sae.Controller;

import fr.iut.saeterraria.sae.Modele.Jeu;

import fr.iut.saeterraria.sae.Modele.Map.Map;
import fr.iut.saeterraria.sae.Vue.SpriteJoueur;
import fr.iut.saeterraria.sae.Vue.vueInventaire;
import javafx.animation.AnimationTimer;

import fr.iut.saeterraria.sae.Vue.Fond;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button


import javafx.scene.input.KeyCode;

import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;





import java.net.URL;
import java.util.ResourceBundle;

import static javafx.application.Platform.exit;

public class Controller implements Initializable {

    @FXML
    private Pane menu;
    @FXML
    private Button start;
    @FXML
    private Button quit;
    @FXML
    private TilePane fond;
    @FXML
    private Pane screen;
    @FXML
    private Button mapButton;
    @FXML
    private Button openInventaire;
    @FXML
    private Button quitterInventaire;
    @FXML
    private Pane screenInventaire;
    @FXML
    private GridPane inventaire;
    @FXML
    private HBox hotbar;

    private Jeu jeu;
    private Fond scene;
    private vueInventaire inventaireVue;
    private SpriteJoueur vuejoueur;





    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        scene = new Fond(fond); // Initialise le fond (décor du jeu)

        jeu = new Jeu("Joueur");
        Clavier controlleurJoueur = new Clavier(jeu);
        inventaireVue = new vueInventaire(quitterInventaire,screenInventaire,jeu.getJoueur(),inventaire,screen);
        Platform.runLater(() -> fond.requestFocus()); // Permet de faire fonctionner la méthode mouvement
        SpriteJoueur vuejoueur = new SpriteJoueur(jeu, screen); // Appelle la classe de la vue pour l'initialiser
        fond.addEventHandler(KeyEvent.ANY, c -> controlleurJoueur.handle(c));









        AnimationTimer timer = new AnimationTimer() { // classe qui sert pour faire des animations fluides car dans sa méthode handle ,ce qui est écrit dedans est effectué toutes les frames
            private long lastUpdate = 0;
            private final long frameInterval = 16_666_666; // Conversion nano secondes en secondes = 60 FPS
            @Override
            public void handle(long now) {
                if (now - lastUpdate >= frameInterval) {

                    jeu.getJoueur().mettreAJour(jeu.getCarte());
                    lastUpdate = now;
                }
            }
        };
        timer.start();  // frameInterval est l'intervalle entre 2 màj graphiques
        // lastUpdate stocke le temps de la dernière màj graphique enregistré
        // La méthode vérifie si entre la dernière update et maintenant il s'est passé 1/60 ème de seconde ( 1 frame), si oui on actualise graphiquement
        scene.afficherCarte(); // Affiche le décor dans la vue

    }

    @FXML
    public void ouvrirInventaire() {
        openInventaire.setVisible(false);
        jeu.getJoueur().setMarcheDroite(false);
        jeu.getJoueur().setMarcheGauche(false);
        screenInventaire.setVisible(true);
    }


    @FXML

    public void exitInventaire(){
        screenInventaire.setVisible(false);
        openInventaire.setVisible(true);
        Platform.runLater(() -> fond.requestFocus());
    }




    @FXML
    public void startGame(){
        start.setVisible(false);
        quit.setVisible(false);
        start.setDisable(true);
        quit.setDisable(true);
        screen.setVisible(true);
        Platform.runLater(() -> fond.requestFocus()); // Permet de faire fonctionner la méthode mouvement
    }
    @FXML
    public void rageQuit(){ exit();}


}
