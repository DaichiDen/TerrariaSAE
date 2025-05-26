package fr.iut.saeterraria.sae.Controller;

import fr.iut.saeterraria.sae.Modele.Jeu;
import fr.iut.saeterraria.sae.Modele.Map.Map;
import fr.iut.saeterraria.sae.Vue.Fond;
import javafx.event.EventHandler;
import javafx.scene.input.InputEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.TilePane;

public class Souris implements EventHandler<MouseEvent> {
    private Jeu jeu;
    private Fond fond;
    private Map map;

    public Souris(Jeu jeu,Fond fond,Map map){
        this.jeu = jeu;
        this.fond = fond;
        this.map = map;


    }
    @Override
    public void handle(MouseEvent mouseEvent) {
        int x = (int)mouseEvent.getX()/32;
        int y = (int)mouseEvent.getY()/32;
        if (mouseEvent.getButton() == MouseButton.PRIMARY) {
            System.out.println("X :" + x + " Y :" + y);
            jeu.getJoueur().miner(jeu.getCarte(), x, y);
            fond.afficherCarte();
        }
        else if (mouseEvent.getButton() == MouseButton.SECONDARY) {
            jeu.getJoueur().poser(jeu.getCarte(), x, y,2);
            fond.afficherCarte();
        }


    }
}
