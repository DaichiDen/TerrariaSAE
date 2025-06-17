package fr.iut.saeterraria.sae.Controller;

import fr.iut.saeterraria.sae.Modele.Jeu;
import fr.iut.saeterraria.sae.Modele.Map.Map;
import fr.iut.saeterraria.sae.Modele.Personnages.Projectile;
import fr.iut.saeterraria.sae.Vue.Fond;
import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.TilePane;

public class Souris implements EventHandler<MouseEvent> {
    private Jeu jeu;
    private Fond fond;
    private TilePane tp;
    private Map map;

    public Souris(Jeu jeu,Fond fond,Map map,TilePane tp){
        this.jeu = jeu;
        this.fond = fond;
        this.map = map;
        this.tp = tp;

    }

    @Override
    public void handle(MouseEvent mouseEvent) {
        int x = ((int) mouseEvent.getX()) / 32;
        int y = ((int) mouseEvent.getY()) / 32;
        int x1 = ((int) mouseEvent.getX());
        int y1 = ((int) mouseEvent.getY());
        if (mouseEvent.getButton() == MouseButton.PRIMARY) {
            if (jeu.getJoueur().estVivant()) {
                if (jeu.getJoueur().arcEnMain()) {
                    boolean oui = false;
                    int[] indice = new int[2];
                    int[][] tab = jeu.getJoueur().getInventaire().findItem(jeu.getItems().get(72));
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
                        jeu.getJoueur().tirerProjectile(new Projectile(jeu.getJoueur(), "Flèche", map, jeu, jeu.getJoueur().getX(), jeu.getJoueur().getY()), x1, y1);
                        jeu.getJoueur().getInventaire().getInventaireJoueur()[indice[0]][indice[1]].retireQuantite(1);
                    }
                }
                else if (jeu.getJoueur().miner(x, y)) {
                    this.tp.getChildren().remove((y * tp.getPrefColumns()) + x);// faire de la taille de la map un un getter
                    this.tp.getChildren().add((((y * tp.getPrefColumns()) + x)), new ImageView(fond.getTiles().get(map.getCase(y, x))));
                }
                else {
                    jeu.getJoueur().action(x1, y1, 2);
                }


            } else if (mouseEvent.getButton() == MouseButton.SECONDARY) {
                if (jeu.getJoueur().estVivant()) {
                    jeu.getJoueur().poser(x, y);
                    this.tp.getChildren().remove((y * tp.getPrefColumns()) + x);// X = Ligne, Y = Colonne
                    this.tp.getChildren().add(((y * tp.getPrefColumns()) + x), new ImageView(fond.getTiles().get(map.getCase(y, x))));
                }
            }
        }
    }


        public void handleCraft (String nom){
            jeu.getJoueur().craftItem(jeu.getItem(nom));
        }

}
