package fr.iut.saeterraria.sae.Modele.Objets;

import fr.iut.saeterraria.sae.Modele.Objets.Etablis.BlocConstruction;

// Classe principale des armes, armures...
public abstract class Equipement extends Item{

    public Equipement(String nom, String desc, BlocConstruction blocConstruction, int codeobjet) {
        super(nom, desc,3,blocConstruction, codeobjet);
    }

    public Equipement(String nom, String desc, int codeobjet) {
        super(nom, desc,3,codeobjet);
    }
}