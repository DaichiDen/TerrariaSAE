package fr.iut.saeterraria.sae.Modele.Objets;

import fr.iut.saeterraria.sae.Modele.Objets.Etablis.BlocConstruction;

public class Plastron extends Armure{

    public Plastron(String nom, String desc, double defense, int codeobjet) {
        super(nom, desc, defense, codeobjet);
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
