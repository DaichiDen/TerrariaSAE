package fr.iut.saeterraria.sae.Modele.Objets;

import java.util.ArrayList;

public class Item {
    private String name;
    private int codeObjet;
    private int typeItem; // 1=stack 64 / 2= stack 16 / 3=stack 1
    private Recette recette;

    public Item(){
        this.name = "";
        this.typeItem = 0;
        this.recette = new Recette();
        this.codeObjet =0;
    }

    public Item(String nom, int typeItem, int codeobjet) {
        this.name = nom;
        this.typeItem =typeItem;
        this.recette = new Recette();
        this.codeObjet = codeobjet;
    }

    public int getCodeObjet() { return codeObjet; }

    public int getType(){
        return typeItem;
    }

    public String getName(){
        return this.name;
    }

    public void addInRecette(ElementRecette recette){
        this.recette.addElementRecette(recette);
    }

    public ArrayList<ElementRecette> getRecette(){
        return this.recette.getRecette();
    }
    public Recette getAttributRecette() {
        return this.recette;
    }

}
