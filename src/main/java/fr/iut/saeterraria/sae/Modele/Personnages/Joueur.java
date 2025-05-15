package fr.iut.saeterraria.sae.Modele.Personnages;

import fr.iut.saeterraria.sae.Modele.Map.Map;
import javafx.geometry.Rectangle2D;

public class Joueur extends Entite {
    private int[] inventaire; //hotbar (1-7), inventaire de taille 64
    private int[] equipement;//armure, outil
    private boolean enSaut = false;
    private boolean marcheDroite = false;
    private boolean marcheGauche = false;
    private int vitesseY = 0;
    private final int gravité = 2;
    private final int forceSaut = -20; // variable qui peut être modif pour avoir un saut plus ou moins haut etc..
    private final int seuil_sol = 500;
    // variable temporaire ,elle sert juste à tester le saut, on en aura plus besoin lorsque on fera les collisions en sprint 2

    public Joueur(String nom) {

        super(nom, 20, 20, 100, 20, 0, 0, 1, 10, new Rectangle2D(0, 0, 64, 70));
        this.inventaire = new int[56];
        this.equipement = new int[7];
    }

    public void initInventaire() {
        for (int i : inventaire) {
            i = 0;
        }
        for (int i : equipement) {
            i = 0;
        }
    }

    public int[] getInventaire() {
        return inventaire;
    }

    public int[] getEquipement() {
        return equipement;
    }

    public void setMarcheGauche(boolean marcheGauche) {
        this.marcheGauche = marcheGauche;
    }

    public void setMarcheDroite(boolean marcheDroite) {
        this.marcheDroite = marcheDroite;
    }


    public void sauter() {
        if (!enSaut) {
            enSaut = true;
            vitesseY = forceSaut;
        }
    }
    //        super.setY(super.getY() - super.getVitesse());

//        for(int i = 1 ; i < 11 ; i++){ // Hauteur de saut à voir et arrêt aussi à collision
//            System.out.println("up");
//            super.setY(super.getY() + super.getVitesse()/i); //Pour un ralentissement dans la montée
//        }
//        for(int i = 11 ; i > 1 ; i--){ // A modifier pour s'arreter à collision
//            System.out.println("down");
//            super.setY(super.getY() - super.getVitesse()/i); //Pour une accélération dans la descente
//        }


    public void mettreAJour() {
        if (marcheGauche) {
            super.setX(getX() - super.getVitesse());
        } else if (marcheDroite) {
            super.setX(getX() + super.getVitesse());
        }
        if (enSaut) {
            super.setY(super.getY() + vitesseY);
            vitesseY += gravité;

            if (super.getY() >= seuil_sol) {
                super.setY(seuil_sol);
                vitesseY = 0;
                enSaut = false;
            }
        }
    }

    public void descendre() {
        super.setY(super.getY() + super.getVitesse());
    }

    public void collision(Map map) {
        for (int i = 0; i < map.getLigne(); i++) {
            for (int j = 0; j < map.getColonne(); j++) {
                if (this.getX() == map.getCoordonnéesX(i) && (map.getCase(i,j)!=3)) {
                    this.setVitesse(0);
                }
            }
        }
    }

}
