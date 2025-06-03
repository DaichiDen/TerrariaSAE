package fr.iut.saeterraria.sae.Modele.Personnages;
import fr.iut.saeterraria.sae.Modele.Jeu;
import fr.iut.saeterraria.sae.Modele.Map.Map;
import fr.iut.saeterraria.sae.Modele.Objets.Item;
import fr.iut.saeterraria.sae.Modele.Objets.Outil.Pierre_TP;
import javafx.geometry.Rectangle2D;


public class Joueur extends Entite {
    private Inventaire inventaire; //hotbar (1-6), inventaire de taille 36
    private int[] equipement;
    private Pierre_TP pierreTp;
    private int mainCourante;

    private Map map;


    public Joueur(String nom, Map map, Jeu jeu) {


        super(nom, 20, 100, 20, 0, 0, 1, 10, map, jeu);
        this.equipement = new int[7];
        this.inventaire = new Inventaire();
        this.pierreTp = new Pierre_TP();
        this.mainCourante = 0;
        this.map = map;

    }


        public void incrementeMainCourante () {
            if (this.mainCourante == 6) {
                setMainCourante(0);
            } else {
                this.mainCourante++;
            }
        }
        public void decrementeMainCourante () {
            if (this.mainCourante == 0) {
                setMainCourante(6);
            } else {
                this.mainCourante--;
            }
        }
        public void setMainCourante ( int mainCourante){
            this.mainCourante = mainCourante;
        }
        public int getMainCourante () {
            return mainCourante;
        }

        public void ajouterItem (Item item,int quantite){
            inventaire.ajoutInventaire(item, quantite);
        }

        public Inventaire getInventaire () {
            return inventaire;
        }





        public void mettreAJour () {
            super.mettreAJour();
        }


        public int getVitesseY () {
            return vitesseY;
        }



    public void miner (Map map,int x, int y){
            if (peutEtreAtteint(map, x, y, 2.5)) {
                map.detruireBloc(x, y);
            }
        }


        public void poser (Map map,int x, int y, int val){

            if (peutEtreAtteint(map, x, y, 2.5)) {
                map.poserBloc(x, y, val);
            }
        }
    @Override
    public void attaquer(int x, int y, int range) {
        for (Entite e : jeu.getEnnemis()) {

            Rectangle2D hitboxMob = new Rectangle2D(e.getX(), e.getY(),taille1bloc,taille1bloc*2);

            // Si le clic est à l'intérieur de la hitbox du mob
            if (hitboxMob.contains(x,y)) {
                int ennemiX = (e.getX() + 16) / 32;
                int ennemiY = (e.getY() + 16) / 32;
                if (peutEtreAtteint(jeu.getCarte(), ennemiX, ennemiY, range)) {
                    e.decrementVie(1);
                    System.out.println("Touché !");
                    System.out.println(e.getBarreVie().getVie());
                }
            }
        }
    }








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


        public void tp ( int x, int y){
            this.setX(x);
            this.setY(y);
        }
        public Pierre_TP getPierreTp () {
            return this.pierreTp;
        }

    }

