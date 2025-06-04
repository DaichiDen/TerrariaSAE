package fr.iut.saeterraria.sae.Modele.Objets.Outil;

import fr.iut.saeterraria.sae.Modele.Objets.Etablis.BlocConstruction;
import fr.iut.saeterraria.sae.Modele.Objets.Outils;

public class Hache extends Outils {

    public Hache(String nom, String description) {
        super(nom, description);
    }

    public Hache(String nom, String description, BlocConstruction blocConstruction) {
        super(nom, description);
    }

    public void action() {
        System.out.println("test");
    }
}
