package fr.iut.saeterraria.sae.Modele.Personnages;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.ArrayList;

public class PNJ extends Entite{

    private ArrayList<String> dialogue;
    public PNJ(String nom, int x, int y) {
        super(nom,1,1,1,x,y,1,1);
        dialogue = new ArrayList<String>();
    }

}