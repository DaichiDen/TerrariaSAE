package fr.iut.saeterraria.sae.Controller;

import fr.iut.saeterraria.sae.Modele.Jeu;

import fr.iut.saeterraria.sae.Modele.Objets.Etablis.BlocConstruction;
import fr.iut.saeterraria.sae.Modele.Personnages.*;
import fr.iut.saeterraria.sae.Vue.*;
import javafx.animation.AnimationTimer;

import javafx.animation.PauseTransition;
import javafx.application.Platform;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;


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
import java.util.ResourceBundle;

public class Controller implements Initializable {

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
    private ImageView imageaccueil;
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
    private ScrollPane craftSansBlocConstruction;
    @FXML
    private ScrollPane craftEtabli;
    @FXML
    private ScrollPane craftForge;
    @FXML
    private VBox caseRecetteSansBloc;
    @FXML
    private VBox caseRecetteEtabli;
    @FXML
    private VBox caseRecetteForge;
    @FXML
    private Pane screenPrincipal;
    @FXML
    private ScrollPane four;
    @FXML
    private VBox caseRecetteFour;
    @FXML
    private TilePane background;
    @FXML
    private Pane opaciteBackground;
    @FXML
    private Spinner<Integer> ligneCase1;
    @FXML
    private Spinner<Integer> colonneCase1;
    @FXML
    private Spinner<Integer> ligneCase2;
    @FXML
    private Spinner<Integer> colonneCase2;


    private Jeu jeu;
    public Fond scene;
    private VueInventaire inventaireVue;
    private VueHotbar hotBarVue;
    private VueProjectile projectileVue;
    private SpriteJoueur vuejoueur;
    private VueEnnemi vueEnnemi;
    private VueSon BiblioSon = new VueSon();
    private VueCraft vueCraft;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        jeu = new Jeu("Nom");
        zoneNom.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode() == KeyCode.ENTER) {
                confirmerNom();
            }
        });

        scene = new Fond(fond, jeu);// Initialise le fond (décor du jeu)

        projectileVue = new VueProjectile(jeu, screen,fond, scene);
        jeu.getListe_projectilesObservable().addListener(new ObsProjectile(jeu, screen, projectileVue));

        vueEnnemi = new VueEnnemi(jeu, screen);
        jeu.getMobs().addListener(new ObsEnnemi(jeu, screen));
        initialisationMobs();

        imageaccueil.setFitWidth(menu.getWidth());
        imageaccueil.fitWidthProperty().bind(imagebloc_accueil.widthProperty());
        imageaccueil.fitHeightProperty().bind(imagebloc_accueil.widthProperty());
        SpriteVie barre = new SpriteVie(Vie, jeu);

        Clavier controlleurJoueur = new Clavier(jeu,screenInventaire,quitterInventaire,openInventaire,fond,hotBar, screenPrincipal);

        Souris controlleurSouris = new Souris(jeu,scene,jeu.getCarte(),fond,screenInventaire,craftSansBlocConstruction,craftEtabli,craftForge,four);

        inventaireVue = new VueInventaire(quitterInventaire, screenInventaire, jeu.getJoueur(), inventaire, screen);

        hotBarVue = new VueHotbar(jeu,hotBar);
        Platform.runLater(() -> fond.requestFocus()); // Permet de faire fonctionner la méthode mouvement

        vuejoueur = new SpriteJoueur(jeu, screen, background,opaciteBackground); // Appelle la classe de la vue pour l'initialiser
        vuejoueur.mettreAJourSpriteJoueur(jeu.getJoueur());
        vueCraft = new VueCraft(craftSansBlocConstruction,craftEtabli,craftForge,caseRecetteSansBloc,caseRecetteEtabli,caseRecetteForge,
                ((BlocConstruction) jeu.getItems().get(11)).getListeRecette(), ((BlocConstruction) jeu.getItems().get(12)).getListeRecette(),
                ((BlocConstruction) jeu.getItems().get(13)).getListeRecette(),jeu.getItems(), caseRecetteFour, ((BlocConstruction) jeu.getItems().get(14)).getListeRecette());

        screenPrincipal.addEventHandler(KeyEvent.ANY, c -> controlleurJoueur.handle(c));
        screen.addEventHandler(MouseEvent.MOUSE_CLICKED, s -> controlleurSouris.handle(s));

        ObsJoueur obsJ = new ObsJoueur(jeu, vuejoueur, controlleurJoueur);

        jeu.getJoueur().getXMaxProperty().addListener(new ObsMapX(jeu, scene));
        jeu.getJoueur().getYMaxProperty().addListener(new ObsMapY(jeu, scene));

        jeu.getJoueur().yProperty().addListener(obsJ);

        jeu.getJoueur().getBarreVie().vieProperty().addListener((obs, oldVal, newVal) -> {
            barre.mettreAJourSpriteVie(jeu.getJoueur());
        });


        for (int i = 0; i < jeu.getJoueur().getInventaire().getInventaireJoueur().length; i++) {
            for (int j = 0; j < jeu.getJoueur().getInventaire().getInventaireJoueur()[i].length; j++) {
                jeu.getJoueur().getInventaire().getInventaireJoueur()[i][j].changementProperty().addListener(new ListenerInventaire(inventaireVue, hotBarVue, i, j));
            }
        }

        for (int i = 0; i < caseRecetteSansBloc.getChildren().size(); i++) {
            int finalI = i;
            caseRecetteSansBloc.getChildren().get(i).setOnMouseClicked(mouseEvent -> {
                controlleurSouris.handleCraft(vueCraft.getCodeObjetLigne(finalI, 0));
            });
        }
        for (int i = 0; i < caseRecetteEtabli.getChildren().size(); i++) {
            int finalI = i;
            caseRecetteEtabli.getChildren().get(i).setOnMouseClicked(mouseEvent -> {
                controlleurSouris.handleCraft(vueCraft.getCodeObjetLigne(finalI, 1));
            });
        }
        for (int i = 0; i < caseRecetteForge.getChildren().size(); i++) {
            int finalI = i;
            caseRecetteForge.getChildren().get(i).setOnMouseClicked(mouseEvent -> {
                controlleurSouris.handleCraft(vueCraft.getCodeObjetLigne(finalI, 2));
            });
        }
        //BiblioSon.play(1);
        for(int i=0; i<caseRecetteFour.getChildren().size(); i++) {
            int finalI = i;
            caseRecetteFour.getChildren().get(i).setOnMouseClicked(mouseEvent -> {
                controlleurSouris.handleCraft(vueCraft.getCodeObjetLigne(finalI,3));
            });
        }

        setupSpinner(ligneCase1, 1, 6, 1);
        setupSpinner(colonneCase1, 1, 7, 1);
        setupSpinner(ligneCase2, 1, 6, 1);
        setupSpinner(colonneCase2, 1, 7, 1);


        // BiblioSon.play(1);
        AnimationTimer timer = new AnimationTimer() { // classe qui sert pour faire des animations fluides car dans sa méthode handle ,ce qui est écrit dedans est effectué toutes les frames
            private long lastUpdate = 0;
            private final long frameInterval = 16_666_666;
            // Conversion nano secondes en secondes = 60 FPS
            private boolean timeStopActive = false;

            @Override
            public void handle(long now) {
                if (now - lastUpdate >= frameInterval) {
                    if (jeu.getJoueur().isTimeStop()) {
                        jeu.getJoueur().mettreAJour();

                        for(int i=0;i<jeu.getListe_projectiles().size();i++){
                            if(jeu.getListe_projectiles().get(i).getType().equals("balle")){
                                jeu.màjProjectiles();
                            }
                        }

                        if (!timeStopActive) {
                            timeStopActive = true;
                            PauseTransition delay = new PauseTransition(Duration.seconds(5));
                            delay.setOnFinished(event -> {
                                jeu.getJoueur().setTimeStop(false);
                                timeStopActive = false;
                            });
                            delay.play();
                        }
                    } else {
                        jeu.getJoueur().mettreAJour();

                        for (int i = 0; i < jeu.getMobs().size(); i++) {
                            jeu.getMobs().get(i).mettreAJour();
                        }

                        jeu.màjProjectiles();
                    }

                    lastUpdate = now;

                    if (!jeu.getJoueur().getEstVivant()) {
                        PauseTransition delay = new PauseTransition(Duration.seconds(0.5));
                        delay.setOnFinished(event -> {
                            vuejoueur.mettreAJourSpriteJoueur(jeu.getJoueur());
                            initialisationJoueur();
                            initialisationMobs();
                        });
                        delay.play();
                    }
                }
            }
        };
        timer.start();
    }



    @FXML
    public void ouvrirInventaire() {
        screenInventaire.toFront();
        jeu.getJoueur().ajouterItem(jeu.getItems().get(72),1);
        jeu.getJoueur().ajouterItem(jeu.getItems().get(78),1);
        jeu.getJoueur().ajouterItem(jeu.getItems().get(77),64);
        jeu.getJoueur().ajouterItem(jeu.getItems().get(51),1);
        jeu.getJoueur().ajouterItem(jeu.getItems().get(54),1);
        jeu.getJoueur().ajouterItem(jeu.getItems().get(51),1);
        jeu.getJoueur().ajouterItem(jeu.getItems().get(79),1);
        jeu.getJoueur().ajouterItem(jeu.getItems().get(80),64);




    }

    @FXML
    public void exitInventaire() {
        screenInventaire.toBack();
        Platform.runLater(() -> screenPrincipal.requestFocus());
    }

    @FXML
    public void startGame() {
        boxAccueil.toBack();
        imagebloc_accueil.toBack();
        choixNom.toFront();
    }

    public void confirmerNom() {
        menu.toBack();
        screenPrincipal.toFront();
        jeu.getJoueur().setNom(zoneNom.getText());
        Platform.runLater(() -> fond.requestFocus());
    }

    @FXML
    public void rageQuit() {
        Platform.exit();
    }

    @FXML
    public void activerSwitch(){
        jeu.getJoueur().swapItem(ligneCase1.getValue()-1,colonneCase1.getValue()-1,ligneCase2.getValue()-1,colonneCase2.getValue()-1);
    }

    private void setupSpinner(Spinner<Integer> spinner, int min, int max, int initialValue) {
        spinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(min, max, initialValue));
    }


    public void initialisationMobs(){
        int i = 0;
        while(jeu.getMobs().size() > 0){
            jeu.getMobs().get(i).setEstVivant(false);
            i++;
        }
        Ennemi ogre = new Ogre("Pierre l'ogre vert",50,20,3000,0,0, jeu,4, jeu.getTaille1bloc(), jeu.getTaille1bloc()*2,10,3);
        Ennemi ogre2 = new Ogre("Pierre l'ogre vert pale",50,20,1340,1340,0, jeu,4, jeu.getTaille1bloc(), jeu.getTaille1bloc()*2,10,3);
        Ennemi ogre3 = new Ogre("Pierre l'ogre vert foncé",50,20,4962,1376,0, jeu,4, jeu.getTaille1bloc(), jeu.getTaille1bloc()*2,10,3);
        Ennemi ogre4 = new Ogre("Pierre l'ogre vert clair",50,20,3068,1600,0, jeu,4, jeu.getTaille1bloc(), jeu.getTaille1bloc()*2,10,3);
        Ennemi goblin = new Goblin("Caillou le gobelin vert",20,20,5000,0,0, jeu,2, jeu.getTaille1bloc(), jeu.getTaille1bloc()*2, 10, 8);
        Ennemi goblin2 = new Goblin("Caillou le gobelin vert pale",20,20,1456,1728,0, jeu,2, jeu.getTaille1bloc(), jeu.getTaille1bloc()*2, 10, 8);
        Ennemi goblin3 = new Goblin("Caillou le gobelin vert foncé",20,20,2959,1088,0, jeu,2, jeu.getTaille1bloc(), jeu.getTaille1bloc()*2, 10, 8);
        Ennemi goblin4 = new Goblin("Caillou le gobelin vert clair",20,20,5238,1760,0, jeu,2, jeu.getTaille1bloc(), jeu.getTaille1bloc()*2, 10, 8);
        Ennemi goblin5 = new Goblin("Caillou le gobelin vert émeraude",20,20,4544,1632,0, jeu,2, jeu.getTaille1bloc(), jeu.getTaille1bloc()*2, 10, 8);
        Ennemi mh = new MH("Monsieur Homps", 250, 20, 4500, 0, 5, jeu, 2, jeu.getTaille1bloc(), jeu.getTaille1bloc()*2, 15, 8);
        jeu.addEnnemis(ogre);
        jeu.addEnnemis(ogre2);
        jeu.addEnnemis(ogre3);
        jeu.addEnnemis(ogre4);
        jeu.addMobs(ogre);
        jeu.addMobs(ogre2);
        jeu.addMobs(ogre3);
        jeu.addMobs(ogre4);
        jeu.addEnnemis(goblin);
        jeu.addEnnemis(goblin2);
        jeu.addEnnemis(goblin3);
        jeu.addEnnemis(goblin4);
        jeu.addEnnemis(goblin5);
        jeu.addMobs(goblin);
        jeu.addMobs(goblin2);
        jeu.addMobs(goblin3);
        jeu.addMobs(goblin4);
        jeu.addMobs(goblin5);
        jeu.addEnnemis(mh);
        jeu.addMobs(mh);

    }

    public void initialisationJoueur(){
        jeu.getJoueur().getBarreVie().setVie(jeu.getJoueur().getBarreVie().getVieMax());
        jeu.getJoueur().setEstVivant(true);
        jeu.getJoueur().setX(20*32);
        jeu.getJoueur().setY(14*32);
    }
}


