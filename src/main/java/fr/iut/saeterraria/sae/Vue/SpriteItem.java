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
        items.put(3, "/Tiles/Bois.png"); // Bois
        items.put(4, "/Tiles/Minerais_charbon.png"); // Minerai Charbon
        items.put(5, "/Sprite_objets/Coal.png"); // Charbon
        items.put(6, "/Tiles/Roche_moche.png"); // Pierre
        items.put(7, "/Tiles/Minerais_fer.png"); // Minerai Fer
        items.put(8, "/Sprite_objets/Iron_ingot.png"); // Fer
        items.put(9, "/Tiles/Minerais_DELJCCium.png"); //Minerai DELJCCIUM
        items.put(10, "/Sprite_objets/DELJCCIUM_ingot.png"); // DELJCCium
        items.put(16, "/Tiles/piques_vorpales.png");
        // Blocs à constructions
        items.put(12, "/Tiles/Etabli.png"); // Etabli
        items.put(13, "/Tiles/Forge.png"); // Forge
        items.put(14, "/Tiles/Fond_noir.png"); // Alambique
        items.put(15, "/Tiles/Four.png"); // Four

        /* ---------- À ajouter le reste ------------- */
        items.put(17,"/Tiles/bedrock.png");
        items.put(24,"/Tiles/Coffre.png");// Coffre
        // Outils

        items.put(51, "/Sprite_objets/Pioche_bois.png"); // Pioche de bois
        items.put(52, "/Sprite_objets/Pioche_pierre.png"); // Pioche de pierre
        items.put(53, "/Sprite_objets/Pioche_fer.png"); // Pioche de fer
        items.put(54, "/Sprite_objets/Pioche_pierre.png"); // Pioche de DELJCCium
        items.put(62, "/Sprite_objets/Sceau_vide.png"); // Seau vide
        items.put(63, "/Sprite_objets/Sceau_eau.png"); // Sceau d'eau

        // Armures

        items.put(64,"/Sprite_objets/Casque_fer.png"); // Casque en fer
        items.put(65,"/Sprite_objets/Casque_DELJCCium.png"); // Casque en DELJCCium
        items.put(66,"/Sprite_objets/Plastron_fer.png"); // Plastron en fer
        items.put(67,"/Sprite_objets/Plastron_DELJCCium.png"); // Plastron en DELJCCium
        items.put(68,"/Sprite_objets/Jambieres_fer.png"); // Jambière en fer
        items.put(69,"/Sprite_objets/Jambieres_DELJCCium.png"); // Jambière en DELJCCium
        items.put(70,"/Sprite_objets/Bottes_fer.png"); // Botte en fer
        items.put(71,"/Sprite_objets/Bottes_DELJCCium.png"); // Botte en DELJCCium

        //Armes

        items.put(72, "/Sprite_objets/KatanaDash.png");
        items.put(73, "/Sprite_objets/Epee_bois.png");
        items.put(74, "/Sprite_objets/Epee_pierre.png");
        items.put(75, "/Sprite_objets/Pioche_pierre.png");
        items.put(76, "/Sprite_objets/Pioche_pierre.png");
        items.put(77, "/Sprite_objets/Flèche.png");
        items.put(78, "/Sprite_objets/Arc_repos.png");
        items.put(79, "/Sprite_objets/gun_bh.png");
        items.put(80, "/Sprite_objets/Balle.png");
        items.put(81, "/Sprite_Objets/Boule_de_feu");


    }

    public HashMap<Integer,String> getHmap(){
        return items;
    }

}
