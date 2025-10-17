package fr.iut.saeterraria.sae.Vue;

import fr.iut.saeterraria.sae.Modele.Entites.Joueur;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;

public class VueHotbar extends SpriteItem {
    private GridPane hotBar;

    public VueHotbar( GridPane hotBar) {
        this.hotBar = hotBar;
        afficherHotBar();
    }

    public void afficherHotBar() {

        // Affiche l'hotbar
        for (int j = 0; j < hotBar.getColumnCount(); j++) {
            if ((Joueur.getUniqueJoueur().getInventaire().getCase(0,j).getItem().getCodeObjet() != 0)) {

                String URL = super.getHmap().get((Joueur.getUniqueJoueur().getInventaire().getCase(0,j).getItem().getCodeObjet()));



                int quantite = (Joueur.getUniqueJoueur().getInventaire().getCase(0,j).getQuantite());
                afficheItemQuantite(URL, quantite,j);
            } else {
                structureHotBar(j,false);
            }
        }
    }

    public void afficheItemQuantite(String path, int quantite, int colonne) {
        HBox hBox;
        if (Joueur.getUniqueJoueur().getMainCourante() == colonne) {
            hBox = structureHotBar(colonne, true);
        } else {
            hBox = structureHotBar(colonne, false);
        }
        hBox.getChildren().add(super.createImageView(path, 50, 50));//Item
        hBox.getChildren().add(createLabelQuantite(quantite)); //Quantite
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
        if (Joueur.getUniqueJoueur().getInventaire().getCase(0,colonne).getItem().getCodeObjet() != 0) { // Si la case a un item

            String URL = super.getHmap().get((Joueur.getUniqueJoueur().getInventaire().getCase(0,colonne).getItem().getCodeObjet()));

            int quantite = Joueur.getUniqueJoueur().getInventaire().getCase(0,colonne).getQuantite();
                afficheItemQuantite(URL, quantite,colonne);
        }
        else if (Joueur.getUniqueJoueur().getMainCourante() == colonne) {
            structureHotBar(colonne,true);
        }
        else { // Si la case de la hotbar est vide
            structureHotBar(colonne, false);
        }
    }
}
