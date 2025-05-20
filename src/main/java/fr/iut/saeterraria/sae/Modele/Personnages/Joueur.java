package fr.iut.saeterraria.sae.Modele.Personnages;
import fr.iut.saeterraria.sae.Modele.Map.Map;
import fr.iut.saeterraria.sae.Modele.Objets.Item;
import javafx.geometry.Rectangle2D;

public class Joueur extends Entite {
    private Inventaire inventaire; //hotbar (1-6), inventaire de taille 36
    private int[] equipement;
    private boolean enSaut = false;
    private boolean marcheDroite = false;
    private boolean marcheGauche = false;
    private int vitesseY = 0;

    private int etat; // etat pour savoir s'il marche ou pas (voir si nécessaire)

    //constantes
    private final int gravité = 2;
    private final int forceSaut = -18;

    private boolean collisionBas = false;

    public Joueur(String nom) {

        super(nom, 100, 100, 20, 0, 0, 1, 10);

        this.equipement = new int[7];
	    this.inventaire = new Inventaire();

    }

    public void setEtat(){
        if(getMarcheGauche() && !getMarcheDroite()){
            etat = 1;
        }else if(getMarcheDroite() && !getMarcheGauche()){
            etat = 2;
        }else{
            etat = 0;
        }
    }

    public int getEtat(){
        return etat;
    }

    public void ajouterItem(Item item, int quantite) {
        inventaire.ajoutInventaire(item, quantite);
    }

    public int[][] getInventaire(){
        return inventaire.getInventaireJoueur();
    }

    public void setMarcheGauche(boolean marcheGauche) {
        this.marcheGauche = marcheGauche;
    }
    public void setMarcheDroite(boolean marcheDroite) {
        this.marcheDroite = marcheDroite;
    }


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

    public void mettreAJour(Map map) {
        // Appliquer gravité
        if (!collisionBas) {
		vitesseY+=gravité;		
	}
        setY(getY() + vitesseY);
        collisionVerticale(map);
        // Appliquer déplacement vertical, ensuite vérification des collisions, si le setY l'a fait rentrer dans qqch, alors le setY de la méthode collisionVertical le fait rester en dehors du bloc


        // Appliquer déplacement horizontal
        if (marcheGauche) {
            setX(getX() - getVitesse());
        }
        else if (marcheDroite) {
            setX(getX() + getVitesse());
        }
        collisionHorizontale(map);
    }

    public void collisionVerticale(Map map) { /** Fonction qui teste la collision verticale de façon dynamique, regarde seulement les 3 blocs autour du joueur (verticalement et horizontalement)*/
        collisionBas = false;

        Rectangle2D hitboxJoueur = new Rectangle2D(getX(),getY(),taille1bloc,taille1bloc*2);

        int caseX = (int) (getX() / taille1bloc);
        int caseY = (int) (getY() / taille1bloc);
        //boucle sur les 4 blocs autour du joueur , i+1 i-1 ,j+1 j-1
        for (int i = caseY - 1; i <= caseY + 3; i++) { // +3 pour la taille du personnage (2 blocs de hauteur)
            for (int j = caseX - 1; j <= caseX + 1; j++) {
                if (i >= 0 && i < map.getLigne() && j >= 0 && j < map.getColonne()) {
                    if (map.getCase(i, j) != 3) { // si le bloc n'est pas du ciel
                        int xBloc = map.getCoordonnéesX(j);
                        int yBloc = map.getCoordonnéesY(i);
                        Rectangle2D hitboxBloc = new Rectangle2D(xBloc, yBloc, taille1bloc, taille1bloc*2); // création d'un rectangle de hitbox pour le bloc en cours

                        if (hitboxJoueur.intersects(hitboxBloc)) { // si le rectangle du joueur se superpose au carré du bloc alors :
                            int blocHaut = yBloc;
                            int blocBas = yBloc + taille1bloc;
                            int joueurHaut = getY();
                            int joueurBas = getY() + (taille1bloc*2);

                            if (joueurBas >= blocHaut && vitesseY >= 0 && joueurHaut < blocHaut) { //vitesseY >=0 vérifie si le joueur est entrain de tomber
                                collisionBas = true;
                                enSaut = false;
                                vitesseY = 0;
                                setY(blocHaut - (taille1bloc*2));
                            }
                            else if (joueurHaut <= blocBas && vitesseY < 0 && joueurBas > blocBas) {
                                vitesseY = 0;
                                setY(blocBas);
                            }
                            if(map.getCase(i,j) == 4){
                                this.decrementVie(20);
                                System.out.println(this.getBarreVie().getVie());
                            }
                        }
                    }

                }
            }
        }
    }
    public void collisionHorizontale(Map map) {

        Rectangle2D hitboxJoueur = new Rectangle2D(this.getX(), this.getY(), taille1bloc, taille1bloc*2);

        int caseX = (int) (this.getX() / taille1bloc);
        int caseY = (int) (this.getY() / taille1bloc);

        boolean collisionDroite = false;
        boolean collisionGauche = false;

        for (int i = caseY - 1; i <= caseY + 3; i++) {
            for (int j = caseX - 1; j <= caseX + 2; j++) {
                if (i >= 0 && i < map.getLigne() && j >= 0 && j < map.getColonne()) {
                    if (map.getCase(i, j) != 3) {
                        int xBloc = map.getCoordonnéesX(j);
                        int yBloc = map.getCoordonnéesY(i);
                        Rectangle2D hitboxBloc = new Rectangle2D(xBloc, yBloc, taille1bloc, taille1bloc);

                        if (hitboxJoueur.intersects(hitboxBloc)) {
                            // Bords du bloc
                            int blocGauche = xBloc;
                            int blocDroite = xBloc + taille1bloc;
                            // Bords du joueur
                            int joueurGauche = this.getX();
                            int joueurDroite = this.getX() + taille1bloc;

                            if (joueurDroite > blocGauche && joueurGauche < blocGauche) {
                                // Collision côté droit du joueur contre gauche du bloc
                                collisionDroite = true;
                                // Repositionner le joueur pile à gauche du bloc
                                this.setX(blocGauche - taille1bloc);
                            } else if (joueurGauche < blocDroite && joueurDroite > blocDroite) {
                                // Collision côté gauche du joueur contre droite du bloc
                                collisionGauche = true;
                                // Repositionner le joueur pile à droite du bloc
                                this.setX(blocDroite);
                            }
                        }
                    }
                }
            }
        }

        if (collisionDroite) setMarcheDroite(false);
        if (collisionGauche) setMarcheGauche(false);
    }






    }




