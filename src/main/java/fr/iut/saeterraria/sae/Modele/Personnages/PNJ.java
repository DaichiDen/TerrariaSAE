package fr.iut.saeterraria.sae.Modele.Personnages;
import fr.iut.saeterraria.sae.Modele.Jeu;
import fr.iut.saeterraria.sae.Modele.Map.Map;
import  javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.geometry.Rectangle2D;

import java.util.ArrayList;

public class PNJ extends EntiteVivante{

    private ArrayList<String> dialogue;
    public PNJ(String nom, int x, int y, Map map, Jeu jeu) {

        super(nom, 20, 20, 10, x, y, 2, 0, jeu,0);

        dialogue = new ArrayList<String>();
    }

    @Override
    public void action(int x, int y, int range) {

    }
}
