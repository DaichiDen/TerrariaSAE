package fr.iut.saeterraria.sae.Controller;

import fr.iut.saeterraria.sae.Modele.Jeu;
import fr.iut.saeterraria.sae.Modele.Map.Map;
import fr.iut.saeterraria.sae.Vue.Fond;
import javafx.event.EventHandler;
import javafx.scene.input.InputEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.TilePane;

public class Souris implements EventHandler<MouseEvent> {
    private Jeu jeu;
    private Fond tilePane;
    private Map map;

    public Souris(Jeu jeu, Fond fond){
        this.jeu = jeu;
        this.tilePane = fond;
    }
    @Override
    public void handle(MouseEvent mouseEvent) {
        int x = (int)mouseEvent.getSceneX();
        int y = (int)mouseEvent.getSceneY();
        System.out.println("X :"+x+ " Y :"+y);
        jeu.getJoueur().miner(jeu.getCarte(),x,y);
        tilePane.afficherCarte();
    }
}
