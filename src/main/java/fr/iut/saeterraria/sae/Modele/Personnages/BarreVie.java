package fr.iut.saeterraria.sae.Modele.Personnages;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class BarreVie {
    private int x = 800;
    private int y = 10;
    private IntegerProperty vieMax;
    private IntegerProperty vie;

    public BarreVie(int  vieMax) {
        this.vie = new SimpleIntegerProperty(vieMax);
        this.vieMax = new SimpleIntegerProperty(vieMax);

    }

    public final IntegerProperty vieProperty(){return vie;}
    public final int getVieMax() {return vieMax.get();}
    public final void setVieMax(int vie) {this.vieMax.set(vie);}
    public final int getVie() {return vie.get();}
    public final void setVie(int vie) {this.vie.set(vie);}

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
}


//    public boolean peutEtreAtteint(Map map, int blocX, int blocY) {
//        int centreX = (this.getX() + 16) / 32; // centre horizontal
//        int centreY = (this.getY() + 16) / 32; // centre vertical (milieu du perso)
//
//        int dx = blocX - centreX;
//        int dy = blocY - centreY;
//
//        if (Math.abs(dx) > 2 || Math.abs(dy) > 2) {
//            return false;
//        }
//
//        // Si dans un rayon de 1, accessible direct
//        if (Math.abs(dx) <= 1 && Math.abs(dy) <= 1) {
//            return true;
//        }
//
//        // Si distance de 2 en X (horizontal)
//        if (Math.abs(dx) == 2 && Math.abs(dy) <= 1) {
//            int midX = centreX + Integer.signum(dx);
//            return map.getCase(centreY, midX) == 3 &&
//                    map.getCase(centreY - 1, midX) == 3;
//        }
//
//        // Si distance de 2 en Y (vertical)
//        if (Math.abs(dy) == 2 && Math.abs(dx) <= 1) {
//            int midY = centreY + Integer.signum(dy);
//            return map.getCase(midY, centreX) == 3;
//        }
//
//        // Diagonales longues (2 en X et 2 en Y)
//        if (Math.abs(dx) == 2 && Math.abs(dy) == 2) {
//            int midX = centreX + Integer.signum(dx);
//            int midY = centreY + Integer.signum(dy);
//            return map.getCase(centreY, midX) == 3 &&
//                    map.getCase(centreY - 1, midX) == 3 &&
//                    map.getCase(midY, centreX) == 3;
//        }
//
//        // Cas en L : (2,1)
//        if (Math.abs(dx) == 2 && Math.abs(dy) == 1) {
//            int midX = centreX + Integer.signum(dx);
//            return map.getCase(centreY, midX) == 3 &&
//                    map.getCase(centreY - 1, midX) == 3;
//        }
//
//        // Cas en L : (1,2)
//        if (Math.abs(dx) == 1 && Math.abs(dy) == 2) {
//            int midY = centreY + Integer.signum(dy);
//            return map.getCase(midY, centreX) == 3;
//        }
//
//        return false;
//    }