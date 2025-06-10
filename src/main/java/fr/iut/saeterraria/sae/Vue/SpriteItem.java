package fr.iut.saeterraria.sae.Vue;

import java.util.HashMap;

public class SpriteItem extends CreateRessourceVisuel{

    private HashMap<Integer, String> items;

    public SpriteItem() {
        items = new HashMap<>();
        initialiseItems();
    }

    public void initialiseItems() {


        items.put(0, "/Tiles/Ciel.png");
        // Blocs
        items.put(1, "/Tiles/Dirt_1.png"); // Terre Haute
        items.put(2, "/Tiles/Dirt_2.png"); // Terre Basse
        items.put(3, "/Tiles/Roche_moche.png"); // Bois
        items.put(4, "/Tiles/Fond_noir.png"); // Minerai Charbon
        items.put(5, "/Tiles/Fond_noir.png"); // Charbon
        items.put(6, "/Tiles/Fond_noir.png"); // Pierre
        items.put(7, "/Tiles/Fond_noir.png"); // Minerai Fer
        items.put(8, "/Tiles/Fond_noir.png"); // Fer
        items.put(9, "/Tiles/Fond_noir.png"); // Glace
        items.put(10, "/Tiles/Fond_noir.png"); // DELJCCium


        // Blocs à constructions
        items.put(12, "/Tiles/Fond_noir.png"); // Etabli
        items.put(13, "/Tiles/Fond_noir.png"); // Forge
        items.put(14, "/Tiles/Fond_noir.png"); // Alambique
        items.put(15, "/Tiles/Fond_noir.png"); // Four

        /* ---------- À ajouter le reste ------------- */
        items.put(16, "/Tiles/Fond_noir.png"); // Coffre
        // Outils
        items.put(17, "/Tiles/Fond_noir.png");


        items.put(18, "/Sprite_objets/Pelle_pierre.png"); // Pelle de bois
        items.put(19, "/Sprite_objets/Hache_pierre.png"); // Hache de bois
        items.put(20, "/Sprite_objets/Pioche_pierre.png"); // Pioche de bois
        items.put(21, "/Sprite_objets/Pelle_pierre.png"); // Pelle de pierre
        items.put(22, "/Sprite_objets/Hache_pierre.png"); // Hache de pierre
        items.put(23, "/Sprite_objets/Pioche_pierre.png"); // Pioche de pierre
        items.put(24, "/Sprite_objets/Pelle_pierre.png"); // Pelle de fer
        items.put(25, "/Sprite_objets/Hache_pierre.png"); // Hache de fer
        items.put(26, "/Sprite_objets/Pioche_pierre.png"); // Pioche de fer
        items.put(27, "/Sprite_objets/Pelle_pierre.png"); // Pelle de DELJCCium
        items.put(28, "/Sprite_objets/Hache_pierre.png"); // Hache de DELJCCium
        items.put(29, "/Sprite_objets/Pioche_pierre.png"); // Pioche de DELJCCium
        items.put(30, "/Sprite_objets/Pioche_pierre.png"); // Seau vide
        items.put(31, "/Sprite_objets/Pioche_pierre.png"); // Sceau d'eau

        for (int i = 32; i < 63; i++) {
            items.put(i,"/Tiles/Fond_noir.png");
        }
        // Armures





        
        items.put(63,"/Tiles/Fond_noir.png"); // Casque en fer
        items.put(64,"/Tiles/Fond_noir.png"); // Casque en DELJCCium
        items.put(65,"/Tiles/Fond_noir.png"); // Plastron en fer
        items.put(66,"/Tiles/Fond_noir.png"); // Plastron en DELJCCium
        items.put(67,"/Tiles/Fond_noir.png"); // Jambière en fer
        items.put(68,"/Tiles/Fond_noir.png"); // Jambière en DELJCCium
        items.put(69,"/Tiles/Fond_noir.png"); // Botte en fer


        //Armes
        items.put(71, "/Sprite_objets/KatanaDash.png");
        items.put(73,"/Sprite_objets/Arc_repos.png");
        items.put(72,"/Sprite_objets/Flèche.png");


    }

    public HashMap<Integer,String> getHmap(){
        return items;
    }

}
