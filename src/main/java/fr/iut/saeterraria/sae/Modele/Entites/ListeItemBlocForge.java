package fr.iut.saeterraria.sae.Modele.Personnages;

import fr.iut.saeterraria.sae.Modele.Objets.Arme.Distance;
import fr.iut.saeterraria.sae.Modele.Objets.Arme.Epee;
import fr.iut.saeterraria.sae.Modele.Objets.Armure;
import fr.iut.saeterraria.sae.Modele.Objets.ElementRecette;
import fr.iut.saeterraria.sae.Modele.Objets.Item;
import fr.iut.saeterraria.sae.Modele.Objets.Outil.Pioche;
import javafx.scene.AmbientLight;

public class ListeItemBlocForge extends ListeItems{

    public ListeItemBlocForge() {

    }

    @Override
    public Item creerItem(String nom) {
        switch (nom) {
            case "Pioche de fer" :
                Pioche piocheDeFer = new Pioche("Pioche de fer","",4,53);
                piocheDeFer.addInRecette(new ElementRecette(22,3));
                piocheDeFer.addInRecette(new ElementRecette(3,1));
                return piocheDeFer;
            case "Pioche DELJCCium" :
                Pioche piocheDeljcCium = new Pioche("Pioche DELJCCium","",5,54);
                piocheDeljcCium.addInRecette(new ElementRecette(23,3));
                piocheDeljcCium.addInRecette(new ElementRecette(3,1));
                return piocheDeljcCium;
            case "Casque en fer" :
                Armure casqueEnFer = new Armure("Casque en fer","",2,1,64);
                casqueEnFer.addInRecette(new ElementRecette(22,5));
                return casqueEnFer;
            case "Casque en DELJCCium" :
                Armure casqueEnDeljcCium =  new Armure("Casque en DELJCCium","",3,1,65);
                casqueEnDeljcCium.addInRecette(new ElementRecette(23,5));
                return casqueEnDeljcCium;
            case "Plastron en fer" :
                Armure plastronEnFer = new Armure("Plastron en fer","",6,2,66);
                plastronEnFer.addInRecette(new ElementRecette(22,8));
                return plastronEnFer;
            case "Plastron en DELJCCium" :
                Armure plastronEnDeljcCium = new Armure("Plastron en DELJCCium","",9,2,67);
                plastronEnDeljcCium.addInRecette(new ElementRecette(23,8));
                return plastronEnDeljcCium;
            case "Jambière en fer" :
                Armure jambièreEnFer = new Armure("Jambière en fer","",4,3,68);
                jambièreEnFer.addInRecette(new ElementRecette(22,6));
                return jambièreEnFer;
            case "Jambière en DELJCCium" :
                Armure jambièreEnDeljcCium = new Armure("Jambière en DELJCCium","",6,3,69);
                jambièreEnDeljcCium.addInRecette(new ElementRecette(23,6));
                return jambièreEnDeljcCium;
            case "Botte en fer" :
                Armure botteEnFer = new Armure("Botte en fer","",3,4,70);
                botteEnFer.addInRecette(new ElementRecette(22,4));
                return botteEnFer;
            case "Botte en DELJCCium" :
                Armure botteEnDeljcCium = new Armure("Botte en DELJCCium","",5,4,71);
                botteEnDeljcCium.addInRecette(new ElementRecette(23,4));
                return botteEnDeljcCium;
            case "Epée en Fer" :
                Epee epéeEnFer = new Epee("Epée en Fer","",4,75);
                epéeEnFer.addInRecette(new ElementRecette(22,2));
                epéeEnFer.addInRecette(new ElementRecette(3,1));
                return epéeEnFer;
            case "Epée en DELJCCium" :
                Epee epéeEnDeljcCium = new Epee("Epée en DELJCCium","",5,76);
                epéeEnDeljcCium.addInRecette(new ElementRecette(23,2));
                epéeEnDeljcCium.addInRecette(new ElementRecette(3,1));
                return epéeEnDeljcCium;
            case "Flèche" :
                Item fleche = new Item("Flèche","Flèche",1,77);
                fleche.addInRecette(new ElementRecette(22,1));
                fleche.addInRecette(new ElementRecette(3,1));
                return fleche;
            case "Arc en bois" :
                Distance arc =new Distance("Arc en bois","Un vieil arc usé",10,78);
                arc.addInRecette(new ElementRecette(3,3));
                arc.addInRecette(new ElementRecette(22,2));
                return arc;
            case "Grappin" :
                Distance grappin = new Distance("Grappin","Permet de s'accrocher aux surfaces",0,81);
                grappin.addInRecette(new ElementRecette(22,3));
                grappin.addInRecette(new ElementRecette(3,3));
                grappin.addInRecette(new ElementRecette(23,1));
                return grappin;
            case "Balle en plomb" :
                Item balle = new Item("Balle en plomb","Un projectile qui peut être utlisé",1,80);
                balle.addInRecette(new ElementRecette(22,2));
                return balle;
        }
        return null;
    }

    @Override
    public Item creerItem(int i) {
        switch (i) {
            case 53 :
                Pioche piocheDeFer = new Pioche("Pioche de fer","",4,53);
                piocheDeFer.addInRecette(new ElementRecette(22,3));
                piocheDeFer.addInRecette(new ElementRecette(3,1));
                return piocheDeFer;
            case 54 :
                Pioche piocheDeljcCium = new Pioche("Pioche DELJCCium","",5,54);
                piocheDeljcCium.addInRecette(new ElementRecette(23,3));
                piocheDeljcCium.addInRecette(new ElementRecette(3,1));
                return piocheDeljcCium;
            case 64 :
                Armure casqueEnFer = new Armure("Casque en fer","",2,1,64);
                casqueEnFer.addInRecette(new ElementRecette(22,5));
                return casqueEnFer;
            case 65 :
                Armure casqueEnDeljcCium =  new Armure("Casque en DELJCCium","",3,1,65);
                casqueEnDeljcCium.addInRecette(new ElementRecette(23,5));
                return casqueEnDeljcCium;
            case 66 :
                Armure plastronEnFer = new Armure("Plastron en fer","",6,2,66);
                plastronEnFer.addInRecette(new ElementRecette(22,8));
                return plastronEnFer;
            case 67 :
                Armure plastronEnDeljcCium = new Armure("Plastron en DELJCCium","",9,2,67);
                plastronEnDeljcCium.addInRecette(new ElementRecette(23,8));
                return plastronEnDeljcCium;
            case 68 :
                Armure jambièreEnFer = new Armure("Jambière en fer","",4,3,68);
                jambièreEnFer.addInRecette(new ElementRecette(22,6));
                return jambièreEnFer;
            case 69 :
                Armure jambièreEnDeljcCium = new Armure("Jambière en DELJCCium","",6,3,69);
                jambièreEnDeljcCium.addInRecette(new ElementRecette(23,6));
                return jambièreEnDeljcCium;
            case 70 :
                Armure botteEnFer = new Armure("Botte en fer","",3,4,70);
                botteEnFer.addInRecette(new ElementRecette(22,4));
                return botteEnFer;
            case 71 :
                Armure botteEnDeljcCium = new Armure("Botte en DELJCCium","",5,4,71);
                botteEnDeljcCium.addInRecette(new ElementRecette(23,4));
                return botteEnDeljcCium;
            case 75 :
                Epee epéeEnFer = new Epee("Epée en Fer","",4,75);
                epéeEnFer.addInRecette(new ElementRecette(22,2));
                epéeEnFer.addInRecette(new ElementRecette(3,1));
                return epéeEnFer;
            case 76 :
                Epee epéeEnDeljcCium = new Epee("Epée en DELJCCium","",5,76);
                epéeEnDeljcCium.addInRecette(new ElementRecette(23,2));
                epéeEnDeljcCium.addInRecette(new ElementRecette(3,1));
                return epéeEnDeljcCium;
            case 77 :
                Item fleche = new Item("Flèche","Flèche",1,77);
                fleche.addInRecette(new ElementRecette(22,1));
                fleche.addInRecette(new ElementRecette(3,1));
                return fleche;
            case 78 :
                Distance arc =new Distance("Arc en bois","Un vieil arc usé",10,78);
                arc.addInRecette(new ElementRecette(3,3));
                arc.addInRecette(new ElementRecette(22,2));
                return arc;
            case 81 :
                Distance grappin = new Distance("Grappin","Permet de s'accrocher aux surfaces",0,81);
                grappin.addInRecette(new ElementRecette(22,3));
                grappin.addInRecette(new ElementRecette(3,3));
                grappin.addInRecette(new ElementRecette(23,1));
                return grappin;
            case 80 :
                Item balle = new Item("Balle en plomb","Un projectile qui peut être utlisé",1,80);
                balle.addInRecette(new ElementRecette(22,2));
                return balle;
        }
        return null;
    }
}
