package fr.iut.saeterraria.sae.Vue;

import fr.iut.saeterraria.sae.Modele.Map.Map;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;


import java.net.URL;
import java.util.HashMap;


public class Fond extends CreateRessourceVisuel {

    private Map carte;
    private HashMap<Integer, Image> tiles;
    private static int id = 0;
    private TilePane pane;


    public Fond(TilePane pane,Map carte) {
        this.carte = carte;
        this.tiles = new HashMap<>();
        this.pane = pane;
        initialiseTile();
        afficherCarte();
    }


    public void ajoutTile(String imagePath) {
        tiles.put(id, createImage(imagePath));
        id++;
    }

    public void initialiseTile() {
        ajoutTile("/Tiles/Fond_noir.png");
        ajoutTile("/Tiles/Dirt_1.png");
        ajoutTile("/Tiles/Dirt_2.png");
        ajoutTile("/Tiles/Ciel.png");
        ajoutTile("/Tiles/piques_vorpales.png");
    }

    // Permet d'afficher le terrain dans la scène (Pane principal)
    public void afficherCarte() {
        pane.getChildren().clear();
        for (int i = 0; i < carte.getLigne(); i++) { //Ligne
            for (int j = 0; j < carte.getColonne(); j++) { //Colonne
                this.pane.getChildren().add(new ImageView(tiles.get(carte.getCase(i, j))));
            }
        }
        System.out.println(this.pane.getChildren().size());
    }

    public void changeBloc(int x, int y){

    }

    public HashMap<Integer, Image> getTiles() {
        return tiles;
    }




    public Image createImage(String imagePath) {

        URL imageURL = getClass().getResource(imagePath);
        Image image = new Image(String.valueOf(imageURL));
        ImageView imageView = new ImageView(image);
        return image;

    }


}
