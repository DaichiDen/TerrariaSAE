package fr.iut.saeterraria.sae.Vue;

import fr.iut.saeterraria.sae.Modele.Jeu;

import fr.iut.saeterraria.sae.Modele.Personnages.Joueur;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;



import java.net.URL;


public class SpriteJoueur extends CreateImage{

    private Pane screen;
    private Jeu jeu;

    private Rectangle2D hitboxJoueur;


    public SpriteJoueur(Jeu jeu, Pane screen){
        this.jeu = jeu;
        this.screen = screen;

        creerSpriteJoueur(this.jeu.getJoueur());



    }

    // Permet d'associer l'image au joueur au pane (conteneur principal)
    public void creerSpriteJoueur(Joueur joueur){
        ImageView sprite = createImageView("/Sprite/Hero_stop.png");
	    sprite.setId(joueur.getNom());
        sprite.translateXProperty().bind(joueur.xProperty());
        sprite.translateYProperty().bind(joueur.yProperty());
        sprite.setFitWidth(54);
        sprite.setFitHeight(64);
        screen.getChildren().add(sprite);
    }

    }









