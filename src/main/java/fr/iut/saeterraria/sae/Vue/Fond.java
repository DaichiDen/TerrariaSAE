
package fr.iut.saeterraria.sae.Vue;

import fr.iut.saeterraria.sae.Modele.Jeu;
import fr.iut.saeterraria.sae.Modele.Objets.Coffre;
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
        ajoutTile("/Tiles/Roche_moche.png");//5

        ajoutTile("/Tiles/Minerais_fer.png");
        ajoutTile("/Tiles/Minerais_DELJCCium.png");
        ajoutTile("/Tiles/piques_vorpales.png");
        ajoutTile("/Tiles/bedrock.png");
        ajoutTile("/Tiles/Noir.png"); //10

        ajoutTile("/Tiles/Fond_noir.png");
        ajoutTile("/Tiles/Etabli.png");
        ajoutTile("/Tiles/Forge.png");
        ajoutTile("/Tiles/Four.png");
        ajoutTile("/Tiles/planche_bois.png");//15

        ajoutTile("/Tiles/toit_bois_gauche.png");
        ajoutTile("/Tiles/toit_bois_droit.png");
        ajoutTile("/Tiles/Fonce_mur_bois.png");
        ajoutTile("/Tiles/feuilles.png");
        ajoutTile("/Tiles/Coffre.png"); // 20

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
