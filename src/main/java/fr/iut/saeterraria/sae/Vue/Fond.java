package fr.iut.saeterraria.sae.Vue;
import fr.iut.saeterraria.sae.Modele.Jeu;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;


import java.net.URL;
import java.util.HashMap;


public class Fond extends CreateRessourceVisuel {

    private Jeu jeu;
    private HashMap<Integer, Image> tiles;
    private static int id = 0;
    private TilePane pane;


    public Fond(TilePane pane,Jeu jeu) {
        this.jeu = jeu;
        this.tiles = new HashMap<>();
        this.pane = pane;
        initialiseTile();
        afficherCarte();
    }


    public void ajoutTile(String imagePath) {
        System.out.println(id);
        tiles.put(id, createImage(imagePath));
        id++;
    }

    public void initialiseTile() {
        ajoutTile("/Tiles/Ciel.png");
        ajoutTile("/Tiles/Dirt_1.png");
        ajoutTile("/Tiles/Dirt_2.png");
        ajoutTile("/Tiles/Fond_noir.png");
        ajoutTile("/Tiles/piques_vorpales.png");
    }

    // Permet d'afficher le terrain dans la scène (Pane principal)
    public void afficherCarte() {
        pane.getChildren().clear();
        for (int i = 0; i < jeu.getCarte().getLigne(); i++) { //Ligne
            for (int j = 0; j < jeu.getCarte().getColonne(); j++) { //Colonne
                this.pane.getChildren().add(new ImageView(tiles.get(jeu.getCarte().getCase(i, j))));
            }
        }
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
