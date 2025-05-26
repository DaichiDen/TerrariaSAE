package fr.iut.saeterraria.sae.Modele.Personnages;
import fr.iut.saeterraria.sae.Modele.Map.Map;
import  javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.geometry.Rectangle2D;

import java.util.ArrayList;

public class PNJ extends Entite{

    private ArrayList<String> dialogue;
    public PNJ(String nom, int x, int y, Map map) {

        super(nom,1,1,1,0,x,y,1, map);

        dialogue = new ArrayList<String>();
    }

    @Override
    public void attaquer() {

    }
}
