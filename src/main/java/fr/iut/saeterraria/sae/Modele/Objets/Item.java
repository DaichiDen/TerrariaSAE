package fr.iut.saeterraria.sae.Modele.Objets;

import fr.iut.saeterraria.sae.Modele.Objets.Etablis.BlocConstruction;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.ArrayList;
import java.util.HashMap;

public class Item {
    private StringProperty name;
    private StringProperty description;
    private IntegerProperty codeObjet;
    private IntegerProperty typeItem; // 1=stack 64 / 2= stack 16 / 3=stack 1
    private static int id = 0;
    private Recette recette;
    private BlocConstruction provenance;

    public Item(){
        this.name = new SimpleStringProperty("");
        this.description = new SimpleStringProperty("");
        this.typeItem = new SimpleIntegerProperty(0);
        this.recette = new Recette();
        this.codeObjet = new SimpleIntegerProperty(0);
        this.provenance = null;
    }
    public Item(String nom, String description,int typeItem) {


        this.name = new SimpleStringProperty(nom);
        this.description = new SimpleStringProperty(description);
        this.typeItem = new SimpleIntegerProperty(typeItem);
        this.recette = new Recette();
        this.codeObjet = new SimpleIntegerProperty(id);
        this.provenance = null;
        id++;
    }












    public Item(String nom, String description,int typeItem,BlocConstruction provenance) {


        this.name = new SimpleStringProperty(nom);
        this.description = new SimpleStringProperty(description);
        this.typeItem = new SimpleIntegerProperty(typeItem);
        this.recette = new Recette();
        this.codeObjet = new SimpleIntegerProperty(id);
        this.provenance = provenance;
        id++;
    }

    public IntegerProperty codeObjetProperty() { return codeObjet; }
    public int getCodeObjet() { return codeObjet.getValue(); }

    public int getType(){
        return typeItem.getValue();
    }

    public int nombreMax(){
        int nbMax=1;
        if (getType()==1){
            nbMax=64;
        }
        else if (getType()==2) {
            nbMax=16;
        }
        return nbMax;
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

    public void addInRecette(ElementRecette recette){
        this.recette.addElementRecette(recette);
    }

    public ArrayList<ElementRecette> getRecette(){
        return this.recette.getRecette();
    }
}
