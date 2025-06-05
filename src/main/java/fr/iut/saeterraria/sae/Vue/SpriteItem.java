package fr.iut.saeterraria.sae.Vue;

import java.util.HashMap;

public class SpriteItem extends CreateRessourceVisuel{

    private HashMap<Integer, String> items;

    public SpriteItem() {
        items = new HashMap<>();
        initialiseItems();
    }

    public void initialiseItems() {
        items.put(0, "/Tiles/Ciel");
        // Blocs
        items.put(1, "/Tiles/Dirt_1"); // Terre Haute
        items.put(2, "/Tiles/Dirt_2"); // Terre Basse
        items.put(3, "/Tiles/Roche_moche"); // Bois
        items.put(4, ""); // Minerai Charbon
        items.put(5, ""); // Charbon
        items.put(6, ""); // Pierre
        items.put(7, ""); // Minerai Fer
        items.put(8, ""); // Fer
        items.put(9, ""); // Glace

        // Blocs à constructions
        items.put(10, ""); // Etabli
        items.put(11, ""); // Forge
        items.put(12, ""); // Alambique

        /* ---------- À ajouter le reste ------------- */

        // Outils
        items.put(50, ""); // Pelle de bois
        items.put(51, ""); // Hache de bois
        items.put(19, "/Tiles/Ciel"); // Pioche de bois
        items.put(53, ""); // Pelle de pierre
        items.put(54, ""); // Hache de pierre
        items.put(55, ""); // Pioche de pierre
        items.put(56, ""); // Pelle de fer
        items.put(57, ""); // Hache de fer
        items.put(58, ""); // Pioche de fer
        items.put(59, ""); // Pelle de DELJCCium
        items.put(60,""); // Hache de DELJCCium
        items.put(61,""); // Pioche de DELJCCium

        // Armures
        items.put(62,""); // Casque en fer
        items.put(63,""); // Casque en DELJCCium
        items.put(64,""); // Plastron en fer
        items.put(65,""); // Plastron en DELJCCium
        items.put(66,""); // Jambière en fer
        items.put(67,""); // Jambière en DELJCCium
        items.put(68,""); // Botte en fer
        items.put(69,""); // Botte en DELJCCium

        //Armres
        items.put(39,"/Sprite/KatanaDash");
    }

    public HashMap<Integer,String> getHmap(){
        return items;
    }

}
