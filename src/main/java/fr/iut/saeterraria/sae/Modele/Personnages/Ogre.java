package fr.iut.saeterraria.sae.Modele.Personnages;

import fr.iut.saeterraria.sae.Modele.Jeu;

public class Ogre extends Ennemi{
    public Ogre(String nom, int vieMax, int energieMax, int x, int y, int def, Jeu jeu, int attaque, int tailleL, int tailleH) {
        super(nom, vieMax, energieMax, x, y, def, jeu, attaque, tailleL, tailleH, 20, 2);
    }

    @Override
    public void action(int x, int y, int range) {
        if(getDernièreAttaque()==getCooldown()) {
            if (this.getAttaque() - getJeu().getJoueur().getDef() > 0){
                super.getJeu().getJoueur().decrementVie(this.getAttaque() - getJeu().getJoueur().getDef());
            }
            setDernièreAttaque(0);
        }
        setDernièreAttaque(getDernièreAttaque()+1);
    }
}
