package fr.iut.saeterraria.sae.Modele.Objets;

import fr.iut.saeterraria.sae.Modele.Objets.Arme.*;
import fr.iut.saeterraria.sae.Modele.Objets.Etablis.*;
import fr.iut.saeterraria.sae.Modele.Objets.Outil.*;

public enum ListeItems {
    Ciel(0,new Bloc("Ciel","Incassable+traversable fond",10,0)),
    Terre_Haute(1, new Bloc("Terre Haute","Bloc commun qui recouvre le monde", 1,1)),
    Terre_Basse(2, new Bloc("Terre Basse","Bloc commun qui recouvre le sol du monde", 1,2)),
    Bois(3, new Bloc("Bois","Element indispensable, base de créativité", 1,3)),
    Minerai_Charbon(4, new Bloc("Minerai Charbon","Bloc contenant plusieurs charbons", 2,4)),
    Pierre(5, new Bloc("Pierre","Bloc basique de pierre commun dans les sous-sol", 2,5)),
    Minerai_Fer(6, new Bloc("Minerai Fer","Métal commun de Fer", 3,6)),
    Minerai_DELJCCium(7, new Bloc("Minerai DELJCCium","", 4,7)),
    Pique(8, new Bloc("Pique","", 10,8)),
    BedRock(9, new Bloc("BedRock","Incassable", 10,9)),
    Noir(10, new Bloc("Noir","Incassable+traversable fond", 10,10)),

    ConstructionSansBloc(11, new BlocConstruction("ConstructionSansBloc","",0,0,11)),
    Etabli(12, new BlocConstruction("Etabli","Un établi qui permet la fabrication d'objets",1,1, (BlocConstruction) ConstructionSansBloc.getItem(),12)),
    Forge(13, new BlocConstruction("Forge","Un établi qui permet la fabrication d'objets",1,3,(BlocConstruction) Etabli.getItem(),13)),
    Four(14, new BlocConstruction("Four","Permet de fondre et cuire ses objets",1,2,(BlocConstruction) Etabli.getItem(),14)),

    Planche_Bois(15, new Bloc("Planche de bois","", 1,15)),
    Toit_Bois_Gauche(16, new Bloc("Toit_bois_gauche","", 1,16)),
    Toit_Bois_Droite(17, new Bloc("Toit bois droite", "", 1,17)),
    Mur_Bois_Fonce(18, new Bloc("Fonce bois mur", "", 0,18)),
    Feuilles(19, new Bloc("Feuilles", "", 1,19)),

    Coffre(20, new Coffre("Coffre", "", 3, 3,20)),
    Charbon(21, new Item("Charbon","Permet d'alimenter le four et la forge en chaleur",1,21)),
    Fer(22, new Item("Fer","Métal obtenu en fondant des Minerai de Fer",1,(BlocConstruction) Four.getItem(),22)),
    DELJCCium(23, new Item("DELJCCium", "", 1,(BlocConstruction) Four.getItem(),23)),

    Pierre_TP(49, new Pierre_TP(49)),
    Pioche_Bois(51, new Pioche("Pioche de bois","",2,(BlocConstruction) Etabli.getItem(),51)),
    Pioche_Pierre(52, new Pioche("Pioche de pierre","",3,(BlocConstruction) Etabli.getItem(),52)),
    Pioche_Fer(53, new Pioche("Pioche de fer","",4,(BlocConstruction) Forge.getItem(),53)),
    Pioche_DELJCCium(54 ,new Pioche("Pioche DELJCCium","",5,(BlocConstruction) Forge.getItem(),54)),

    Seau_Vide(62, new Item("Seau vide", "", 1,(BlocConstruction) Etabli.getItem(),62)),
    Seau_Eau(63, new Item("Seau eau", "", 1,(BlocConstruction) Forge.getItem(),63)),

    Casque_Fer(64, new Armure("Casque en fer","",2,(BlocConstruction) Forge.getItem(),1,64)),
    Casque_DELJCCium(65, new Armure("Casque en DELJCCium","",3,(BlocConstruction) Forge.getItem(),1,65)),
    Plastron_Fer(66, new Armure("Plastron en fer","",6,(BlocConstruction) Forge.getItem(),2,66)),
    Plastron_DELJCCium(67, new Armure("Plastron en DELJCCium","",9,(BlocConstruction) Forge.getItem(),2,67)),
    Jambiere_Fer(68, new Armure("Jambière en fer","",4,(BlocConstruction) Forge.getItem(),3,68)),
    Jambiere_DELJCCium(69, new Armure("Jambière en DELJCCium","",6,(BlocConstruction) Forge.getItem(),3,69)),
    Botte_Fer(70, new Armure("Botte en fer","",3,(BlocConstruction) Forge.getItem(),4,70)),
    Botte_DELJCCium(71, new Armure("Botte en DELJCCium","",5,(BlocConstruction) Forge.getItem(),4,71)),

    Katana_Etrange(72, new DashingKatana("Katana étrange","Ce Katana semble pouvoir octroyer la capacité à son détenteur de se déplacer à la vitesse du son",10,72)),
    Epee_Bois(73, new Epee("Epée en Bois","",1,(BlocConstruction) Etabli.getItem(),73)),
    Epee_Pierre(74, new Epee("Epée en Pierre","",3,(BlocConstruction) Etabli.getItem(),74)),
    Epee_Fer(75, new Epee("Epée en Fer","",4,(BlocConstruction) Forge.getItem(),75)),
    Epee_DELJCCium(76, new Epee("Epée en DELJCCium","",5,(BlocConstruction) Forge.getItem(),76)),

    Fleche(77, new Item("Flèche","Flèche",1,(BlocConstruction) Forge.getItem(),77)),
    Arc_Bois(78, new Distance("Arc en bois","Un vieil arc usé",10,(BlocConstruction) Forge.getItem(),78)),
    Arquebuse(79, new Distance("Arquebuse","Etrange objet qui semble ralentir le temps",5,79)),
    Balle_Plonb(80, new Item("Balle en plomb","Un projectile qui peut être utlisé",1,80)),
    Grappin(81, new Distance("Grappin","Permet de s'accrocher aux surfaces",0,(BlocConstruction) Forge.getItem(),81)),
    Boule_De_Feu(82, new Item("Boule de feu","Une boule de feu qui explose à l'impact",1,82));


    private int id;
    private Item item;

    ListeItems(int id, Item item) {
        this.id = id;
        this.item = item;
    }

    public static void initialiserRecettes() {
        // Lingot de fer : 1 Minérai de fer et 1 charbon et 1 four
        Fer.getItem().addInRecette(new ElementRecette(Minerai_Fer.getItem(),1));
        Fer.getItem().addInRecette(new ElementRecette(Charbon.getItem(),1));

        // Lingot de DELJCCium : 1 Minérai de DELJCCium et 1 charbon et 1 four
        DELJCCium.getItem().addInRecette(new ElementRecette(Minerai_DELJCCium.getItem(),1));
        DELJCCium.getItem().addInRecette(new ElementRecette(Charbon.getItem(),1));

        //Etabli : 2 bois
        Etabli.getItem().addInRecette(new ElementRecette(Bois.getItem(),2));

        //Four : 8 Pierre
        Four.getItem().addInRecette(new ElementRecette(Pierre.getItem(),8));

        // Forge : 9 fer et 1 seau
        Forge.getItem().addInRecette(new ElementRecette(Fer.getItem(),9));
        Forge.getItem().addInRecette(new ElementRecette(Seau_Vide.getItem(),1));

        // Pioche de bois : 4 bois
        Pioche_Bois.getItem().addInRecette(new ElementRecette(Bois.getItem(),4));
        // Pioche de pierre : 3 pierre + 1 bois
        Pioche_Pierre.getItem().addInRecette(new ElementRecette(Pierre.getItem(),3));
        Pioche_Pierre.getItem().addInRecette(new ElementRecette(Bois.getItem(),1));
        // Pioche de fer : 3 fer + 1 bois + forge
        Pioche_Fer.getItem().addInRecette(new ElementRecette(Fer.getItem(),3));
        Pioche_Fer.getItem().addInRecette(new ElementRecette(Bois.getItem(),1));
        // Pioche en DELJCCnium : 3 DELJCCium + 1 bois + forge
        Pioche_DELJCCium.getItem().addInRecette(new ElementRecette(DELJCCium.getItem(),3));
        Pioche_DELJCCium.getItem().addInRecette(new ElementRecette(Bois.getItem(),1));

        //Sceau
        Seau_Vide.getItem().addInRecette(new ElementRecette(Fer.getItem(),3));
        // Casque en fer + forge
        Casque_Fer.getItem().addInRecette(new ElementRecette(Fer.getItem(),5));
        // Plastron en fer + forge
        Plastron_Fer.getItem().addInRecette(new ElementRecette(Fer.getItem(),8));
        // Jambière en fer + forge
        Jambiere_Fer.getItem().addInRecette(new ElementRecette(Fer.getItem(),6));
        // Botte en fer + forge
        Botte_Fer.getItem().addInRecette(new ElementRecette(Fer.getItem(),4));

        // Casque en DELJCCnium + forge
        Casque_DELJCCium.getItem().addInRecette(new ElementRecette(DELJCCium.getItem(),5));
        // Plastron en DELJCCnium + forge
        Plastron_DELJCCium.getItem().addInRecette(new ElementRecette(DELJCCium.getItem(),8));
        // Jambière en DELJCCnium + forge
        Jambiere_DELJCCium.getItem().addInRecette(new ElementRecette(DELJCCium.getItem(),6));
        // Botte en DELJCCnium + forge
        Botte_DELJCCium.getItem().addInRecette(new ElementRecette(DELJCCium.getItem(),4));

        // Epée de bois : 3 bois
        Epee_Bois.getItem().addInRecette(new ElementRecette(Bois.getItem(),3));
        // Epée de pierre : 2 pîerre + 1 bois
        Epee_Pierre.getItem().addInRecette(new ElementRecette(Pierre.getItem(),2));
        Epee_Pierre.getItem().addInRecette(new ElementRecette(Bois.getItem(),1));
        // Epée de fer : 2 lingot de fer + 1 bois
        Epee_Fer.getItem().addInRecette(new ElementRecette(Fer.getItem(),2));
        Epee_Fer.getItem().addInRecette(new ElementRecette(Bois.getItem(),1));
        // Epée de DELJCCium : 2 DELJCCium + 1 bois
        Epee_DELJCCium.getItem().addInRecette(new ElementRecette(DELJCCium.getItem(),2));
        Epee_DELJCCium.getItem().addInRecette(new ElementRecette(Bois.getItem(),1));

        // Flèche : 1 Bois 1 fer + forge
        Fleche.getItem().addInRecette(new ElementRecette(Bois.getItem(),1));
        Fleche.getItem().addInRecette(new ElementRecette(Fer.getItem(),1));

        // Arc : 3 bois 2 fer + forge
        Arc_Bois.getItem().addInRecette(new ElementRecette(Bois.getItem(),3));
        Arc_Bois.getItem().addInRecette(new ElementRecette(Fer.getItem(),2));

        // Balle en plomb : 2 fer + forge
        Balle_Plonb.getItem().addInRecette(new ElementRecette(Fer.getItem(),2));

        Grappin.getItem().addInRecette(new ElementRecette(Bois.getItem(),3));
        Grappin.getItem().addInRecette(new ElementRecette(Fer.getItem(),3));
        Grappin.getItem().addInRecette(new ElementRecette(DELJCCium.getItem(),1));
    }

    public static void initialiserBlocConstructions() {
        for (ListeItems i : ListeItems.values()) {
            // Sans bloc
            if (i.getItem().getProvenance() == ConstructionSansBloc.getItem()) {
                ((BlocConstruction) ConstructionSansBloc.getItem()).addRecette(i.getItem().getCodeObjet(), i.getItem().getAttributRecette());
            }

            // Etabli
            else if (i.getItem().getProvenance() == Etabli.getItem()) {
                ((BlocConstruction) Etabli.getItem()).addRecette(i.getItem().getCodeObjet(), i.getItem().getAttributRecette());
            }

            // Forge
            else if (i.getItem().getProvenance() == Forge.getItem()) {
                ((BlocConstruction) Forge.getItem()).addRecette(i.getItem().getCodeObjet(), i.getItem().getAttributRecette());
            }

            // Four
            else if (i.getItem().getProvenance() == Four.getItem()) {
                ((BlocConstruction) Four.getItem()).addRecette(i.getItem().getCodeObjet(), i.getItem().getAttributRecette());
            }
        }
    }

    public int getId() {
        return id;
    }

    public Item getItem() {
        return item;
    }

    public static Item getItemParId(int i) {
        for(ListeItems item : values()) {
            if(item.getId()==i) {
                return item.getItem();
            }
        }
        return null;
    }

    public static Item getItemParNom(String nom) {
        for(ListeItems item : values()) {
            if(item.getItem().getName().equals(nom)) {
                return item.getItem();
            }
        }
        return null;
    }
}
