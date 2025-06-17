package fr.iut.saeterraria.sae.Modele.Objets.Outil;

import fr.iut.saeterraria.sae.Modele.Objets.Etablis.BlocConstruction;
import fr.iut.saeterraria.sae.Modele.Objets.Outils;

public class Pioche extends Outils {

    public Pioche(String nom,String description, int efficacite) {
        super(nom,description,efficacite);
    }

    public Pioche(String nom, String description, int efficacite, BlocConstruction blocConstruction) {
        super(nom,description, efficacite, blocConstruction);
    }

    @Override
    public void action() {
        System.out.println("test");
    }
}
