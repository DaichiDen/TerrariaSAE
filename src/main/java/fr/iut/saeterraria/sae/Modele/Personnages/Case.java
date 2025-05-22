package fr.iut.saeterraria.sae.Modele.Personnages;

import fr.iut.saeterraria.sae.Modele.Objets.Item;
import javafx.beans.property.IntegerProperty;

public class Case {

    private Item item;
    private int quantite;
    private int ligne;
    private int colonne;

    public Case(int ligne, int colonne) {
        this.item = new Item();
        this.quantite = 0;
        this.ligne = ligne;
        this.colonne = colonne;
    }

    public void setCase(Item item, int quantite) {
        this.item = item;
        this.quantite = quantite;
    }

    public boolean comparerId(int id) {
        return this.item.getCodeObjet()==id;
    }

    public void setQuantite (int quantite) {
        this.quantite = quantite;
    }
    public void ajouteQuantite(int quantite) {this.quantite += quantite;}
    public void retireQuantite(int quantite) { this.quantite -= quantite;}
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
}
