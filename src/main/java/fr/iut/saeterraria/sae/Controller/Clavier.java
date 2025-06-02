package fr.iut.saeterraria.sae.Controller;

import fr.iut.saeterraria.sae.Modele.Jeu;
import fr.iut.saeterraria.sae.Vue.vueHotbar;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
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
    private AnchorPane screenInventaire;
    @FXML
    private GridPane hotBarInventaire;
    private final Set<KeyCode> touchesAppuyees = new HashSet<>();
    private boolean inventaireOuvert = false;
    private vueHotbar vueHotbar;






    public Clavier(Jeu jeu, AnchorPane screenInventaire,Button quitterInventaire,Button openInventaire,TilePane fond, GridPane hotBarInventaire) {

        this.jeu=jeu;
        this.screenInventaire=screenInventaire;
        this.quitterInventaire=quitterInventaire;
        this.openInventaire=openInventaire;
        this.fond=fond;
        this.hotBarInventaire=hotBarInventaire;
        this.vueHotbar = new vueHotbar(jeu,hotBarInventaire);
    }


    public void handle(KeyEvent event) { //
        KeyCode code = event.getCode(); // le code de la touche de l'event
        if (event.getEventType() == KeyEvent.KEY_PRESSED) {
            touchesAppuyees.add(code); // ajout du code à la liste pour les garder en mémoire (touches enfoncées)

            if (code == KeyCode.SPACE || code == KeyCode.UP) {
                jeu.getJoueur().sauter();
            }
            if (code == KeyCode.NUMPAD5) {
                jeu.getJoueur().getBarreVie().setVie(0);
            }
            if(code == KeyCode.I ) {
                if (!inventaireOuvert){

                    ouvrirInventaire();
                    inventaireOuvert = true;
                } else {
                    exitInventaire();
                    inventaireOuvert = false;
                }

            }
            if (code == KeyCode.J) { // à déplacer dans souris quand on aura une hotbar (main courante)
                if (!jeu.getJoueur().getPierreTp().getEtat_tp()) {
                    jeu.getJoueur().getPierreTp().setX(jeu.getJoueur().getX());
                    jeu.getJoueur().getPierreTp().setY(jeu.getJoueur().getY());
                    jeu.getJoueur().getPierreTp().setEtat_tp(true);
                } else {
                    jeu.getJoueur().tp(jeu.getJoueur().getPierreTp().getX(), jeu.getJoueur().getPierreTp().getY());
                    jeu.getJoueur().getPierreTp().setEtat_tp(false);
                }
            }
            String keyText = event.getText();
            if (keyText.equals("&") || keyText.equals("\"") || keyText.equals("é") || keyText.equals("'") || keyText.equals("(") || keyText.equals("-")) {
            int mainCourante = jeu.getJoueur().getMainCourante();
            switch (keyText) {
                case "&" -> { jeu.getJoueur().setMainCourante(0); vueHotbar.updateElement(0); }
                case "é" -> { jeu.getJoueur().setMainCourante(1); vueHotbar.updateElement(1); }
                case "\"" -> { jeu.getJoueur().setMainCourante(2); vueHotbar.updateElement(2); }
                case "'" -> { jeu.getJoueur().setMainCourante(3); vueHotbar.updateElement(3); }
                case "(" -> { jeu.getJoueur().setMainCourante(4); vueHotbar.updateElement(4); }
                case "-" -> { jeu.getJoueur().setMainCourante(5); vueHotbar.updateElement(5); }
            }
                vueHotbar.updateElement(mainCourante);
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
        jeu.testCraft();
        openInventaire.setVisible(false);
        hotBarInventaire.setVisible(false);
        jeu.getJoueur().setMarcheDroite(false);
        jeu.getJoueur().setMarcheGauche(false);
        screenInventaire.setVisible(true);
    }
    @FXML
    public void exitInventaire(){
        screenInventaire.setVisible(false);
        hotBarInventaire.setVisible(true);
        openInventaire.setVisible(true);
        Platform.runLater(() -> fond.requestFocus());
    }

}
