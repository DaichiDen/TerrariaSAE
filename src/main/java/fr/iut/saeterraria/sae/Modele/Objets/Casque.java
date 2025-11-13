package fr.iut.saeterraria.sae.Modele.Objets;

public class Casque extends Armure{
    public Casque(String nom, double defense, int codeobjet) {
        super(nom, defense, codeobjet);
    }

    @Override
    public int getTypeArmure() {
        return 1;
    }

    @Override
    public int getCaseEquipement() {
        return 0;
    }
}
