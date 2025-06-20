package fr.iut.saeterraria.sae.Vue;

import fr.iut.saeterraria.sae.Modele.Jeu;

import fr.iut.saeterraria.sae.Modele.Personnages.Ennemi;
import fr.iut.saeterraria.sae.Modele.Personnages.Goblin;
import fr.iut.saeterraria.sae.Modele.Personnages.Ogre;
import fr.iut.saeterraria.sae.Modele.Personnages.Projectile;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;


public class VueEnnemi extends CreateRessourceVisuel {


    private Pane screen;
    private Jeu jeu;


    private ImageView spriteActuel;
    private Map<Ennemi, ImageView> sprites = new HashMap<>();

    public VueEnnemi(Jeu jeu, Pane screen){
        this.jeu = jeu;
        this.screen = screen;

    }

    public void initialisationSpritesMobs(Ennemi ennemi){

        if (sprites.containsKey(ennemi)) return;

        ImageView sprite;
        if(ennemi.getClass().equals(Ogre.class)){
            sprite = createImageView("/Sprite/ogre.png", 42, 76);
        } else if(ennemi.getClass().equals(Goblin.class)){
            sprite = createImageView("/Sprite/BM_Sac_a_caca.png", 32, 64);
        } else {
            sprite = createImageView("/Sprite/MH.png", 32, 64);
        }

        sprite.setId(ennemi.getNom());
        sprite.translateXProperty().bind(ennemi.xProperty());
        sprite.translateYProperty().bind(ennemi.yProperty());

        sprites.put(ennemi, sprite);
        screen.getChildren().add(sprite);
    }

    public ImageView getSprite(Ennemi ennemi) {
        return sprites.get(ennemi);
    }

}









