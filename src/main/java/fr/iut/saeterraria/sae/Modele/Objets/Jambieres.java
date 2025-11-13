package fr.iut.saeterraria.sae.Modele.Objets;

public class Jambieres extends Armure{

    public Jambieres(String nom, double defense, int codeobjet) {
        super(nom, defense, codeobjet);
    }

    @Override
    public int getTypeArmure() {
        return 3;
    }

    @Override
    public int getCaseEquipement() {
        return 2;
    }
}
