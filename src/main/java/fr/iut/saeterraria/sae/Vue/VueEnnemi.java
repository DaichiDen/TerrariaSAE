package fr.iut.saeterraria.sae.Vue;

import fr.iut.saeterraria.sae.Modele.Jeu;

import fr.iut.saeterraria.sae.Modele.Personnages.Ennemi;
import fr.iut.saeterraria.sae.Modele.Personnages.Joueur;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;



public class VueEnnemi extends CreateRessourceVisuel {


    private Pane screen;
    private Jeu jeu;
    private int width,height;

    private ImageView spriteActuel;
    private String dernierEtat = "";

    private Rectangle2D hitboxJoueur;

    public VueEnnemi(Jeu jeu, Pane screen){
        this.jeu = jeu;
        this.screen = screen;
        this.width=150;
        this.height=150;
        spriteActuel = createImageView("/Sprite/test_ennemi.png",width,height);
        spriteActuel.setId(jeu.getMobs().get(0).getNom());
        spriteActuel.translateXProperty().bind(jeu.getMobs().get(0).xProperty());
        spriteActuel.translateYProperty().bind(jeu.getMobs().get(0).yProperty());
        spriteActuel.setFitWidth(54);
        spriteActuel.setFitHeight(64);
        screen.getChildren().add(spriteActuel);
    }

    public void mettreAjour(){

    }


}









