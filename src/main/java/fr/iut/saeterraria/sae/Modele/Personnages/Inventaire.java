package fr.iut.saeterraria.sae.Modele.Personnages;

import fr.iut.saeterraria.sae.Modele.Objets.Item;

public class Inventaire {
    private int[][] inventaireJoueur;

    public Inventaire() {
        this.inventaireJoueur = new int[2][42]; // 6 première colonne pour hotbar36 Colonnes pour les 36 cases et 2 lignes pour l'id item et sa quantité
    }

    public boolean ajoutInventaire(Item item, int quantite) {
        boolean trouve = false;
        int compteur = 0;
        while (!trouve && compteur<42) {
            if(inventaireJoueur[0][compteur]==item.getCodeObjet()) {
                if (inventaireJoueur[1][compteur]+quantite<=item.nombreMax()){
                    inventaireJoueur[1][compteur]+=quantite;
                }

            }
            else if(inventaireJoueur[0][compteur]==0) {
                inventaireJoueur[0][compteur]=item.getCodeObjet();
                inventaireJoueur[1][compteur]=quantite;
                trouve = true;
                System.out.println("Ajout item dans inventaire effectué");
            }
            compteur++;
        }
        if(!trouve) {
            return false;
        }
        else {
            return true;
        }
    }

    public int[][] getInventaireJoueur() {
        return inventaireJoueur;
    }

    public void removeItem(Item item) {
        boolean trouve = false;
        int compteur =0;
        while (!trouve || compteur==36) {
            if(inventaireJoueur[0][compteur]==item.getCodeObjet()) {
                inventaireJoueur[0][compteur]=0;
                inventaireJoueur[1][compteur]=0;
                trouve=true;
                System.out.println("Suppression item de l'inventaire effectué");
            }
            compteur++;
        }
    }
}
