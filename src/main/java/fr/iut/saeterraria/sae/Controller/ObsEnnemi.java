package fr.iut.saeterraria.sae.Controller;

import fr.iut.saeterraria.sae.Modele.Personnages.Entite;

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

public class ObsEnnemi implements ListChangeListener<Entite> {
    private Jeu jeu;
    private Pane screen;
    private HashMap<Entite, Node> spritesMobs;

    public ObsEnnemi(Jeu jeu, Pane screen){
        this.jeu = jeu;
        this.screen = screen;
        this.spritesMobs = new HashMap<>();
    }

    @Override
    public void onChanged(Change<? extends Entite> change) {
            while (change.next()) {
                if (change.wasAdded()) {
                    for (Entite mobAjoute : change.getAddedSubList()) {
                        // 1) Créer le Node graphique du mob (par ex. un ImageView)
                        URL imageURL = getClass().getResource("/Sprite/BM_Sac_a_caca.png");
                        Image image = new Image(String.valueOf(imageURL));
                        ImageView sprite = new ImageView(image);
                        sprite.setFitWidth(54);
                        sprite.setFitHeight(64);
                        // Positionner au bon endroit, ex. :
                        sprite.setLayoutX(mobAjoute.getX());
                        sprite.setLayoutY(mobAjoute.getY());

                        // 2) Conserver la correspondance Entite → Node
                        spritesMobs.put(mobAjoute, sprite);

                        // 3) Ajouter le sprite dans le Pane
                        screen.getChildren().add(sprite);

                        // 4) Mettre à jour la position à chaque frame (si tu utilises yProperty/xProperty)
                        mobAjoute.xProperty().addListener((obs, oldX, newX) -> {
                            sprite.setLayoutX(newX.doubleValue());
                        });
                        mobAjoute.yProperty().addListener((obs, oldY, newY) -> {
                            sprite.setLayoutY(newY.doubleValue());
                        });

                        // 5) **ÉCOUTER la propriété estVivantProperty()**
                        mobAjoute.estVivantProperty().addListener((obs, ancienEtat, nouvelEtat) -> {
                            if (!nouvelEtat) {
                                // Le mob vient de mourir → on retire son Node à l’écran
                                Node nodeAMettreAJour = spritesMobs.get(mobAjoute);
                                if (nodeAMettreAJour != null) {
                                    screen.getChildren().remove(nodeAMettreAJour);
                                    spritesMobs.remove(mobAjoute);
                                }
                                // (Optionnel) on peut aussi le supprimer de la liste de mobs si ce n’est pas déjà fait
                                jeu.getMobs().remove(mobAjoute);
                            }
                        });
                    }
                }

                if (change.wasRemoved()) {
                    // En cas de suppressions “externes”, supprimer aussi du Pane
                    for (Entite mobSupprime : change.getRemoved()) {
                        Node spriteARetirer = spritesMobs.remove(mobSupprime);
                        if (spriteARetirer != null) {
                            screen.getChildren().remove(spriteARetirer);
                        }
                    }
                }
            }

    }

}

