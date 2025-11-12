package fr.iut.saeterraria.sae.Controller;

import fr.iut.saeterraria.sae.Modele.Jeu;
import fr.iut.saeterraria.sae.Modele.Entites.Joueur;
import fr.iut.saeterraria.sae.Vue.VueBarreRaccourci;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;

import java.util.HashSet;
import java.util.Set;
/*
Gère toutes les instructions clavier que le joueur peut utiliser dans le jeu, certaines touches font réagir le programme
 */
public class Clavier implements EventHandler<KeyEvent> {
    @FXML
    private TilePane fond;
    @FXML
    private Button ouvrirInventaire;
    @FXML
    private Button quitterInventaire;
    @FXML
    private AnchorPane screenInventaire;
    @FXML
    private Pane screenPrincipal;

    private final Set<KeyCode> touchesAppuyees = new HashSet<>();
    private boolean inventaireOuvert = false;
    private VueBarreRaccourci vueBarreRaccourci;

    public Clavier( AnchorPane screenInventaire,Button quitterInventaire,Button ouvrirInventaire,TilePane fond, GridPane hotBarInventaire, Pane pane) {

        this.screenInventaire=screenInventaire;
        this.quitterInventaire=quitterInventaire;
        this.ouvrirInventaire=ouvrirInventaire;
        this.fond=fond;
        this.screenPrincipal=pane;
        this.vueBarreRaccourci = new VueBarreRaccourci(hotBarInventaire);
    }

    public void handle(KeyEvent event) { //
        KeyCode code = event.getCode(); // le code de la touche de l'event
        if (event.getEventType() == KeyEvent.KEY_PRESSED) {
            touchesAppuyees.add(code); // ajout du code à la liste pour les garder en mémoire (touches enfoncées)

            if (code == KeyCode.SPACE || code == KeyCode.UP) {
                Joueur.getUniqueJoueur().sauter();
            }

            if (code == KeyCode.NUMPAD5) {
                Joueur.getUniqueJoueur().estVivantProperty().set(false);
            }

            if(code == KeyCode.I ) {
                if (!inventaireOuvert){
                    ouvrertureInventaire();
                    inventaireOuvert = true;
                } else {
                    sortirInventaire();
                    inventaireOuvert = false;
                }
            }

            if(code == KeyCode.E && Joueur.getUniqueJoueur().katanaEnMain()){
                Joueur.getUniqueJoueur().setEnDash(true);
                Joueur.getUniqueJoueur().dashKatana();
            }


            String keyText = event.getText();
            if (keyText.equals("&") || keyText.equals("\"") || keyText.equals("é") || keyText.equals("'") || keyText.equals("(") || keyText.equals("-")) {
            int mainCourante = Joueur.getUniqueJoueur().getMainCourante();
            switch (keyText) {
                case "&" -> { Joueur.getUniqueJoueur().setMainCourante(0); vueBarreRaccourci.updateElement(0); }
                case "é" -> { Joueur.getUniqueJoueur().setMainCourante(1); vueBarreRaccourci.updateElement(1); }
                case "\"" -> { Joueur.getUniqueJoueur().setMainCourante(2); vueBarreRaccourci.updateElement(2); }
                case "'" -> { Joueur.getUniqueJoueur().setMainCourante(3); vueBarreRaccourci.updateElement(3); }
                case "(" -> { Joueur.getUniqueJoueur().setMainCourante(4); vueBarreRaccourci.updateElement(4); }
                case "-" -> { Joueur.getUniqueJoueur().setMainCourante(5); vueBarreRaccourci.updateElement(5); }
            }
                vueBarreRaccourci.updateElement(mainCourante);
            }
        }
        else if (event.getEventType() == KeyEvent.KEY_RELEASED) {
            touchesAppuyees.remove(code);// touche retirée de la liste car relâchée
        }
    }

    public void misAJour() {
        Joueur.getUniqueJoueur().setMarcheDroite(touchesAppuyees.contains(KeyCode.RIGHT) || touchesAppuyees.contains(KeyCode.D));
        Joueur.getUniqueJoueur().setMarcheGauche(touchesAppuyees.contains(KeyCode.LEFT) || touchesAppuyees.contains(KeyCode.Q));
    }

    @FXML
    public void ouvrertureInventaire() {
        Platform.runLater(() -> screenInventaire.requestFocus());
        Jeu.getUniqueJeu().testCraft();
        screenInventaire.toFront();
        Joueur.getUniqueJoueur().setMarcheDroite(false);
        Joueur.getUniqueJoueur().setMarcheGauche(false);
    }
    @FXML
    public void sortirInventaire(){
        screenInventaire.toBack();
        Platform.runLater(() -> screenPrincipal.requestFocus());
    }
}
