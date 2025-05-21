package fr.iut.saeterraria.sae.Controller;

import fr.iut.saeterraria.sae.Modele.Jeu;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
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
                if (inventaireOuvert == false){
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
