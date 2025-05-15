package fr.iut.saeterraria.sae.Modele.Personnages;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.geometry.Rectangle2D;

import java.util.ArrayList;

public class PNJ extends Entite{

    private ArrayList<String> dialogue;
    public PNJ(String nom, int x, int y) {
        super(nom,1,1,1,0,x,y,1,1,new Rectangle2D(0,0,32,32));
        dialogue = new ArrayList<String>();
    }

}