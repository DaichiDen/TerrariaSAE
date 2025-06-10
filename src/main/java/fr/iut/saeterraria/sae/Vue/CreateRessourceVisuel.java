package fr.iut.saeterraria.sae.Vue;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Font;

import java.net.URL;

public abstract class CreateRessourceVisuel {

    public ImageView createImageView(String imagePath, int width, int height){
        URL imageURL = getClass().getResource(imagePath);
        Image image = new Image(String.valueOf(imageURL));
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(width);
        imageView.setFitHeight(height);
        return imageView;

    }

    public Label createLabelQuantite(int quantite) {
        Label label = new Label();
        label.setFont(new Font("Times New Roman",18));
        label.setText(""+quantite);
        return label;
    }

    public Label createLabelNom(String nom) {
        Label label = new Label();
        label.setFont(new Font("Times New Roman",13));
        label.setText(nom);
        return label;
    }

    public Label createLabel(int quantite) {
        Label label = new Label();
        label.setFont(new Font("Times New Roman",18));
        label.setText(""+quantite);
        return label;
    }
}
