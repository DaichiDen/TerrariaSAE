package fr.iut.saeterraria.sae.Modele.Personnages;

import fr.iut.saeterraria.sae.Modele.Jeu;

public class Ogre extends Ennemi{
    public Ogre(String nom, int vieMax, int energieMax, int x, int y, int def,  int attaque, int tailleL, int tailleH, int rangeVue, int rangeAttaque) {
        super(nom, vieMax, energieMax, x, y, def, attaque, tailleL, tailleH, rangeVue, rangeAttaque);
    }

    @Override
    public void action(int x, int y) {
        if(getDernièreAttaque()==getCooldown()) {
            if (this.getAttaque() - Joueur.getUniqueJoueur().getDef() > 0){
                Joueur.getUniqueJoueur().decrementVie(this.getAttaque() - Joueur.getUniqueJoueur().getDef());
            }
            setDernièreAttaque(0);
        }
        setDernièreAttaque(getDernièreAttaque()+1);
    }
}
