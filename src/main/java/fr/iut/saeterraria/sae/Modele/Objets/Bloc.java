package fr.iut.saeterraria.sae.Modele.Objets;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.image.Image;

public class Bloc extends Item{
    private int width, height;
    private int typeBloc; //Définit les outils nécéssaire pour sa destruction
    private int resistance; // Selon le type, la résistance évolue avec le integer

    public Bloc (String nom,String description, int typeBloc, int resistance) {
        super(nom,description,1);
        this.typeBloc = typeBloc;
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
