package fr.iut.saeterraria.sae.Vue;

import fr.iut.saeterraria.sae.Modele.Map.Carte;
import fr.iut.saeterraria.sae.Modele.Entites.Projectile;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import javafx.scene.layout.TilePane;

import java.util.HashMap;
import java.util.Map;

public class VueProjectile extends CreateRessourceVisuel {
    private Pane screen;
    private TilePane tp;
    private Fond fond;

    private Map<Projectile, ImageView> sprites = new HashMap<>();


    private ImageView spriteActuel = new ImageView();

    public VueProjectile(Pane screen, TilePane tp, Fond fond) {
        this.screen = screen;
        this.tp = tp;
        this.fond = fond;
    }

    public void mettreAJourSpriteProjectile(Projectile projectile) {
        // Vérifier si un sprite existe déjà pour ce projectile
        ImageView sprite = sprites.get(projectile);

        if (sprite == null) {
            // Si pas existant, créer et configurer le sprite une fois
            if (projectile.getNom().equals("flèche")) {
                sprite = createImageView("/Sprite_objets/Flèche.png", 24, 24);

            } else if (projectile.getNom().equals("balle")) {
                sprite = createImageView("/Sprite_objets/Balle.png", 20, 20);

            } else {
                sprite = createImageView("/Sprite_objets/Boule_de_feu.png", 46, 46);

            }

            sprite.setId(projectile.getNom());
            sprite.translateXProperty().bind(projectile.xProperty());
            sprite.translateYProperty().bind(projectile.yProperty());

            screen.getChildren().add(sprite);
            sprites.put(projectile,sprite);
        }
    }

    public Node getSprite(Projectile p) {
        return sprites.get(p);
    }

    public void detruireBlocExplosion(int x, int y) { // Schtongé
        System.out.println("j'ai explosé woaaaah");
        for (int j = x - 1; j <= x + 1; j++) {
            for (int i = y - 1; i <= y + 1; i++) {
                this.tp.getChildren().remove((j * tp.getPrefColumns()) + i);
                this.tp.getChildren().add((((j * tp.getPrefColumns()) +  i)), new ImageView(fond.getTiles().get(Carte.getUniqueCarte().getCase(j, i))));
            }
        }
    }


}