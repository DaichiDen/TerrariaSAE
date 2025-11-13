package fr.iut.saeterraria.sae.Modele.Objets;

public abstract class Armes extends Equipement {
    private Integer attaque; // Valeur d'attaque de l'arme

    public Armes (String nom,  int attaque, int codeobjet) {
        super(nom,codeobjet);
        this.attaque = attaque;
    }

    public int getAttaque(){ return attaque; }



}
