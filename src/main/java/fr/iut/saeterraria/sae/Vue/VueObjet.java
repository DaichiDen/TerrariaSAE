package fr.iut.saeterraria.sae.Vue;

import fr.iut.saeterraria.sae.Modele.Jeu;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class VueObjet extends CreateRessourceVisuel{
    private Pane screen;
    private Jeu jeu;
    private int width,height;

    private ImageView spriteActuel;
    private String dernierEtat = "";
    private int codeObjet;

    private Rectangle2D hitboxJoueur;

    public void vueObjet(Jeu jeu, Pane screen){
        this.jeu = jeu;
        this.screen = screen;
        this.width=150;
        this.height=150;
        spriteActuel = createImageView("/Sprite_objets/arme_test.png",150,150);
        screen.getChildren().add(spriteActuel);
        spriteActuel.setId("" + this.jeu.getItems().get(1).getCodeObjet());
        spriteActuel.translateXProperty().bind(jeu.getMobs().get(0).xProperty());
        spriteActuel.translateYProperty().bind(jeu.getMobs().get(0).yProperty());
        spriteActuel.setFitWidth(54);
        spriteActuel.setFitHeight(64);
    }

    public void mettreAjour(){

    }
}
