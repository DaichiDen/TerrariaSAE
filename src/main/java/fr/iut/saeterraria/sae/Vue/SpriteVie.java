package fr.iut.saeterraria.sae.Vue;

import fr.iut.saeterraria.sae.Modele.Jeu;
import fr.iut.saeterraria.sae.Modele.Personnages.Joueur;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class SpriteVie extends CreateImage{
    private int[] liste_hp;
    private Pane screen;
    private Jeu jeu;
    private int width,height;

    private int viePre;
    private int vieAct;

    public SpriteVie(Pane screen, Jeu jeu) {
        this.screen = screen;
        this.jeu = jeu;
        this.width=25;
        this.height=25;
        liste_hp = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        for(int j : liste_hp){
            if(j == 0) {
                screen.getChildren().add(createImageView("/Sprite/full_life.png",width,height));
            }
        }
    }

    public void mettreAJourSpriteVie(Joueur joueur){
        vieAct = joueur.getBarreVie().getVie();
        int vieEnMoins = (viePre - vieAct)%5;
        int i = liste_hp.length-1;

//        while(vieEnMoins > 0){
//            screen.getChildren().remove(i);
//            if(liste_hp[i] == 0) {
//                screen.getChildren().add(i,createImageView("/Sprite/full_life.png",width,height));
//            }
//            if(liste_hp[i] == 1){
//                screen.getChildren().add(i,createImageView("/Sprite/half_life.png",width,height));
//            }
//            if(liste_hp[i] == 2){
//                screen.getChildren().add(i,createImageView("/Sprite/no_life.png",width,height));
//            }
//            vieEnMoins =- 1;
//            i--;
//        }

        while(vieEnMoins > 0){
            screen.getChildren().remove(i);
            if(vieEnMoins > 0){
                screen.getChildren().add(i,createImageView("/Sprite/half_life.png",width,height));
                vieEnMoins =- 1;
            }
            if(vieEnMoins > 0){
                screen.getChildren().add(i,createImageView("/Sprite/no_life.png",width,height));
                vieEnMoins =- 1;
            }

            i--;
        }
    }


}
