package fr.iut.saeterraria.sae.Modele.Personnages;

import fr.iut.saeterraria.sae.Modele.Objets.Item;

public class Joueur extends Entite {
    private Inventaire inventaire; //hotbar (1-7), inventaire de taille 36
    private Hotbar hotbar;//armure, outil
    private boolean enSaut = false;
    private boolean marcheDroite = false;
    private boolean marcheGauche = false;
    private int vitesseY = 0;
    private final int gravité = 2;
    private final int forceSaut = -20; // variable qui peut être modif pour avoir un saut plus ou moins haut etc..
    private final int seuil_sol = 500 ; // variable temporaire ,elle sert juste à tester le saut, on en aura plus besoin lorsque on fera les collisions en sprint 2

    public Joueur(String nom) {
        super(nom,20,20, 100, 20,0, 0,1,10);
        this.inventaire = new Inventaire();
        this.hotbar = new Hotbar();
    }

    public Inventaire getInventaire(){
        return inventaire;
    }
    public Hotbar getHotbar(){
        return hotbar;
    }

    public void setMarcheGauche(boolean marcheGauche){
        this.marcheGauche = marcheGauche;
    }
    public void setMarcheDroite(boolean marcheDroite){
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

    public void ajouterItem(Item item, int quantite) {
        if(!hotbar.ajoutHotbar(item, quantite)) {
            inventaire.ajoutInventaire(item,quantite);
        }
    }
}
