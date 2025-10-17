package fr.iut.saeterraria.sae.Modele.Entites;

public class Fleche extends Projectile{

    public Fleche(int xJoueur, int yJoueur) {
        super("flèche", xJoueur, yJoueur, 5, 32, 32);
    }

    public void màjProjectile(){
        this.setForceY(this.getForceY() + super.getGravité());
        super.màjProjectile();
    }

    @Override
    public void action() {

    }
}
