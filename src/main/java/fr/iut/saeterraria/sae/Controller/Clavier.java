package fr.iut.saeterraria.sae.Controller;

import fr.iut.saeterraria.sae.Modele.Jeu;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;


public class Clavier implements EventHandler<KeyEvent> {
    private Jeu jeu;


    public Clavier(Jeu jeu) {
        this.jeu=jeu;

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

    public void actionJoueur(KeyEvent event) {
        switch (event.getCode()) {
            case UP: // Saute
                jeu.getJoueur().sauter();
                System.out.println("Saute");
                break;
//            case DOWN: // Descend d'une plateforme
//                jeu.getJoueur().descendre();
//                System.out.println("Descend");
//                break;
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
//            case S: // Descend d'une plateforme
//                jeu.getJoueur().descendre();
//                System.out.println("Descend");
//                break;
            case I:
                break;

            default:
                break;
        }
    }

    @Override
    public void handle(KeyEvent keyEvent) {
        if(keyEvent.getEventType().equals(KeyEvent.KEY_PRESSED)){
            actionJoueur(keyEvent);
        }
        else if (keyEvent.getEventType().equals(KeyEvent.KEY_RELEASED)){
            stopmouvement(keyEvent);
        }
    }
}
