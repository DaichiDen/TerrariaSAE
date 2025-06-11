package fr.iut.saeterraria.sae.Vue;

import fr.iut.saeterraria.sae.Modele.Jeu;

import fr.iut.saeterraria.sae.Modele.Personnages.Joueur;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;


public class SpriteJoueur extends CreateRessourceVisuel {


    private Pane screen;
    private Jeu jeu;
    private int width, height;

    private ImageView spriteActuel;
    private String dernierEtat = "";
    private ImageView marchedroite = createImageView("/Sprite/Chevalier_marcheDroite_Bon.gif", width, height);
    private ImageView marchegauche = createImageView("/Sprite/Chevalier_marcheGauche_Bon.gif", width, height);
    private ImageView marcheNon = createImageView("/Sprite/Hero_stop.png", width, height);


    public SpriteJoueur(Jeu jeu, Pane screen) {
        this.jeu = jeu;
        this.screen = screen;
        this.width = 150;
        this.height = 150;
        jeu.getJoueur().marcheGaucheProperty().addListener((obs, oldVal, newVal) -> mettreAJourSpriteJoueur(jeu.getJoueur()));
        jeu.getJoueur().marcheDroiteProperty().addListener((obs, oldVal, newVal) -> mettreAJourSpriteJoueur(jeu.getJoueur()));
        jeu.getJoueur().xProperty().addListener((obs, oldVal, newVal) -> mettreAJourSpriteJoueur(jeu.getJoueur()));

        screen.translateXProperty().bind(jeu.getJoueur().xProperty().multiply(-1).add(20*32));
        screen.translateYProperty().bind(jeu.getJoueur().yProperty().multiply(-1).add(12*32));
    }

    public void mettreAJourSpriteJoueur(Joueur joueur) {
        String etatActuel = "";


        if (!joueur.estVivant()) {
            etatActuel = "mort";
        } else if (joueur.getMarcheGauche()) {
            etatActuel = "gauche";
        } else if (joueur.getMarcheDroite()) {
            etatActuel = "droite";
        } else if ((!joueur.getMarcheDroite() && !joueur.getMarcheGauche()) || (joueur.getMarcheDroite() && joueur.getMarcheGauche())) {
            etatActuel = "stop";
        }
        if (joueur.getEnDash()) {
            if (joueur.getDirectionDash().equals("droite")) {
                etatActuel = "dash_droit";
            } else {
                etatActuel = "dash_gauche";
            }
        }

        // Si l'état n'a pas changé, ne rien faire
        if (etatActuel.equals(dernierEtat)) {
            return;
        }

        // Sinon, supprimer le sprite précédent s’il existe
        if (spriteActuel != null) {
            screen.getChildren().remove(spriteActuel);
        }

        // Créer le bon sprite
        if (etatActuel.equals("gauche")) {
            spriteActuel = marchegauche;
        } else if (etatActuel.equals("droite")) {
            spriteActuel = marchedroite;
        } else if (etatActuel.equals("stop")) {
            spriteActuel = marcheNon;
        } else if (etatActuel.equals("mort")) {
            spriteActuel = createImageView("/Sprite/mort_hd(1).gif", width, height);
        } else if (etatActuel.equals("dash_droit")) {
            spriteActuel = createImageView("/Sprite/Dash-Droite1.gif", width, height);
        } else if (etatActuel.equals("dash_gauche")) {
            spriteActuel = createImageView("/Sprite/Dash-Gauche1.gif", width, height);
        }


        spriteActuel.setId(joueur.getNom());
        spriteActuel.translateXProperty().bind(joueur.xProperty());
        spriteActuel.translateYProperty().bind(joueur.yProperty());
        spriteActuel.setFitWidth(54);
        spriteActuel.setFitHeight(64);
        screen.getChildren().add(spriteActuel);

        dernierEtat = etatActuel;
    }

}









