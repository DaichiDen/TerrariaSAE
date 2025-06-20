package fr.iut.saeterraria.sae.Controller;

import fr.iut.saeterraria.sae.Modele.Personnages.Entite;

import fr.iut.saeterraria.sae.Modele.Personnages.Projectile;
import fr.iut.saeterraria.sae.Vue.SpriteMob;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import fr.iut.saeterraria.sae.Modele.Jeu;
import javafx.scene.layout.Pane;
import fr.iut.saeterraria.sae.Vue.VueProjectile;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class ObsProjectile implements ListChangeListener<Projectile> {
    private Jeu jeu;
    private Pane screen;
    private VueProjectile p;
    private HashMap<Projectile, Node> spriteProjectiles;

    public ObsProjectile(Jeu jeu, Pane screen, VueProjectile p) {
        this.jeu = jeu;
        this.screen = screen;
        this.p = p;
        this.spriteProjectiles = new HashMap<>();
    }

    public void onChanged(Change<? extends Projectile> c) {
        while (c.next()) {
            if (c.wasAdded()) {
                for (Projectile projectile_aj : c.getAddedSubList()) {

                    projectile_aj.xProperty().addListener((obs, oldVal, newVal) -> p.mettreAJourSpriteProjectile(projectile_aj));
                    projectile_aj.yProperty().addListener((obs, oldVal, newVal) -> p.mettreAJourSpriteProjectile(projectile_aj));
                    projectile_aj.aExploséProperty().addListener((obs, oldVal, newVal) -> p.detruireBlocExplosion(projectile_aj.getY() / 32, projectile_aj.getX() / 32));

                    // Met à jour le sprite depuis VueProjectile
                    p.mettreAJourSpriteProjectile(projectile_aj); // <- pour forcer la création du sprite
                    Node sprite = p.getSprite(projectile_aj);     // <- nouvelle méthode à créer
                    spriteProjectiles.put(projectile_aj, sprite);

                    projectile_aj.getActifProperty().addListener((obs, ancienEtat, nouvelEtat) -> {
                        if (!nouvelEtat) {
                            Node nodeAMettreAJour = spriteProjectiles.remove(projectile_aj);
                            if (nodeAMettreAJour != null) {
                                screen.getChildren().remove(nodeAMettreAJour);
                            }
                            jeu.getListe_projectilesObservable().remove(projectile_aj);
                        }
                    });
                }
            }

            if (c.wasRemoved()) {
                for (Projectile projSuppr : c.getRemoved()) {
                    Node spriteARetirer = spriteProjectiles.remove(projSuppr);
                    if (spriteARetirer != null) {
                        screen.getChildren().remove(spriteARetirer);
                    }
                }
            }
        }
    }
}

