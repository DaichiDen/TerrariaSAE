package fr.iut.saeterraria.sae.Modele.Objets;

import fr.iut.saeterraria.sae.Modele.Objets.Etablis.BlocConstruction;
import fr.iut.saeterraria.sae.Modele.Personnages.Case;

public class Coffre extends Bloc{
    private Case[][]contenu;

    public Coffre(String nom, String description, int typeBloc, int resistance, BlocConstruction blocConstruction) {
        super(nom, description, typeBloc, resistance);
        this.contenu = new Case[4][4];
    }

    public Case[][] getContenu() {
        return contenu;
    }

    public void addItem(Item item, int quantite) {
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
                        if (this.contenu[i][j].getQuantite()+reste <= this.contenu[i][j].getMaxStack())  { // Si l'ajout de l'item va pas dépasser la limite de stack
                            this.contenu[i][j].ajouteQuantite(reste);
                            placer = true;
                        }
                        else {//Limite atteinte par stack
                            int ajout = this.contenu[i][j].getMaxStack() - this.contenu[i][j].getQuantite();
                            this.contenu[i][j].ajouteQuantite(ajout);
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
                        if (reste <= this.contenu[i][j].getMaxStack())  { // Si l'ajout de l'item va pas dépasser la limite de stack
                            this.contenu[i][j].setCase(item,reste);
                            placer = true;
                        }
                        else {//Limite atteinte par stack
                            int ajout = this.contenu[i][j].getMaxStack();
                            this.contenu[i][j].setCase(item,ajout);
                            reste = reste - ajout;
                        }
                    }
                    j++;
                }
                i++;
            }
        }
    }

    public int[][] findItem(Item item) {
        boolean presentItemCaseLibre = false;
        int[][] instance = new int[this.contenu.length][this.contenu[0].length];
        for (int i = 0; i < this.contenu.length; i++) {
            for (int j = 0; j < this.contenu[i].length; j++) {
                if (this.contenu[i][j].comparerId(item.getCodeObjet())) {
                    instance[i][j] = 1;
                    presentItemCaseLibre = true;
                }
                else if (this.contenu[i][j] == null){
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
}
