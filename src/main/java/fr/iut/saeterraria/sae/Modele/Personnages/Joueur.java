package fr.iut.saeterraria.sae.Modele.Personnages;

public class Joueur extends Entite {
    private int[] inventaire; //hotbar (1-7), inventaire de taille 64
    private int[] equipement; //armure, outil

    public Joueur(String nom) {
        super(nom,20,20, 100, 0,0, 0,1);
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
        int v = vitesseProperty().intValue();
        for(int i = super.getY(); i < super.getY() + 10; i++){ // Hauteur de saut à voir et arrêt aussi à collision
            super.setY(super.getY() + v/i); //Pour un ralentissement dans la montée
        }
        for(int i = super.getY(); i > super.getY() - 10; i--){ // A modifier pour s'arreter à collision
            super.setY(super.getY() - v/i); //Pour une accélération dans la descente
        }

    }

}
