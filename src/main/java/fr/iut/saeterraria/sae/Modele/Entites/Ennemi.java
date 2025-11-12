package fr.iut.saeterraria.sae.Modele.Entites;

import fr.iut.saeterraria.sae.Modele.Map.Carte;
import fr.iut.saeterraria.sae.Modele.Entites.Comportements.ComportementEnnemi;
import fr.iut.saeterraria.sae.Modele.Entites.Comportements.ComportementPasVu;
import fr.iut.saeterraria.sae.Modele.Entites.Comportements.ComportementVu;

/*
 * Classe représentant les ennemis
 * Elle définit ses propriétés:
 * son comportement, son cooldown (sa durée avant prochaine attaque)
 *
 * Elle donne les méthodes utilisées par l'ennemi pour la détection du joueur et pour la gestion de son cooldown
 *
 */

public abstract class Ennemi extends EntiteVivante {
    private long dernièreAttaque=60;
    private long delaiAttaque =60;
    private ComportementEnnemi comportementEnnemi;

    public Ennemi(int vieMax,int energieMax, int x, int y, int def, int attaque, int tailleL, int tailleH, int porteeVue, int porteeAttaque) {

        super(vieMax,  energieMax, 20, x, y, def, 5,attaque, tailleL, tailleH, porteeVue,porteeAttaque );

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
        if (Carte.getUniqueCarte().peutEtreAtteint(Joueur.getUniqueJoueur().getX()/32, Joueur.getUniqueJoueur().getY()/32, getPorteeVue(), this)) {
            aVuJoueur = true;
        }
        return aVuJoueur;
    }
    public long getDernièreAttaque(){
        return dernièreAttaque;
    }
    public long getDelaiAttaque(){
        return delaiAttaque;
    }
    public void setDernièreAttaque(long val){
        this.dernièreAttaque=val;

    }

}
