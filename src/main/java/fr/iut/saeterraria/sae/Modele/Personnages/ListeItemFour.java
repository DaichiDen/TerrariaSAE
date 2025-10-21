package fr.iut.saeterraria.sae.Modele.Personnages;

import fr.iut.saeterraria.sae.Modele.Objets.Armure;
import fr.iut.saeterraria.sae.Modele.Objets.Etablis.BlocConstruction;
import fr.iut.saeterraria.sae.Modele.Objets.Item;
import fr.iut.saeterraria.sae.Modele.Objets.Outil.Pioche;

public class ListeItemFour extends ListeItems{

    public ListeItemFour() {

    }

    @Override
    public Item creerItem(String nom) {
        switch (nom) {
            case "Fer" :
                return new Item("Fer","Métal obtenu en fondant des Minerai de Fer",1,22);
            case "DELJCCium" :
                return  new Item("DELJCCium", "", 1,(23));
        }
        return null;
    }
}
