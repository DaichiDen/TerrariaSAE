package fr.iut.saeterraria.sae.Modele.Objets;

public abstract class Consommables extends Item {
    private int valeur;

    public Consommables(String nom, String description, int valeur, int codeobjet) {
        super(nom, description,3,codeobjet);
        this.valeur=valeur;
    }


}
