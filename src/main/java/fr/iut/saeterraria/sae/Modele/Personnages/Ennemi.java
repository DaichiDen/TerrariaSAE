package fr.iut.saeterraria.sae.Modele.Personnages;

import fr.iut.saeterraria.sae.Modele.A_Star.Algo_A_Star;
import fr.iut.saeterraria.sae.Modele.A_Star.Node;
import fr.iut.saeterraria.sae.Modele.Jeu;
import fr.iut.saeterraria.sae.Modele.Objets.Item;

import java.util.ArrayList;
import java.util.List;

public abstract class Ennemi extends EntiteVivante {
    private long dernièreAttaque=60;
    private long cooldown=60;
    private ComportementEnnemi comportementEnnemi;

    //PB avec ennemis dans CAVE , il ne utilisent pas bien A*

    private ArrayList<Item> listDrops;

    public Ennemi(String nom, int vieMax,int energieMax, int x, int y, int def, int attaque, int tailleL, int tailleH, int rangeVue, int rangeAttaque) {

        super(nom,vieMax,  energieMax, 20, x, y, def, 5,attaque, tailleL, tailleH, rangeVue,rangeAttaque );
        listDrops = new ArrayList<>();
    }

    public ArrayList<Item> getListDrops() {
        return this.listDrops;
    }
    public int dropItem(){
        return (int) (Math.random()*11);
    }


    public abstract void action(int x, int y);

    public int distanceJoueur(int x, int y){
        int dx = x - Joueur.getUniqueJoueur().getX();
        int dy = y - Joueur.getUniqueJoueur().getY();
        int distance = (int) Math.sqrt(dx * dx + dy * dy);
        return distance;
    }

    @Override
    public void mettreAJour(){
        if(!detecterJoueur()){
            comportementEnnemi = new ComportementPasVu();
            comportementEnnemi.agir(this);
            super.mettreAJour();
        }else{
            comportementEnnemi = new ComportementVu();
            comportementEnnemi.agir(this);
            super.mettreAJour();

        }

    }

    public boolean detecterJoueur() {// À définir la distance où il détecte le joueur
        boolean aVuJoueur = false;
        if (peutEtreAtteint(Joueur.getUniqueJoueur().getX()/32, Joueur.getUniqueJoueur().getY()/32, 5)) {
            aVuJoueur = true;
        }
        return aVuJoueur;
    }
    public long getDernièreAttaque(){
        return dernièreAttaque;
    }
    public long getCooldown(){
        return cooldown;
    }
    public void setDernièreAttaque(long val){
        this.dernièreAttaque=val;

    }
    public void setCooldown(long val){
        this.cooldown=val;
    }
}
