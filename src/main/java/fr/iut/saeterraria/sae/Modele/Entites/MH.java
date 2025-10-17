package fr.iut.saeterraria.sae.Modele.Entites;

public class MH extends Ennemi{

    public MH(String nom, int vieMax, int energieMax, int x, int y, int def, int attaque, int tailleL, int tailleH, int rangeVue, int rangeAttaque) {
        super(nom, vieMax, energieMax, x, y, def, attaque, tailleL, tailleH, rangeVue, rangeAttaque);
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
                    Projectile p=new BouleDeFeu(this.getX(), this.getY());
                    p.initialiserProjectile(Joueur.getUniqueJoueur().getX(), Joueur.getUniqueJoueur().getY());
                    System.out.println("BDF");
                }
            }
            setDernièreAttaque(0);
        }
        setDernièreAttaque(getDernièreAttaque() + 1);
    }
}
