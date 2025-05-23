package fr.iut.saeterraria.sae.Modele.Objets.Outil;

import fr.iut.saeterraria.sae.Modele.Objets.Outils;

public class Pelle extends Outils {

    public Pelle(String nom, String description) {
        super(nom, description);
    }

    public void action(){
        System.out.println("test");
    }
}
