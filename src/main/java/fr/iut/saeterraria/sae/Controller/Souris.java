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
        int x = (int)mouseEvent.getX();
        int y = (int)mouseEvent.getY();
        if (mouseEvent.getButton() == MouseButton.PRIMARY) {
            jeu.getJoueur().miner(jeu.getCarte(), x/32, y/32);
            fond.afficherCarte();
            jeu.getJoueur().attaquer(x,y,2);
        }
        else if (mouseEvent.getButton() == MouseButton.SECONDARY) {
            jeu.getJoueur().poser(jeu.getCarte(), x/32, y/32,2);
            fond.afficherCarte();
        }
    }
}
