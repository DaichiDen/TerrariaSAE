package fr.iut.saeterraria.sae.Modele.Objets;

import fr.iut.saeterraria.sae.Modele.Objets.Arme.Distance;
import fr.iut.saeterraria.sae.Modele.Objets.Arme.Epee;
import fr.iut.saeterraria.sae.Modele.Objets.Outil.Pioche;

import java.util.HashMap;

public class ListeItemBlocForge extends ListeItemCraft{

    public ListeItemBlocForge() {

    }

    @Override
    public Item creerItem(String nom) {
        switch (nom) {
            case "Pioche de fer" :
                Pioche piocheDeFer = new Pioche("Pioche de fer","",4,53);
                piocheDeFer.addInRecette(new ElementRecette(22,"Fer",3));
                piocheDeFer.addInRecette(new ElementRecette(3,"Bois",1));
                return piocheDeFer;
            case "Pioche DELJCCium" :
                Pioche piocheDeljcCium = new Pioche("Pioche DELJCCium","",5,54);
                piocheDeljcCium.addInRecette(new ElementRecette(23,"DELJCCium",3));
                piocheDeljcCium.addInRecette(new ElementRecette(3,"Bois",1));
                return piocheDeljcCium;
            case "Casque en fer" :
                Armure casqueEnFer = new Armure("Casque en fer","",2,1,64);
                casqueEnFer.addInRecette(new ElementRecette(22,"Fer",5));
                return casqueEnFer;
            case "Casque en DELJCCium" :
                Armure casqueEnDeljcCium =  new Armure("Casque en DELJCCium","",3,1,65);
                casqueEnDeljcCium.addInRecette(new ElementRecette(23,"DELJCCium",5));
                return casqueEnDeljcCium;
            case "Plastron en fer" :
                Armure plastronEnFer = new Armure("Plastron en fer","",6,2,66);
                plastronEnFer.addInRecette(new ElementRecette(22,"Fer",8));
                return plastronEnFer;
            case "Plastron en DELJCCium" :
                Armure plastronEnDeljcCium = new Armure("Plastron en DELJCCium","",9,2,67);
                plastronEnDeljcCium.addInRecette(new ElementRecette(23,"DELJCCium",8));
                return plastronEnDeljcCium;
            case "Jambière en fer" :
                Armure jambièreEnFer = new Armure("Jambière en fer","",4,3,68);
                jambièreEnFer.addInRecette(new ElementRecette(22,"Fer",6));
                return jambièreEnFer;
            case "Jambière en DELJCCium" :
                Armure jambièreEnDeljcCium = new Armure("Jambière en DELJCCium","",6,3,69);
                jambièreEnDeljcCium.addInRecette(new ElementRecette(23,"DELJCCium",6));
                return jambièreEnDeljcCium;
            case "Botte en fer" :
                Armure botteEnFer = new Armure("Botte en fer","",3,4,70);
                botteEnFer.addInRecette(new ElementRecette(22,"Fer",4));
                return botteEnFer;
            case "Botte en DELJCCium" :
                Armure botteEnDeljcCium = new Armure("Botte en DELJCCium","",5,4,71);
                botteEnDeljcCium.addInRecette(new ElementRecette(23,"DELJCCium",4));
                return botteEnDeljcCium;
            case "Epée en Fer" :
                Epee epéeEnFer = new Epee("Epée en Fer","",4,75);
                epéeEnFer.addInRecette(new ElementRecette(22,"Fer",2));
                epéeEnFer.addInRecette(new ElementRecette(3,"Bois",1));
                return epéeEnFer;
            case "Epée en DELJCCium" :
                Epee epéeEnDeljcCium = new Epee("Epée en DELJCCium","",5,76);
                epéeEnDeljcCium.addInRecette(new ElementRecette(23,"DELJCCium",2));
                epéeEnDeljcCium.addInRecette(new ElementRecette(3,"Bois",1));
                return epéeEnDeljcCium;
            case "Flèche" :
                Item fleche = new Item("Flèche","Flèche",1,77);
                fleche.addInRecette(new ElementRecette(22,"Fer",1));
                fleche.addInRecette(new ElementRecette(3,"Bois",1));
                return fleche;
            case "Arc en bois" :
                Distance arc =new Distance("Arc en bois","Un vieil arc usé",10,78);
                arc.addInRecette(new ElementRecette(3,"Bois",3));
                arc.addInRecette(new ElementRecette(22,"Fer",2));
                return arc;
            case "Grappin" :
                Distance grappin = new Distance("Grappin","Permet de s'accrocher aux surfaces",0,81);
                grappin.addInRecette(new ElementRecette(22,"Fer",3));
                grappin.addInRecette(new ElementRecette(3,"Bois",3));
                grappin.addInRecette(new ElementRecette(23,"DELJCCium",1));
                return grappin;
            case "Balle en plomb" :
                Item balle = new Item("Balle en plomb","Un projectile qui peut être utlisé",1,80);
                balle.addInRecette(new ElementRecette(22,"Fer",2));
                return balle;
        }
        return null;
    }

    @Override
    public Item creerItem(int i) {
        switch (i) {
            case 53 :
                Pioche piocheDeFer = new Pioche("Pioche de fer","",4,53);
                piocheDeFer.addInRecette(new ElementRecette(22,"Fer",3));
                piocheDeFer.addInRecette(new ElementRecette(3,"Bois",1));
                return piocheDeFer;
            case 54 :
                Pioche piocheDeljcCium = new Pioche("Pioche DELJCCium","",5,54);
                piocheDeljcCium.addInRecette(new ElementRecette(23,"DELJCCium",3));
                piocheDeljcCium.addInRecette(new ElementRecette(3,"Bois",1));
                return piocheDeljcCium;
            case 64 :
                Armure casqueEnFer = new Armure("Casque en fer","",2,1,64);
                casqueEnFer.addInRecette(new ElementRecette(22,"Fer",5));
                return casqueEnFer;
            case 65 :
                Armure casqueEnDeljcCium =  new Armure("Casque en DELJCCium","",3,1,65);
                casqueEnDeljcCium.addInRecette(new ElementRecette(23,"DELJCCium",5));
                return casqueEnDeljcCium;
            case 66 :
                Armure plastronEnFer = new Armure("Plastron en fer","",6,2,66);
                plastronEnFer.addInRecette(new ElementRecette(22,"Fer",8));
                return plastronEnFer;
            case 67 :
                Armure plastronEnDeljcCium = new Armure("Plastron en DELJCCium","",9,2,67);
                plastronEnDeljcCium.addInRecette(new ElementRecette(23,"DELJCCium",8));
                return plastronEnDeljcCium;
            case 68 :
                Armure jambièreEnFer = new Armure("Jambière en fer","",4,3,68);
                jambièreEnFer.addInRecette(new ElementRecette(22,"Fer",6));
                return jambièreEnFer;
            case 69 :
                Armure jambièreEnDeljcCium = new Armure("Jambière en DELJCCium","",6,3,69);
                jambièreEnDeljcCium.addInRecette(new ElementRecette(23,"DELJCCium",6));
                return jambièreEnDeljcCium;
            case 70 :
                Armure botteEnFer = new Armure("Botte en fer","",3,4,70);
                botteEnFer.addInRecette(new ElementRecette(22,"Fer",4));
                return botteEnFer;
            case 71 :
                Armure botteEnDeljcCium = new Armure("Botte en DELJCCium","",5,4,71);
                botteEnDeljcCium.addInRecette(new ElementRecette(23,"DELJCCium",4));
                return botteEnDeljcCium;
            case 75 :
                Epee epéeEnFer = new Epee("Epée en Fer","",4,75);
                epéeEnFer.addInRecette(new ElementRecette(22,"Fer",2));
                epéeEnFer.addInRecette(new ElementRecette(3,"Bois",1));
                return epéeEnFer;
            case 76 :
                Epee epéeEnDeljcCium = new Epee("Epée en DELJCCium","",5,76);
                epéeEnDeljcCium.addInRecette(new ElementRecette(23,"DELJCCium",2));
                epéeEnDeljcCium.addInRecette(new ElementRecette(3,"Bois",1));
                return epéeEnDeljcCium;
            case 77 :
                Item fleche = new Item("Flèche","Flèche",1,77);
                fleche.addInRecette(new ElementRecette(22,"Fer",1));
                fleche.addInRecette(new ElementRecette(3,"Bois",1));
                return fleche;
            case 78 :
                Distance arc =new Distance("Arc en bois","Un vieil arc usé",10,78);
                arc.addInRecette(new ElementRecette(3,"Bois",3));
                arc.addInRecette(new ElementRecette(22,"Fer",2));
                return arc;
            case 81 :
                Distance grappin = new Distance("Grappin","Permet de s'accrocher aux surfaces",0,81);
                grappin.addInRecette(new ElementRecette(22,"Fer",3));
                grappin.addInRecette(new ElementRecette(3,"Bois",3));
                grappin.addInRecette(new ElementRecette(23,"DELJCCium",1));
                return grappin;
            case 80 :
                Item balle = new Item("Balle en plomb","Un projectile qui peut être utlisé",1,80);
                balle.addInRecette(new ElementRecette(22,"Fer",2));
                return balle;
        }
        return null;
    }

    @Override
    public HashMap<Integer, Recette> listRecettes() {
        HashMap<Integer,Recette> recettes = new HashMap<>();

        recettes.put(53,creerItem(53).getAttributRecette());
        recettes.put(54,creerItem(54).getAttributRecette());
        recettes.put(64,creerItem(64).getAttributRecette());
        recettes.put(65,creerItem(65).getAttributRecette());
        recettes.put(66,creerItem(66).getAttributRecette());
        recettes.put(67,creerItem(67).getAttributRecette());
        recettes.put(68,creerItem(68).getAttributRecette());
        recettes.put(69,creerItem(69).getAttributRecette());
        recettes.put(70,creerItem(70).getAttributRecette());
        recettes.put(71,creerItem(71).getAttributRecette());
        recettes.put(75,creerItem(75).getAttributRecette());
        recettes.put(76,creerItem(76).getAttributRecette());
        recettes.put(77,creerItem(77).getAttributRecette());
        recettes.put(78,creerItem(78).getAttributRecette());
        recettes.put(81,creerItem(81).getAttributRecette());
        recettes.put(80,creerItem(80).getAttributRecette());

        return recettes;

    }
}
