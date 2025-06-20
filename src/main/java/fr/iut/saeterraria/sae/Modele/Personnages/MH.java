package fr.iut.saeterraria.sae.Modele.Personnages;

import fr.iut.saeterraria.sae.Modele.Jeu;

public class MH extends Ennemi{

    public MH(String nom, int vieMax, int energieMax, int x, int y, int def, Jeu jeu, int attaque, int tailleL, int tailleH) {
        super(nom, vieMax, energieMax, x, y, def, jeu, attaque, tailleL, tailleH, 20, 10);
    }

    @Override
    public void action(int x, int y, int range) {
       int attaque = (int) (Math.random() * 10);
        if (getDernièreAttaque() == getCooldown()) {
            if (this.getAttaque() - getJeu().getJoueur().getDef() > 0) {
                if (attaque<6) {
                    getJeu().getJoueur().decrementVie(getAttaque());
                    System.out.println("Mandale");
                } else {
                    tirerProjectile(new Projectile("bdf", getJeu(), this.getX(), this.getY(), 5, "boule_de_feu", 16, 16), super.getJeu().getJoueur().getX(), super.getJeu().getJoueur().getY());
                    System.out.println("BDF");
                }
            }
            setDernièreAttaque(0);
        }
        setDernièreAttaque(getDernièreAttaque() + 1);
    }
}
