package fr.iut.saeterraria.sae.Vue;

import fr.iut.saeterraria.sae.Modele.Objets.Outil.Pelle;

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
        items.put(11, "");
        // Blocs à constructions
        items.put(12, "/Tiles/Fond_noir.png"); // Etabli
        items.put(13, "/Tiles/Fond_noir.png"); // Forge
        items.put(14, "/Tiles/Fond_noir.png"); // Alambique
        items.put(15, "/Tiles/Fond_noir.png"); // Four

        /* ---------- À ajouter le reste ------------- */
        items.put(16, "/Tiles/Fond_noir.png"); // Coffre
        items.put(17,"/Tiles/Fond_noir.png");
        // Outils
        for (int i = 18; i < 50; i++) {
            items.put(i,"/Tiles/Fond_noir.png");
        }
        items.put(50, "/Sprite_objets/Pelle_pierre.png"); // Pelle de bois
        items.put(51, "/Sprite_objets/Hache_pierre.png"); // Hache de bois
        items.put(52, "/Sprite_objets/Pioche_pierre.png"); // Pioche de bois
        items.put(53, "/Sprite_objets/Pelle_pierre.png"); // Pelle de pierre
        items.put(54, "/Sprite_objets/Hache_pierre.png"); // Hache de pierre
        items.put(55, "/Sprite_objets/Pioche_pierre.png"); // Pioche de pierre
        items.put(56, "/Sprite_objets/Pelle_pierre.png"); // Pelle de fer
        items.put(57, "/Sprite_objets/Hache_pierre.png"); // Hache de fer
        items.put(58, "/Sprite_objets/Pioche_pierre.png"); // Pioche de fer
        items.put(59, "/Sprite_objets/Pelle_pierre.png"); // Pelle de DELJCCium
        items.put(60, "/Sprite_objets/Hache_pierre.png"); // Hache de DELJCCium
        items.put(61, "/Sprite_objets/Pioche_pierre.png"); // Pioche de DELJCCium
        items.put(62, "/Sprite_objets/Pioche_pierre.png"); // Seau vide
        items.put(63, "/Sprite_objets/Pioche_pierre.png"); // Sceau d'eau

        // Armures

        items.put(64,"/Tiles/Fond_noir.png"); // Casque en fer
        items.put(65,"/Tiles/Fond_noir.png"); // Casque en DELJCCium
        items.put(66,"/Tiles/Fond_noir.png"); // Plastron en fer
        items.put(67,"/Tiles/Fond_noir.png"); // Plastron en DELJCCium
        items.put(68,"/Tiles/Fond_noir.png"); // Jambière en fer
        items.put(69,"/Tiles/Fond_noir.png"); // Jambière en DELJCCium
        items.put(70,"/Tiles/Fond_noir.png"); // Botte en fer
        items.put(71,"/Tiles/Fond_noir.png"); // Botte en DELJCCium

        //Armes
        items.put(72, "/Sprite_objets/KatanaDash.png");
        items.put(73, "/Sprite_objets/Epee_pierre.png");
        items.put(74, "/Sprite_objets/Epee_pierre.png");
        items.put(75, "/Sprite_objets/Pioche_pierre.png");
        items.put(76, "/Sprite_objets/Pioche_pierre.png");
        items.put(77,"/Sprite_objets/Flèche.png");
        items.put(78,"/Sprite_objets/Arc_repos.png");


    }

    public HashMap<Integer,String> getHmap(){
        return items;
    }

}
