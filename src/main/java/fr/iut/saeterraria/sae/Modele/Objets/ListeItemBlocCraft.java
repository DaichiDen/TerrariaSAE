package fr.iut.saeterraria.sae.Modele.Objets;

import fr.iut.saeterraria.sae.Modele.Objets.Arme.Epee;
import fr.iut.saeterraria.sae.Modele.Objets.Etablis.BlocForge;
import fr.iut.saeterraria.sae.Modele.Objets.Etablis.BlocFour;
import fr.iut.saeterraria.sae.Modele.Objets.Outil.Pioche;

import java.util.HashMap;

public class ListeItemBlocCraft implements ListeItemCraft{

    public ListeItemBlocCraft() {

    }
    @Override
    public Item creerItem(String nom) {
        switch (nom) {
            case "Forge":
                BlocForge blocForge = new BlocForge();
                blocForge.addInRecette(new ElementRecette(22, "Fer", 9));
                blocForge.addInRecette(new ElementRecette(62,"Seau vide",1));
                return blocForge;
            case "Four":
                BlocFour blocFour = new BlocFour();
                blocFour.addInRecette(new ElementRecette(5, "Pierre", 8));
                return blocFour;
            case "Pioche de bois":
                Pioche piocheDeBois = new Pioche("Pioche de bois", "", 2, 51);
                piocheDeBois.addInRecette(new ElementRecette(3, "Bois", 4));
                return piocheDeBois;
            case "Pioche de pierre":
                Pioche piocheDePierre = new Pioche("Pioche de pierre", "", 3, 52);
                piocheDePierre.addInRecette(new ElementRecette(3, "Bois", 1));
                piocheDePierre.addInRecette(new ElementRecette(5, "Pierre", 3));
                return piocheDePierre;
            case "Epée en Bois":
                Epee epeeBois = new Epee("Epée en Bois", 1, 73);
                epeeBois.addInRecette(new ElementRecette(3, "Bois", 3));
                return epeeBois;
            case "Seau vide":
                Item seau = new Item("Seau vide", 1, 62);
                seau.addInRecette(new ElementRecette(22, "Fer", 3));
                return seau;
        }
        return null;
    }

    @Override
    public Item creerItem(int i) {
        switch (i) {
            case 13 :
                BlocForge blocForge = new BlocForge();
                blocForge.addInRecette(new ElementRecette(22,"Fer",9));
                blocForge.addInRecette(new ElementRecette(62,"Seau vide",1));
                return blocForge;
            case 62 :
                Item seau = new Item("Seau vide", 1,62);
                seau.addInRecette(new ElementRecette(22,"Fer",3));
                return seau;
            case 14 :
                BlocFour blocFour = new BlocFour();
                blocFour.addInRecette(new ElementRecette(5,"Pierre",8));
                return blocFour;
            case 51 :
                Pioche piocheDeBois = new Pioche("Pioche de bois","",2,51);
                piocheDeBois.addInRecette(new ElementRecette(3,"Bois",4));
                return piocheDeBois;
            case 52 :
                Pioche piocheDePierre = new Pioche("Pioche de pierre","",3,52);
                piocheDePierre.addInRecette(new ElementRecette(3,"Bois",1));
                piocheDePierre.addInRecette(new ElementRecette(5,"Pierre",3));
                return piocheDePierre;
            case 73 :
                Epee epeeBois = new Epee("Epée en Bois", 1,73);
                epeeBois.addInRecette(new ElementRecette(3,"Bois",3));
                return epeeBois;
        }
        return null;
    }

    @Override
    public HashMap<Integer, Recette> listRecettes() {
        HashMap<Integer,Recette> list = new HashMap<>();

        list.put(13,creerItem(13).getAttributRecette());
        list.put(14,creerItem(14).getAttributRecette());
        list.put(51,creerItem(52).getAttributRecette());
        list.put(52,creerItem(52).getAttributRecette());
        list.put(62,creerItem(62).getAttributRecette());
        list.put(73,creerItem(73).getAttributRecette());

        return list;
    }
}
