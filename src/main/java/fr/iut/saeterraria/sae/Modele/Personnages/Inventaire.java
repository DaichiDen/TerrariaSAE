package fr.iut.saeterraria.sae.Modele.Personnages;

import fr.iut.saeterraria.sae.Modele.Objets.Item;

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
    public boolean ajoutInventaire(Item item, int quantite) {
        int[][] planInventaire= findItem(item);
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
                        else if( !(this.inventaireJoueur[i][j].getQuantite() == this.inventaireJoueur[i][j].getMaxStack()) ){//Limite atteinte par stack
                            int ajout = this.inventaireJoueur[i][j].getMaxStack() - this.inventaireJoueur[i][j].getQuantite();
                            this.inventaireJoueur[i][j].ajouteQuantite(ajout);
                            reste = reste - ajout;
                        }
                    }
                    j++;
                }
                j=0;
                i++;
            }
            i=0;
            while(!placer && i < planInventaire.length ) {
                while (!placer && j < planInventaire[i].length) {
                    if (planInventaire[i][j] == 2) {              // Si la case est vide
                        this.inventaireJoueur[i][j].ajouterItem(item);
                        if (reste <= this.inventaireJoueur[i][j].getMaxStack()) { // Si l'ajout de l'item va pas dépasser la limite de stack
                            this.inventaireJoueur[i][j].ajouteQuantite(reste);
                            placer = true;
                        }
                        else {//Limite atteinte par stack
                            int ajout = this.inventaireJoueur[i][j].getMaxStack();
                            this.inventaireJoueur[i][j].ajouteQuantite(ajout);
                            reste = reste - ajout;
                        }
                    }
                    j++;
                }
                j=0;
                i++;
            }
            if (placer) {
                System.out.println("Ajout dans l'inventaire");
            }
        }
        return placer;
    }

//    public void decrementeItem(int ligne, int colonne) {
//        if(inventaireJoueur[ligne][colonne].getQuantite()-1>0) {
//            this.inventaireJoueur[ligne][colonne].retireQuantite(1);
//        }
//        else {
//            inventaireJoueur[ligne][colonne].setCase(new Item(), 0);
//        }
//    }
//
//    public void incrementerItem(int ligne,int colonne) {
//        if(inventaireJoueur[ligne][colonne].getQuantite()+1<=this.inventaireJoueur[ligne][colonne].getMaxStack()) {
//            this.inventaireJoueur[ligne][colonne].ajouteQuantite(1);
//        }
//        else {
//            ajoutInventaire(inventaireJoueur[ligne][colonne].getItem(),1);
//        }
//    }

    public Case[][] getInventaireJoueur() {
        return inventaireJoueur;
    }

    public void removeItem(int ligne, int colonne) {
        inventaireJoueur[ligne][colonne].setCase(new Item(), 0);
        System.out.println("Suppression item de l'inventaire effectué");
    }

    // Trouve toutes les instances de l'item dans l'inventaire ainsi que les cases vides, retourne null si pas de place
    public int[][] findItem(Item item) {
        boolean presentItemCaseLibre = false;
        int[][] instance = new int[this.inventaireJoueur.length][this.inventaireJoueur[0].length];
        for (int i = 0; i < this.inventaireJoueur.length; i++) {
            for (int j = 0; j < this.inventaireJoueur[i].length; j++) {
                if (this.inventaireJoueur[i][j].comparerId(item.getCodeObjet()) ) {
                    instance[i][j] = 1;
                    presentItemCaseLibre = true;
                }
                else if (this.inventaireJoueur[i][j].getItem().getCodeObjet()==0){
                    instance[i][j] = 2;
                    presentItemCaseLibre = true;
                }
            }
        }
        if (presentItemCaseLibre) {
            return instance;
        }
        else{
            System.out.println("Impossible de rajouter dans l'inventaire");
            return null;
        }
    }
}
