package fr.iut.saeterraria.sae.Modele.Objets.Arme;

import fr.iut.saeterraria.sae.Modele.Objets.Armes;
import fr.iut.saeterraria.sae.Modele.Objets.Etablis.BlocConstruction;

public class Epee extends Melee {

    public Epee(String nom, String desc, int attaque, BlocConstruction blocConstruction) {
        super(nom, desc,attaque,blocConstruction);
    }
    @Override
    public void attaquer() {

    }
}
