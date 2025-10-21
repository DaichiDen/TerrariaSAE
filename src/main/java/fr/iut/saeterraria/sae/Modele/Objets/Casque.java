package fr.iut.saeterraria.sae.Modele.Objets;

import fr.iut.saeterraria.sae.Modele.Objets.Etablis.BlocConstruction;

public class Casque extends Armure{
    public Casque(String nom, String desc, double defense, BlocConstruction blocConstruction, int codeobjet) {
        super(nom, desc, defense, blocConstruction, codeobjet);
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
