package fr.iut.saeterraria.sae.Controller;

import fr.iut.saeterraria.sae.Modele.Jeu;

import fr.iut.saeterraria.sae.Modele.Personnages.Ennemi;
import fr.iut.saeterraria.sae.Modele.Personnages.Entite;
import fr.iut.saeterraria.sae.Vue.*;
import javafx.animation.AnimationTimer;

import javafx.animation.PauseTransition;
import javafx.application.Platform;

import javafx.beans.value.ChangeListener;

import javafx.beans.InvalidationListener;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ListChangeListener;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;


import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.util.Duration;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

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
    private GridPane hotBar;
    @FXML
    private VBox choixNom;
    @FXML
    private VBox boxAccueil;
    @FXML
    private Label phraseNom;
    @FXML
    private TextField zoneNom;
    @FXML
    ScrollPane craftSansBlocConstruction;

    private Jeu jeu;
    public Fond scene;
    private VueInventaire inventaireVue;
    private VueHotbar hotBarVue;
    private SpriteJoueur vuejoueur;
    private SpriteMob vueEnnemi;
    private VueSon BiblioSon = new VueSon();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        jeu = new Jeu("Nom");
        zoneNom.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode() == KeyCode.ENTER) {
                confirmerNom();
        }});
        scene = new Fond(fond,jeu.getCarte());// Initialise le fond (décor du jeu)
        vueEnnemi = new SpriteMob(jeu, screen,"Pierre");

        jeu.getMobs().addListener(new ObsEnnemi(jeu, screen));

        Ennemi ennemiCaca = new Ennemi("Pierre",20,20,1000,0,0,10,jeu.getCarte(), jeu);
        jeu.addEnnemis(ennemiCaca);
        jeu.addMobs(ennemiCaca);

        imagefond.fitWidthProperty().bind(imagebloc_death.widthProperty());
        imagefond.fitHeightProperty().bind(imagebloc_death.widthProperty());

        imageaccueil.setFitWidth(menu.getWidth());
        imageaccueil.fitWidthProperty().bind(imagebloc_accueil.widthProperty());
        imageaccueil.fitHeightProperty().bind(imagebloc_accueil.widthProperty());

        Platform.runLater(() -> fond.requestFocus());
        SpriteVie barre = new SpriteVie(Vie, jeu);

        Clavier controlleurJoueur = new Clavier(jeu,screenInventaire,quitterInventaire,openInventaire,fond,hotBar);

        Souris controlleurSouris = new Souris(jeu,scene,jeu.getCarte(),fond);

        inventaireVue = new VueInventaire(quitterInventaire,screenInventaire,jeu.getJoueur(),inventaire,screen);
        hotBarVue = new VueHotbar(jeu,hotBar);
        Platform.runLater(() -> fond.requestFocus()); // Permet de faire fonctionner la méthode mouvement

        vuejoueur = new SpriteJoueur(jeu, screen); // Appelle la classe de la vue pour l'initialiser
        vuejoueur.mettreAJourSpriteJoueur(jeu.getJoueur());

        fond.addEventHandler(KeyEvent.ANY, c -> controlleurJoueur.handle(c));
        screen.addEventHandler(MouseEvent.MOUSE_CLICKED, s -> controlleurSouris.handle(s));

        ObsJoueur obsJ = new ObsJoueur(jeu,vuejoueur, controlleurJoueur);
        jeu.getJoueur().yProperty().addListener(obsJ);

        jeu.getJoueur().getBarreVie().vieProperty().addListener((obs, oldVal, newVal) -> {
            barre.mettreAJourSpriteVie(jeu.getJoueur());
        });


        for (int i=0; i<jeu.getJoueur().getInventaire().getInventaireJoueur().length; i++) {
            for(int j=0; j<jeu.getJoueur().getInventaire().getInventaireJoueur()[i].length; j++) {
                jeu.getJoueur().getInventaire().getInventaireJoueur()[i][j].changementProperty().addListener(new ListenerInventaire(inventaireVue,hotBarVue,i,j));
            }
        }

        BiblioSon.play(1);
        AnimationTimer timer = new AnimationTimer() { // classe qui sert pour faire des animations fluides car dans sa méthode handle ,ce qui est écrit dedans est effectué toutes les frames
            private long lastUpdate = 0;
            private final long frameInterval = 16_666_666; // Conversion nano secondes en secondes = 60 FPS

            @Override
            public void handle(long now) {
                if (now - lastUpdate >= frameInterval) {

                    jeu.getJoueur().mettreAJour(); //Laisser ici ? on tombe tt le temps ect, donc dans tt les cas c a chaque frame non ?
                    for(int i = 0; i < jeu.getMobs().size(); i++){
                        jeu.getMobs().get(i).mettreAJour();
                    }

                    lastUpdate = now;


                    if (!jeu.getJoueur().estVivant()) {
                        PauseTransition delay = new PauseTransition(Duration.seconds(0.5));
                        delay.setOnFinished(event ->{






                            vuejoueur.mettreAJourSpriteJoueur(jeu.getJoueur());

                        }); // Action à faire après le délai
                        delay.play();


                        // Démarre le délai
                    }
                }
            }
        };
        timer.start(); // frameInterval est l'intervalle entre 2 màj graphiques
        // lastUpdate stocke le temps de la dernière màj graphique enregistré
        // La méthode vérifie si entre la dernière update et maintenant il s'est passé 1/60 ème de seconde ( 1 frame), si oui on actualise graphiquement


    }

    @FXML
    public void ouvrirInventaire() {
        screenInventaire.toFront();
        jeu.getJoueur().craftItem(jeu.getItems().get(52));
    }
    @FXML
    public void exitInventaire(){
        screenInventaire.toBack();
        Platform.runLater(() -> fond.requestFocus());
    }

    @FXML
    public void startGame(){
        boxAccueil.toBack();
        imagebloc_accueil.toBack();
        choixNom.toFront();
    }

    public void confirmerNom(){
        menu.toBack();
        principal.toFront();
        jeu.getJoueur().setNom(zoneNom.getText());
        Platform.runLater(() -> fond.requestFocus());
    }
    @FXML
    public void rageQuit(){ Platform.exit();}

}
//TODO faire des listener pour chaque vue qui fait une màj
