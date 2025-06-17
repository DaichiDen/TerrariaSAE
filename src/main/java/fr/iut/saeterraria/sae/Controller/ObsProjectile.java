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

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class ObsProjectile implements ListChangeListener<Projectile> {
    private Jeu jeu;
    private Pane screen;
    private HashMap<Projectile, Node> spriteProjectiles;

    public ObsProjectile(Jeu jeu, Pane screen){
        this.jeu = jeu;
        this.screen = screen;
        this.spriteProjectiles = new HashMap<>();
    }



    @Override
    public void onChanged(Change<? extends Projectile> c) {
        while (c.next()) {
            if (c.wasAdded()) {
                ImageView sprite;
                for (Projectile projectile_aj : c.getAddedSubList()) {
                    // 1) Créer le Node graphique du mob (par ex. un ImageView)
                    if (projectile_aj.getType().equals("Flèche")){
                        URL imageURL = getClass().getResource("/Sprite_objets/Flèche.png");
                        Image image = new Image(String.valueOf(imageURL));
                        sprite = new ImageView(image);
                        sprite.setFitWidth(54);
                        sprite.setFitHeight(64);
                    }
                    else{
                        URL imageURL = getClass().getResource("/Sprite_objets/Balle.png");
                        Image image = new Image(String.valueOf(imageURL));
                        sprite = new ImageView(image);
                        sprite.setFitWidth(54);
                        sprite.setFitHeight(64);
                    }

                    // Positionner au bon endroit, ex. :
                    sprite.setLayoutX(projectile_aj.getX());
                    sprite.setLayoutY(projectile_aj.getY());

                    // 2) Conserver la correspondance Entite → Node
                    spriteProjectiles.put(projectile_aj, sprite);

                    // 3) Ajouter le sprite dans le Pane
                    screen.getChildren().add(sprite);

                    // 4) Mettre à jour la position à chaque frame (si tu utilises yProperty/xProperty)
                    ImageView finalSprite = sprite;
                    projectile_aj.xProperty().addListener((obs, oldX, newX) -> {
                        finalSprite.setLayoutX(newX.doubleValue());
                    });
                    ImageView finalSprite1 = sprite;
                    projectile_aj.yProperty().addListener((obs, oldY, newY) -> {
                        finalSprite1.setLayoutY(newY.doubleValue());
                    });

                    // 5) **ÉCOUTER la propriété estVivantProperty()**
                    projectile_aj.getActifProperty().addListener((obs, ancienEtat, nouvelEtat) -> {
                        if (!nouvelEtat) {

                            // Le mob vient de mourir → on retire son Node à l’écran
                            Node nodeAMettreAJour = spriteProjectiles.get(projectile_aj);
                            if (nodeAMettreAJour != null) {
                                screen.getChildren().remove(nodeAMettreAJour);
                                spriteProjectiles.remove(projectile_aj);
                            }
                            // (Optionnel) on peut aussi le supprimer de la liste de mobs si ce n’est pas déjà fait
                            jeu.getListe_projectilesObservable().remove(projectile_aj);
                        }
                    });
                }
            }

            if (c.wasRemoved()) {
                // En cas de suppressions “externes”, supprimer aussi du Pane
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


