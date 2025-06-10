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
    private ImageView flèche = createImageView("/Sprite_objets/Flèche.png",width,height);
    private ImageView spriteActuel;

    public VueProjectile(Jeu jeu, Pane screen) {
        this.jeu = jeu;
        this.screen = screen;
        this.width = 100;
        this.height = 100;

        for(int i = 0; i < jeu.getJoueur().getListe_projectiles().size() ; i++) {
            int finalI = i;
            jeu.getJoueur().getListe_projectiles().get(i).xProperty().addListener((obs, oldVal, newVal) -> mettreAJourSpriteProjectile(jeu.getJoueur().getListe_projectiles().get(finalI)));
            jeu.getJoueur().getListe_projectiles().get(i).yProperty().addListener((obs, oldVal, newVal) -> mettreAJourSpriteProjectile(jeu.getJoueur().getListe_projectiles().get(finalI)));
        }
    }

    public void mettreAJourSpriteProjectile(Projectile projectile) {
        spriteActuel=flèche;
        spriteActuel.setId(projectile.getNom());
        spriteActuel.translateXProperty().bind(projectile.xProperty());
        spriteActuel.translateYProperty().bind(projectile.yProperty());
        spriteActuel.setFitWidth(30);
        spriteActuel.setFitHeight(30);
        screen.getChildren().add(spriteActuel);
    }

}
