package fr.iut.saeterraria.sae.Modele.Personnages;

import fr.iut.saeterraria.sae.Modele.Objets.Item;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class Case {

    private Item item;
    private int quantite;
    private int ligne;
    private int colonne;
    private BooleanProperty changement;

    public Case(int ligne, int colonne) {
        this.item = new Item();
        this.quantite = 0;
        this.ligne = ligne;
        this.colonne = colonne;
        this.changement = new SimpleBooleanProperty(false);
    }

    public void setCase(Item item, int quantite) {
        this.item = item;
        this.quantite = quantite;
        activerChangement();
    }

    public boolean comparerId(int id) {
        return this.item.getCodeObjet()==id;
    }

    public void setQuantite (int quantite) {
        this.quantite = quantite;
    }
    public void ajouteQuantite(int quantite) {
        this.quantite += quantite;
        activerChangement();
    }
    public void retireQuantite(int quantite) {
        this.quantite -= quantite;
        activerChangement();
    }
    public int getQuantite() {
        return quantite;
    }

    public Item getItem() {
        return item;
    }

    public int getColonne() {
        return colonne;
    }
    public int getLigne() {
        return ligne;
    }

    public int getMaxStack() {
        int maxStack;
        switch (item.getType()){
            case 1:
                maxStack=64;
                break;
            case 2:
                maxStack=16;
                break;
            case 3:
                maxStack=1;
                break;
            default:
                maxStack=0;
                break;
        }
        return maxStack;
    }
    public void activerChangement() {
        this.changement.setValue(true);
        this.changement.setValue(false);
    }

    public BooleanProperty changementProperty() {
        return this.changement;
    }
}
