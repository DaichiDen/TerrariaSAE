package fr.iut.saeterraria.sae.Modele.Objets.Arme;

import fr.iut.saeterraria.sae.Modele.Objets.Armes;
import fr.iut.saeterraria.sae.Modele.Objets.Etablis.BlocConstruction;

public class Melee extends Armes {


    public Melee(String nom, String desc, int attaque, BlocConstruction blocConstruction, int codeobjet) {
        super(nom, desc, attaque,blocConstruction,codeobjet);
    }

    public Melee(String nom, String desc, int attaque, int codeobjet) {
        super(nom, desc, attaque,codeobjet);
    }

    @Override
    public void attaquer() {

    }
    
}
