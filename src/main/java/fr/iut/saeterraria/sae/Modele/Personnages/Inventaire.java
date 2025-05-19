package fr.iut.saeterraria.sae.Modele.Personnages;

import fr.iut.saeterraria.sae.Modele.Objets.Item;

import java.util.HashMap;
import java.util.Map;

public class Inventaire {
    private HashMap<Integer,Item> items;
    private int[][] inventaireJoueur;

    public Inventaire(HashMap<Integer,Item> items) {
        this.inventaireJoueur = new int[2][42]; // 6 première colonne pour hotbar 36 Colonnes pour les 36 cases et 2 lignes pour l'id item et sa quantité
        items = items;
    }

    public boolean ajoutInventaire(Item item, int quantite) { //A revoir
        
        boolean trouve = false;
        int compteur = 0;
        while (!trouve && compteur<42) {
            if(inventaireJoueur[0][compteur]==) {
                if (maxStack(quantite,compteur)){ // Si la case de l'inventaire contient déjà un item
                    inventaireJoueur[1][compteur]+=quantite;
                }

            }
            else if(inventaireJoueur[0][compteur]==0) { // Si la case de l'inventaire contient pas d'item
                inventaireJoueur[0][compteur]=item.getCodeObjet();
                inventaireJoueur[1][compteur]=quantite;
                trouve = true;
                System.out.println("Ajout item dans inventaire effectué");
            }
            compteur++;
        }
        return trouve;
    }

    public int[][] getInventaireJoueur() {
        return inventaireJoueur;
    }

    public void removeItem(Item item) {
        boolean trouve = false;
        int compteur =0;
        while (!trouve && compteur < 42) {
            if(inventaireJoueur[0][compteur]==item.getCodeObjet()) {
                inventaireJoueur[0][compteur]=0;
                inventaireJoueur[1][compteur]=0;
                trouve=true;
                System.out.println("Suppression item de l'inventaire effectué");
            }
            compteur++;
        }
    }

    public boolean maxStack(int ajout,int place){
        boolean stack = false;
        switch (items.get(inventaireJoueur[0][place]).getType()){
            case 1:
                if (inventaireJoueur[1][place]+ajout<=64) {
                    stack = true;
                }
                break;
            case 2:
                if (inventaireJoueur[1][place]+ajout<=16) {
                    stack = true;
                }
                break;
            case 3:
                if (inventaireJoueur[1][place]+ajout<=1) {
                    stack = true;
                }
                break;
        }
        return stack;
    }

}
