package fr.iut.saeterraria.sae.Modele.Objets;

public abstract class Consommables extends Item {
    private int valeur;

    public Consommables(String nom, String description, int valeur) {
        super(nom, description,3);
        this.valeur=valeur;
    }

    public abstract void actionJoueur();
}
