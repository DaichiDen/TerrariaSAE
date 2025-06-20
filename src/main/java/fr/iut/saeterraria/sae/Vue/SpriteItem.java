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
        items.put(5, "/Tiles/Roche_moche.png"); // Pierre
        items.put(6, "/Tiles/Minerais_fer.png"); // Minerai Fer
        items.put(7, "/Tiles/Minerais_DELJCCium.png"); //Minerai DELJCCIUM
        items.put(8, "/Tiles/piques_vorpales.png"); // Pique
        items.put(9,"/Tiles/bedrock.png"); // Bedrock
        items.put(10,"/Tiles/Noir.png");
        // Blocs à constructions
        items.put(12, "/Tiles/Etabli.png"); // Etabli
        items.put(13, "/Tiles/Forge.png"); // Forge
        items.put(14, "/Tiles/Four.png"); // Four

        items.put(15,"/Tiles/planche_bois.png");
        items.put(16,"/Tiles/toit_bois_gauche.png");
        items.put(17,"/Tiles/toit_bois_droit.png");
        items.put(18,"/Tiles/Fonce_mur_bois.png");
        items.put(19,"/Tiles/feuilles.png");
        items.put(20,"/Tiles/Coffre.png");// Coffre

        items.put(21, "/Sprite_objets/Coal.png"); // Charbon
        items.put(22, "/Sprite_objets/Iron_ingot.png"); // Fer
        items.put(23, "/Sprite_objets/DELJCCIUM_ingot.png"); // DELJCCium

        // Outils

        items.put(51, "/Sprite_objets/Pioche_bois.png"); // Pioche de bois
        items.put(52, "/Sprite_objets/Pioche_pierre.png"); // Pioche de pierre
        items.put(53, "/Sprite_objets/Pioche_fer.png"); // Pioche de fer
        items.put(54, "/Sprite_objets/Pioche_pierre.png"); // Pioche de DELJCCium

        for(int i=55; i<62; i++) {
            items.put(i,"/Tiles/Fond_noir.png");
        }

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
<<<<<<< HEAD
        items.put(81, "/Sprite_objets/Grappin.png");
=======
        items.put(81, "/Sprite_Objets/Boule_de_feu");
>>>>>>> attaqueProjoMojo


    }

    public HashMap<Integer,String> getHmap(){
        return items;
    }

}
