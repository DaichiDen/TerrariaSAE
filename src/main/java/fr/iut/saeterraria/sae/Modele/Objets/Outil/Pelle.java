package fr.iut.saeterraria.sae.Modele.Objets.Outil;

import fr.iut.saeterraria.sae.Modele.Objets.Etablis.BlocConstruction;
import fr.iut.saeterraria.sae.Modele.Objets.Outils;

public class Pelle extends Outils {

    public Pelle(String nom, String description) {
        super(nom, description);
    }

    public Pelle(String nom, String description, BlocConstruction blocConstruction) {
        super(nom, description, blocConstruction);
    }

    public void action(){
        System.out.println("test");
    }
}
