package fr.iut.saeterraria.sae.Modele.Objets;

public class Plastron extends Armure{

    public Plastron(String nom,  double defense, int codeobjet) {
        super(nom, defense, codeobjet);
    }

    @Override
    public int getTypeArmure() {
        return 2;
    }

    @Override
    public int getCaseEquipement() {
        return 1;
    }
}
