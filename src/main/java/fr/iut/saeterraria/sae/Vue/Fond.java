package fr.iut.saeterraria.sae.Vue;

import fr.iut.saeterraria.sae.Modele.Jeu;
import fr.iut.saeterraria.sae.Modele.Map.Map;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;

import java.net.URL;
import java.util.HashMap;

public class Fond {
    private Map carte;
    private HashMap<Integer, Image> tiles;
    private static int id=0;
    private int longueur;
    private int largeur;
    private TilePane pane;

    public Fond(int longueur,int largeur, TilePane pane){
        this.carte = new Map();
        this.tiles = new HashMap<>();
        this.pane = pane;
    }

    public void ajoutTile(String imagePath){
      tiles.put(id, createImage(imagePath));
      id++;
    }

    public void afficherCarte(int x, int y){
        this.pane.getChildren().add(new ImageView(tiles.get(carte.getCase(x,y))));
    }

    public Image createImage(String imagePath){

        URL imageURL = getClass().getResource(imagePath);
        Image image = new Image(String.valueOf(imageURL));
        ImageView imageView = new ImageView(image);
        return image;

    }
}
