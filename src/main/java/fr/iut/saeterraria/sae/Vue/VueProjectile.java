package fr.iut.saeterraria.sae.Vue;

import fr.iut.saeterraria.sae.Modele.Jeu;
import fr.iut.saeterraria.sae.Modele.Personnages.Joueur;
import fr.iut.saeterraria.sae.Modele.Personnages.Projectile;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class VueProjectile extends CreateRessourceVisuel{
    private Pane screen;
    private Jeu jeu;
    private int width, height;

    private ImageView spriteActuel;

    public VueProjectile(Jeu jeu, Pane screen) {
        this.jeu = jeu;
        this.screen = screen;
        this.width = 10;
        this.height = 10;

        for(int i = 0; i < jeu.getListe_projectiles().size() ; i++) {
            int finalI = i;
            jeu.getListe_projectiles().get(i).xProperty().addListener((obs, oldVal, newVal) -> mettreAJourSpriteProjectile(jeu.getListe_projectiles().get(finalI)));
            jeu.getListe_projectiles().get(i).yProperty().addListener((obs, oldVal, newVal) -> mettreAJourSpriteProjectile(jeu.getListe_projectiles().get(finalI)));
        }
    }

    public void mettreAJourSpriteProjectile(Projectile projectile) {
        spriteActuel=createImageView("/Sprite_objets/Flèche.png",width,height);
        spriteActuel.setId(projectile.getNom());
        spriteActuel.translateXProperty().bind(projectile.xProperty());
        spriteActuel.translateYProperty().bind(projectile.yProperty());
        screen.getChildren().add(spriteActuel);
    }

}
