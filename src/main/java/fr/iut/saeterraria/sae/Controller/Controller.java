package fr.iut.saeterraria.sae.Controller;

import fr.iut.saeterraria.sae.Modele.Jeu;

import fr.iut.saeterraria.sae.Vue.*;
import javafx.animation.AnimationTimer;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;


import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.scene.input.KeyEvent;

import javafx.scene.layout.*;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import static javafx.application.Platform.exit;

public class Controller implements Initializable{

    @FXML
    private AnchorPane menu;
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
    private AnchorPane screenInventaire;
    @FXML
    private GridPane inventaire;
    @FXML
    private HBox hotbar;
    @FXML
    private Pane Vie;
    @FXML
    private BorderPane principal;

    @FXML
    private AnchorPane death;

    @FXML
    private ImageView imagefond;

    @FXML
    private ImageView imageaccueil;

    @FXML
    private StackPane imagebloc_death;

    @FXML
    private StackPane imagebloc_accueil;

    @FXML
    private ImageView fondinventaire;

    private Jeu jeu;
    private Fond scene;
    private vueInventaire inventaireVue;
    private SpriteJoueur vuejoueur;
    private VueSon BiblioSon = new VueSon();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        scene = new Fond(fond); // Initialise le fond (décor du jeu)

        imagefond.fitWidthProperty().bind(imagebloc_death.widthProperty());
        imagefond.fitHeightProperty().bind(imagebloc_death.widthProperty());

        imageaccueil.setFitWidth(menu.getWidth());
        imageaccueil.fitWidthProperty().bind(imagebloc_accueil.widthProperty());
        imageaccueil.fitHeightProperty().bind(imagebloc_accueil.widthProperty());

        jeu = new Jeu("Joueur");//Mettre un nom dynamique?
        SpriteVie barre = new SpriteVie(Vie, jeu);
        Clavier controlleurJoueur = new Clavier(jeu,screenInventaire,quitterInventaire,openInventaire,fond);
        inventaireVue = new vueInventaire(quitterInventaire,screenInventaire,jeu.getJoueur(),inventaire,screen);
        Platform.runLater(() -> fond.requestFocus()); // Permet de faire fonctionner la méthode mouvement
        vuejoueur = new SpriteJoueur(jeu, screen); // Appelle la classe de la vue pour l'initialiser

        fond.addEventHandler(KeyEvent.ANY, c -> controlleurJoueur.handle(c));

        BiblioSon.play(1);
        AnimationTimer timer = new AnimationTimer() { // classe qui sert pour faire des animations fluides car dans sa méthode handle ,ce qui est écrit dedans est effectué toutes les frames
            private long lastUpdate = 0;
            private final long frameInterval = 16_666_666; // Conversion nano secondes en secondes = 60 FPS

            @Override
            public void handle(long now) {
                if (now - lastUpdate >= frameInterval) {

                    controlleurJoueur.update();
                    jeu.getJoueur().mettreAJour(jeu.getCarte());
                    barre.mettreAJourSpriteVie(jeu.getJoueur());
                    vuejoueur.mettreAJourSpriteJoueur(jeu.getJoueur());
                    lastUpdate = now;

                    if (!jeu.estVivant(jeu.getJoueur())) {
                        // Le joueur est mort, démarrer le délai de 10 secondes avant rageQuit
                        PauseTransition delay = new PauseTransition(Duration.seconds(0.70)); // Délai de 10 secondes
                        delay.setOnFinished(event ->{
                            principal.setVisible(false);
                            death.setVisible(true);
                            BiblioSon.stop(1);
                            BiblioSon.play(4);

                        }); // Action à faire après le délai
                        delay.play();
                        // Démarre le délai

                        PauseTransition delay2 = new PauseTransition(Duration.seconds(16));
                        delay2.setOnFinished(event ->{
                            BiblioSon.stop(4);
                            rageQuit();
                        });
                        delay2.play();
                        // Démarre le délai
                    }
                }
            }
        };
        timer.start(); // frameInterval est l'intervalle entre 2 màj graphiques
        // lastUpdate stocke le temps de la dernière màj graphique enregistré
        // La méthode vérifie si entre la dernière update et maintenant il s'est passé 1/60 ème de seconde ( 1 frame), si oui on actualise graphiquement
        scene.afficherCarte();// Affiche le décor dans la vue

    }

    @FXML
    public void ouvrirInventaire() {
        openInventaire.setVisible(false);
        screenInventaire.setVisible(true);
        Platform.runLater(() -> fond.requestFocus());
    }
    @FXML
    public void exitInventaire(){
        screenInventaire.setVisible(false);
        openInventaire.setVisible(true);
        Platform.runLater(() -> fond.requestFocus());
    }


    @FXML
    public void startGame(){
        menu.setVisible(false);
        principal.setVisible(true);
        Platform.runLater(() -> fond.requestFocus()); // Permet de faire fonctionner la méthode mouvement
    }
    @FXML
    public void rageQuit(){ exit();}


}
