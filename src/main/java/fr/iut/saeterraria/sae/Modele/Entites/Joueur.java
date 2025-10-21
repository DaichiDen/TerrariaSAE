    package fr.iut.saeterraria.sae.Modele.Entites;
    import fr.iut.saeterraria.sae.Modele.Jeu;
    import fr.iut.saeterraria.sae.Modele.Map.Carte;
    import fr.iut.saeterraria.sae.Modele.Objets.*;
    import fr.iut.saeterraria.sae.Modele.Objets.Arme.Grappin;
    import fr.iut.saeterraria.sae.Modele.Objets.Outil.Pioche;
    import fr.iut.saeterraria.sae.Modele.Objets.Outil.Pierre_TP;

    import fr.iut.saeterraria.sae.Modele.Inventaire.Inventaire;

    import javafx.beans.property.IntegerProperty;
    import javafx.beans.property.SimpleIntegerProperty;

    import javafx.geometry.Rectangle2D;


    import java.util.*;


    public class Joueur extends EntiteVivante {

        private static Joueur uniqueJoueur = null;
        private Inventaire inventaire; //hotbar (1-6), inventaire de taille 36
        private int[] equipement;
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


        private Joueur(String nom, int rangeVue, int rangeAttaque) {

            super(nom, 20, 100, 20, 20*32, 14*32, 1, 10,1,Jeu.getUniqueJeu().getTaille1bloc(),Jeu.getUniqueJeu().getTaille1bloc()*2,rangeVue,rangeAttaque);
            this.equipement = new int[7];
            this.inventaire = new Inventaire(7,6);
            this.mainCourante = 0;
            this.xPrec = super.getX()/32;
            this.xMax = new SimpleIntegerProperty(getX()/Jeu.getUniqueJeu().getTaille1bloc());
            this.yMax = new SimpleIntegerProperty(getY()/Jeu.getUniqueJeu().getTaille1bloc());
            this.stockItem = new int[2];

        }

        public static Joueur getUniqueJoueur() {
            if(uniqueJoueur == null) {
                uniqueJoueur = new Joueur("Joueur",3,3);
            }
            return uniqueJoueur;
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
            if (this.getX()/Jeu.getUniqueJeu().getTaille1bloc()>this.xMax.get()) {
                setXMax(this.getX());
            }
            if (this.getY()/Jeu.getUniqueJeu().getTaille1bloc()>this.yMax.get()) {
                setYMax(this.getY());
            }
            if(getMarcheGauche()){
                setDernierPos("gauche");
            }
            else if(getMarcheDroite()){
                setDernierPos("droite");
            }
            if (enDash) {
                Rectangle2D hitboxJoueur = new Rectangle2D(getX(), getY(), Jeu.getUniqueJeu().getTaille1bloc(), Jeu.getUniqueJeu().getTaille1bloc()*2);

                for (int i = 0; i < Jeu.getUniqueJeu().getEnnemis().size(); i++) {
                    Ennemi e = Jeu.getUniqueJeu().getEnnemis().get(i);
                    Rectangle2D hitboxEnnemi = new Rectangle2D(e.getX(), e.getY(), Jeu.getUniqueJeu().getTaille1bloc(), Jeu.getUniqueJeu().getTaille1bloc()*2);

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
            if (Carte.getUniqueCarte().peutEtreAtteint(x, y, 2.5,this)) {
                System.out.println(Carte.getUniqueCarte().getCase(y,x));
                if ( ((Bloc) ListeItems.getItemParId(Carte.getUniqueCarte().getCase(y,x))).getResistance() == 1 || Carte.getUniqueCarte().getCase(y, x) != 0 && Carte.getUniqueCarte().getCase(y, x) != 18 && Carte.getUniqueCarte().getCase(y, x) != 22 && inventaire.getCase(0,mainCourante).getItem().getCodeObjet()<55 && inventaire.getCase(0,mainCourante).getItem().getCodeObjet()>50 && compareResistance(((Bloc) ListeItems.getItemParId(Carte.getUniqueCarte().getCase(y,x)))) ) {
                    int[] bloc = Carte.getUniqueCarte().detruireBloc(x, y);
                    ajouterItem(ListeItems.getItemParId(bloc[0]), bloc[1]);
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
                 if (Carte.getUniqueCarte().peutEtreAtteint(x, y, 2.5, this) && inventaire.getCase(0,mainCourante).getItem().getCodeObjet() < 20 && Carte.getUniqueCarte().blocTraversable(y,x)) {
                    if (inventaire.getCase(0,mainCourante).getQuantite()>0) {
                        Carte.getUniqueCarte().poserBloc(x,y,inventaire.getCase(0,mainCourante).getItem().getCodeObjet());
                        inventaire.getCase(0, mainCourante).retireQuantite(1);
                    }
                }
            }
        }

        @Override
        public void action(int x, int y) {
            for (EntiteVivante e : Jeu.getUniqueJeu().getEnnemis()) {

                Rectangle2D hitboxMob = new Rectangle2D(e.getX(), e.getY(), Jeu.getUniqueJeu().getTaille1bloc(), (Jeu.getUniqueJeu().getTaille1bloc()) * 2);

                // Si le clic est à l'intérieur de la hitbox du mob
                if (hitboxMob.contains(x, y)) {
                    int ennemiX = (e.getX() + 16) / 32;
                    int ennemiY = (e.getY() + 16) / 32;
                    if (Carte.getUniqueCarte().peutEtreAtteint(ennemiX, ennemiY, getRangeVue(), e)){
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
            inventaire.verifierCraftPossible(item);
        }

        public void tp(int x, int y) {
            this.setX(x);
            this.setY(y);
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
        public void setXMax(int xMax){ this.xMax.setValue(xMax/Jeu.getUniqueJeu().getTaille1bloc()); }

        public int getYMax(){
            return yMax.getValue();
        }
        public IntegerProperty getYMaxProperty(){ return yMax; }

        public void setYMax(int yMax){ this.yMax.setValue(yMax/Jeu.getUniqueJeu().getTaille1bloc()); }

        public boolean gunEnMain() {
            return inventaire.getCase(0,mainCourante).getItem().getCodeObjet() == 79;
        }

        public void equiper(Armure armure) {
            switch (armure.getTypeArmure()){
                case 1:
                    if (equipement[0]==64 || equipement[0]==65) {
                       inventaire.ajoutInventaire(ListeItems.getItemParId(equipement[0]), 1);
                    }
                    equipement[0]=armure.getCodeObjet();
                    break;
                case 2:
                    if (equipement[1]==66 || equipement[1]==67) {
                        inventaire.ajoutInventaire(ListeItems.getItemParId(equipement[1]), 1);
                    }
                    equipement[1]=armure.getCodeObjet();
                    break;
                case 3:
                    if (equipement[2]==68 || equipement[2]==69) {
                        inventaire.ajoutInventaire(ListeItems.getItemParId(equipement[2]), 1);
                    }
                    equipement[2]=armure.getCodeObjet();
                    break;
                case 4:
                    if (equipement[3]==70 || equipement[3]==71) {
                        inventaire.ajoutInventaire(ListeItems.getItemParId(equipement[3]), 1);
                    }
                    equipement[3]=armure.getCodeObjet();
                    break;
            }
            inventaire.getCase(0,mainCourante).retireQuantite(1);
            updateDefense();
        }

        public void updateDefense(){
            for ( int piece : equipement){
                this.defProperty().setValue(this.defProperty().getValue() + ((Armure)ListeItems.getItemParId(piece)).getDefense());
            }
        }

        public void swapItem(int ligneDep, int colonneDep, int ligneFin, int colonneFin) {
            stockItem[0] = inventaire.getCase(ligneDep,colonneDep).getItem().getCodeObjet();
            stockItem[1] = inventaire.getCase(ligneDep,colonneDep).getQuantite();
            inventaire.getCase(ligneDep,colonneDep).setCase(ListeItems.getItemParId(inventaire.getCase(ligneFin,colonneFin).getItem().getCodeObjet()),inventaire.getCase(ligneFin,colonneFin).getQuantite());
            inventaire.getCase(ligneFin,colonneFin).setCase(ListeItems.getItemParId(stockItem[0]), stockItem[1]);
        }

        public void grappiner(int cibleX,int cibleY){

            int tab[];

            tab= ((Grappin) inventaire.getCase(0,mainCourante).getItem()).calculTrajectoireGrappin(cibleX,cibleY);
            this.setVitesseY(this.getVitesseY() + tab[1]);
            this.setVitesseX(this.getVitesseX() + tab[0]);
        }
    }




