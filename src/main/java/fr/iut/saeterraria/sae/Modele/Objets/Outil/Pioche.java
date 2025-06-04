package fr.iut.saeterraria.sae.Modele.Objets.Outil;

import fr.iut.saeterraria.sae.Modele.Objets.Etablis.BlocConstruction;
import fr.iut.saeterraria.sae.Modele.Objets.Outils;

public class Pioche extends Outils {

    public Pioche(String nom,String description) {
        super(nom,description);
    }

    public Pioche(String nom, String description, BlocConstruction blocConstruction) {
        super(nom,description, blocConstruction);
    }

    @Override
    public void action() {
        System.out.println("test");
    }
}
