package fr.iut.saeterraria.sae.Modele.Personnages;
import fr.iut.saeterraria.sae.Modele.Map.Map;
import fr.iut.saeterraria.sae.Modele.Objets.Bloc;
import fr.iut.saeterraria.sae.Modele.Objets.Item;
import fr.iut.saeterraria.sae.Modele.Objets.Outil.Pierre_TP;
import javafx.geometry.Rectangle2D;


import javafx.scene.input.MouseEvent;


import java.util.LinkedList;
import java.util.Queue;

import java.util.HashMap;


public class Joueur extends Entite {
    private Inventaire inventaire; //hotbar (1-6), inventaire de taille 36
    private int[] equipement;

    private Pierre_TP pierreTp;

    private boolean enSaut = false;
    private boolean marcheDroite = false;
    private boolean marcheGauche = false;
    private int vitesseY = 0;
    private int mainCourante;


    public Joueur(String nom, Map map) {


        super(nom, 20, 100, 20, 0, 0, 1, 10, map);
        this.equipement = new int[7];
	    this.inventaire = new Inventaire();
        this.pierreTp = new Pierre_TP();


    private boolean collisionBas = false;

    // valeurs pour l'inertie dur joueur (voir dans mettreAJour)
    private int vitesseX = 0;
    private final int accel_sol = 5;
    private final int accel_air = 2;
    private final int friction_sol = 4;
    private final int friction_air = 1;









    public void incrementeMainCourante() {
        if (this.mainCourante == 6) {
            setMainCourante(0);
        }
        else {
            this.mainCourante++;
        }
    }
    public void decrementeMainCourante() {
        if (this.mainCourante == 0) {
            setMainCourante(6);
        }
        else {
            this.mainCourante--;
        }
    }
    public void setMainCourante(int mainCourante) {
        this.mainCourante = mainCourante;
    }
    public int getMainCourante(){ return mainCourante; }

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

    public boolean getMarcheGauche() {
        return this.marcheGauche;
    }
    public boolean getMarcheDroite() {
        return this.marcheDroite;
    }

    public int getVitesseY(){
        return vitesseY;
    }

    public void sauter() {
        if (!enSaut) {
            enSaut = true;
            vitesseY = forceSaut;
        }
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


    public boolean peutEtreAtteint(Map map, int blocX, int blocY) {
        int joueurX = (this.getX() + 16) / 32;
        int joueurY = (this.getY() + 16) / 32;

        int dx = blocX - joueurX;
        int dy = blocY - joueurY;

        double distance = Math.sqrt(dx * dx + dy * dy);
        if (distance > 2.5) return false; // Quand c'est pas à portée

        // On marche dans la ligne du joueur au bloc cible
        int rayonLaser = (Math.max(Math.abs(dx), Math.abs(dy)) * 2); // le nombre d'étapes
        for (int i = 1; i < rayonLaser; i++) {
            double t = i / (double)rayonLaser;
            int xi = (int)Math.round(joueurX + dx * t);
            int yi = (int)Math.round(joueurY + dy * t);

            if ((xi != blocX || yi != blocY) && map.getCase(yi, xi) != 3) { // Si bloc devant (obstacle)
                return false;
            }
        }

        return true;
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




