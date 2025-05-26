package fr.iut.saeterraria.sae.Vue;

import fr.iut.saeterraria.sae.Modele.Jeu;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;

import java.util.HashMap;

public class vueHotbar extends CreateRessourceVisuel {
    private Jeu jeu;
    private HashMap<Integer, String> items;
    private GridPane hotBar;

    public vueHotbar(Jeu jeu, GridPane hotBar) {
        this.jeu = jeu;
        this.items = new HashMap<>();
        initialiseItems();
        this.hotBar = hotBar;
        afficherHotBar();
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

    public void afficherHotBar() {

        // Affiche l'hotbar
        for (int j = 0; j < hotBar.getColumnCount(); j++) {
            if ((jeu.getJoueur().getInventaire().getInventaireJoueur()[0][j].getItem().getCodeObjet() != 0)) {
                String URL = "/Tiles/".concat(items.get((jeu.getJoueur().getInventaire().getInventaireJoueur())[0][j].getItem().getCodeObjet())).concat(".png");
                int quantite = (jeu.getJoueur().getInventaire().getInventaireJoueur())[0][j].getQuantite();
                afficheItemQuantite(URL, quantite,j);
            } else {
                structureHotBar(j,false);
            }
        }
    }

    public void afficheItemQuantite(String path, int quantite, int colonne) {
        HBox hBox;
        if (jeu.getJoueur().getMainCourante() == colonne) {
            hBox = structureHotBar(colonne, true);
        } else {
            hBox = structureHotBar(colonne, false);
        }
        hBox.getChildren().add(super.createImageView(path, 50, 50));//Item
        hBox.getChildren().add(createLabel(quantite)); //Quantite
    }

    public HBox structureHotBar(int j, boolean maincourante) {
        HBox hBox = new HBox();
        StackPane stackPane = new StackPane();
        Region region = new Region();
        region.setOpacity(0.8);
        stackPane.getChildren().add(region);
        stackPane.getChildren().add(hBox);
        hBox.setAlignment(Pos.CENTER);
        hBox.setSpacing(5);
        if(maincourante) {
            region.setStyle("-fx-background-color: red");
        }
        else {
            region.setStyle("-fx-background-color: beige");
        }
        hotBar.add(stackPane,j,0);
        return hBox;
    }


    public void updateElement(int colonne) { //Quand le joueur obtient/perd un item
        Node caseInventaire = null;
        for (Node node : hotBar.getChildren()) {
            Integer colonne1 = GridPane.getColumnIndex(node);
            if(colonne1==null) {
                colonne1 = 0;
            }
            if( colonne1==colonne) {
                caseInventaire = node;
            }
        }
        hotBar.getChildren().remove(caseInventaire);
        if (jeu.getJoueur().getInventaire().getInventaireJoueur()[0][colonne].getItem().getCodeObjet() != 0) { // Si la case a un item
            String URL = "/Tiles/".concat(items.get((jeu.getJoueur().getInventaire().getInventaireJoueur())[0][colonne].getItem().getCodeObjet())).concat(".png");
            int quantite = jeu.getJoueur().getInventaire().getInventaireJoueur()[0][colonne].getQuantite();
                afficheItemQuantite(URL, quantite,colonne);
        }
        else if (jeu.getJoueur().getMainCourante() == colonne) {
            structureHotBar(colonne,true);
        }
        else { // Si la case de la hotbar est vide
            structureHotBar(colonne, false);
        }
    }
}
