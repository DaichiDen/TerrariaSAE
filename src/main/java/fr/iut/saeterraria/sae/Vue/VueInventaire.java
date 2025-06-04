package fr.iut.saeterraria.sae.Vue;

import fr.iut.saeterraria.sae.Modele.Personnages.Joueur;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.*;

import java.util.HashMap;

public class VueInventaire extends SpriteItem {
    private Pane fond;
    private Button quitterInventaire;
    private Pane screenInventaire;
    private Joueur player;
    private GridPane tableauInventaire;//Affichage de l'inventaire

    // gridpane.add(new Button(), 1, 0); // column=1 row=0
    public VueInventaire(Button button, Pane pane, Joueur joueur, GridPane tableauInventaire, Pane fond) {
        this.quitterInventaire = button;
        this.screenInventaire = pane;
        this.player = joueur;
        this.tableauInventaire = tableauInventaire;
        this.fond = fond;
        afficherInventaire();
    }

    // Permet de lier l'id de l'item avec le chemin amenant vers son image

    
    public void afficherInventaire() {

        // Affiche l'hotbar
        for (int j = 0; j < tableauInventaire.getColumnCount(); j++) {
            if ( (player.getInventaire().getInventaireJoueur()[0][j].getItem().getCodeObjet() != 0)) {
                String URL = "/Tiles/".concat(super.getHmap().get((player.getInventaire().getInventaireJoueur())[0][j].getItem().getCodeObjet())).concat(".png");
                System.out.println(URL);
                int quantite = (player.getInventaire().getInventaireJoueur())[0][j].getQuantite();
                afficheItemQuantite(URL,quantite, 0, j,0);
            }
            else{
                structureGridpane(j,0,0);
            }
        }
        // Affiche l'inventaire

        for (int i = 1; i < tableauInventaire.getRowCount(); i++) {
            for (int j = 0; j < tableauInventaire.getColumnCount(); j++) {
               if (player.getInventaire().getInventaireJoueur()[i][j].getItem().getCodeObjet() != 0) {
                   String URL = "/Tiles/".concat(super.getHmap().get((player.getInventaire().getInventaireJoueur())[i][j].getItem().getCodeObjet())).concat(".png");
                   int quantite = (player.getInventaire().getInventaireJoueur())[i][j].getQuantite();
                   afficheItemQuantite(URL, quantite, i, j, 1);
                }
                else {
                    structureGridpane(j,i,1);
                }
            }
        }
    }

    public void afficheItemQuantite(String path,int quantite, int i, int j, int zoneinventaire){//j la colonne et i la ligne
        HBox hBox = structureGridpane(j,i,zoneinventaire);
        hBox.getChildren().add(super.createImageView(path,50,50));//Item
        hBox.getChildren().add(createLabel(quantite)); //Quantite
    }

    public HBox structureGridpane(int colonne, int ligne, int zoneinventaire){
        HBox hBox = new HBox();
        StackPane stackPane = new StackPane();
        Region region = new Region();
        region.setOpacity(0.8);
        stackPane.getChildren().add(region);
        stackPane.getChildren().add(hBox);
        hBox.setAlignment(Pos.CENTER);
        hBox.setSpacing(5);
        tableauInventaire.add(stackPane,colonne,ligne);

        if(zoneinventaire==0) {
            region.setStyle("-fx-background-color: beige");
        }
        else {
            region.setStyle("-fx-background-color: grey");
        }
        return hBox;
    }

    public void updateElement(int ligne, int colonne) { //Quand le joueur obtient/perd un item
        Node caseInventaire = null;
            for (Node node : tableauInventaire.getChildren()) {
                Integer ligne1 = GridPane.getRowIndex(node);
                Integer colonne1 = GridPane.getColumnIndex(node);
                if(ligne1==null) {
                    ligne1 = 0;
                }
                if(colonne1==null) {
                    colonne1 = 0;
                }
                if(ligne1==ligne && colonne1==colonne) {
                    caseInventaire = node;
                }
            }
        tableauInventaire.getChildren().remove(caseInventaire);
        if(player.getInventaire().getInventaireJoueur()[ligne][colonne].getItem().getCodeObjet()!= 0) {
            String URL = "/Tiles/".concat(super.getHmap().get((player.getInventaire().getInventaireJoueur())[ligne][colonne].getItem().getCodeObjet())).concat(".png");
            int quantite = player.getInventaire().getInventaireJoueur()[ligne][colonne].getQuantite();

            if(ligne==0) {
                afficheItemQuantite(URL, quantite, ligne, colonne, 0);
            }
            else {
                afficheItemQuantite(URL, quantite, ligne, colonne, 1);
            }
        }
        else {
            if(ligne==0) {
                structureGridpane(colonne,ligne,0);
            }
            else {
                structureGridpane(colonne,ligne,1);
            }
        }
    }

}

