package fr.iut.saeterraria.sae.Modele.Entites;

import fr.iut.saeterraria.sae.Modele.Map.Carte;
import fr.iut.saeterraria.sae.Modele.Objets.Item;
import fr.iut.saeterraria.sae.Modele.Entites.Comportements.ComportementEnnemi;
import fr.iut.saeterraria.sae.Modele.Entites.Comportements.ComportementPasVu;
import fr.iut.saeterraria.sae.Modele.Entites.Comportements.ComportementVu;

import java.util.ArrayList;

public abstract class Ennemi extends EntiteVivante {
    private long dernièreAttaque=60;
    private long cooldown=60;
    private ComportementEnnemi comportementEnnemi;

    //PB avec ennemis dans CAVE , il ne utilisent pas bien A*

    private ArrayList<Item> listDrops;

    public Ennemi(int vieMax,int energieMax, int x, int y, int def, int attaque, int tailleL, int tailleH, int rangeVue, int rangeAttaque) {

        super(vieMax,  energieMax, 20, x, y, def, 5,attaque, tailleL, tailleH, rangeVue,rangeAttaque );
        listDrops = new ArrayList<>();
    }

    public ArrayList<Item> getListDrops() {
        return this.listDrops;
    }
    public int dropItem(){
        return (int) (Math.random()*11);
    }


    public abstract void action(int x, int y);


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
        if (Carte.getUniqueCarte().peutEtreAtteint(Joueur.getUniqueJoueur().getX()/32, Joueur.getUniqueJoueur().getY()/32, getRangeVue(), this)) {
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

}
