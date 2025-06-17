package fr.iut.saeterraria.sae.Modele.Objets;

import fr.iut.saeterraria.sae.Modele.Objets.Etablis.BlocConstruction;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.image.Image;

public class Bloc extends Item{
    private int resistance; // Selon le type, la résistance évolue avec le integer

    public Bloc (String nom,String description, int resistance) {
        super(nom,description,1);
        this.resistance = resistance;
    }

    public Bloc (String nom, String description, int resistance, BlocConstruction provenance) {
        super(nom,description,1,provenance);
        this.resistance = resistance;
    }


    public int getResistance() { return resistance; }

    public int getResistanceBloc() { return resistance; }

    @Override
    public int getType() {
        return super.getType();
    }
}
