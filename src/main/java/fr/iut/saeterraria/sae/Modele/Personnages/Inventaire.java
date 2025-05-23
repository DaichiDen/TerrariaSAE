package fr.iut.saeterraria.sae.Modele.Personnages;

import fr.iut.saeterraria.sae.Modele.Objets.Item;

import java.util.HashMap;
import java.util.Map;

public class Inventaire {
    private Case[][] inventaireJoueur;

    public Inventaire() {
        this.inventaireJoueur = new Case[7][6]; // 6 première colonne pour hotbar 36 Colonnes pour les 36 cases
        initialiseInventaire();
    }

    public void initialiseInventaire(){
        for (int i = 0; i < inventaireJoueur.length; i++) {
            for (int j = 0; j < inventaireJoueur[i].length; j++) {
              this.inventaireJoueur[i][j] = new Case(i, j);
            }
        }
    }

    // Ajoute l'item dans une case ou dans plusieurs si aucune case peut contenir toute la quantité (ou pas du tout si aucune case le permet)==
    public void ajoutInventaire(Item item, int quantite) {
        int[][] planInventaire= findItem(item,quantite);
        int i=0;
        int j=0;
        boolean placer = false;
        int reste = quantite;
        if(planInventaire == null) {//Pas de place
            System.out.println("Pas de place dans l'inventaire");
        }
        else {
            while (!placer && i < planInventaire.length) {//Si Item déjà présent dans l'inventaire
                while (!placer && j < planInventaire[i].length) {
                    if ( planInventaire[i][j] == 1) {
                        if (this.inventaireJoueur[i][j].getQuantite()+reste <= this.inventaireJoueur[i][j].getMaxStack())  { // Si l'ajout de l'item va pas dépasser la limite de stack
                            this.inventaireJoueur[i][j].ajouteQuantite(reste);
                            placer = true;
                        }
                        else {//Limite atteinte par stack
                            int ajout = this.inventaireJoueur[i][j].getMaxStack() - this.inventaireJoueur[i][j].getQuantite();
                            this.inventaireJoueur[i][j].ajouteQuantite(ajout);
                            reste = reste - ajout;
                        }
                    }
                    j++;
                }
                i++;
            }
            i=0;
            // Si la case est vide
            while(!placer && i < planInventaire.length ) {
                j=0;
                while (!placer && j < planInventaire[i].length) {
                    if (planInventaire[i][j] == 2) {
                        if (reste <= this.inventaireJoueur[i][j].getMaxStack())  { // Si l'ajout de l'item va pas dépasser la limite de stack
                            this.inventaireJoueur[i][j].setCase(item,reste);
                            placer = true;
                        }
                        else {//Limite atteinte par stack
                            int ajout = this.inventaireJoueur[i][j].getMaxStack();
                            this.inventaireJoueur[i][j].setCase(item,ajout);
                            reste = reste - ajout;
                        }
                    }
                    j++;
                }
                i++;
            }
        }
    }

    public void decrementeItem(int ligne, int colonne) {
        if(inventaireJoueur[ligne][colonne].getQuantite()-1>0) {
            this.inventaireJoueur[ligne][colonne].retireQuantite(1);
        }
        else {
            inventaireJoueur[ligne][colonne].setCase(new Item(), 0);
        }
    }

    public void incrementerItem(int ligne,int colonne) {
        if(inventaireJoueur[ligne][colonne].getQuantite()+1<=this.inventaireJoueur[ligne][colonne].getMaxStack()) {
            this.inventaireJoueur[ligne][colonne].ajouteQuantite(1);
        }
        else {
            ajoutInventaire(inventaireJoueur[ligne][colonne].getItem(),1);
        }
    }

    public Case[][] getInventaireJoueur() {
        return inventaireJoueur;
    }

    public void removeItem(int ligne, int colonne) {
        inventaireJoueur[ligne][colonne].setCase(null, 0);
        System.out.println("Suppression item de l'inventaire effectué");
    }

    // Trouve toutes les instances de l'item dans l'inventaire ainsi que les cases vides
    public int[][] findItem(Item item, int quantite) {
        boolean presentItemCaseLibre = false;
        int[][] instance = new int[this.inventaireJoueur.length][this.inventaireJoueur[0].length];
        for (int i = 0; i < this.inventaireJoueur.length; i++) {
            for (int j = 0; j < this.inventaireJoueur[i].length; j++) {
                if (this.inventaireJoueur[i][j].comparerId(item.getCodeObjet())) {
                    instance[i][j] = 1;
                    presentItemCaseLibre = true;
                }
                else if (this.inventaireJoueur[i][j] == null){
                    instance[i][j] = 2;
                    presentItemCaseLibre = true;
                }
            }
        }
        if (presentItemCaseLibre) {
            return instance;
        }
        else{
            return null;
        }
    }

    public void mettreAJourInventaire() {
        for(int i=0; i<this.inventaireJoueur.length; i++) {
            for (int j = 0; j < this.inventaireJoueur[0].length; j++) {
                if(inventaireJoueur[i][j].getQuantite()==0 && inventaireJoueur[i][j].getItem() != null) {
                    removeItem(i,j);
                }
            }
        }
    }

    public void remplirTest(HashMap<Integer, Item> items) {
        for(int i=0; i<this.inventaireJoueur.length; i++) {
            for(int j=0; j<this.inventaireJoueur[i].length; j++) {
                inventaireJoueur[i][j].setCase(items.get(1),1);
            }
        }
    }

    public void viderTest() {
        for(int i=0; i<this.inventaireJoueur.length; i++) {
            for(int j=0; j<this.inventaireJoueur[i].length; j++) {
                incrementerItem(i,j);
            }
        }
    }

}
