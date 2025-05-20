package fr.iut.saeterraria.sae.Vue;

import fr.iut.saeterraria.sae.Modele.Objets.Item;
import fr.iut.saeterraria.sae.Modele.Personnages.Joueur;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

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
    }
    public void afficherInventaire() {
        // Affiche l'hotbar
        for (int j = 0; j < 7; j++) {
            tableauInventaire.add(super.createImageView("/Tiles/Dirt_1.png",150,150),0,j);
        }
        // Affiche l'inventaire
        for (int i = 2; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                tableauInventaire.add(super.createImageView("/Tiles/Ciel.png",150,150),i,j);
            }
        }
    }

    public void chercheItem(){

    }
}
