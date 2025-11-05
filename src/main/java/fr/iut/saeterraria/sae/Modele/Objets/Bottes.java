package fr.iut.saeterraria.sae.Modele.Objets;

import fr.iut.saeterraria.sae.Modele.Objets.Etablis.BlocConstruction;

public class Bottes extends Armure{

    public Bottes(String nom, String desc, double defense, BlocConstruction blocConstruction, int codeobjet) {
        super(nom, desc, defense, blocConstruction, codeobjet);
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
