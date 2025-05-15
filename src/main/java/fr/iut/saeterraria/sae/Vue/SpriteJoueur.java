package fr.iut.saeterraria.sae.Vue;

import fr.iut.saeterraria.sae.Modele.Jeu;

import fr.iut.saeterraria.sae.Modele.Personnages.Joueur;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;



import java.net.URL;


public class SpriteJoueur {

    private Pane screen;
    private Jeu jeu;

    private Rectangle2D hitboxJoueur;


    public SpriteJoueur(Jeu jeu, Pane screen){
        this.jeu = jeu;
        this.screen = screen;
    }

    public void stopmouvement(KeyEvent event) {
        switch (event.getCode()) {
            case D:
                jeu.getJoueur().setMarcheDroite(false);
                break;
            case RIGHT:
                jeu.getJoueur().setMarcheDroite(false);
                break;
            case LEFT:
                jeu.getJoueur().setMarcheGauche(false);
                break;
            case Q:
                jeu.getJoueur().setMarcheGauche(false);
        }
    }

    public void mouvement(KeyEvent event) {
        switch (event.getCode()) {
            case UP: // Saute
                jeu.getJoueur().sauter();
                System.out.println("Saute");
                break;
            case DOWN: // Descend d'une plateforme
                jeu.getJoueur().descendre();
                System.out.println("Descend");
                break;
            case LEFT: // Déplace à gauche
                jeu.getJoueur().setMarcheGauche(true);
                System.out.println("Gauche");
                break;
            case RIGHT: // Déplace à droite
                jeu.getJoueur().setMarcheDroite(true);
                System.out.println("Droite");
                break;
            case SPACE: // Saute
                jeu.getJoueur().sauter();
                System.out.println("Saute");
                break;
            case Q: // Déplace à gauche
                jeu.getJoueur().setMarcheGauche(true);
                System.out.println("Gauche");
                break;
            case D: // Déplace à droite
                jeu.getJoueur().setMarcheDroite(true);
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

    // Permet de renvoyer une fenêtre d'image par son URL
    public ImageView createImageView(String imagePath){

        URL imageURL = getClass().getResource(imagePath);
        Image image = new Image(String.valueOf(imageURL));
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(150);
        imageView.setFitHeight(150);
        return imageView;

    }

    // Permet d'associer l'image au joueur au pane (conteneur principal)
    public void creerSpriteJoueur(Joueur joueur){
        ImageView sprite = createImageView("/Sprite/test.png");
        sprite.setId(joueur.getNom());
        sprite.translateXProperty().bind(joueur.xProperty());
        sprite.translateYProperty().bind(joueur.yProperty());
        sprite.setFitWidth(32);
        sprite.setFitHeight(64);
        screen.getChildren().add(sprite);
    }

    }









