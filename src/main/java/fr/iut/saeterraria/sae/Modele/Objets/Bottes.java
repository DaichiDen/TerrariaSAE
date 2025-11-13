package fr.iut.saeterraria.sae.Modele.Objets;

public class Bottes extends Armure{

    public Bottes(String nom, double defense, int codeobjet) {
        super(nom, defense, codeobjet);
    }

    @Override
    public int getTypeArmure() {
        return 4;
    }

    @Override
    public int getCaseEquipement() {
        return 3;
    }
}
