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
        Jeu.getUniqueJeu();
        zoneNom.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode() == KeyCode.ENTER) {
                confirmerNom();
            }
        });

        scene = new Fond(fond);// Initialise le fond (décor du jeu)

        projectileVue = new VueProjectile(screen,fond, scene);
        Jeu.getUniqueJeu().getListe_projectilesObservable().addListener(new ObsProjectile(screen, projectileVue));

        vueEnnemi = new VueEnnemi(screen);
        Jeu.getUniqueJeu().getMobs().addListener(new ObsEnnemi(screen));
        initialisationMobs();

        imageaccueil.setFitWidth(menu.getWidth());
        imageaccueil.fitWidthProperty().bind(imagebloc_accueil.widthProperty());
        imageaccueil.fitHeightProperty().bind(imagebloc_accueil.widthProperty());
        SpriteVie barre = new SpriteVie(Vie);

        Clavier controlleurJoueur = new Clavier(screenInventaire,quitterInventaire,openInventaire,fond,hotBar, screenPrincipal);

        Souris controlleurSouris = new Souris(scene,Jeu.getUniqueJeu().getCarte(),fond,screenInventaire,craftSansBlocConstruction,craftEtabli,craftForge,four);

        inventaireVue = new VueInventaire(quitterInventaire, screenInventaire, inventaire, screen);

        hotBarVue = new VueHotbar(hotBar);
        Platform.runLater(() -> fond.requestFocus()); // Permet de faire fonctionner la méthode mouvement

        vuejoueur = new SpriteJoueur(screen, background,opaciteBackground); // Appelle la classe de la vue pour l'initialiser
        vuejoueur.mettreAJourSpriteJoueur(Joueur.getUniqueJoueur());
        vueCraft = new VueCraft(craftSansBlocConstruction,craftEtabli,craftForge,caseRecetteSansBloc,caseRecetteEtabli,caseRecetteForge,
                ((BlocConstruction) Jeu.getUniqueJeu().getItems().get(11)).getListeRecette(), ((BlocConstruction) Jeu.getUniqueJeu().getItems().get(12)).getListeRecette(),
                ((BlocConstruction) Jeu.getUniqueJeu().getItems().get(13)).getListeRecette(),Jeu.getUniqueJeu().getItems(), caseRecetteFour, ((BlocConstruction) Jeu.getUniqueJeu().getItems().get(14)).getListeRecette());

        screenPrincipal.addEventHandler(KeyEvent.ANY, c -> controlleurJoueur.handle(c));
        screen.addEventHandler(MouseEvent.MOUSE_CLICKED, s -> controlleurSouris.handle(s));

        ObsJoueur obsJ = new ObsJoueur(vuejoueur,controlleurJoueur);

        Joueur.getUniqueJoueur().getXMaxProperty().addListener(new ObsMapX(scene));
        Joueur.getUniqueJoueur().getYMaxProperty().addListener(new ObsMapY(scene));

        Joueur.getUniqueJoueur().yProperty().addListener(obsJ);

        Joueur.getUniqueJoueur().getBarreVie().vieProperty().addListener((obs, oldVal, newVal) -> {
            barre.mettreAJourSpriteVie(Joueur.getUniqueJoueur());
        });


        for (int i = 0; i < Joueur.getUniqueJoueur().getInventaire().getInventaireJoueur().size(); i++) {
            Joueur.getUniqueJoueur().getInventaire().getInventaireJoueur().get(i).changementProperty().addListener(new ListenerInventaire(inventaireVue, hotBarVue, Joueur.getUniqueJoueur().getInventaire().getInventaireJoueur().get(i).getLigne(), Joueur.getUniqueJoueur().getInventaire().getInventaireJoueur().get(i).getColonne()));
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

        setupSpinner(colonneCase1, 1, 6, 1);
        setupSpinner(ligneCase1, 1, 7, 1);
        setupSpinner(colonneCase2, 1, 6, 1);
        setupSpinner(ligneCase2, 1, 7, 1);


        // BiblioSon.play(1);
        AnimationTimer timer = new AnimationTimer() { // classe qui sert pour faire des animations fluides car dans sa méthode handle ,ce qui est écrit dedans est effectué toutes les frames
            private long lastUpdate = 0;
            private final long frameInterval = 16_666_666;
            // Conversion nano secondes en secondes = 60 FPS
            private boolean timeStopActive = false;

            @Override
            public void handle(long now) {
                if (now - lastUpdate >= frameInterval) {
                    if (Joueur.getUniqueJoueur().isTimeStop()) {
                        Joueur.getUniqueJoueur().mettreAJour();

                        for(int i=0;i<Jeu.getUniqueJeu().getListe_projectiles().size();i++){
                            if(Jeu.getUniqueJeu().getListe_projectiles().get(i).getType().equals("balle")){
                                Jeu.getUniqueJeu().màjProjectiles();
                            }
                        }

                        if (!timeStopActive) {
                            timeStopActive = true;
                            PauseTransition delay = new PauseTransition(Duration.seconds(5));
                            delay.setOnFinished(event -> {
                                Joueur.getUniqueJoueur().setTimeStop(false);
                                timeStopActive = false;
                            });
                            delay.play();
                        }
                    } else {
                        Joueur.getUniqueJoueur().mettreAJour();

                        for (int i = 0; i < Jeu.getUniqueJeu().getMobs().size(); i++) {
                            Jeu.getUniqueJeu().getMobs().get(i).mettreAJour();
                        }

                        Jeu.getUniqueJeu().màjProjectiles();
                    }

                    lastUpdate = now;

                    if (!Joueur.getUniqueJoueur().getEstVivant()) {
                        PauseTransition delay = new PauseTransition(Duration.seconds(0.5));
                        delay.setOnFinished(event -> {
                            vuejoueur.mettreAJourSpriteJoueur(Joueur.getUniqueJoueur());


                        });
                        delay.play();
                        initialisationJoueur();
                        déinitialisationMobs();
                        initialisationMobs();
                    }
                }
            }
        };
        timer.start();
    }



    @FXML
    public void ouvrirInventaire() {
        screenInventaire.toFront();
        Joueur.getUniqueJoueur().ajouterItem(Jeu.getUniqueJeu().getItems().get(20),1);
        Joueur.getUniqueJoueur().ajouterItem(Jeu.getUniqueJeu().getItems().get(72),1);
        Joueur.getUniqueJoueur().ajouterItem(Jeu.getUniqueJeu().getItems().get(78),1);
        Joueur.getUniqueJoueur().ajouterItem(Jeu.getUniqueJeu().getItems().get(77),64);
        Joueur.getUniqueJoueur().ajouterItem(Jeu.getUniqueJeu().getItems().get(51),1);
        Joueur.getUniqueJoueur().ajouterItem(Jeu.getUniqueJeu().getItems().get(54),1);
        Joueur.getUniqueJoueur().ajouterItem(Jeu.getUniqueJeu().getItems().get(51),1);
        Joueur.getUniqueJoueur().ajouterItem(Jeu.getUniqueJeu().getItems().get(79),1);
        Joueur.getUniqueJoueur().ajouterItem(Jeu.getUniqueJeu().getItems().get(80),64);


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
        Joueur.getUniqueJoueur().setNom(zoneNom.getText());
        Platform.runLater(() -> fond.requestFocus());
    }

    @FXML
    public void rageQuit() {
        Platform.exit();
    }

    @FXML
    public void activerSwitch(){
        Joueur.getUniqueJoueur().swapItem(ligneCase1.getValue()-1,colonneCase1.getValue()-1,ligneCase2.getValue()-1,colonneCase2.getValue()-1);
    }

    private void setupSpinner(Spinner<Integer> spinner, int min, int max, int initialValue) {
        spinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(min, max, initialValue));
    }


    public void déinitialisationMobs() {
        int i = Jeu.getUniqueJeu().getEnnemis().size()-1;
        while (i >= 0) {
            Jeu.getUniqueJeu().getEnnemis().get(i).decrementVie(Jeu.getUniqueJeu().getEnnemis().get(i).getBarreVie().getVieMax());

            i--;
        }
     }
        public void initialisationMobs () {
            Ennemi ogre = new Ogre("Pierre l'ogre vert", 50, 20, 3000, 0, 0, 4, Jeu.getUniqueJeu().getTaille1bloc(), Jeu.getUniqueJeu().getTaille1bloc() * 2, 10, 3);
            Ennemi ogre2 = new Ogre("Pierre l'ogre vert pale", 50, 20, 1340, 1340, 0, 4, Jeu.getUniqueJeu().getTaille1bloc(), Jeu.getUniqueJeu().getTaille1bloc() * 2, 10, 3);
            Ennemi ogre3 = new Ogre("Pierre l'ogre vert foncé", 50, 20, 4962, 1376, 0, 4, Jeu.getUniqueJeu().getTaille1bloc(), Jeu.getUniqueJeu().getTaille1bloc() * 2, 10, 3);
            Ennemi ogre4 = new Ogre("Pierre l'ogre vert clair", 50, 20, 3068, 1600, 0, 4, Jeu.getUniqueJeu().getTaille1bloc(), Jeu.getUniqueJeu().getTaille1bloc() * 2, 10, 3);
            Ennemi goblin = new Goblin("Caillou le gobelin vert", 20, 20, 5000, 0, 0, 2, Jeu.getUniqueJeu().getTaille1bloc(), Jeu.getUniqueJeu().getTaille1bloc() * 2, 10, 8);
            Ennemi goblin2 = new Goblin("Caillou le gobelin vert pale", 20, 20, 1456, 1728, 0, 2, Jeu.getUniqueJeu().getTaille1bloc(), Jeu.getUniqueJeu().getTaille1bloc() * 2, 10, 8);
            Ennemi goblin3 = new Goblin("Caillou le gobelin vert foncé", 20, 20, 2959, 1088, 0, 2, Jeu.getUniqueJeu().getTaille1bloc(), Jeu.getUniqueJeu().getTaille1bloc() * 2, 10, 8);
            Ennemi goblin4 = new Goblin("Caillou le gobelin vert clair", 20, 20, 5238, 1760, 0,  2, Jeu.getUniqueJeu().getTaille1bloc(), Jeu.getUniqueJeu().getTaille1bloc() * 2, 10, 8);
            Ennemi goblin5 = new Goblin("Caillou le gobelin vert émeraude", 20, 20, 4544, 1632, 0,  2, Jeu.getUniqueJeu().getTaille1bloc(), Jeu.getUniqueJeu().getTaille1bloc() * 2, 10, 8);
            Ennemi mh = new MH("Monsieur Homps", 250, 20, 4500, 0, 5,  2, Jeu.getUniqueJeu().getTaille1bloc(), Jeu.getUniqueJeu().getTaille1bloc() * 2, 15, 8);

            Jeu.getUniqueJeu().addEnnemis(ogre);
            Jeu.getUniqueJeu().addEnnemis(ogre2);
            Jeu.getUniqueJeu().addEnnemis(ogre3);
            Jeu.getUniqueJeu().addEnnemis(ogre4);
            Jeu.getUniqueJeu().addMobs(ogre);
            Jeu.getUniqueJeu().addMobs(ogre2);
            Jeu.getUniqueJeu().addMobs(ogre3);
            Jeu.getUniqueJeu().addMobs(ogre4);

            Jeu.getUniqueJeu().addEnnemis(goblin);
            Jeu.getUniqueJeu().addEnnemis(goblin2);
            Jeu.getUniqueJeu().addEnnemis(goblin3);
            Jeu.getUniqueJeu().addEnnemis(goblin4);
            Jeu.getUniqueJeu().addEnnemis(goblin5);
            Jeu.getUniqueJeu().addMobs(goblin);
            Jeu.getUniqueJeu().addMobs(goblin2);
            Jeu.getUniqueJeu().addMobs(goblin3);
            Jeu.getUniqueJeu().addMobs(goblin4);
            Jeu.getUniqueJeu().addMobs(goblin5);

            Jeu.getUniqueJeu().addEnnemis(mh);
            Jeu.getUniqueJeu().addMobs(mh);

        }





    public void initialisationJoueur(){
        Joueur.getUniqueJoueur().getBarreVie().setVie(Joueur.getUniqueJoueur().getBarreVie().getVieMax());
        Joueur.getUniqueJoueur().setEstVivant(true);
        Joueur.getUniqueJoueur().setX(20*32);
        Joueur.getUniqueJoueur().setY(0*32);
    }
}


