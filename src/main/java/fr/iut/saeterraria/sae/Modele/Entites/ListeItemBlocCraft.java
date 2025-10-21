package fr.iut.saeterraria.sae.Modele.Personnages;

import fr.iut.saeterraria.sae.Modele.Objets.Arme.Epee;
import fr.iut.saeterraria.sae.Modele.Objets.Etablis.BlocForge;
import fr.iut.saeterraria.sae.Modele.Objets.Etablis.BlocFour;
import fr.iut.saeterraria.sae.Modele.Objets.Item;
import fr.iut.saeterraria.sae.Modele.Objets.Outil.Pioche;

public class ListeItemBlocCraft extends ListeItems{

    public ListeItemBlocCraft() {

    }
    @Override
    public Item creerItem(String nom) {
        switch (nom) {
            case "Forge" :
                return new BlocForge();
            case "Four" :
                return new BlocFour();
            case "Pioche de bois" :
                return new Pioche("Pioche de bois","",2,51);
            case "Pioche de pierre" :
                return new Pioche("Pioche de pierre","",3,52);
            case "Epée en Bois" :
                return new Epee("Epée en Bois","",1,73);
        }
        return null;
    }
}
