package fr.iut.saeterraria.sae.Modele.Objets.Arme;

import fr.iut.saeterraria.sae.Modele.Objets.Etablis.BlocConstruction;

public class Epee extends Melee {

    public Epee(String nom, String desc, int attaque, BlocConstruction blocConstruction, int codeobjet) {
        super(nom, desc,attaque,blocConstruction,codeobjet);
    }

    public Epee(String nom,String desc,int attaque, int codeobjet) {
        super(nom,desc,attaque,codeobjet);
    }


}
