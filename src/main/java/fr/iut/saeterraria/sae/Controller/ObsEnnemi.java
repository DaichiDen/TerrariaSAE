package fr.iut.saeterraria.sae.Controller;

import fr.iut.saeterraria.sae.Modele.Personnages.Entite;

import fr.iut.saeterraria.sae.Modele.Personnages.EntiteVivante;
import fr.iut.saeterraria.sae.Vue.SpriteMob;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.scene.Node;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import fr.iut.saeterraria.sae.Modele.Jeu;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class ObsEnnemi implements ListChangeListener<EntiteVivante> {
    private Jeu jeu;
    private Pane screen;
    private HashMap<Entite, Node> spritesMobs;

    public ObsEnnemi(Jeu jeu, Pane screen){
        this.jeu = jeu;
        this.screen = screen;
        this.spritesMobs = new HashMap<>();
    }

    @Override
    public void onChanged(Change<? extends EntiteVivante> change) {
            while (change.next()) {
                if (change.wasAdded()) {
                    for (EntiteVivante mobAjoute : change.getAddedSubList()) {
                        // 1) Créer le Node graphique du mob (par ex. un ImageView)
                        URL imageURL = getClass().getResource("/Sprite/BM_Sac_a_caca.png");
                        Image image = new Image(String.valueOf(imageURL));
                        ImageView sprite = new ImageView(image);
                        sprite.setFitWidth(54);
                        sprite.setFitHeight(64);
                        // Positionner au bon endroit, ex. :
                        sprite.translateXProperty().bind(mobAjoute.xProperty());
                        sprite.translateYProperty().bind(mobAjoute.yProperty());
                        ProgressBar progressBar = new ProgressBar();
                        progressBar.progressProperty().bind(mobAjoute.getBarreVie().vieProperty().divide(mobAjoute.getBarreVie().getVieMax()));
                        progressBar.translateXProperty().bind(mobAjoute.xProperty().subtract(20));
                        progressBar.translateYProperty().bind(mobAjoute.yProperty().subtract(20));
                        // 2) Conserver la correspondance Entite → Node
                        spritesMobs.put(mobAjoute, sprite);

                        // 3) Ajouter le sprite dans le Pane
                        screen.getChildren().add(sprite);
                        screen.getChildren().add(progressBar);

                        // 4) **ÉCOUTER la propriété estVivantProperty()**
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

