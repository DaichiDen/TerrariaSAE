package fr.iut.saeterraria.sae.Vue;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;

public class CreateImage {

    // Permet de renvoyer une fenêtre d'image par son URL
    public ImageView createImageView(String imagePath, int width, int height){

        URL imageURL = getClass().getResource(imagePath);
        Image image = new Image(String.valueOf(imageURL));
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(width);
        imageView.setFitHeight(height);
        return imageView;

    }


}
