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
        private final int DUREE_DASH_MAX = 20; // environ 15 frames = 250ms à 60fps
        private int vitesseDash = 5;
        private int[] stockItem;

        private String directionDash = "droite";// 1 = droite, -1 = gauche
        private String dernierPos = "droite"; // 1 gauche et -1 droite
        ArrayList<Ennemi> ennemis_touchées_dash = new ArrayList();
        private int xPrec;
        private IntegerProperty xMax,yMax;


        public Joueur(String nom, Jeu jeu, Pierre_TP pierreTp, int tailleL, int tailleH, int rangeVue, int rangeAttaque) {

            super(nom, 20, 100, 20, 20*32, 14*32, 1, 10, jeu,1, tailleL, tailleH, rangeVue, rangeAttaque);
            this.equipement = new int[7];
            this.inventaire = new Inventaire(7,6);
            this.pierreTp = pierreTp;
            this.mainCourante = 0;
            this.xPrec = super.getX()/32;
            this.xMax = new SimpleIntegerProperty(getX()/super.getJeu().getTaille1bloc());
            this.yMax = new SimpleIntegerProperty(getY()/super.getJeu().getTaille1bloc());
            this.stockItem = new int[2];

        }

        public boolean isTimeStop(){
            return timeStop.getValue();
        }
        public void setTimeStop(boolean timeStop) {
            this.timeStop.setValue(timeStop);
        }

        public void setDernierPos(String val){
            this.dernierPos=val;
        }

        public void setMainCourante(int mainCourante) {
            this.mainCourante = mainCourante;
            if(inventaire.getCase(0,mainCourante).getItem().getCodeObjet()>=72 && inventaire.getCase(0,mainCourante).getItem().getCodeObjet()<77){
                setAttaque(1 + ((Armes)(inventaire.getCase(0,mainCourante).getItem())).getAttaque());
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
            return  inventaire.getCase(0,mainCourante).getItem().getCodeObjet() == 72;
        }
        public boolean arcEnMain() {
            return inventaire.getCase(0,mainCourante).getItem().getCodeObjet() == 78;
        }
        public boolean bdfEnMain() {
            return inventaire.getCase(0,mainCourante).getItem().getCodeObjet() == 81;
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
                Rectangle2D hitboxJoueur = new Rectangle2D(getX(), getY(), getJeu().getTaille1bloc(), getJeu().getTaille1bloc()*2);

                for (int i = 0; i < super.getJeu().getEnnemis().size(); i++) {
                    Ennemi e = super.getJeu().getEnnemis().get(i);
                    Rectangle2D hitboxEnnemi = new Rectangle2D(e.getX(), e.getY(), getJeu().getTaille1bloc(), getJeu().getTaille1bloc()*2);

                    if (hitboxJoueur.intersects(hitboxEnnemi) && !ennemis_touchées_dash.contains(e)) {
                        ennemis_touchées_dash.add(e);
                        e.decrementVie(10);
                    }
                }

                // Mouvement : une seule fois par frame, hors de la boucle ennemis
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

            super.mettreAJour();

            // appel normal sinon

        }

        public boolean miner(int x, int y) {
            boolean miner = false;
            if (peutEtreAtteint(x, y, 2.5)) {
                if ( ((Bloc) super.getJeu().getItems().get(getJeu().getCarte().getCase(y,x))).getResistance() == 1 || getJeu().getCarte().getCase(y, x) != 0 && getJeu().getCarte().getCase(y, x) != 18 && getJeu().getCarte().getCase(y, x) != 22 && inventaire.getCase(0,mainCourante).getItem().getCodeObjet()<55 && inventaire.getCase(0,mainCourante).getItem().getCodeObjet()>50 && compareResistance(((Bloc) super.getJeu().getItems().get(getJeu().getCarte().getCase(y,x)))) ) {
                    int[] bloc = getJeu().getCarte().detruireBloc(x, y);
                    ajouterItem(super.getJeu().getItems().get(bloc[0]), bloc[1]);
                    miner = true;
                }
            }
            return miner;
        }

        public boolean compareResistance(Bloc bloc){
            return bloc.getResistance()<=((Pioche)inventaire.getCase(0,mainCourante).getItem()).getEfficacite();
        }

        public void poser(int x, int y) {//x = colonne && y = ligne
            if( ((this.getX()/32)!=x) || ((this.getY()/32)!=y) ) {
                 if (peutEtreAtteint(x, y, 2.5) && inventaire.getCase(0,mainCourante).getItem().getCodeObjet() < 20 && (getJeu().getCarte().getCase(y, x) == 0 || getJeu().getCarte().getCase(y, x) == 10 || getJeu().getCarte().getCase(y, x) == 18)) {
                    getJeu().getCarte().poserBloc(x, y, inventaire.getCase(0,mainCourante).getItem().getCodeObjet());
                     inventaire.getCase(0,mainCourante).retireQuantite(1);
                }
            }

        }

        @Override
        public void action(int x, int y) {
            for (EntiteVivante e : super.getJeu().getEnnemis()) {

                Rectangle2D hitboxMob = new Rectangle2D(e.getX(), e.getY(), getJeu().getTaille1bloc(), (getJeu().getTaille1bloc()) * 2);

                // Si le clic est à l'intérieur de la hitbox du mob
                if (hitboxMob.contains(x, y)) {
                    int ennemiX = (e.getX() + 16) / 32;
                    int ennemiY = (e.getY() + 16) / 32;
                    if (peutEtreAtteint(ennemiX, ennemiY, getRangeVue())){
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
            return inventaire.getCase(0,mainCourante).getItem().getCodeObjet() == 81;
        }

        // Vérifie si la quantité d'items nécessaires sont suffisants pour construire, puis craft l'item si les ressources sont suffisantes
        public void craftItem(Item item) {
            int[][] necessaire = getRecette(item);

            ArrayList<Case> position = new ArrayList<>();
            boolean craftableFin = verifieQuantiteSuffisante(necessaire,position, item);
            if (craftableFin) {
                craftFin(item,necessaire,position);
            }
            else {
                System.out.println("Craft non possible!");
            }
        }

        public int[][] getRecette(Item item) {
            int[][] necessaire = new int[2][item.getRecette().size()];
            for (int i = 0; i < necessaire[0].length; i++) {
                necessaire[0][i] = item.getRecette().get(i).getItem().getCodeObjet();
                necessaire[1][i] = item.getRecette().get(i).getQuantite();
            }
            return necessaire;
        }

        public boolean verifieQuantiteSuffisante(int[][] necessaire, ArrayList<Case> position, Item item) {
            boolean craftableFin = true;
            boolean[] craftable = new boolean[necessaire[0].length] ;//Indique si l'objet est en quantité suffisante
            int quantite;
            int i = 0;
            while ( craftableFin && i < craftable.length) { // Vérifie si les quantités sont suffisantes côté joueur
                ArrayList<Case> tabResult = inventaire.findItem(item.getRecette().get(i).getItem());
                if (tabResult != null) {
                    quantite = 0;
                    int o = 0;
                    while (!craftable[i] && o < tabResult.size()) {
                        if (tabResult.get(o).getItem().getCodeObjet()!=0) {
                            quantite = quantite + inventaire.getInventaireJoueur().get(o).getQuantite();
                            position.add(inventaire.getInventaireJoueur().get(o));
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
            return craftableFin;
        }

        public void craftFin(Item item, int[][] necessaire, ArrayList<Case> position) {
            int c = 0;
            int k = 0;
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
            }
            else {
                System.out.println("non réussi");
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
                System.out.println("dash");
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
            return inventaire.getCase(0,mainCourante).getItem().getCodeObjet() == 79;
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
            inventaire.getCase(0,mainCourante).retireQuantite(1);
            updateDefense();
        }

        public void updateDefense(){
            for ( int piece : equipement){
                this.defProperty().setValue(this.defProperty().getValue() + ((Armure)getJeu().getItems().get(piece)).getDefense());
            }
        }

        public void swapItem(int ligneDep, int colonneDep, int ligneFin, int colonneFin) {
            stockItem[0] = inventaire.getCase(ligneDep,colonneDep).getItem().getCodeObjet();
            stockItem[1] = inventaire.getCase(ligneDep,colonneDep).getQuantite();
            inventaire.getCase(ligneDep,colonneDep).setCase(getJeu().getItems().get(inventaire.getCase(ligneFin,colonneFin).getItem().getCodeObjet()),inventaire.getCase(ligneFin,colonneFin).getQuantite());
            inventaire.getCase(ligneFin,colonneFin).setCase(getJeu().getItems().get(stockItem[0]), stockItem[1]);
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




