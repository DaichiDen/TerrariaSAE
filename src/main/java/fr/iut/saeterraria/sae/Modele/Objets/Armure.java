package fr.iut.saeterraria.sae.Modele.Objets;

import fr.iut.saeterraria.sae.Modele.Entites.Joueur;
import fr.iut.saeterraria.sae.Modele.Objets.Etablis.BlocConstruction;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class Armure extends Equipement {
    private DoubleProperty defense; // Valeur de défense de l'armure
    private int typeArmure;

    public Armure (String nom, String desc, double defense, BlocConstruction blocConstruction, int typeArmure, int codeobjet) {
        super(nom,desc,blocConstruction,codeobjet);
        this.defense = new SimpleDoubleProperty(defense);
    this.typeArmure = typeArmure;
    }

    public int getTypeArmure() { return typeArmure; }
    public DoubleProperty defenseProperty() { return defense; }
    public double getDefense(){ return defense.getValue(); }

//TODO à voir mais pas fou
//    public void equiper() {
//        switch (this.getTypeArmure()){
//            case 1:
//                if (Joueur.getUniqueJoueur().getCaseEquipement(0)==64 || Joueur.getUniqueJoueur().getCaseEquipement(0)==65) {
//                    Joueur.getUniqueJoueur().getInventaire().ajoutInventaire(ListeItems.getItemParId(Joueur.getUniqueJoueur().getCaseEquipement(0)), 1);
//                }
//                Joueur.getUniqueJoueur().setEquipement(0, this.getCodeObjet());
//                break;
//            case 2:
//                if (Joueur.getUniqueJoueur().getCaseEquipement(1)==66 || Joueur.getUniqueJoueur().getCaseEquipement(1)==67) {
//                    Joueur.getUniqueJoueur().getInventaire().ajoutInventaire(ListeItems.getItemParId(Joueur.getUniqueJoueur().getCaseEquipement(1)), 1);
//                }
//                Joueur.getUniqueJoueur().setEquipement(1, this.getCodeObjet());
//                break;
//            case 3:
//                if (Joueur.getUniqueJoueur().getCaseEquipement(2)==68 || Joueur.getUniqueJoueur().getCaseEquipement(2)==69) {
//                    Joueur.getUniqueJoueur().getInventaire().ajoutInventaire(ListeItems.getItemParId(Joueur.getUniqueJoueur().getCaseEquipement(2)), 1);
//                }
//                Joueur.getUniqueJoueur().setEquipement(2, this.getCodeObjet());
//                break;
//            case 4:
//                if (Joueur.getUniqueJoueur().getCaseEquipement(3)==70 || Joueur.getUniqueJoueur().getCaseEquipement(3)==71) {
//                    Joueur.getUniqueJoueur().getInventaire().ajoutInventaire(ListeItems.getItemParId(Joueur.getUniqueJoueur().getCaseEquipement(3)), 1);
//                }
//                Joueur.getUniqueJoueur().setEquipement(3, this.getCodeObjet());
//                break;
//        }
//        Joueur.getUniqueJoueur().getInventaire().getCase(0,Joueur.getUniqueJoueur().getMainCourante()).retireQuantite(1);
//        updateDefense();
//    }
//    public void updateDefense(){
//        for ( int piece : Joueur.getUniqueJoueur().getEquipement()){
//            Joueur.getUniqueJoueur().setDef((int)(Joueur.getUniqueJoueur().getDef() + ((Armure)ListeItems.getItemParId(piece)).getDefense()));
//        }
//    }
}