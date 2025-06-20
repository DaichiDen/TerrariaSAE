    package fr.iut.saeterraria.sae.Modele.Personnages;
    import fr.iut.saeterraria.sae.Modele.Jeu;
    import fr.iut.saeterraria.sae.Modele.Map.Map;
    import fr.iut.saeterraria.sae.Modele.Objets.*;
    import fr.iut.saeterraria.sae.Modele.Objets.Outil.Pioche;
    import fr.iut.saeterraria.sae.Modele.Objets.Outil.Pierre_TP;

    import javafx.beans.property.BooleanProperty;
    import javafx.beans.property.SimpleBooleanProperty;

    import javafx.beans.property.IntegerProperty;
    import javafx.beans.property.SimpleIntegerProperty;

    import javafx.geometry.Rectangle2D;

    import javafx.scene.input.MouseEvent;


    import java.util.*;


    public class Joueur extends EntiteVivante {
        private Inventaire inventaire; //hotbar (1-6), inventaire de taille 36
        private int[] equipement;
        private BooleanProperty timeStop = new SimpleBooleanProperty(false);
        private Pierre_TP pierreTp;
        private int mainCourante;
        private boolean enDash = false;
        private int dureeDash = 0;
        private final int DUREE_DASH_MAX = 30; // environ 15 frames = 250ms à 60fps
        private int vitesseDash = 10;
        private int[] stockItem;

        private String directionDash = "droite";// 1 = droite, -1 = gauche
        private String dernierPos = "droite"; // 1 gauche et -1 droite
        ArrayList<Ennemi> ennemis_touchées_dash = new ArrayList();
        private int xPrec;
        private IntegerProperty xMax,yMax;


        public Joueur(String nom, Jeu jeu, Pierre_TP pierreTp, int tailleL, int tailleH) {

            super(nom, 20, 100, 20, 20*32, 14*32, 1, 10, jeu,1, tailleL, tailleH);
            this.equipement = new int[7];
            this.inventaire = new Inventaire();
            this.pierreTp = pierreTp;
            this.mainCourante = 0;
            this.xPrec = super.getX()/32;
            this.xMax = new SimpleIntegerProperty(getX()/super.getJeu().getTaille1bloc());
            this.yMax = new SimpleIntegerProperty(getY()/super.getJeu().getTaille1bloc());
            this.stockItem = new int[2];

        }

        public void incrementeMainCourante() {
            if (this.mainCourante == 6) {
                setMainCourante(0);
            } else {
                this.mainCourante++;
            }
        }
        public boolean isTimeStop(){
            return timeStop.getValue();
        }
        public BooleanProperty timeStopProperty() {
            return timeStop;
        }
        public void setTimeStop(boolean timeStop) {
            this.timeStop.setValue(timeStop);
        }

        public void setDernierPos(String val){
            this.dernierPos=val;
        }
        public String getDernierPos(){
            return this.dernierPos;
        }

        public void setMainCourante(int mainCourante) {
            this.mainCourante = mainCourante;
            if(inventaire.getInventaireJoueur()[0][mainCourante].getItem().getCodeObjet()>=72 && inventaire.getInventaireJoueur()[0][mainCourante].getItem().getCodeObjet()<77){
                setAttaque(1 + ((Armes)(inventaire.getInventaireJoueur()[0][mainCourante].getItem())).getAttaque());
            }
            else {
                setAttaque(2);
            }
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
            return  inventaire.getInventaireJoueur()[0][mainCourante].getItem().getCodeObjet() == 72;
        }
        public boolean arcEnMain() {
            return inventaire.getInventaireJoueur()[0][mainCourante].getItem().getCodeObjet() == 78;
        }
        public boolean bdfEnMain() {
            return inventaire.getInventaireJoueur()[0][mainCourante].getItem().getCodeObjet() == 81;
        }

        public void mettreAJour() {
            if (this.getX()/super.getJeu().getTaille1bloc()>this.xMax.get()) {
                setXMax(this.getX());
            }
            if (this.getY()/super.getJeu().getTaille1bloc()>this.yMax.get()) {
                setYMax(this.getY());
            }
            if(getMarcheGauche()){
                setDernierPos("gauche");
            }
            else if(getMarcheDroite()){
                setDernierPos("droite");
            }
            if (enDash) {
                Rectangle2D hitboxJoueur = new Rectangle2D(getX(),getY(), getJeu().getTaille1bloc(),getJeu().getTaille1bloc()*2);
                for(int i=0;i<super.getJeu().getEnnemis().size();i++){
                    Rectangle2D hitboxEnnemi = new Rectangle2D(super.getJeu().getEnnemis().get(i).getX(),super.getJeu().getEnnemis().get(i).getY(),getJeu().getTaille1bloc(),getJeu().getTaille1bloc()*2);
                    if(hitboxJoueur.intersects(hitboxEnnemi) && !ennemis_touchées_dash.contains(super.getJeu().getEnnemis().get(i))){
                        ennemis_touchées_dash.add(super.getJeu().getEnnemis().get(i));
                        ennemis_touchées_dash.get(i).decrementVie(10);
                    }

                    if (directionDash.equals("droite")) {
                        this.setX(this.getX() + vitesseDash);
                    } else {
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
                if ( ((Bloc) super.getJeu().getItems().get(getJeu().getCarte().getCase(y,x))).getResistance() == 1 || getJeu().getCarte().getCase(y, x) != 0 && getJeu().getCarte().getCase(y, x) != 18 && getJeu().getCarte().getCase(y, x) != 22 && this.inventaire.getInventaireJoueur()[0][mainCourante].getItem().getCodeObjet()<55 &&this.inventaire.getInventaireJoueur()[0][mainCourante].getItem().getCodeObjet()>50 && compareResistance(((Bloc) super.getJeu().getItems().get(getJeu().getCarte().getCase(y,x)))) ) {
                    int[] bloc = getJeu().getCarte().detruireBloc(x, y);
                    ajouterItem(super.getJeu().getItems().get(bloc[0]), bloc[1]);
                    miner = true;
                }
            }
            return miner;
        }

        public boolean compareResistance(Bloc bloc){
            return bloc.getResistance()<=((Pioche)inventaire.getInventaireJoueur()[0][mainCourante].getItem()).getEfficacite();
        }

        public void poser(int x, int y) {//x = colonne && y = ligne
            if( ((this.getX()/32)!=x) || ((this.getY()/32)!=y) ) {
                 if (peutEtreAtteint(x, y, 2.5) && inventaire.getInventaireJoueur()[0][mainCourante].getItem().getCodeObjet() < 20 && (getJeu().getCarte().getCase(y, x) == 0 || getJeu().getCarte().getCase(y, x) == 10 || getJeu().getCarte().getCase(y, x) == 18)) {
                    getJeu().getCarte().poserBloc(x, y, inventaire.getInventaireJoueur()[0][mainCourante].getItem().getCodeObjet());
                    inventaire.getInventaireJoueur()[0][mainCourante].retireQuantite(1);
                }
            }

        }

        @Override
        public void action(int x, int y, int range) {
            for (EntiteVivante e : super.getJeu().getEnnemis()) {

                Rectangle2D hitboxMob = new Rectangle2D(e.getX(), e.getY(), getJeu().getTaille1bloc(), (getJeu().getTaille1bloc()) * 2);

                // Si le clic est à l'intérieur de la hitbox du mob
                if (hitboxMob.contains(x, y)) {
                    int ennemiX = (e.getX() + 16) / 32;
                    int ennemiY = (e.getY() + 16) / 32;
                    if (peutEtreAtteint(ennemiX, ennemiY, range)) {
                        if (this.getAttaque() - e.getDef()>0){
                            e.decrementVie(getAttaque() - e.getDef());
                        }
                        System.out.println("Touché !");
                        System.out.println("Vie restante : dddddd" +e.getBarreVie().getVie());
                    }
                }
            }
        }
        public boolean grappinEnMain() {
            return inventaire.getInventaireJoueur()[0][mainCourante].getItem().getCodeObjet() == 81;
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

        public int getXMax(){
            return xMax.getValue();
        }
        public IntegerProperty getXMaxProperty(){ return xMax; }
        public void setXMax(int xMax){ this.xMax.setValue(xMax/super.getJeu().getTaille1bloc()); }

        public int getYMax(){
            return yMax.getValue();
        }
        public IntegerProperty getYMaxProperty(){ return yMax; }
        public void setYMax(int yMax){ this.yMax.setValue(yMax/super.getJeu().getTaille1bloc()); }

        public boolean gunEnMain() {
            return inventaire.getInventaireJoueur()[0][mainCourante].getItem().getCodeObjet() == 79;
        }

        public void equiper(Armure armure) {
            switch (armure.getTypeArmure()){
                case 1:
                    if (equipement[0]==64 || equipement[0]==65) {
                       inventaire.ajoutInventaire(getJeu().getItems().get(equipement[0]), 1);
                    }
                    equipement[0]=armure.getCodeObjet();
                    break;
                case 2:
                    if (equipement[1]==66 || equipement[1]==67) {
                        inventaire.ajoutInventaire(getJeu().getItems().get(equipement[1]), 1);
                    }
                    equipement[1]=armure.getCodeObjet();
                    break;
                case 3:
                    if (equipement[2]==68 || equipement[2]==69) {
                        inventaire.ajoutInventaire(getJeu().getItems().get(equipement[2]), 1);
                    }
                    equipement[2]=armure.getCodeObjet();
                    break;
                case 4:
                    if (equipement[3]==70 || equipement[3]==71) {
                        inventaire.ajoutInventaire(getJeu().getItems().get(equipement[3]), 1);
                    }
                    equipement[3]=armure.getCodeObjet();
                    break;
            }
            inventaire.getInventaireJoueur()[0][mainCourante].retireQuantite(1);
            updateDefense();
        }

        public void updateDefense(){
            for ( int piece : equipement){
                this.defProperty().setValue(this.defProperty().getValue() + ((Armure)getJeu().getItems().get(piece)).getDefense());
            }
        }

        public void swapItem(int ligneDep, int colonneDep, int ligneFin, int colonneFin) {
            stockItem[0] = inventaire.getInventaireJoueur()[ligneDep][colonneDep].getItem().getCodeObjet();
            stockItem[1] = inventaire.getInventaireJoueur()[ligneDep][colonneDep].getQuantite();
            inventaire.getInventaireJoueur()[ligneDep][colonneDep].setCase(getJeu().getItems().get(inventaire.getInventaireJoueur()[ligneFin][colonneFin].getItem().getCodeObjet()),inventaire.getInventaireJoueur()[ligneFin][colonneFin].getQuantite());
            inventaire.getInventaireJoueur()[ligneFin][colonneFin].setCase(getJeu().getItems().get(stockItem[0]), stockItem[1]);
        }

        public void grappiner(int cibleX, int cibleY){
            // Position de l'entité
            int ex = this.getX();
            int ey = this.getY();

            // Direction du tir
            int dx = cibleX - ex;
            int dy = cibleY - ey;

            // Normalisation du vecteur (dx, dy)
            int distance = (int) Math.sqrt(dx * dx + dy * dy);
            if (distance == 0) distance = 1; // éviter division par zéro

            // Vitesse initiale (puissance du tir)
            int puissance = 50;

            int vx = (int) (((float) dx / distance) * puissance);
            int vy = (int) (((float) dy / distance) * puissance);

            this.setVitesseY(this.getVitesseY() + vy);
            this.setVitesseX(this.getVitesseX() + vx);
        }
    }




