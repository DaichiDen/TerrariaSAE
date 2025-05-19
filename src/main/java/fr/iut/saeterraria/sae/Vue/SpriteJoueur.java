package fr.iut.saeterraria.sae.Vue;

import fr.iut.saeterraria.sae.Modele.Jeu;

import fr.iut.saeterraria.sae.Modele.Personnages.Joueur;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;



import java.net.URL;


public class SpriteJoueur extends CreateImage{

    private Pane screen;
    private Jeu jeu;

    private ImageView spriteActuel;
    private String dernierEtat = "";

    private Rectangle2D hitboxJoueur;


    public SpriteJoueur(Jeu jeu, Pane screen){
        this.jeu = jeu;
        this.screen = screen;
    }

    // Permet d'associer l'image au joueur au pane (conteneur principal)
    public void creerSpriteJoueur(Joueur joueur) {
        if (joueur.getMarcheGauche() == true) {
            ImageView sprite = createImageView("/Sprite/Chevalier_marcheGauche.gif");
            sprite.setId(joueur.getNom());
            sprite.translateXProperty().bind(joueur.xProperty());
            sprite.translateYProperty().bind(joueur.yProperty());
            sprite.setFitWidth(54);
            sprite.setFitHeight(64);
            screen.getChildren().add(sprite);
        }
        else if (joueur.getMarcheDroite() == true) {
            ImageView sprite = createImageView("/Sprite/Chevalier_marcheDroite.gif");
            sprite.setId(joueur.getNom());
            sprite.translateXProperty().bind(joueur.xProperty());
            sprite.translateYProperty().bind(joueur.yProperty());
            sprite.setFitWidth(54);
            sprite.setFitHeight(64);
            screen.getChildren().add(sprite);
        }
        else{
            ImageView sprite = createImageView("/Sprite/Hero_stop.png");
            sprite.setId(joueur.getNom());
            sprite.translateXProperty().bind(joueur.xProperty());
            sprite.translateYProperty().bind(joueur.yProperty());
            sprite.setFitWidth(54);
            sprite.setFitHeight(64);
            screen.getChildren().add(sprite);
        }
    }

    public void supprimerSpriteJoueur(Joueur joueur) {
        ImageView sprite = (ImageView) screen.lookup("#" + joueur.getNom());
        if (sprite != null) {
            screen.getChildren().remove(sprite);
        }
    }

    public void mettreAJourSpriteJoueur(Joueur joueur) {
        String etatActuel;

        if (joueur.getMarcheGauche()) {
            etatActuel = "gauche";
        } else if (joueur.getMarcheDroite()) {
            etatActuel = "droite";
        } else {
            etatActuel = "stop";
        }

        // Si l'état n'a pas changé, ne rien faire
        if (etatActuel.equals(dernierEtat)) {
            return;
        }

        // Sinon, supprimer le sprite précédent s’il existe
        if (spriteActuel != null) {
            screen.getChildren().remove(spriteActuel);
        }

        // Créer le bon sprite
        if (etatActuel.equals("gauche")) {
            spriteActuel = createImageView("/Sprite/Chevalier_marcheGauche.gif");
        } else if (etatActuel.equals("droite")) {
            spriteActuel = createImageView("/Sprite/Chevalier_marcheDroite.gif");
        } else {
            spriteActuel = createImageView("/Sprite/Hero_stop.png");
        }

        spriteActuel.setId(joueur.getNom());
        spriteActuel.translateXProperty().bind(joueur.xProperty());
        spriteActuel.translateYProperty().bind(joueur.yProperty());
        spriteActuel.setFitWidth(54);
        spriteActuel.setFitHeight(64);
        screen.getChildren().add(spriteActuel);

        dernierEtat = etatActuel;
    }


}









