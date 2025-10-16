package fr.iut.saeterraria.sae.Modele.Personnages;

import fr.iut.saeterraria.sae.Modele.Jeu;
import fr.iut.saeterraria.sae.Modele.Map.Map;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.geometry.Rectangle2D;

public class BouleDeFeu extends Projectile{

    private int xExplosion, yExplosion;
    private BooleanProperty aExplosé = new SimpleBooleanProperty(false);

    public BouleDeFeu(int xEntite, int yEntite) {
        super("bdf", xEntite, yEntite, 12, 32, 32);
    }

    public void màjProjectile(){
        this.setForceY(this.getForceY() + super.getGravité());
        super.màjProjectile();
    }

    public BooleanProperty aExploséProperty() {
        return aExplosé;
    }
    public boolean getaExplosé(){
        return aExplosé.getValue();
    }
    public void setaExplosé(boolean a){
        aExplosé.setValue(a);
    }

    public void action() {
        int x = getX() / 32;
        int y = getY() / 32;
        Map map = Jeu.getUniqueJeu().getCarte();
        for (int j = x - 1; j <= x + 1; j++) {
            for (int i = y - 1; i <= y + 1; i++) {
                if (map.getCase(i, j) != 0 && map.getCase(i, j) != 10 && map.getCase(i, j) != 18) {
                    map.detruireBloc(j,i);
                }
                appliquerDegatsExplosion(j, i);
                xExplosion = x;
                yExplosion = y;
            }
        }
        setaExplosé(true);
    }

    public void appliquerDegatsExplosion(int j, int i){
        Rectangle2D touché = new Rectangle2D(j*32, i*32,Jeu.getUniqueJeu().getTaille1bloc(), Jeu.getUniqueJeu().getTaille1bloc());
        for (int e = 0; e < Jeu.getUniqueJeu().getMobs().size(); e++) {
            if (touché.intersects(Jeu.getUniqueJeu().getMobs().get(e).getHitbox()) && Jeu.getUniqueJeu().getMobs().get(e).getDef()<8) {
                Jeu.getUniqueJeu().getMobs().get(e).decrementVie(8-Jeu.getUniqueJeu().getMobs().get(e).getDef());
            }
        }
        if(Joueur.getUniqueJoueur().getHitbox().intersects(touché) && Joueur.getUniqueJoueur().getDef()<5){
            Joueur.getUniqueJoueur().decrementVie(5-Joueur.getUniqueJoueur().getDef());
        }
    }
}
