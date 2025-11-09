package fr.iut.saeterraria.sae.Modele.Entites;

import fr.iut.saeterraria.sae.Modele.Jeu;

public class Ogre extends Ennemi{
    public Ogre(int x, int y) {
        super(50, 20, x, y, 0, 4, Jeu.getUniqueJeu().getTaille1bloc(), Jeu.getUniqueJeu().getTaille1bloc() * 2, 10, 3);
    }

    @Override
    public void action(int x, int y) {
        if(getDernièreAttaque()== getDelaiAttaque()) {
            if (this.getAttaque() - Joueur.getUniqueJoueur().getDef() > 0){
                Joueur.getUniqueJoueur().decrementVie(this.getAttaque() - Joueur.getUniqueJoueur().getDef());
            }
            setDernièreAttaque(0);
        }
        setDernièreAttaque(getDernièreAttaque()+1);
    }
}
