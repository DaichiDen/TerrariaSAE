package fr.iut.saeterraria.sae.Modele.Objets;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.image.Image;

public class Bloc {
    private StringProperty nom;
    private Image tiles;

    public Bloc (String nom, Image tiles) {
        this.nom = new SimpleStringProperty(nom);
        this.tiles = new Image("");
    }
}
