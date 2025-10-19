package fr.iut.saeterraria.sae.Vue;

import fr.iut.saeterraria.sae.Modele.Entites.Ennemi;
import fr.iut.saeterraria.sae.Modele.Entites.Goblin;
import fr.iut.saeterraria.sae.Modele.Entites.Ogre;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.util.HashMap;
import java.util.Map;


public class VueEnnemi extends CreateRessourceVisuel {


    private Pane screen;


    private ImageView spriteActuel;
    private Map<Ennemi, ImageView> sprites = new HashMap<>();

    public VueEnnemi(Pane screen){
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

        sprite.setId("Ennemi"+ennemi.getId());
        sprite.translateXProperty().bind(ennemi.xProperty());
        sprite.translateYProperty().bind(ennemi.yProperty());

        sprites.put(ennemi, sprite);
        screen.getChildren().add(sprite);
    }

    public ImageView getSprite(Ennemi ennemi) {
        return sprites.get(ennemi);
    }

}









