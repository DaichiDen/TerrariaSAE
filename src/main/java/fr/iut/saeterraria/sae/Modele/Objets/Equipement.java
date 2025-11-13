package fr.iut.saeterraria.sae.Modele.Objets;

import fr.iut.saeterraria.sae.Modele.Objets.Etablis.BlocConstruction;

// Classe principale des armes, armures...
public abstract class Equipement extends Item{

    public Equipement(String nom, int codeobjet) {
        super(nom, 3, codeobjet);
    }

}