package fr.iut.saeterraria.sae.Modele.Objets;

// Classe pour les sceau, canne à pèche, Coffres...
public abstract class Outils extends Item{

    public Outils (String nom, int codeobjet) {
        super(nom,3, codeobjet);
    }

    public abstract void action();
}
