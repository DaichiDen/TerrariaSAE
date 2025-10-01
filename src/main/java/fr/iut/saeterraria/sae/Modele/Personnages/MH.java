package fr.iut.saeterraria.sae.Modele.Personnages;

import fr.iut.saeterraria.sae.Modele.Jeu;

public class MH extends Ennemi{
    private static MH uniqueMh = null;

    private MH() {
        super("Monsieur Homps", 250, 20, 4500, 0, 5, 2, Jeu.getUniqueJeu().getTaille1bloc(), Jeu.getUniqueJeu().getTaille1bloc()*2, 15, 8);
    }

    @Override
    public void action(int x, int y) {
       int attaque = (int) (Math.random() * 10);
        if (getDernièreAttaque() == getCooldown()) {
            if (this.getAttaque() - Joueur.getUniqueJoueur().getDef() > 0) {
                if (attaque<2) {
                    Joueur.getUniqueJoueur().decrementVie(getAttaque());
                    System.out.println("Mandale");
                } else {
                    initialiserProjectile(new Projectile("bdf", this.getX(), this.getY(), getAttaque(), "boule_de_feu", 32, 32), Joueur.getUniqueJoueur().getX(), Joueur.getUniqueJoueur().getY());
                    System.out.println("BDF");
                }
            }
            setDernièreAttaque(0);
        }
        setDernièreAttaque(getDernièreAttaque() + 1);
    }

    public static MH getUniqueMh() {
        if (uniqueMh == null) {
            uniqueMh = new MH();
        }
        return uniqueMh;
    }
}
