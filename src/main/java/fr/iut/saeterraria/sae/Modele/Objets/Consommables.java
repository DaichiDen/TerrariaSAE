package fr.iut.saeterraria.sae.Modele.Objets;

public abstract class Consommables extends Item {
    private int efficacite;

    public Consommables(String nom, String description, int efficacite) {
        super(nom, description,3);
        this.efficacite=efficacite;
    }

    public abstract void actionJoueur();
}
