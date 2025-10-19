package fr.iut.saeterraria.sae.Modele.Map;

import fr.iut.saeterraria.sae.Modele.Entites.Entite;
import fr.iut.saeterraria.sae.Modele.Entites.Joueur;
import fr.iut.saeterraria.sae.Modele.Objets.Bloc;
import fr.iut.saeterraria.sae.Modele.Objets.ListeItems;
import fr.iut.saeterraria.sae.Modele.Objets.Outil.Pioche;

public class Carte {
    private static Carte CarteUnique = null;
    private int[][] carte;

    public Carte() {
        JSONMapLoader.MapData mapData = JSONMapLoader.loadMap("/MapRéduite.tmj");

        int largeur = mapData.width;
        int hauteur = mapData.height;
        int[] tiles = mapData.layers.get(0);

        carte = new int[hauteur][largeur];

        for (int y = 0; y < hauteur; y++) {
            for (int x = 0; x < largeur; x++) {
                int index = y * largeur + x;
                carte[y][x] = tiles[index];
            }
        }
        rechargeMap();
    }

    public static Carte getUniqueCarte() {
        if (CarteUnique == null) {
            CarteUnique = new Carte();
        }
        return CarteUnique;
    }

    public void rechargeMap(){
        for (int i = 0; i < carte.length; i++) {
            for (int j = 0; j < carte[0].length; j++) {
                switch (carte[i][j]){
                    case 1:
                        carte[i][j] = 3;
                        break;
                    case 2:
                        carte[i][j] = 7;
                        break;
                    case 3:
                        carte[i][j] = 0;
                        break;
                    case 4:
                        carte[i][j] = 10;
                        break;
                    case 5:
                        carte[i][j] = 1;
                        break;
                    case 6:
                        carte[i][j] = 19;
                        break;
                    case 7:
                        carte[i][j] = 6;
                        break;
                    case 8:
                        carte[i][j] = 4;
                        break;
                    case 9:
                        carte[i][j] = 9;
                        break;
                    case 10:
                        carte[i][j] = 2;
                        break;
                    case 11:
                        carte[i][j] = 5;
                        break;
                    case 12:
                        carte[i][j] = 13;
                        break;
                    case 13:
                        carte[i][j] = 12;
                        break;
                    case 14:
                        carte[i][j] = 17;
                        break;
                    case 15:
                        carte[i][j] = 8;
                        break;
                    case 16:
                        carte[i][j] = 20;
                        break;
                    case 17:
                        carte[i][j] = 14;
                        break;
                    case 18:
                        carte[i][j] = 15;
                        break;
                    case 19:
                        carte[i][j] = 16;
                        break;
                    case 20:
                        carte[i][j] = 18;
                        break;
                }
            }
        }
    }

    public int recupColonneTaille(){
        return carte[0].length;
    }
    public int recupLigneTaille(){
        return carte.length;
    }

    public int getCase(int x, int y){ return carte[x][y];}

    public int[] detruireBloc(int x,int y){
        int[] blocRecup = new int[2];
        blocRecup[0] = getCase(y,x);
        blocRecup[1] = 1;
        if (y>20){
            carte[y][x]=10;
        }
        else {
            carte[y][x]=0;
        }

        switch (blocRecup[0]){
            case 1:
                blocRecup[0] = 2;
                break;
            case 4:
                blocRecup[0] = 21;
                blocRecup[1] = 4;
                break;
            case 20:
                blocRecup[0] = 72;
                break;
            default:
                break;

        }
        return blocRecup;
    } //TODO segmentation possible ?
    public void poserBloc(int x,int y,int val){
        carte[y][x]=val;
    }

    public boolean getPique(int x,int y){
        return carte[x][y]==8;
    }
    public boolean blocDeFabrication(int x,int y){
        return carte[y][x]==12 || carte[y][x]==13 || carte[y][x]==15;
    }
    public boolean blocTraversable(int x,int y){
        return carte[x][y] == 0 || carte[x][y] == 10 || carte[x][y] == 18;
    }

    public boolean peutEtreAtteint(int blocX, int blocY, double portee, Entite entite) {
        boolean peutEtreAtteint = true;
        if(!estDansLaPortée(blocX,blocY,portee,entite)){// Quand c'est pas à portée
            peutEtreAtteint= false;
        }

        if (!DDA(blocX,blocY,entite)) { // Si bloc devant (obstacle)
            peutEtreAtteint= false;
        }

        return peutEtreAtteint;
    }

    public int calculDX(int blocX, Entite entite){

        return blocX - transfoXEntite(entite);

    }
    public int calculDY(int blocY, Entite entite){

        return blocY - transfoYEntite(entite);
    }

    public int transfoXEntite(Entite entite){

        return (entite.getX() + 16) / 32;

    }
    public int transfoYEntite(Entite entite){

        return (entite.getY() + 16) / 32;
    }

    public boolean estDansLaPortée(int blocX, int blocY, double portee, Entite entite) {
        boolean valreturn=true;

        double distance = Math.sqrt(calculDX(blocX,entite) * calculDX(blocX,entite) + calculDY(blocY,entite) * calculDY(blocY,entite));
        if (distance > portee)
            valreturn=false;

        return valreturn;
    }
    public boolean DDA(int blocX, int blocY,Entite entite){
        boolean valreturn=true;

        int rayonLaser = (Math.max(Math.abs(calculDX(blocX,entite)), Math.abs(calculDY(blocY,entite))) * 2); // le nombre d'étapes
        for (int i = 1; i < rayonLaser; i++) {
            double t = i / (double) rayonLaser;
            int xi = (int) Math.round(transfoXEntite(entite) + (calculDX(blocX,entite)) * t);
            int yi = (int) Math.round(transfoYEntite(entite) + (calculDY(blocY,entite)) * t);

            if ((xi != blocX || yi != blocY) && !blocTraversable(yi, xi)) { // Si bloc devant (obstacle)
                valreturn=false;
            }
        }
        return valreturn;

    }


    public boolean miner(int x, int y) {
        boolean miner = false;
        if (Carte.getUniqueCarte().peutEtreAtteint(x, y, 2.5,Joueur.getUniqueJoueur())) {
            System.out.println(Carte.getUniqueCarte().getCase(y,x));
            if (possibiliteMinerBloc(x,y)) {
                int[] bloc = Carte.getUniqueCarte().detruireBloc(x, y);
                Joueur.getUniqueJoueur().ajouterItem(ListeItems.getItemParId(bloc[0]), bloc[1]);
                miner = true;
            }
        }
        return miner;
    }
    public boolean possibiliteMinerBloc(int x, int y) {
        return ((Bloc) ListeItems.getItemParId(Carte.getUniqueCarte().getCase(y,x))).getResistance() == 1 ||
                Carte.getUniqueCarte().getCase(y, x) != 0 && Carte.getUniqueCarte().getCase(y, x) != 18 &&
                Carte.getUniqueCarte().getCase(y, x) != 22 &&
                Joueur.getUniqueJoueur().getInventaire().getCase(0,Joueur.getUniqueJoueur().getMainCourante()).getItem().getCodeObjet()<55 &&
                Joueur.getUniqueJoueur().getInventaire().getCase(0,Joueur.getUniqueJoueur().getMainCourante()).getItem().getCodeObjet()>50 &&
                compareResistance(((Bloc) ListeItems.getItemParId(Carte.getUniqueCarte().getCase(y,x))));
    }
    public boolean compareResistance(Bloc bloc){
        return bloc.getResistance()<=((Pioche) Joueur.getUniqueJoueur().getInventaire().getCase(0, Joueur.getUniqueJoueur().getMainCourante()).getItem()).getEfficacite();
    }
    public void poser(int x, int y) {//x = colonne && y = ligne
        if( ((Joueur.getUniqueJoueur().getX()/32)!=x) || ((Joueur.getUniqueJoueur().getY()/32)!=y) ) {
            if (conditionPoser(x,y)) {
                if (Joueur.getUniqueJoueur().getInventaire().getCase(0,Joueur.getUniqueJoueur().getMainCourante()).getQuantite()>0) {
                    Carte.getUniqueCarte().poserBloc(x,y,Joueur.getUniqueJoueur().getInventaire().getCase(0,Joueur.getUniqueJoueur().getMainCourante()).getItem().getCodeObjet());
                    Joueur.getUniqueJoueur().getInventaire().getCase(0, Joueur.getUniqueJoueur().getMainCourante()).retireQuantite(1);
                }
            }
        }
    }
    public boolean conditionPoser(int x, int y) {
        return Carte.getUniqueCarte().peutEtreAtteint(x, y, 2.5, Joueur.getUniqueJoueur()) &&
                Joueur.getUniqueJoueur().getInventaire().getCase(0,Joueur.getUniqueJoueur().getMainCourante()).getItem().getCodeObjet() < 20 &&
                Carte.getUniqueCarte().blocTraversable(y,x);
    }
}
