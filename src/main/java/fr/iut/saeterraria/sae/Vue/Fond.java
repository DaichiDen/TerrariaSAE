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
    private TilePane environnement;


    public Fond(TilePane environnement,Jeu jeu) {
       this.jeu = jeu;
        this.tiles = new HashMap<>();
        this.environnement = environnement;
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
        for (int i = 0; i < environnement.getPrefRows(); i++) {
            for (int j = 0; j < environnement.getPrefColumns(); j++) {
                this.environnement.getChildren().add(new ImageView(tiles.get(this.jeu.getCarte().getCase(i, j))));
            }
        }
    }

    public HashMap<Integer, Image> getTiles() {
        return tiles;
    }

    public Image createImage(String imagePath) {

        URL imageURL = getClass().getResource(imagePath);
        Image image = new Image(String.valueOf(imageURL));
        return image;

    }

    public void updateMap(int MaxX, int MaxY) {
        //Y+1 = ajouter 1 colonne et donc ajouter à chaque ligne une case
        //X+1 = ajouter 1 ligne donc ajouter à chaque colonne une case
//        for (int x = 0; x < this.environnement.getPrefColumns(); x++) {
//            this.environnement.getChildren().add((y*tp.getPrefColumns())+x),new ImageView(tiles.get(this.jeu.getCarte().getCase(i, j))));
//        }
//        for (int y = 0; y < this.environnement.getPrefRows(); y++) {
//
//        }

    }


}
