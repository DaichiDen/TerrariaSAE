package fr.iut.saeterraria.sae.Modele.Inventaire;

import fr.iut.saeterraria.sae.Modele.Objets.Item;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class Inventaire {
    private ObservableList<Case> inventaireJoueur;
    private int ligneMax;
    private int colonneMax;

    public Inventaire(int ligneMax, int colonneMax) {
        this.inventaireJoueur = FXCollections.observableArrayList(); // 6 première colonne pour hotbar 36 Colonnes pour les 36 cases
        this.ligneMax = ligneMax;
        this.colonneMax = colonneMax;
        initialiseInventaire();
    }

    public void initialiseInventaire() {
        for (int ligne = 0; ligne < this.ligneMax; ligne++) {       // 7 lignes
            for (int colonne = 0; colonne < this.colonneMax; colonne++) { // 6 colonnes
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
            placer = AddDansCaseItem(planInventaire,item,placer,reste);
        }
        return placer;
    }

    public ObservableList<Case> getInventaireJoueur() {
        return inventaireJoueur;
    }

    public void removeItem(int ligne, int colonne) {
        this.inventaireJoueur.get(ligne*this.colonneMax+colonne).setCase(new Item(), 0);
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







    // Essaye d'ajouter l'item dans une case ayant le même item, si ce n'est pas possible, il va appeler addItemCaseVide qui va essayer d'ajouter dans une case vide
    public boolean AddDansCaseItem(ArrayList<Case> planInventaire, Item item, boolean placer, int reste) {
        int compteur = 0;
        while (compteur<planInventaire.size() && !placer) {
            if (planInventaire.get(compteur).comparerId(item.getCodeObjet())) { // Si Item déjà présent dans l'inventaire
                if (AjoutItemDepassePasMaxStack(getNumeroCase(planInventaire.get(compteur)),reste))  { // Si l'ajout de l'item va pas dépasser la limite de stack
                    this.inventaireJoueur.get(getNumeroCase(planInventaire.get(compteur))).ajouteQuantite(reste);
                    placer = true;
                }
                else if(caseEstPlein(getNumeroCase(planInventaire.get(compteur)))){ //Limite atteinte par stack
                    int ajout = this.inventaireJoueur.get(getNumeroCase(planInventaire.get(compteur))).getMaxStack() - this.inventaireJoueur.get(getNumeroCase(planInventaire.get(compteur))).getQuantite();
                    this.inventaireJoueur.get(getNumeroCase(planInventaire.get(compteur))).ajouteQuantite(ajout);
                    reste = reste - ajout;
                }
            }
            compteur++;
        }
        // Si ce n'est pas possible d'ajouter les items dans les cases ayant déjà un item alors on va essayer d'ajouter dans des cases vides
        if(!placer) {
            placer = addItemCaseVide(planInventaire,item,placer,reste);
        }
        return placer;
    }





    public boolean addItemCaseVide(ArrayList<Case> planInventaire, Item item, boolean placer, int reste) {
        int compteur2=0;
        while (compteur2<planInventaire.size() && !placer) {
            if (planInventaire.get(compteur2).getItem().getCodeObjet()==0) {  // Si la case est vide
                this.inventaireJoueur.get(getNumeroCase(planInventaire.get(compteur2))).ajouterItem(item);
                if (AjoutItemDepassePasMaxStack(getNumeroCase(planInventaire.get(compteur2)),reste)) { // Si l'ajout de l'item va pas dépasser la limite de stack
                    this.inventaireJoueur.get(planInventaire.get(compteur2).getLigne()*6+planInventaire.get(compteur2).getColonne()).ajouteQuantite(reste);
                    placer = true;
                }
                else { //Limite atteinte par stack
                    int ajout = this.inventaireJoueur.get(getNumeroCase(planInventaire.get(compteur2))).getMaxStack();
                    this.inventaireJoueur.get(getNumeroCase(planInventaire.get(compteur2))).ajouteQuantite(ajout);
                    reste = reste - ajout;
                }
            }
            compteur2++;
        }
        return placer;
    }


    public boolean AjoutItemDepassePasMaxStack(int numeroCase, int reste) {
        return this.inventaireJoueur.get(numeroCase).getQuantite()+reste <= this.inventaireJoueur.get(numeroCase).getMaxStack();
    }

    public int getNumeroCase(Case c) {
        return c.getLigne()*this.colonneMax+c.getColonne();
    }

    public boolean caseEstPlein(int numeroCase) {
        return this.inventaireJoueur.get(numeroCase).getQuantite()==this.inventaireJoueur.get(numeroCase).getMaxStack();
    }

    public boolean estEnQuantiteSuffisante(int[][] necessaire, ArrayList<Case> position, Item item) {
        boolean craftableFin = true;
        boolean[] craftable = new boolean[necessaire[0].length] ;//Indique si l'objet est en quantité suffisante
        int quantite;
        int i = 0;
        while ( craftableFin && i < craftable.length) { // Vérifie si les quantités sont suffisantes côté joueur
            ArrayList<Case> tabResult = findItem(item.getRecette().get(i).getItem());
            if (tabResult != null) {
                quantite = 0;
                int o = 0;
                while (!craftable[i] && o < tabResult.size()) {
                    if (tabResult.get(o).getItem().getCodeObjet()!=0) {
                        quantite = quantite + getCase(tabResult.get(o).getLigne(), tabResult.get(o).getColonne()).getQuantite();
                        position.add(getCase(tabResult.get(o).getLigne(), tabResult.get(o).getColonne()));
                    }
                    if (quantite >= necessaire[1][i]) {
                        craftable[i] = true;
                    }
                    o++;
                }
            }
            else {
                craftableFin = false;
            }
            i++;
        }

        int j = 0;
        while ( j<craftable.length && craftableFin) {
            if (!craftable[j]) {
                craftableFin = false;
            }
            j++;
        }

        return craftableFin;
    }

    public int[][] getRecette(Item item) {
        int[][] necessaire = new int[2][item.getRecette().size()];
        for (int i = 0; i < necessaire[0].length; i++) {
            necessaire[0][i] = item.getRecette().get(i).getItem().getCodeObjet();
            necessaire[1][i] = item.getRecette().get(i).getQuantite();
        }
        return necessaire;
    }

    public void verifierCraftPossible(Item item) {
        int[][] necessaire = getRecette(item);

        ArrayList<Case> position = new ArrayList<>();
        boolean craftableFin = estEnQuantiteSuffisante(necessaire,position, item);
        if (craftableFin) {
            debuterCraft(item,necessaire,position);
        }
    }

    public void debuterCraft(Item item, int[][] necessaire, ArrayList<Case> position) {
        int c = 0;
        int k = 0;
        if (ajoutInventaire(item, 1)) {
            while (c < necessaire[1].length && necessaire[1][c] > 0) {//Pour chaque item nécessaire
                while (k < position.size() && necessaire[1][c] > 0) { //Retire à chaque position des items
                    if (necessaire[1][c] <= position.get(k).getQuantite()) { // Si la case a assez pour le craft
                        position.get(k).retireQuantite(necessaire[1][c]);
                        necessaire[1][c] = 0;
                    } else { // Si la case n'a pas assez pour le craft
                        necessaire[1][c] -= position.get(k).getQuantite();
                        position.get(k).retireQuantite(position.get(k).getQuantite());
                    }
                    k++;
                }
                c++;
            }
            System.out.println("craft réussi");
        }
        else {
            System.out.println("craft non réussi");
        }
    }
}
