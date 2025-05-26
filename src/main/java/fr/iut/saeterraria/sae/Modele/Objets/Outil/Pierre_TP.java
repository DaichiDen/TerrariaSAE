package fr.iut.saeterraria.sae.Modele.Objets.Outil;

import fr.iut.saeterraria.sae.Modele.Objets.Outils;

public class Pierre_TP extends Outils {
    private int x;
    private int y;
    private boolean etat_tp;

    public Pierre_TP() {
        super("Waypoint","Cet objet semble détenir le pouvoir de se téléporter...");
        this.x = 0;
        this.y = 0;
        this.etat_tp = false;

    }

    @Override
    public void action() {
        System.out.println("baaaa");
    }
    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }
    public int getX(){
        return this.x;
    }
    public int getY(){
        return this.y;
    }
    public void setEtat_tp(boolean etat_tp) {
        this.etat_tp = etat_tp;
    }
    public boolean getEtat_tp(){
        return this.etat_tp;
    }

}
