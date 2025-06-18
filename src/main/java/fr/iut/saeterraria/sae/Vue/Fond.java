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
        ajoutTile("/Tiles/Ciel.png"); // 0 et ensuite dcp +1
        ajoutTile("/Tiles/Dirt_1.png");
        ajoutTile("/Tiles/Dirt_2.png");
        ajoutTile("/Tiles/Bois.png");
        ajoutTile("/Tiles/Minerais_charbon.png");
        ajoutTile("/Tiles/Roche_moche.png");
        ajoutTile("/Tiles/Fond_noir.png");
        ajoutTile("/Tiles/Minerais_fer.png");
        ajoutTile("/Tiles/Fond_noir.png");
        ajoutTile("/Tiles/Minerais_DELJCCium.png");
        for (int i = 10; i < 17; i++) {
            ajoutTile("/Tiles/Fond_noir.png");
        }
        ajoutTile("/Tiles/bedrock.png");

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

    public TilePane getEnvironnement() {
        return environnement;
    }

    public void updateMapX() {
        //X+1 = ajouter 1 colonne et donc ajouter à chaque ligne une case
        for (int x = 0; x < this.environnement.getPrefRows(); x++) {
            this.environnement.getChildren().add((x*environnement.getPrefColumns()+environnement.getPrefColumns()+x),new ImageView(tiles.get(this.jeu.getCarte().getCase(x, environnement.getPrefColumns()))));
        }
    }

    public void updateMapY() {
        //Y+1 = ajouter 1 ligne donc ajouter à chaque colonne une case
        for (int y = 0; y < this.environnement.getPrefColumns(); y++) {
            this.environnement.getChildren().add(((environnement.getPrefRows()*environnement.getPrefColumns())+y),new ImageView(tiles.get(this.jeu.getCarte().getCase(environnement.getPrefRows(), y))));
        }
    }


}
