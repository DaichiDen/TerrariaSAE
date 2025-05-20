package fr.iut.saeterraria.sae.Modele.Objets;

import java.util.ArrayList;
import java.util.HashMap;

public class Recette {
    ArrayList<ElementRecette> prerequis;

    public Recette() {
        this.prerequis = new ArrayList<>();
    }

    public void addElementRecette(ElementRecette elementRecette){
        this.prerequis.add(elementRecette);
    }

    public ArrayList<ElementRecette> getRecette(){
        return this.prerequis;
    }
}
