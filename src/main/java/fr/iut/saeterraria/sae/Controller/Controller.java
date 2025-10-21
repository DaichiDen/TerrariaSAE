package fr.iut.saeterraria.sae.Controller;

import fr.iut.saeterraria.sae.Modele.Jeu;

import fr.iut.saeterraria.sae.Modele.Objets.Etablis.BlocConstruction;
import fr.iut.saeterraria.sae.Modele.Objets.ListeItems;
import fr.iut.saeterraria.sae.Modele.Entites.*;
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
        ListeItems.initialiserRecettes();
        ListeItems.initialiserBlocConstructions();
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
        Jeu.getUniqueJeu().initialisationMobs();

        imageaccueil.setFitWidth(menu.getWidth());
        imageaccueil.fitWidthProperty().bind(imagebloc_accueil.widthProperty());
        imageaccueil.fitHeightProperty().bind(imagebloc_accueil.widthProperty());
        SpriteVie barre = new SpriteVie(Vie);

        Clavier controlleurJoueur = new Clavier(screenInventaire,quitterInventaire,openInventaire,fond,hotBar, screenPrincipal);

        Souris controlleurSouris = new Souris(scene,fond,screenInventaire,craftSansBlocConstruction,craftEtabli,craftForge,four);

        inventaireVue = new VueInventaire(quitterInventaire, screenInventaire, inventaire, screen);

        hotBarVue = new VueHotbar(hotBar);
        Platform.runLater(() -> fond.requestFocus()); // Permet de faire fonctionner la méthode mouvement

        vuejoueur = new SpriteJoueur(screen, background,opaciteBackground); // Appelle la classe de la vue pour l'initialiser
        vuejoueur.mettreAJourSpriteJoueur(Joueur.getUniqueJoueur());
        vueCraft = new VueCraft(craftSansBlocConstruction,craftEtabli,craftForge,caseRecetteSansBloc,caseRecetteEtabli,caseRecetteForge,
                ((BlocConstruction) ListeItems.getItemParId(11)).getListeRecette(), ((BlocConstruction) ListeItems.getItemParId(12)).getListeRecette(),
                ((BlocConstruction) ListeItems.getItemParId(13)).getListeRecette(), caseRecetteFour, ((BlocConstruction) ListeItems.getItemParId(14)).getListeRecette());

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
            private final long frameInterval = 16_666_666; //TODO plus faire comme ça
            // Conversion nano secondes en secondes = 60 FPS
            private boolean arretTempsActif = false;

            @Override
            public void handle(long now) {
                if (now - lastUpdate >= frameInterval) {
                    if (Jeu.getUniqueJeu().getArretTemps()) {
                        Joueur.getUniqueJoueur().mettreAJour();

                        for (Projectile p : Jeu.getUniqueJeu().getListe_projectiles()) {
                            if (p.affecteTemps()) {
                                Jeu.getUniqueJeu().màjProjectiles();
                            }
                        }

                        if (!arretTempsActif) {
                            arretTempsActif = true;
                            PauseTransition delay = new PauseTransition(Duration.seconds(5));
                            delay.setOnFinished(event -> {
                                Jeu.getUniqueJeu().setArretTemps(false);
                                arretTempsActif = false;
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
                        Jeu.getUniqueJeu().initialisationJoueur();
                        Jeu.getUniqueJeu().déinitialisationMobs();
                        Jeu.getUniqueJeu().initialisationMobs();
                    }
                }
            }
        };
        timer.start();
    }



    @FXML
    public void ouvrirInventaire() {
        screenInventaire.toFront();
        Joueur.getUniqueJoueur().ajouterItem(ListeItems.getItemParId(54), 1);
        Joueur.getUniqueJoueur().ajouterItem(ListeItems.getItemParId(76), 1);
        Joueur.getUniqueJoueur().ajouterItem(ListeItems.getItemParId(64), 1);
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



}


