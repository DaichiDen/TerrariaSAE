package fr.iut.saeterraria.sae.Vue;

import fr.iut.saeterraria.sae.Modele.Jeu;
import fr.iut.saeterraria.sae.Modele.Personnages.Joueur;
import javafx.scene.layout.Pane;

public class SpriteVie extends CreateRessourceVisuel {
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


    public void mettreAJourSpriteVie(Joueur joueur) {
        vieAct = joueur.getBarreVie().getVie();

        // Nettoyer tous les anciens cœurs
        screen.getChildren().clear();

        int maxVie = joueur.getBarreVie().getVieMax();
        int nbCoeurs = maxVie / 2;

        int hpRestant = vieAct;

        for (int i = 0; i < nbCoeurs; i++) {
            if (hpRestant >= 2) {
                screen.getChildren().add(createImageView("/Sprite/full_life.png", width, height));
                hpRestant -= 2;
            } else if (hpRestant == 1) {
                screen.getChildren().add(createImageView("/Sprite/half_life.png", width, height));
                hpRestant -= 1;
            } else {
                screen.getChildren().add(createImageView("/Sprite/no_life.png", width, height));
            }
        }

        viePre = vieAct; // Met à jour l'ancienne vie
    }
//commencer par la fin



}
