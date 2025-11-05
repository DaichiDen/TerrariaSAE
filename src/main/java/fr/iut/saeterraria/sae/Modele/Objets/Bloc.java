package fr.iut.saeterraria.sae.Modele.Objets;

import fr.iut.saeterraria.sae.Modele.Objets.Etablis.BlocConstruction;

public class Bloc extends Item{
    private int resistance; // Selon le type, la résistance évolue avec le integer

    public Bloc (String nom,String description, int resistance, int codeobjet) {
        super(nom,description,1,codeobjet);
        this.resistance = resistance;
    }


    public int getResistance() { return resistance; }

    @Override
    public int getType() {
        return super.getType();
    }
}
