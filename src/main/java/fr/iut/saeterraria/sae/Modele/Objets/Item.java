package fr.iut.saeterraria.sae.Modele.Objets;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Item {
    private StringProperty name;
    private StringProperty description;
    private IntegerProperty codeObjet;
    private IntegerProperty typeItem; // 1=stack 4 / 2= stack 16 / 3=stack 1
    private static int id=1;

    public Item(String nom, String descripcion,int typeItem) {
        this.name = new SimpleStringProperty(nom);
        this.description = new SimpleStringProperty(descripcion);
        this.codeObjet = new SimpleIntegerProperty(id);
        id++;
        this.typeItem = new SimpleIntegerProperty(typeItem);
    }

    public StringProperty nameProperty() {
        return this.name;
    }
    public String getName(){
        return this.name.getValue();
    }


    public StringProperty descriptionProperty() {
        return this.description;
    }
    public String getDescription(){ return this.description.getValue(); }

    public IntegerProperty codeObjetProperty() { return codeObjet; }
    public int getCodeObjet(){ return codeObjet.getValue(); }


}