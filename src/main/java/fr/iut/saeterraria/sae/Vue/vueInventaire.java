package fr.iut.saeterraria.sae.Vue;

import fr.iut.saeterraria.sae.Modele.Personnages.Joueur;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.text.Font;

import java.util.HashMap;

public class vueInventaire extends CreateRessourceVisuel {
    private Pane fond;
    private Button quitterInventaire;
    private Pane screenInventaire;
    private Joueur player;
    private GridPane tableauInventaire;
    private HashMap<Integer, String> items;

    // gridpane.add(new Button(), 1, 0); // column=1 row=0
    public vueInventaire(Button button, Pane pane, Joueur joueur, GridPane tableauInventaire, Pane fond) {
        this.quitterInventaire = button;
        this.screenInventaire = pane;
        this.player = joueur;
        this.tableauInventaire = tableauInventaire;
        this.fond = fond;
        this.items = new HashMap<>();
        initialiseItems();
        afficherInventaire();
    }

    // Permet de lier l'id de l'item avec le chemin amenant vers son image
    public void initialiseItems() {
        // Blocs
        items.put(1, "Dirt_1"); // Terre Haute
        items.put(2, "Dirt_2"); // Terre Basse
        items.put(3, "Ciel"); // Bois
        items.put(4, ""); // Minerai Charbon
        items.put(5, ""); // Charbon
        items.put(6, ""); // Pierre
        items.put(7, ""); // Minerai Fer
        items.put(8, ""); // Fer
        items.put(9, ""); // Glace

        // Blocs à constructions
        items.put(10, ""); // Etabli
        items.put(11, ""); // Forge
        items.put(12, ""); // Alambique

        /* ---------- À ajouter le reste ------------- */

        // Outils
        items.put(50, ""); // Pelle de bois
        items.put(51, ""); // Hache de bois
        items.put(52, ""); // Pioche de bois
        items.put(53, ""); // Pelle de pierre
        items.put(54, ""); // Hache de pierre
        items.put(55, ""); // Pioche de pierre
        items.put(56, ""); // Pelle de fer
        items.put(57, ""); // Hache de fer
        items.put(58, ""); // Pioche de fer
        items.put(59, ""); // Pelle de DELJCCium
        items.put(60,""); // Hache de DELJCCium
        items.put(61,""); // Pioche de DELJCCium

        // Armures
        items.put(62,""); // Casque en fer
        items.put(63,""); // Casque en DELJCCium
        items.put(64,""); // Plastron en fer
        items.put(65,""); // Plastron en DELJCCium
        items.put(66,""); // Jambière en fer
        items.put(67,""); // Jambière en DELJCCium
        items.put(68,""); // Botte en fer
        items.put(69,""); // Botte en DELJCCium
    }
    
    public void afficherInventaire() {

        // Affiche l'hotbar
        for (int j = 0; j < tableauInventaire.getColumnCount(); j++) {
            if ( (player.getInventaire().getInventaireJoueur()[0][j].getItem() != null)) {

                afficheItemQuantite("/Tiles/".concat(items.get( (player.getInventaire().getInventaireJoueur())[0][j].getItem().getCodeObjet())).concat(".png"), (player.getInventaire().getInventaireJoueur())[0][j].getQuantite(), 0, j,0);
            }
            else{
                HBox hBox = new HBox();
                StackPane stackPane = new StackPane();
                Region region = new Region();
                region.setStyle("-fx-background-color: beige");
                region.setOpacity(0.8);
                stackPane.getChildren().add(region);
                stackPane.getChildren().add(hBox);
                hBox.setAlignment(Pos.CENTER);
                hBox.setSpacing(5);
                tableauInventaire.add(stackPane,j,0);
            }
        }
        // Affiche l'inventaire
        for (int i = 1; i < tableauInventaire.getRowCount(); i++) {
            for (int j = 0; j < tableauInventaire.getColumnCount(); j++) {
                if (player.getInventaire().getInventaireJoueur()[i][j].getItem() != null) {
                    afficheItemQuantite("/Tiles/".concat(items.get((player.getInventaire().getInventaireJoueur())[i][j].getItem().getCodeObjet())).concat(".png"), (player.getInventaire().getInventaireJoueur())[i][j].getQuantite(), i, j, 1);
                }
                else {
                    HBox hBox = new HBox();
                    StackPane stackPane = new StackPane();
                    Region region = new Region();
                    region.setStyle("-fx-background-color: grey");
                    region.setOpacity(0.8);
                    stackPane.getChildren().add(region);
                    stackPane.getChildren().add(hBox);
                    hBox.setAlignment(Pos.CENTER);
                    hBox.setSpacing(5);
                    tableauInventaire.add(stackPane,j,i);
                }
            }
        }
    }

    public void afficheItemQuantite(String path,int quantite, int i, int j, int zoneinventaire){//j la colonne et i la ligne
        HBox hBox = new HBox();
        StackPane stackPane = new StackPane();
        Region region = new Region();
        if(zoneinventaire==0) {
            region.setStyle("-fx-background-color: beige");
        }
        else {
            region.setStyle("-fx-background-color: grey");
        }
        region.setOpacity(0.8);
        stackPane.getChildren().add(region);
        stackPane.getChildren().add(hBox);
        hBox.setAlignment(Pos.CENTER);
        hBox.setSpacing(5);
        hBox.getChildren().add(super.createImageView(path,(int)(tableauInventaire.getWidth()/tableauInventaire.getColumnCount()),((int)tableauInventaire.getHeight()/tableauInventaire.getRowCount())));//Item
        hBox.getChildren().add(createLabel(quantite)); //Quantite
        tableauInventaire.add(stackPane,i,j);
    }

    public void chercheItem(){

    }
}
