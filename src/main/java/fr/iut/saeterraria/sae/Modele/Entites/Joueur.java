    package fr.iut.saeterraria.sae.Modele.Entites;
    import fr.iut.saeterraria.sae.Modele.Jeu;
    import fr.iut.saeterraria.sae.Modele.Map.Carte;
    import fr.iut.saeterraria.sae.Modele.Objets.*;
    import fr.iut.saeterraria.sae.Modele.Objets.Arme.Grappin;
    import fr.iut.saeterraria.sae.Modele.Objets.Outil.Pioche;
    import fr.iut.saeterraria.sae.Modele.Objets.Outil.Pierre_TP;

    import fr.iut.saeterraria.sae.Modele.Inventaire.Inventaire;

    import javafx.beans.property.BooleanProperty;
    import javafx.beans.property.IntegerProperty;
    import javafx.beans.property.SimpleBooleanProperty;
    import javafx.beans.property.SimpleIntegerProperty;

    import javafx.geometry.Rectangle2D;


    import java.util.*;

    /*
     * Classe représentant le joueur
     * Elle définit ses propriétés:
     * son inventaire, son equipement, les propriétés de son dash avec le katana
     *
     * Elle donne les méthodes utilisées par le joueur pour la gestion de son inventaire et equipement et la gestion de son dash
     *
     */

    public class Joueur extends EntiteVivante {

        private static Joueur uniqueJoueur = null;
        private Inventaire inventaire; //hotbar (1-6), inventaire de taille 36



        private Armure[] equipement;
        private BooleanProperty timeStop = new SimpleBooleanProperty(false);

        private Pierre_TP pierreTp;
        private int mainCourante;
        private boolean enDash = false;
        private int dureeDash = 0;
        private final int DUREE_DASH_MAX = 20; // environ 15 frames = 250ms à 60fps
        private int vitesseDash = 5;
        private int[] stockItem;

        private String nom;

        private String directionDash = "droite";// 1 = droite, -1 = gauche
        private String dernierPos = "droite"; // 1 gauche et -1 droite
        ArrayList<Ennemi> ennemis_touchées_dash = new ArrayList();
        private int xPrec;
        private IntegerProperty xMax,yMax;


        private Joueur(String nom, int rangeVue, int rangeAttaque) {

            super(20, 100, 20, 20*32, 14*32, 1, 10,1,Jeu.getUniqueJeu().getTaille1bloc(),Jeu.getUniqueJeu().getTaille1bloc()*2,rangeVue,rangeAttaque);
            this.equipement = new Armure[7];
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

        public Armure[] getEquipement() {
            return equipement;
        }
        public Armure getCaseEquipement(int x) {
            return equipement[x];
        }
        public void setEquipement(int x, Armure codeObjet) {
            this.equipement[x] = codeObjet;
        }

        public String getNom() {
            return nom;
        }
        public void setNom(String nom) {
            this.nom = nom;
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
        public boolean grappinEnMain() {
            return inventaire.getCase(0,mainCourante).getItem().getCodeObjet() == 81;
        }
        public boolean gunEnMain() {
            return inventaire.getCase(0,mainCourante).getItem().getCodeObjet() == 79;
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
                appliquerDash();
            }
            super.mettreAJour();
            // appel normal sinon
        }

        public void appliquerDash(){
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

        public void craftItem(Item item) {
            inventaire.verifierCraftPossible(item);
        }
        public void equiper(Armure armure) {

            int typeArmure = armure.getTypeArmure();
            int caseEquipement = armure.getCaseEquipement();
            if (equipement[caseEquipement] != null){
                if (equipement[caseEquipement].getTypeArmure() == typeArmure) {
                    inventaire.ajoutInventaire(ListeItems.getItemParId(equipement[caseEquipement].getCodeObjet()), 1);
                }
            }
            equipement[caseEquipement] = armure;

            inventaire.getCase(0, mainCourante).retireQuantite(1);
            updateDefense();
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
                        System.out.println("Vie restante : " +e.getBarreVie().getVie());
                    }
                }
            }
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



        public void updateDefense(){
            int defense = 1;
            for (Armure armure : equipement){
                if (armure!=null){
                    defense+=armure.getDefense();
                }
            }
            this.defProperty().setValue(defense);
        }

        public void swapItem(int ligneDep, int colonneDep, int ligneFin, int colonneFin) {
            Item itemCase1 = inventaire.getCase(ligneDep,colonneDep).getItem();
            int quantiteCase1 = inventaire.getCase(ligneDep,colonneDep).getQuantite();

            inventaire.getCase(ligneDep,colonneDep).setCase(inventaire.getCase(ligneFin,colonneFin).getItem(),inventaire.getCase(ligneFin,colonneFin).getQuantite());
            inventaire.getCase(ligneFin,colonneFin).setCase(itemCase1, quantiteCase1);
        }

        public void grappiner(int cibleX,int cibleY){

            int tab[];

            tab= ((Grappin) inventaire.getCase(0,mainCourante).getItem()).calculTrajectoireGrappin(cibleX,cibleY);
            this.setVitesseY(this.getVitesseY() + tab[1]);
            this.setVitesseX(this.getVitesseX() + tab[0]);
        }
    }




