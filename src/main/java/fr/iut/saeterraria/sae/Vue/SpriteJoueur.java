package fr.iut.saeterraria.sae.Vue;

import fr.iut.saeterraria.sae.Modele.Jeu;

import fr.iut.saeterraria.sae.Modele.Personnages.Joueur;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;


public class SpriteJoueur extends CreateRessourceVisuel {


    private Pane screen;
    private Jeu jeu;
    private int width, height;
    private TilePane background;
    private Pane opacite;

    private ImageView spriteActuel;
    private String dernierEtat = "";
    private boolean bindXActif = true;
    private boolean bindYActif = true;
    private ImageView marchedroite = createImageView("/Sprite/Chevalier_marcheDroite_Bon.gif", width, height);
    private ImageView marchegauche = createImageView("/Sprite/Chevalier_marcheGauche_Bon.gif", width, height);
    private ImageView marcheNon = createImageView("/Sprite/Hero_stop.png", width, height);


    public SpriteJoueur(Jeu jeu, Pane screen,TilePane background, Pane opacite) {
        this.jeu = jeu;
        this.screen = screen;
        this.width = 150;
        this.height = 150;
        jeu.getJoueur().marcheGaucheProperty().addListener((obs, oldVal, newVal) -> mettreAJourSpriteJoueur(jeu.getJoueur()));
        jeu.getJoueur().marcheDroiteProperty().addListener((obs, oldVal, newVal) -> mettreAJourSpriteJoueur(jeu.getJoueur()));
        jeu.getJoueur().xProperty().addListener((obs, oldVal, newVal) -> mettreAJourSpriteJoueur(jeu.getJoueur()));
        this.background = background;
        this.opacite = opacite;
        background.getChildren().add(createImageView("/Backgrounds/background.jpg",2000,1120));

        bindAll();
    }

    public void mettreAJourSpriteJoueur(Joueur joueur) {

        if (bindXActif && joueur.xProperty().getValue() <= 20*32) { //unbind gauche
            unbindX();
            bindXActif = false;
        }
        else if (bindXActif && joueur.xProperty().getValue() >= 100*32){ //unbind droite 40 sera remplacé par la map final
            unbindX();
            bindXActif = false;
        }
        else if (!bindXActif && joueur.xProperty().getValue() > 20*32 && joueur.xProperty().getValue() < 100*32) {
            bindX();
            bindXActif = true;
        }

        if (bindYActif && joueur.yProperty().getValue()<=14*32){ // unbind haut
            unbindY();
            bindYActif = false;
        }
        else if (bindYActif && joueur.yProperty().getValue()>=(80-8)*32){ //unbind bas 50 est le nombre max de ligne
            unbindY();
            bindYActif = false;
        }
        else if (!bindYActif && joueur.yProperty().getValue() > 14*32 && joueur.yProperty().getValue() < (80-8)*32) {
            bindY();
            bindYActif = true;
        }
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

    public void unbindX(){
        screen.translateXProperty().unbind();
    }
    public void unbindY(){
        screen.translateYProperty().unbind();
    }

    public void bindAll(){
        bindX();
        bindY();
        background.translateXProperty().bind(screen.translateXProperty().multiply(-0.4));
        background.translateYProperty().bind(screen.translateYProperty().multiply(-1));
        opacite.translateXProperty().bind(screen.translateXProperty().multiply(-0.4));
        opacite.translateYProperty().bind(screen.translateYProperty().multiply(-1));
    }
    public void bindX(){
        screen.translateXProperty().bind(jeu.getJoueur().xProperty().multiply(-1).add(20*32));
    }
    public void bindY(){
        screen.translateYProperty().bind(jeu.getJoueur().yProperty().multiply(-1).add(14*32));
    }
}









