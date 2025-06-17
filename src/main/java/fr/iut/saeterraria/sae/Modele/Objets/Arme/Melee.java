package fr.iut.saeterraria.sae.Modele.Objets.Arme;

import fr.iut.saeterraria.sae.Modele.Objets.Armes;
import fr.iut.saeterraria.sae.Modele.Objets.Etablis.BlocConstruction;

public class Melee extends Armes {


    public Melee(String nom, String desc, int attaque, BlocConstruction blocConstruction) {
        super(nom, desc, attaque,blocConstruction);
    }

    public Melee(String nom, String desc, int attaque) {
        super(nom, desc, attaque);
    }

    @Override
    public void attaquer() {

    }
    
}
