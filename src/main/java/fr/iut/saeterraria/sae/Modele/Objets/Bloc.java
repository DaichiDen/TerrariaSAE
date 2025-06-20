package fr.iut.saeterraria.sae.Modele.Objets;

import fr.iut.saeterraria.sae.Modele.Objets.Etablis.BlocConstruction;

public class Bloc extends Item{
    private int width, height;
    private int typeBloc;
    private int resistance; // Selon le type, la résistance évolue avec le integer

    public Bloc (String nom,String description, int resistance) {
        super(nom,description,1);
        this.resistance = resistance;
    }

    public Bloc (String nom, String description, int resistance, BlocConstruction provenance) {
        super(nom,description,3,provenance);
        this.resistance = resistance;
    }

    public int getTypeBloc() {
        return typeBloc;
    }

    public int getResistance() { return resistance; }

    @Override
    public int getType() {
        return super.getType();
    }
}
