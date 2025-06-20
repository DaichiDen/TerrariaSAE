package fr.iut.saeterraria.sae.Modele.Personnages;

import fr.iut.saeterraria.sae.Modele.Jeu;

public class MH extends Ennemi{

    public MH(String nom, int vieMax, int energieMax, int x, int y, int def, Jeu jeu, int attaque, int tailleL, int tailleH, int rangeVue, int rangeAttaque) {
        super(nom, vieMax, energieMax, x, y, def, jeu, attaque, tailleL, tailleH, rangeVue, rangeAttaque);
    }

    @Override
    public void action(int x, int y) {
       int attaque = (int) (Math.random() * 10);
        if (getDernièreAttaque() == getCooldown()) {
            if (this.getAttaque() - getJeu().getJoueur().getDef() > 0) {
                if (attaque<2) {
                    getJeu().getJoueur().decrementVie(getAttaque());
                    System.out.println("Mandale");
                } else {
                    tirerProjectile(new Projectile("bdf", getJeu(), this.getX(), this.getY(), getAttaque(), "boule_de_feu", 32, 32), super.getJeu().getJoueur().getX(), super.getJeu().getJoueur().getY());
                    System.out.println("BDF");
                }
            }
            setDernièreAttaque(0);
        }
        setDernièreAttaque(getDernièreAttaque() + 1);
    }
}
