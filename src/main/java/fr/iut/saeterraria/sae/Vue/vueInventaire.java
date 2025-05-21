package fr.iut.saeterraria.sae.Vue;

import fr.iut.saeterraria.sae.Modele.Objets.Item;
import fr.iut.saeterraria.sae.Modele.Personnages.Joueur;
import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;

import java.util.HashMap;

public class vueInventaire extends CreateImage{
    private Pane fond;
    private Button quitterInventaire;
    private Pane screenInventaire;
    private Joueur player;
    private GridPane tableauInventaire;

    // gridpane.add(new Button(), 1, 0); // column=1 row=0
    public vueInventaire(Button button, Pane pane, Joueur joueur, GridPane tableauInventaire, Pane fond) {
        this.quitterInventaire = button;
        this.screenInventaire = pane;
        this.player = joueur;
        this.tableauInventaire = tableauInventaire;
        this.fond = fond;
        afficherInventaire();
    }
    public void afficherInventaire() {
        // Affiche l'hotbar
        for (int j = 0; j < tableauInventaire.getColumnCount(); j++) {
            afficheItemQuantite("/Tiles/Dirt_1.png",2,j,0);
        }
        // Affiche l'inventaire
        for (int i = 1; i < tableauInventaire.getRowCount(); i++) {
            for (int j = 0; j < tableauInventaire.getColumnCount(); j++) {
                afficheItemQuantite("/Tiles/Roche_moche.png", 5,j,i);
            }
        }
    }

    public void afficheItemQuantite(String path,int quantite, int j, int i){//j la colonne et i la ligne
        HBox hBox = new HBox();
        StackPane stackPane = new StackPane();
        stackPane.getChildren().add(hBox);
        hBox.setAlignment(Pos.CENTER);
        hBox.setSpacing(5);
        hBox.getChildren().add(super.createImageView(path,(int)(tableauInventaire.getWidth()/tableauInventaire.getColumnCount()),((int)tableauInventaire.getHeight()/tableauInventaire.getRowCount())));//Item
        hBox.getChildren().add(createLabel(quantite));//Quantite
        tableauInventaire.add(stackPane,j,i);
    }

    public Label createLabel(int quantite) {
        Label label = new Label();
        label.setFont(new Font("Times New Roman",18));
        label.setText(""+quantite);
        return label;
    }

    public void chercheItem(){

    }
}
