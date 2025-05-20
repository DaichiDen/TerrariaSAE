package fr.iut.saeterraria.sae.Modele.Personnages;

import fr.iut.saeterraria.sae.Modele.Objets.Item;

import java.util.HashMap;
import java.util.Map;

public class Inventaire {
    private int[][] inventaireJoueur;

    public Inventaire() {
        this.inventaireJoueur = new int[2][42]; // 6 première colonne pour hotbar 36 Colonnes pour les 36 cases et 2 lignes pour l'id item et sa quantité
    }

    public void ajoutInventaire(Item item, int quantite) { //A revoir
        if (findItem(item,quantite)==-1) {
            System.out.println("Pas de place dans l'inventaire");
        }
        else {
            inventaireJoueur[0][findItem(item,quantite)]=item.getCodeObjet();
            inventaireJoueur[1][findItem(item,quantite)]+=quantite;
            System.out.println("Item ajouté dans l'inventaire");
        }
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

    public boolean maxStack(Item items,int ajout,int place){ // renvoie le nombre maximal du même item qu'on peut avoir dans la même case d'inventaire avec un classement selon le cas.On peut avoir qu'une pioche par case par exemple,16 de X et 64 de Y
        boolean stack = false;
        switch (items.getType()){
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

    // Trouve la place de l'item dans l'inventaire
    public int findItem(Item item, int quantite) {
        boolean trouve = false;
        int compteur = 0;
        int caseTrouve = -1;
        while (!trouve && compteur < 42 ) {
            if(this.inventaireJoueur[0][compteur]==item.getCodeObjet()) {
                if (maxStack(item,quantite,compteur)){
                    trouve = true;
                    caseTrouve = compteur;
                }
            }
            compteur++;
        }
        if(caseTrouve==-1) {
            compteur=0;
            while (!trouve && compteur < 42 ) {
                if (this.inventaireJoueur[1][compteur]==0) {
                    trouve = true;
                    caseTrouve = compteur;
                }
                compteur++;
            }
        }
        return caseTrouve;
    }
}
