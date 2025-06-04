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


    private ArrayList<Item> listDrops;

    public Ennemi(String nom, int vieMax,int energieMax, int x, int y, int def, int vitesse, Map map, Jeu jeu) {

        super(nom,vieMax,  energieMax, 20, x, y, def, vitesse,map,jeu);
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


    }

    public void mettreAJour(){

        if(!detecterJoueur()){
            comportementPasVu();
            super.mettreAJour();
        }else{
            comportementVu();
        }
    }
    public void comportementVu(){
        Algo_A_Star pathfinding = new Algo_A_Star(jeu.getCarte());
        List<Node> path = pathfinding.trouverchemin(this.getX(), this.getY(), jeu.getJoueur().getX(), jeu.getJoueur().getY());
        if(detecterJoueur() && !path.isEmpty()){
            Node next = path.get(1);
            setMarcheGauche(true);

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
        if (peutEtreAtteint(jeu.getCarte(), this.getX(), this.getY(), 5)) {
            aVuJoueur = true;
        }
        return aVuJoueur;
    }
}
