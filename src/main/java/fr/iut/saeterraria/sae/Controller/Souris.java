package fr.iut.saeterraria.sae.Controller;

import fr.iut.saeterraria.sae.Modele.Jeu;
import fr.iut.saeterraria.sae.Modele.Map.Map;
import fr.iut.saeterraria.sae.Modele.Objets.Armure;
import fr.iut.saeterraria.sae.Modele.Personnages.Projectile;
import fr.iut.saeterraria.sae.Vue.Fond;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;

public class Souris implements EventHandler<MouseEvent> {
    private Jeu jeu;
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

    public Souris(Jeu jeu,Fond fond,Map map,TilePane tp, AnchorPane screenInventaire, ScrollPane craftSansBlocConstruction, ScrollPane craftEtabli, ScrollPane craftForge, ScrollPane four) {
        this.jeu = jeu;
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
            if (jeu.getJoueur().estVivant()) {

                if (jeu.getJoueur().gunEnMain() && !jeu.getJoueur().isTimeStop()) {
                    jeu.getJoueur().setTimeStop(true);

                    boolean oui = false;
                    int[] indice = new int[2];
                    int[][] tab = jeu.getJoueur().getInventaire().findItem(jeu.getItems().get(80));
                    for (int i = 0; i < tab.length; i++) {
                        for (int j = 0; j < tab[i].length; j++) {
                            if (tab[i][j] == 1) {
                                oui = true;
                                indice[0] = i;
                                indice[1] = j;
                            }
                        }
                    }
                    if (oui) {
                        jeu.getJoueur().tirerProjectile(new Projectile("balle",jeu,jeu.getJoueur().getX(),jeu.getJoueur().getY(),5,"balle", jeu.getTaille1bloc(), jeu.getTaille1bloc()),x1,y1);
                        jeu.getJoueur().getInventaire().getInventaireJoueur()[indice[0]][indice[1]].retireQuantite(1);
                    }
                }
                else if (jeu.getJoueur().arcEnMain()) {
                    boolean oui = false;
                    int[] indice = new int[2];
                    int[][] tab = jeu.getJoueur().getInventaire().findItem(jeu.getItems().get(77));
                    for (int i = 0; i < tab.length; i++) {
                        for (int j = 0; j < tab[i].length; j++) {
                            if (tab[i][j] == 1) {
                                oui = true;
                                indice[0] = i;
                                indice[1] = j;
                            }
                        }
                    }
                    if (oui) {

                        jeu.getJoueur().tirerProjectile(new Projectile("Flèche", jeu, jeu.getJoueur().getX(), jeu.getJoueur().getY(), 4,"Flèche", jeu.getTaille1bloc(), jeu.getTaille1bloc()), x1, y1);
                        jeu.getJoueur().getInventaire().getInventaireJoueur()[indice[0]][indice[1]].retireQuantite(1);
                    }

                }

                else if(jeu.getJoueur().grappinEnMain()){
                    System.out.println(x1+"x1" +y1+"y1");
                    System.out.println(x+"y2" +y+"y1");

                    if(jeu.getJoueur().peutEtreAtteint(x,y,100)){
                        if(jeu.getCarte().getCase(x, y) != 0 || jeu.getCarte().getCase(x, y) != 18 || jeu.getCarte().getCase(x, y) != 22 ){
                            jeu.getJoueur().grappiner(x1, y1);
                        }
                    }

                }

                else if (jeu.getJoueur().miner(x, y)) {
                    this.tp.getChildren().remove((y * tp.getPrefColumns()) + x);// faire de la taille de la map un un getter

                    this.tp.getChildren().add((((y * tp.getPrefColumns()) + x)), new ImageView(fond.getTiles().get(map.getCase(y, x))));
                }


                else {
                    jeu.getJoueur().action(x1, y1, 2);
                }
            }
        }
        else if (mouseEvent.getButton() == MouseButton.SECONDARY) {
            if (jeu.getJoueur().estVivant()) {
                if((map.getCase(y,x)==12 || map.getCase(y,x)==13 || map.getCase(y,x)==15) && jeu.getJoueur().peutEtreAtteint(x,y,2.5)){
                    ouvrirInventaire();
                    switch (map.getCase(y,x)) {
                        case 12 :
                            craftEtabli.toFront();
                            break;
                        case 13 :
                            craftForge.toFront();
                            break;
                        case 15 :
                            four.toFront();
                            break;
                    }
                }
                else if (jeu.getJoueur().getInventaire().getInventaireJoueur()[0][jeu.getJoueur().getMainCourante()].getItem().getCodeObjet()>=64 &&jeu.getJoueur().getInventaire().getInventaireJoueur()[0][jeu.getJoueur().getMainCourante()].getItem().getCodeObjet()<=71){
                    jeu.getJoueur().equiper((Armure) (jeu.getJoueur().getInventaire().getInventaireJoueur()[0][jeu.getJoueur().getMainCourante()].getItem()));
                }
                else {
                    jeu.getJoueur().poser(x, y);
                    this.tp.getChildren().remove((y * tp.getPrefColumns()) + x);// X = Ligne, Y = Colonne
                    this.tp.getChildren().add(((y * tp.getPrefColumns()) + x), new ImageView(fond.getTiles().get(map.getCase(y, x))));
                }
            }
        }

    }
    @FXML
    public void ouvrirInventaire() {
        Platform.runLater(() -> screenInventaire.requestFocus());
        jeu.testCraft();
        screenInventaire.toFront();
        jeu.getJoueur().setMarcheDroite(false);
        jeu.getJoueur().setMarcheGauche(false);
    }

    public void handleCraft (String nom){
        jeu.getJoueur().craftItem(jeu.getItem(nom));
    }
}
