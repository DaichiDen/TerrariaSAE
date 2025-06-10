package fr.iut.saeterraria.sae.Modele.Objets.Arme;

import fr.iut.saeterraria.sae.Modele.Objets.Etablis.BlocConstruction;

public class Projectile extends Distance{

    public Projectile(String nom, String desc, double attaque, BlocConstruction blocConstruction) {
        super(nom, desc, attaque, blocConstruction);
    }

    public void attaquer(){

    }
}
