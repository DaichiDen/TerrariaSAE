package fr.iut.saeterraria.sae.Modele.Personnages;

import fr.iut.saeterraria.sae.Modele.A_Star.Algo_A_Star;
import fr.iut.saeterraria.sae.Modele.A_Star.Node;
import fr.iut.saeterraria.sae.Modele.Jeu;
import fr.iut.saeterraria.sae.Modele.Map.Map;
import fr.iut.saeterraria.sae.Modele.Objets.Item;
import javafx.geometry.Rectangle2D;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Ennemi extends Entite {
    private long dernièreAttaque=60;
    private long cooldown=60;


    private ArrayList<Item> listDrops;

    public Ennemi(String nom, int vieMax,int energieMax, int x, int y, int def, int vitesse, Map map, Jeu jeu) {

        super(nom,vieMax,  energieMax, 20, x, y, def, 5,map,jeu);
        listDrops = new ArrayList<>();
    }

    public ArrayList<Item> getListDrops() {
        return this.listDrops;
    }
    public int dropItem(){
        return (int) (Math.random()*11);
    }


    @Override
    public void attaquer(int x, int y, int range) {
        if(dernièreAttaque==cooldown){
            super.getJeu().getJoueur().decrementVie(2);
            dernièreAttaque=0;
        }
        dernièreAttaque++;
    }

    @Override
    public void mettreAJour(){
        if(!detecterJoueur()){
            comportementPasVu();
            super.mettreAJour();
        }else{
            comportementVu();
            super.mettreAJour();
            System.out.println(dernièreAttaque);

        }
    }
    public void comportementVu(){
        Algo_A_Star pathfinding = new Algo_A_Star(super.getJeu().getCarte());
        System.out.println();
        List<Node> path = pathfinding.trouverchemin(this.getX()/32, this.getY()/32, super.getJeu().getJoueur().getX()/32, super.getJeu().getJoueur().getY()/32);


        if (!path.isEmpty() && path.size()>1) {
            Node nextStep = path.get(1); // [0] = position actuelle
            int dx = nextStep.x - (this.getX() / 32);
            int dy = nextStep.y - (this.getY() / 32);

            if (dx < 0) {
                System.out.println("test1");
                setMarcheGauche(true);
                setMarcheDroite(false);
            } else if (dx > 0) {
                setMarcheGauche(false);
                setMarcheDroite(true);
            }
            if(dy < 0) {
                this.sauter();
            }
            System.out.println(dy);
            System.out.println(dx);
            if(peutEtreAtteint(super.getJeu().getJoueur().getX()/32, super.getJeu().getJoueur().getY()/32, 2)){
                attaquer(super.getJeu().getJoueur().getX(), super.getJeu().getJoueur().getY(), 2);
            }

            // Tu peux gérer dy si les ennemis sautent ou volent
        }
        else{
            setMarcheGauche(false);
            setMarcheDroite(false);
            attaquer(super.getJeu().getJoueur().getX(),super.getJeu().getJoueur().getY(),2);

        }
    }

    public void comportementPasVu(){
        int aleaComp = (int) (Math.random()*11);
        if(aleaComp < 3){
            setMarcheDroite(true);
            setMarcheGauche(false);
        } else if (aleaComp < 6) {
            setMarcheDroite(false);
            setMarcheGauche(true);
        } else if (aleaComp < 9) {
            setMarcheDroite(false);
            setMarcheGauche(false);
        }
    }

    public boolean detecterJoueur() {// À définir la distance où il détecte le joueur
        boolean aVuJoueur = false;
        if (peutEtreAtteint(super.getJeu().getJoueur().getX()/32, super.getJeu().getJoueur().getY()/32, 5)) {
            aVuJoueur = true;
        }
        return aVuJoueur;
    }
}
