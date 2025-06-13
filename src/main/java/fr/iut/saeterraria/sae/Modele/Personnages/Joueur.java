    package fr.iut.saeterraria.sae.Modele.Personnages;
    import fr.iut.saeterraria.sae.Modele.Jeu;
    import fr.iut.saeterraria.sae.Modele.Map.Map;
    import fr.iut.saeterraria.sae.Modele.Objets.Item;
    import fr.iut.saeterraria.sae.Modele.Objets.Outil.Pierre_TP;
    import javafx.beans.property.BooleanProperty;
    import javafx.beans.property.SimpleBooleanProperty;
    import javafx.geometry.Rectangle2D;



    import javafx.scene.input.MouseEvent;


    import java.util.*;


    public class Joueur extends EntiteVivante {
        private Inventaire inventaire; //hotbar (1-6), inventaire de taille 36
        private int[] equipement;
        private Pierre_TP pierreTp;
        private int mainCourante;
        private boolean enDash = false;
        private int dureeDash = 0;
        private final int DUREE_DASH_MAX = 30; // environ 15 frames = 250ms à 60fps
        private int vitesseDash = 10;

        private String directionDash = "droite";// 1 = droite, -1 = gauche
        private String dernierPos = "droite"; // 1 gauche et -1 droite
        ArrayList<Ennemi> ennemis_touchées_dash = new ArrayList();

        private Map map;

        public Joueur(String nom, Jeu jeu, Pierre_TP pierreTp) {


            super(nom, 20, 100, 20, 0, 0, 1, 10, jeu.getCarte(), jeu);


            this.equipement = new int[7];
            this.inventaire = new Inventaire();
            this.pierreTp = pierreTp;
            this.mainCourante = 0;
            this.map = jeu.getCarte();

        }


        public void incrementeMainCourante() {
            if (this.mainCourante == 6) {
                setMainCourante(0);
            } else {
                this.mainCourante++;
            }
        }
        public void setDernierPos(String val){
            this.dernierPos=val;
        }
        public String getDernierPos(){
            return this.dernierPos;
        }

        public void decrementeMainCourante() {
            if (this.mainCourante == 0) {
                setMainCourante(6);
            } else {
                this.mainCourante--;
            }
        }

        public void setMainCourante(int mainCourante) {
            this.mainCourante = mainCourante;
        }

        public int getMainCourante() {
            return mainCourante;
        }

        public boolean ajouterItem(Item item, int quantite) {
            return inventaire.ajoutInventaire(item, quantite);
        }

        public Inventaire getInventaire() {
            return inventaire;
        }
        public boolean katanaEnMain(){
            return  inventaire.getInventaireJoueur()[0][mainCourante].getItem().getCodeObjet() == 71;
        }
        public boolean arcEnMain() {
            return inventaire.getInventaireJoueur()[0][mainCourante].getItem().getCodeObjet() == 73;
        }

        public void mettreAJour() {

            if(getMarcheGauche()){
                setDernierPos("gauche");
            }
            else if(getMarcheDroite()){
                setDernierPos("droite");
            }
            if (enDash) {
                Rectangle2D hitboxJoueur = new Rectangle2D(getX(),getY(), getJeu().getTaille1bloc(),getJeu().getTaille1bloc()*2);
                for(int i=0;i<super.getJeu().getEnnemis().size();i++){
                    Rectangle2D hitboxEnnemi = new Rectangle2D(super.getJeu().getEnnemis().get(i).getX(),super.getJeu().getEnnemis().get(i).getY(),getJeu().getTaille1bloc(),getJeu().getTaille1bloc());
                    if(hitboxJoueur.intersects(hitboxEnnemi) && !ennemis_touchées_dash.contains(super.getJeu().getEnnemis().get(i))){
                        ennemis_touchées_dash.add(super.getJeu().getEnnemis().get(i));
                        ennemis_touchées_dash.get(i).decrementVie(10);
                    }

                    if (directionDash.equals("droite")) {
                        System.out.println("droite");
                        this.setX(this.getX() + vitesseDash);
                    } else {
                        System.out.println("gauche");
                        this.setX(this.getX() - vitesseDash);
                    }
                    dureeDash--;
                    if (dureeDash <= 0) {
                        enDash = false;
                    }
                }

            }
            super.mettreAJour();
            // appel normal sinon

        }


        public boolean miner(int x, int y) {


            boolean miner = false;
            if (peutEtreAtteint(x, y, 2.5)) {
                if (map.getCase(y, x) != 0) {
                    ajouterItem(super.getJeu().getItems().get(map.detruireBloc(x, y)), 1);
                    miner = true;
                }
            }
            return miner;
        }

        public void poser(int x, int y) {
            Rectangle2D hitboxJoueur= new Rectangle2D(this.getX()/32,this.getY()/32, getJeu().getTaille1bloc(), getJeu().getTaille1bloc()*2);
            Rectangle2D hitboxBloc = new  Rectangle2D(x/32,y/32, getJeu().getTaille1bloc(), getJeu().getTaille1bloc());
            if(hitboxJoueur.intersects(hitboxBloc)){


            if (peutEtreAtteint(x, y, 2.5) && inventaire.getInventaireJoueur()[0][mainCourante].getItem().getCodeObjet() != 0 && map.getCase(y, x) == 0) {
                map.poserBloc(x, y, inventaire.getInventaireJoueur()[0][mainCourante].getItem().getCodeObjet());
                inventaire.getInventaireJoueur()[0][mainCourante].retireQuantite(1);
            }
            }
        }

        @Override
        public void attaquer(int x, int y, int range) {
            for (EntiteVivante e : super.getJeu().getEnnemis()) {

                Rectangle2D hitboxMob = new Rectangle2D(e.getX(), e.getY(), getJeu().getTaille1bloc(), (getJeu().getTaille1bloc()) * 2);

                // Si le clic est à l'intérieur de la hitbox du mob
                if (hitboxMob.contains(x, y)) {
                    int ennemiX = (e.getX() + 16) / 32;
                    int ennemiY = (e.getY() + 16) / 32;
                    if (peutEtreAtteint(ennemiX, ennemiY, range)) {
                        e.decrementVie(1);
                        System.out.println("Touché !");
                        System.out.println(e.getBarreVie().getVie());
                    }
                }
            }
        }

        // Vérifie si la quantité d'items nécessaires sont suffisants pour construire, puis craft l'item si les ressources sont suffisantes
        public void craftItem(Item item) {
            int[][] necessaire = new int[2][item.getRecette().size()];//Besoin pour faire le craft
            // Liste les items et leur quantité pour le craft
            for (int i = 0; i < necessaire[0].length; i++) {
                necessaire[0][i] = item.getRecette().get(i).getItem().getCodeObjet();
                necessaire[1][i] = item.getRecette().get(i).getQuantite();
            }

            boolean[] craftable = new boolean[necessaire[0].length];//Indique si l'objet est en quantité suffisante
            boolean craftableFin = true;
            for (int i = 0; i < craftable.length; i++) {
                craftable[i] = false;
            }
            ArrayList<Case> position = new ArrayList<>();

            // Vérifie si les quantités sont suffisantes côté joueur
            for (int i = 0; i < necessaire[0].length; i++) {
                int[][] tabResult;
                tabResult = inventaire.findItem(item.getRecette().get(i).getItem());

                if (tabResult != null) {
                    int quantite = 0;
                    int o = 0;
                    int p = 0;
                    while (!craftable[i] && o < tabResult.length) {//Ligne
                        while (!craftable[i] && p < tabResult[o].length) {//Colonne
                            if (tabResult[o][p] == 1) {
                                quantite += inventaire.getInventaireJoueur()[o][p].getQuantite();
                                position.add(inventaire.getInventaireJoueur()[o][p]);
                            }
                            if (quantite >= necessaire[1][i]) {
                                craftable[i] = true;
                            }
                            p++;
                        }
                        o++;
                    }
                } else {
                    craftableFin = false;
                }
            }
            // Vérifie que tout les items nécessaires pour le craft sont en quantité suffisante avant de les décrémenter
            // de l'inventaire du joueur
            int j = 0;
            while (j < craftable.length && craftableFin) {
                if (!craftable[j]) {
                    craftableFin = false;
                }
                j++;
            }

            int c = 0;
            int k = 0;//Pour se déplacer sur chaque position des items

            // Enlève les quantités côté inventaire
            if (craftableFin) {

                if (inventaire.ajoutInventaire(item, 1)) {
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
                } else {
                    System.out.println("non réussi");
                }
            } else {
                System.out.println("craft pas réussi");
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


        public void tp(int x, int y) {
            this.setX(x);
            this.setY(y);
        }

        public Pierre_TP getPierreTp() {
            return this.pierreTp;
        }

        public void dashKatana() {

            if (enDash) {
                ennemis_touchées_dash.clear();
                dureeDash = DUREE_DASH_MAX;
                directionDash = dernierPos; // Dash dans la direction actuelle

            }
        }
        public void setEnDash(boolean val){
            this.enDash=val;
        }
        public boolean getEnDash(){
            return enDash;
        }
        public String getDirectionDash(){
            return directionDash;
        }


    }




