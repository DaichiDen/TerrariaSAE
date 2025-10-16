package fr.iut.saeterraria.sae.Controller;

import fr.iut.saeterraria.sae.Modele.Jeu;
import fr.iut.saeterraria.sae.Modele.Map.Map;
import fr.iut.saeterraria.sae.Modele.Objets.Armure;
import fr.iut.saeterraria.sae.Modele.Personnages.*;
import fr.iut.saeterraria.sae.Vue.Fond;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.TilePane;

import java.util.ArrayList;

public class Souris implements EventHandler<MouseEvent> {
    private Fond fond;
    private TilePane tp;
    private Map map;
    @FXML
    private AnchorPane screenInventaire;
    @FXML
    private ScrollPane craftSansBlocConstruction;
    @FXML
    private ScrollPane craftEtabli;
    @FXML
    private ScrollPane craftForge;
    @FXML
    private ScrollPane four;

    public Souris(Fond fond,Map map,TilePane tp, AnchorPane screenInventaire, ScrollPane craftSansBlocConstruction, ScrollPane craftEtabli, ScrollPane craftForge, ScrollPane four) {
        this.fond = fond;
        this.map = map;
        this.tp = tp;
        this.screenInventaire = screenInventaire;
        this.craftSansBlocConstruction = craftSansBlocConstruction;
        this.craftEtabli = craftEtabli;
        this.craftForge = craftForge;
        this.four = four;
    }

    @Override
    public void handle(MouseEvent mouseEvent) {
        int x = ((int) mouseEvent.getX()) / 32; // position colonne dans le modèle
        int y = ((int) mouseEvent.getY()) / 32; // position ligne dans le modèle
        int x1 = ((int) mouseEvent.getX()); // position x dans le FX
        int y1 = ((int) mouseEvent.getY()); // position y dans le FX
        if (mouseEvent.getButton() == MouseButton.PRIMARY) {
            if (Joueur.getUniqueJoueur().getEstVivant()) {

                if (Joueur.getUniqueJoueur().gunEnMain() && !Jeu.getUniqueJeu().getArretTemps()) {
                    Jeu.getUniqueJeu().setArretTemps(true);

                    boolean oui = false;
                    int[] indice = new int[2];
                    ArrayList<Case> tab = Joueur.getUniqueJoueur().getInventaire().findItem(Jeu.getUniqueJeu().getItems().get(80));
                    for(Case c : tab) {
                            if (c.getItem().getCodeObjet()!=0) {
                                oui = true;
                                indice[0] = c.getLigne();
                                indice[1] = c.getColonne();
                            }
                    }
                    if (oui) {
                        Projectile balle = new Balle( Joueur.getUniqueJoueur().getX(), Joueur.getUniqueJoueur().getY());
                        balle.initialiserProjectile(x1, y1);
                        Joueur.getUniqueJoueur().getInventaire().getInventaireJoueur().get(indice[0]*6+indice[1]).retireQuantite(1);
                    }

                } else if (Joueur.getUniqueJoueur().arcEnMain()) {
                    boolean oui = false;
                    int[] indice = new int[2];
                    ArrayList<Case> tab = Joueur.getUniqueJoueur().getInventaire().findItem(Jeu.getUniqueJeu().getItems().get(77));
                    for(Case c : tab) {
                        if (c.getItem().getCodeObjet()!=0) {

                                oui = true;
                            indice[0] = c.getLigne();
                            indice[1] = c.getColonne();
                            }
                    }
                    if (oui) {
                        Projectile fleche = new Fleche(Joueur.getUniqueJoueur().getX(), Joueur.getUniqueJoueur().getY());
                        fleche.initialiserProjectile(x1, y1);
                        Joueur.getUniqueJoueur().getInventaire().getInventaireJoueur().get(indice[0]*6+indice[1]).retireQuantite(1);

                    }
                } else if (Joueur.getUniqueJoueur().grappinEnMain()) {
                    System.out.println(x1 + "x1" + y1 + "y1");
                    System.out.println(x + "y2" + y + "y1");

                    if (Joueur.getUniqueJoueur().peutEtreAtteint(x, y, 100)) {
                        if (Jeu.getUniqueJeu().getCarte().getCase(y, x) != 0 && Jeu.getUniqueJeu().getCarte().getCase(y, x) != 10 && Jeu.getUniqueJeu().getCarte().getCase(y, x) != 18) {
                            Joueur.getUniqueJoueur().grappiner(x1, y1);
                        }
                    }

                } else if (Joueur.getUniqueJoueur().miner(x, y)) {
                    this.tp.getChildren().remove((y * tp.getPrefColumns()) + x);// faire de la taille de la map un un getter
                    this.tp.getChildren().add((((y * tp.getPrefColumns()) + x)), new ImageView(fond.getTiles().get(map.getCase(y, x))));
                } else {
                    Joueur.getUniqueJoueur().action(x1, y1);
                }
            }

        }

            else if (mouseEvent.getButton() == MouseButton.SECONDARY) {
                if (Joueur.getUniqueJoueur().getEstVivant()) {
                    System.out.println("Bloc de craft ? : " +  (map.getCase(y, x) == 12 || map.getCase(y, x) == 13 || map.getCase(y, x) == 15));
                    System.out.println("Bloc de craft atteignable? : "+ Joueur.getUniqueJoueur().peutEtreAtteint(x, y, 2.5));
                    if ( (map.getCase(y, x) == 12 || map.getCase(y, x) == 13 || map.getCase(y, x) == 15) && Joueur.getUniqueJoueur().peutEtreAtteint(x, y, 2.5)) {
                        System.out.println("pitié");
                        ouvrirInventaire();
                        switch (map.getCase(y, x)) {
                            case 12:
                                craftEtabli.toFront();
                                break;
                            case 13:
                                craftForge.toFront();
                                break;
                            case 15:
                                four.toFront();
                                break;
                        }
                    } else if (Joueur.getUniqueJoueur().getInventaire().getCase(0,Joueur.getUniqueJoueur().getMainCourante()).getItem().getCodeObjet() >= 64 && Joueur.getUniqueJoueur().getInventaire().getCase(0,Joueur.getUniqueJoueur().getMainCourante()).getItem().getCodeObjet() <= 71) {
                        Joueur.getUniqueJoueur().equiper((Armure) (Joueur.getUniqueJoueur().getInventaire().getCase(0,Joueur.getUniqueJoueur().getMainCourante()).getItem()));
                    } else {
                        Joueur.getUniqueJoueur().poser(x, y);
                        this.tp.getChildren().remove((y * tp.getPrefColumns()) + x);// X = Ligne, Y = Colonne
                        this.tp.getChildren().add(((y * tp.getPrefColumns()) + x), new ImageView(fond.getTiles().get(map.getCase(y, x))));

                    }
                } else if (Joueur.getUniqueJoueur().getInventaire().getCase(0,Joueur.getUniqueJoueur().getMainCourante()).getItem().getCodeObjet() >= 64 && Joueur.getUniqueJoueur().getInventaire().getCase(0,Joueur.getUniqueJoueur().getMainCourante()).getItem().getCodeObjet() <= 71) {
                    Joueur.getUniqueJoueur().equiper((Armure) (Joueur.getUniqueJoueur().getInventaire().getCase(0,Joueur.getUniqueJoueur().getMainCourante()).getItem()));
                } else {
                    Joueur.getUniqueJoueur().poser(x, y);
                    this.tp.getChildren().remove((y * tp.getPrefColumns()) + x);// X = Ligne, Y = Colonne
                    this.tp.getChildren().add(((y * tp.getPrefColumns()) + x), new ImageView(fond.getTiles().get(map.getCase(y, x))));
                }
            }
        }





    @FXML
    public void ouvrirInventaire() {
        Platform.runLater(() -> screenInventaire.requestFocus());
        Jeu.getUniqueJeu().testCraft();
        screenInventaire.toFront();
        Joueur.getUniqueJoueur().setMarcheDroite(false);
        Joueur.getUniqueJoueur().setMarcheGauche(false);
    }

    public void handleCraft (String nom){
        Joueur.getUniqueJoueur().craftItem(Jeu.getUniqueJeu().getItem(nom));
    }
}
