package fr.iut.saeterraria.sae.Vue;

import fr.iut.saeterraria.sae.Modele.Jeu;

import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;



public class VueEnnemi extends CreateRessourceVisuel {


    private Pane screen;
    private Jeu jeu;
    private int width,height;

    private ImageView spriteActuel;
    private String dernierEtat = "";

    public VueEnnemi(Jeu jeu, Pane screen){
        this.jeu = jeu;
        this.screen = screen;
        this.width=150;
        this.height=150;
        spriteActuel = createImageView("/Sprite/BM_Sac_a_caca.png",width,height);
        spriteActuel.setId(jeu.getEnnemis().get(0).getNom());
        spriteActuel.translateXProperty().bind(jeu.getEnnemis().get(0).xProperty());
        spriteActuel.translateYProperty().bind(jeu.getEnnemis().get(0).yProperty());
        spriteActuel.setFitWidth(54);
        spriteActuel.setFitHeight(64);
        screen.getChildren().add(spriteActuel);

    }

    public void mettreAjour() {
        System.out.println("ntm");
    }
}









