package fr.iut.saeterraria.sae.Modele.Objets.Arme;

import fr.iut.saeterraria.sae.Modele.Objets.Armes;
import fr.iut.saeterraria.sae.Modele.Objets.Etablis.BlocConstruction;

public class Distance extends Armes {

    public Distance(String nom, String desc, double attaque, BlocConstruction blocConstruction) {
        super(nom, desc, attaque, blocConstruction);
    }

    @Override
    public void attaquer() {

    }
}
