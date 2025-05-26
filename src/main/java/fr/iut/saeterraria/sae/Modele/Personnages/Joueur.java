package fr.iut.saeterraria.sae.Modele.Personnages;
import fr.iut.saeterraria.sae.Modele.Map.Map;
import fr.iut.saeterraria.sae.Modele.Objets.Bloc;
import fr.iut.saeterraria.sae.Modele.Objets.Item;
import fr.iut.saeterraria.sae.Modele.Objets.Outil.Pierre_TP;
import javafx.geometry.Rectangle2D;

public class Joueur extends Entite {
    private Inventaire inventaire; //hotbar (1-6), inventaire de taille 36
    private int[] equipement;
    private Pierre_TP pierreTp;

    public Joueur(String nom, Map map) {

        super(nom, 20, 100, 20, 0, 0, 1, 10, map);
        this.equipement = new int[7];
	    this.inventaire = new Inventaire();
        this.pierreTp = new Pierre_TP();

    }

    public void ajouterItem(Item item, int quantite) {
        inventaire.ajoutInventaire(item, quantite);
    }

    public Inventaire getInventaire(){
        return inventaire;
    }

    @Override
    public void attaquer() {

    }

    public void mettreAJour() {
        super.mettreAJour();
    }
    public void miner(Map map,int x,int y) {
        if(peutEtreAtteint(map,x,y,2.5)){
            map.detruireBloc(x,y);
        }
    }
    public void poser(Map map, int x, int y, int val) {

        if(peutEtreAtteint(map,x,y,2.5)){
            map.poserBloc(x,y,val);
        }
    }



//    public boolean peutEtreAtteint(Map map, int blocX, int blocY) {
//        int centreX = (this.getX() + 16) / 32; // centre horizontal
//        int centreY = (this.getY() + 16) / 32; // centre vertical (milieu du perso)
//
//        int dx = blocX - centreX;
//        int dy = blocY - centreY;
//
//        if (Math.abs(dx) > 2 || Math.abs(dy) > 2) {
//            return false;
//        }
//
//        // Si dans un rayon de 1, accessible direct
//        if (Math.abs(dx) <= 1 && Math.abs(dy) <= 1) {
//            return true;
//        }
//
//        // Si distance de 2 en X (horizontal)
//        if (Math.abs(dx) == 2 && Math.abs(dy) <= 1) {
//            int midX = centreX + Integer.signum(dx);
//            return map.getCase(centreY, midX) == 3 &&
//                    map.getCase(centreY - 1, midX) == 3;
//        }
//
//        // Si distance de 2 en Y (vertical)
//        if (Math.abs(dy) == 2 && Math.abs(dx) <= 1) {
//            int midY = centreY + Integer.signum(dy);
//            return map.getCase(midY, centreX) == 3;
//        }
//
//        // Diagonales longues (2 en X et 2 en Y)
//        if (Math.abs(dx) == 2 && Math.abs(dy) == 2) {
//            int midX = centreX + Integer.signum(dx);
//            int midY = centreY + Integer.signum(dy);
//            return map.getCase(centreY, midX) == 3 &&
//                    map.getCase(centreY - 1, midX) == 3 &&
//                    map.getCase(midY, centreX) == 3;
//        }
//
//        // Cas en L : (2,1)
//        if (Math.abs(dx) == 2 && Math.abs(dy) == 1) {
//            int midX = centreX + Integer.signum(dx);
//            return map.getCase(centreY, midX) == 3 &&
//                    map.getCase(centreY - 1, midX) == 3;
//        }
//
//        // Cas en L : (1,2)
//        if (Math.abs(dx) == 1 && Math.abs(dy) == 2) {
//            int midY = centreY + Integer.signum(dy);
//            return map.getCase(midY, centreX) == 3;
//        }
//
//        return false;
//    }





//    public int[][][] dansZone(Map map) {
//        int caseX = (int) (getX() / taille1bloc);
//        int caseY = (int) (getY() / taille1bloc);
//
//        int[][][] tab = new int[6][5][2]; // 6 lignes visibles (Y), 5 colonnes (X)
//        for (int dy = -2; dy <= 3; dy++) { // hauteur : 6 cases (y -2 à y +3)
//            for (int dx = -2; dx <= 2; dx++) { // largeur : 5 cases (x -2 à x +2)
//
//                int x = caseX + dx;
//                int y = caseY + dy;
//
//                int i = dy + 2; // Pour que dy = -2 commence à l'indice 0
//                int j = dx + 2; // Pour que dx = -2 commence à l'indice 0
//
//                if (y >= 0 && y < map.getLigne() && x >= 0 && x < map.getColonne()) {
//                    if (map.getCase(y, x) != 3) {
//                        tab[i][j][0] = (map.getCoordonnéesX(x));
//                        tab[i][j][1] = (map.getCoordonnéesY(y));
//                    } else {
//                        tab[i][j][0] = -1;
//                        tab[i][j][1] = -1;
//                    }
//                } else {
//                    tab[i][j][0] = -1;
//                    tab[i][j][1] = -1;
//                }
//            }
//        }
//
//        return tab;
//    }


//    public void afficheInRange(int[][][] tab){
//        for(int i = 0; i < tab.length; i++){
//            for(int j = 0; j < tab[i].length; j++){
//                if (tab[i][j][0] != -1) {
//                    System.out.print("[x=" + tab[i][j][0] + ", y=" + tab[i][j][1] + "] ");
//                } else {
//                    System.out.print("[ -- ] ");
//                }
//            }
//            System.out.println();
//        }
//        System.out.println("------------------");
//    }



    public void tp(int x, int y){
        this.setX(x);
        this.setY(y);
    }
    public Pierre_TP getPierreTp(){
        return this.pierreTp;
    }






    }




