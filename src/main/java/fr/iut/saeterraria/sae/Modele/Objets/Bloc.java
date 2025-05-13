package fr.iut.saeterraria.sae.Modele.Objets;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.image.Image;

public class Bloc {
    private StringProperty nom;
    private int width, height;


    public Bloc (String nom, Image tiles) {
        this.nom = new SimpleStringProperty(nom);
        this.width=32;
        this.height=32;
    }

}
