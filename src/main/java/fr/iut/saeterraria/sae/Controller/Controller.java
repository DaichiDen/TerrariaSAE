package fr.iut.saeterraria.sae.Controller;

import fr.iut.saeterraria.sae.Modele.Jeu;
import fr.iut.saeterraria.sae.Modele.Personnages.Joueur;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private TilePane fond;

    @FXML
    private ImageView map;
    @FXML
    private Pane screen;
    @FXML
    private Button mapButton;
    @FXML
    private Button scene1;
    @FXML
    private Button scene2;

    private Jeu jeu;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        jeu = new Jeu("Joueur",1024,1024);
        Platform.runLater(()->fond.requestFocus()); // Permet de faire fonctionner la méthode mouvement
        fond.setOnKeyPressed(Insert -> mouvement(Insert));
        creerSpriteJoueur(jeu.getJoueur());
    }

    public ImageView createImageView(String imagePath){

        URL imageURL = getClass().getResource(imagePath);
        Image image = new Image(String.valueOf(imageURL));
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(150);
        imageView.setFitHeight(150);
        return imageView;

    }


    //public void afficherMap()
    public void mouvement(KeyEvent event) {
        switch (event.getCode()){
            case UP: // Saute
                jeu.getJoueur().sauter();
                System.out.println("Saute");
                break;
            case DOWN: // Descend d'une plateforme
                jeu.getJoueur().descendre();
                System.out.println("Descend");
                break;
            case LEFT: // Déplace à gauche
                jeu.getJoueur().déplacementGauche();
                System.out.println("Gauche");
                break;
            case RIGHT: // Déplace à droite
                jeu.getJoueur().déplacementDroite();
                System.out.println("Droite");
                break;
            case SPACE: // Saute
                jeu.getJoueur().sauter();
                System.out.println("Saute");
                break;
            case Q: // Déplace à gauche
                jeu.getJoueur().déplacementGauche();
                System.out.println("Gauche");
                break;
            case D: // Déplace à droite
                jeu.getJoueur().déplacementDroite();
                System.out.println("Droite");
                break;
            case S: // Descend d'une plateforme
                jeu.getJoueur().descendre();
                System.out.println("Descend");
                break;
            default:
                break;
        }
    }

    // Crée le sprite du joueur et l'ajoute dans la vue
    private void creerSpriteJoueur(Joueur joueur){

        ImageView sprite = createImageView("/Sprite/character.png");
        sprite.setId(joueur.getNom());
        sprite.translateXProperty().bind(joueur.xProperty());
        sprite.translateYProperty().bind(joueur.yProperty());
        sprite.setFitWidth(100);
        sprite.setFitHeight(80);
        fond.getChildren().add(sprite);
    }


}