package fr.iut.saeterraria.sae.Modele.Personnages;

import fr.iut.saeterraria.sae.Modele.Objets.Item;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class Inventaire {
    private ObservableList<Case> inventaireJoueur;

    public Inventaire() {
        this.inventaireJoueur = FXCollections.observableArrayList(); // 6 première colonne pour hotbar 36 Colonnes pour les 36 cases
        initialiseInventaire();
    }

    public void initialiseInventaire() {
        for (int ligne = 0; ligne < 7; ligne++) {       // 7 lignes
            for (int colonne = 0; colonne < 6; colonne++) { // 6 colonnes
                inventaireJoueur.add(new Case(ligne, colonne));
            }
        }
    }


    // Ajoute l'item dans une case ou dans plusieurs si aucune case peut contenir toute la quantité (ou pas du tout si aucune case le permet)==
    public boolean ajoutInventaire(Item item, int quantite) {
        ArrayList<Case> planInventaire= findItem(item);
        boolean placer = false;
        int reste = quantite;
        if(planInventaire == null) {//Pas de place
            System.out.println("Pas de place dans l'inventaire");
        }
        else {
            int compteur = 0;
            while (compteur<planInventaire.size() && !placer) {
                    if (planInventaire.get(compteur).comparerId(item.getCodeObjet())) { //Si Item déjà présent dans l'inventaire
                        if (this.inventaireJoueur.get(planInventaire.get(compteur).getLigne()*6+ planInventaire.get(compteur).getColonne()).getQuantite()+reste <= this.inventaireJoueur.get(planInventaire.get(compteur).getLigne()*6+ planInventaire.get(compteur).getColonne()).getMaxStack())  { // Si l'ajout de l'item va pas dépasser la limite de stack
                            this.inventaireJoueur.get(planInventaire.get(compteur).getLigne()*6+ planInventaire.get(compteur).getColonne()).ajouteQuantite(reste);
                            placer = true;
                        }
                        else if( !(this.inventaireJoueur.get(planInventaire.get(compteur).getLigne()*6+ planInventaire.get(compteur).getColonne()).getQuantite() == this.inventaireJoueur.get(planInventaire.get(compteur).getLigne()*6+ planInventaire.get(compteur).getColonne()).getMaxStack()) ){//Limite atteinte par stack
                            int ajout = this.inventaireJoueur.get(planInventaire.get(compteur).getLigne()*6+ planInventaire.get(compteur).getColonne()).getMaxStack() - this.inventaireJoueur.get(planInventaire.get(compteur).getLigne()*6+ planInventaire.get(compteur).getColonne()).getQuantite();
                            this.inventaireJoueur.get(planInventaire.get(compteur).getLigne()*6+ planInventaire.get(compteur).getColonne()).ajouteQuantite(ajout);
                            reste = reste - ajout;
                        }
                    }
                    compteur++;
            }
            int compteur2=0;
            while (compteur2<planInventaire.size() && !placer) {
                    if (planInventaire.get(compteur2).getItem().getCodeObjet()==0) {  // Si la case est vide
                        System.out.println("Je passe par là");
                        this.inventaireJoueur.get(planInventaire.get(compteur2).getLigne()*6+planInventaire.get(compteur2).getColonne()).ajouterItem(item);
                        if (reste <= this.inventaireJoueur.get(planInventaire.get(compteur2).getLigne()*6+planInventaire.get(compteur2).getColonne()).getMaxStack()) { // Si l'ajout de l'item va pas dépasser la limite de stack
                            this.inventaireJoueur.get(planInventaire.get(compteur2).getLigne()*6+planInventaire.get(compteur2).getColonne()).ajouteQuantite(reste);
                            placer = true;
                        }
                        else {//Limite atteinte par stack
                            int ajout = this.inventaireJoueur.get(planInventaire.get(compteur2).getLigne()*6+planInventaire.get(compteur2).getColonne()).getMaxStack();
                            this.inventaireJoueur.get(planInventaire.get(compteur2).getLigne()*6+planInventaire.get(compteur2).getColonne()).ajouteQuantite(ajout);
                            reste = reste - ajout;
                        }
                    }
                    compteur2++;
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

    public ObservableList<Case> getInventaireJoueur() {
        return inventaireJoueur;
    }

    public void removeItem(int ligne, int colonne) {
        this.inventaireJoueur.get(ligne*6+colonne).setCase(new Item(), 0);
        System.out.println("Suppression item de l'inventaire effectué");
    }

    public Case getCase(int ligne, int colonne) {
        return this.inventaireJoueur.get(ligne*6+colonne);
    }

    // Trouve toutes les instances de l'item dans l'inventaire ainsi que les cases vides, retourne null si pas de place
    public ArrayList<Case> findItem(Item item) {
        ArrayList<Case> listInstances = new ArrayList<>();
        for (int i = 0; i < this.inventaireJoueur.size(); i++) {
                if (this.inventaireJoueur.get(i).comparerId(item.getCodeObjet())) { //Item présent
                    listInstances.add(inventaireJoueur.get(i));
                } else if (this.inventaireJoueur.get(i).getItem().getCodeObjet() == 0) {//Case vide
                    listInstances.add(inventaireJoueur.get(i));
                }
        }
        if (listInstances.isEmpty()) {
            System.out.println("Impossible de rajouter dans l'inventaire");
            return null;
        }
        else{
            return listInstances;
        }
    }
}
