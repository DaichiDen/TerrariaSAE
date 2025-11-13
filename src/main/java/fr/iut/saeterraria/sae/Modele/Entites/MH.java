package fr.iut.saeterraria.sae.Modele.Entites;

import fr.iut.saeterraria.sae.Modele.Jeu;

public class MH extends Ennemi{

    public MH(int x, int y) {
        super(100, x, y, 5, 5, Jeu.getUniqueJeu().getTaille1bloc(), Jeu.getUniqueJeu().getTaille1bloc()*2, 10);
    }

    @Override
    public void action(int x, int y) {
       int attaque = (int) (Math.random() * 10);
        if (getDernièreAttaque() == getDelaiAttaque()) {
            if (this.getAttaque() - Joueur.getUniqueJoueur().getDef() > 0){
                if (attaque<2) {
                    Joueur.getUniqueJoueur().decrementVie(getAttaque());
                } else {
                    Projectile p=new BouleDeFeu(this.getX(), this.getY());
                    p.initialiserProjectile(Joueur.getUniqueJoueur().getX(), Joueur.getUniqueJoueur().getY());
                }
            }
            setDernièreAttaque(0);
        }
        setDernièreAttaque(getDernièreAttaque() + 1);
    }
}
