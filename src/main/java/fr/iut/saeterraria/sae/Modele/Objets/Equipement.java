package fr.iut.saeterraria.sae.Modele.Objets;

// Classe principale des armes, armures...
public abstract class Equipement extends Item{

    public Equipement(String nom, String desc) {
        super(nom, desc,3);
    }
}