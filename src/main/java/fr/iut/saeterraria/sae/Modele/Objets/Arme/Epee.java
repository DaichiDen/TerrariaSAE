package fr.iut.saeterraria.sae.Modele.Objets.Arme;

import fr.iut.saeterraria.sae.Modele.Objets.Etablis.BlocConstruction;

public class Epee extends Melee {

    public Epee(String nom, String desc, int attaque, BlocConstruction blocConstruction) {
        super(nom, desc,attaque,blocConstruction);
    }

    public Epee(String nom,String desc,int attaque) {
        super(nom,desc,attaque);
    }

    @Override
    public void attaquer() {

    }
}
