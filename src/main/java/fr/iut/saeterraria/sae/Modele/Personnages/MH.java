package fr.iut.saeterraria.sae.Modele.Personnages;

import fr.iut.saeterraria.sae.Modele.Jeu;

public class MH extends Ennemi{

    public MH(String nom, int vieMax, int energieMax, int x, int y, int def, Jeu jeu, int attaque, int tailleL, int tailleH) {
        super(nom, vieMax, energieMax, x, y, def, jeu, attaque, tailleL, tailleH, 20, 10);
    }

    @Override
    public void action(int x, int y, int range) {
        boolean inRangeAa = peutEtreAtteint(super.getJeu().getJoueur().getX(), super.getJeu().getJoueur().getY(), 3);
        if (getDernièreAttaque() == getCooldown()) {
            if (this.getAttaque() - getJeu().getJoueur().getDef() > 0) {
                if (inRangeAa) {
                    getJeu().getJoueur().decrementVie(getAttaque());
                } else {
                    tirerProjectile(new Projectile("bdf", getJeu(), this.getX(), this.getY(), 5, "boule_de_feu", 16, 16), super.getJeu().getJoueur().getX(), super.getJeu().getJoueur().getY());
                }
            }
            setDernièreAttaque(0);
        }
        setDernièreAttaque(getDernièreAttaque() + 1);
    }
}
