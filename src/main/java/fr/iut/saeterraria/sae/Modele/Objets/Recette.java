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

    public String toStringListe() {
        String resultat = new String();
        for(int i=0; i<prerequis.size(); i++) {
            resultat += "\t" + prerequis.get(i).getItem().getName();
            resultat += "\n" + prerequis.get(i).getQuantite();
        }
        return resultat;
    }
}
