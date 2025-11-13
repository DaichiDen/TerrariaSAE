package fr.iut.saeterraria.sae.Modele.Objets;

public abstract  class Armure extends Equipement {
    private Double defense; // Valeur de défense de l'armure

    public Armure(String nom, double defense, int codeobjet) {
        super(nom, codeobjet);

        this.defense = defense;
    }

    public double getDefense() {
        return defense;
    }

    public abstract int getTypeArmure();

    public abstract int getCaseEquipement();

}
