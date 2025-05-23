package fr.iut.saeterraria.sae.Controller;

import fr.iut.saeterraria.sae.Modele.Jeu;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;

import java.util.HashSet;
import java.util.Set;




public class Clavier implements EventHandler<KeyEvent> {
    private Jeu jeu;
    @FXML
    private TilePane fond;
    @FXML
    private Button openInventaire;
    @FXML
    private Button quitterInventaire;
    @FXML
    private Pane screenInventaire;
    private final Set<KeyCode> touchesAppuyees = new HashSet<>();
    private boolean inventaireOuvert = false;


    public Clavier(Jeu jeu, Pane screenInventaire,Button quitterInventaire,Button openInventaire,TilePane fond) {
        this.jeu=jeu;
        this.screenInventaire=screenInventaire;
        this.quitterInventaire=quitterInventaire;
        this.openInventaire=openInventaire;
        this.fond=fond;

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
                break;
            case M:
                jeu.getJoueur().getBarreVie().setVie(0);
                break;

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
            default:
                break;
        }
    }

    public void handle(KeyEvent event) { //
        KeyCode code = event.getCode(); // le code de la touche de l'event


        if (event.getEventType() == KeyEvent.KEY_PRESSED) {
            touchesAppuyees.add(code); // ajout du code à la liste pour les garder en mémoire (touches enfoncées)

            if (code == KeyCode.SPACE || code == KeyCode.UP) {
                jeu.getJoueur().sauter();
            }
            if(code == KeyCode.NUMPAD5){
                jeu.getJoueur().getBarreVie().setVie(0);
            }
            if(code == KeyCode.I ) {
                if (!inventaireOuvert){
                    jeu.getJoueur().getInventaire().viderTest();
                    ouvrirInventaire();
                    inventaireOuvert = true;
                }
                else{
                    exitInventaire();
                    inventaireOuvert = false;
                }

            }

        } else if (event.getEventType() == KeyEvent.KEY_RELEASED) {
            touchesAppuyees.remove(code);// touche retirée de la liste car relâchée

        }


    }

    public void update() {
        jeu.getJoueur().setMarcheDroite(touchesAppuyees.contains(KeyCode.RIGHT) || touchesAppuyees.contains(KeyCode.D));
        jeu.getJoueur().setMarcheGauche(touchesAppuyees.contains(KeyCode.LEFT) || touchesAppuyees.contains(KeyCode.Q));
    }
    @FXML
    public void ouvrirInventaire() {
        openInventaire.setVisible(false);
        jeu.getJoueur().setMarcheDroite(false);
        jeu.getJoueur().setMarcheGauche(false);
        screenInventaire.setVisible(true);
    }
    @FXML
    public void exitInventaire(){
        screenInventaire.setVisible(false);
        openInventaire.setVisible(true);
        Platform.runLater(() -> fond.requestFocus());
    }

}
