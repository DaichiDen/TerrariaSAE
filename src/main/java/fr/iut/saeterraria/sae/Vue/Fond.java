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
    public void initialiseTile() {
        ajoutTile("/Tiles/Fond_noir.png");
        ajoutTile("/Tiles/Dirt_1.png");
        ajoutTile("/Tiles/Dirt_2.png");
        ajoutTile("/Tiles/Ciel.png");
    }

    public void afficherCarte(){
        for (int i = 0; i < 16; i++) {//Colonne
            for (int j = 0; j < 32; j++) {//Ligne
                this.pane.getChildren().add(new ImageView(tiles.get(carte.getCase(i,j))));
            }
        }
    }

    public Image createImage(String imagePath){

        URL imageURL = getClass().getResource(imagePath);
        Image image = new Image(String.valueOf(imageURL));
        ImageView imageView = new ImageView(image);
        return image;

    }
}
