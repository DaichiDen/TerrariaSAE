package fr.iut.saeterraria.sae.Modele.Personnages;

public class Joueur extends Entite {
    private int[] inventaire; //hotbar (1-7), inventaire de taille 64
    private int[] equipement; //armure, outil

    public Joueur(String nom) {
        super(nom,20,20, 100, 20,0, 0,1,32);
        this.inventaire = new int[56];
        this.equipement = new int[7];
    }

    public void initInventaire(){
        for (int i: inventaire) {
            i=0;
        }
        for (int i: equipement) {
            i=0;
        }
    }

    public int[] getInventaire(){
        return inventaire;
    }
    public int[] getEquipement(){
        return equipement;
    }

    public void déplacementDroite() {
        super.setX(super.getX() + vitesseProperty().intValue());
    }

    public void déplacementGauche() {
        super.setX(super.getX() - vitesseProperty().intValue());
    }

    public void sauter() {
        super.setY(super.getY() - super.getVitesse());
        
//        for(int i = 1 ; i < 11 ; i++){ // Hauteur de saut à voir et arrêt aussi à collision
//            System.out.println("up");
//            super.setY(super.getY() + super.getVitesse()/i); //Pour un ralentissement dans la montée
//        }
//        for(int i = 11 ; i > 1 ; i--){ // A modifier pour s'arreter à collision
//            System.out.println("down");
//            super.setY(super.getY() - super.getVitesse()/i); //Pour une accélération dans la descente
//        }

      /**  for(int i = super.getY()+1; i < super.getY() + 11; i++){ // Hauteur de saut à voir et arrêt aussi à collision
            System.out.println("up");
            super.setY(super.getY() + super.getVitesse()/i); //Pour un ralentissement dans la montée
        }
        for(int i = super.getY(); i > super.getY(); i--){ // A modifier pour s'arreter à collision
            System.out.println("down");
            super.setY(super.getY() - super.getVitesse()/i); //Pour une accélération dans la descente
       }  **/

    }

    public void descendre() {
        super.setY(super.getY() + super.getVitesse());
    }

}
