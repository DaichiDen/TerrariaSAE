package fr.iut.saeterraria.sae.Vue;

import fr.iut.saeterraria.sae.Modele.Jeu;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;

import java.util.HashMap;

public class VueHotbar extends SpriteItem {
    private Jeu jeu;
    private GridPane hotBar;

    public VueHotbar(Jeu jeu, GridPane hotBar) {
        this.jeu = jeu;
        this.hotBar = hotBar;
        afficherHotBar();
    }

    public void afficherHotBar() {

        // Affiche l'hotbar
        for (int j = 0; j < hotBar.getColumnCount(); j++) {
            if ((jeu.getJoueur().getInventaire().getInventaireJoueur()[0][j].getItem().getCodeObjet() != 0)) {
                String URL = "/Tiles/".concat(super.getHmap().get((jeu.getJoueur().getInventaire().getInventaireJoueur())[0][j].getItem().getCodeObjet())).concat(".png");

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
            String URL = "/Tiles/".concat(super.getHmap().get((jeu.getJoueur().getInventaire().getInventaireJoueur())[0][colonne].getItem().getCodeObjet())).concat(".png");
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
