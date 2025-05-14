package fr.iut.saeterraria.sae.Vue;

import fr.iut.saeterraria.sae.Controller.Controller;
import fr.iut.saeterraria.sae.Modele.Jeu;
import fr.iut.saeterraria.sae.Modele.Personnages.Joueur;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;


import java.net.URL;

public class Sprite {

    private TilePane map;
    private Jeu jeu;

    public Sprite(Jeu jeu, TilePane map){
        this.jeu = jeu;
        this.map = map;
    }

    // Permet de renvoyer une fenêtre d'image par son URL
    public ImageView createImageView(String imagePath){

        URL imageURL = getClass().getResource(imagePath);
        Image image = new Image(String.valueOf(imageURL));
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(150);
        imageView.setFitHeight(150);
        return imageView;

    }

    // Permet d'associer l'image au joueur
    public void creerSpriteJoueur(Joueur joueur){

        ImageView sprite = createImageView("/Sprite/character.png");
        sprite.setId(joueur.getNom());
        sprite.translateXProperty().bind(joueur.xProperty());
        sprite.translateYProperty().bind(joueur.yProperty());
        sprite.setFitWidth(100);
        sprite.setFitHeight(80);
        map.getChildren().add(sprite);
    }

}
