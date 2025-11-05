package fr.iut.saeterraria.sae.Modele.Objets;

import fr.iut.saeterraria.sae.Modele.Objets.Etablis.BlocConstruction;

public class Jambieres extends Armure{

    public Jambieres(String nom, String desc, double defense, int codeobjet) {
        super(nom, desc, defense, codeobjet);
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
