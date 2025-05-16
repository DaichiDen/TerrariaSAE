package fr.iut.saeterraria.sae.Vue;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;

public class CreateImage {

    // Permet de renvoyer une fenêtre d'image par son URL
    public ImageView createImageView(String imagePath){

        URL imageURL = getClass().getResource(imagePath);
        Image image = new Image(String.valueOf(imageURL));
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(150);
        imageView.setFitHeight(150);
        return imageView;

    }

}
