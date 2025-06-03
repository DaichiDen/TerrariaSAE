package fr.iut.saeterraria.sae.Vue;

import fr.iut.saeterraria.sae.Modele.Jeu;

import fr.iut.saeterraria.sae.Modele.Personnages.Entite;
import fr.iut.saeterraria.sae.Modele.Personnages.Joueur;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;



public class SpriteMob extends CreateRessourceVisuel {


    private Pane screen;
    private Jeu jeu;
    private int width,height;

    private ImageView spriteActuel;
    private String dernierEtat = "";
    private Rectangle2D hitboxJoueur;

    private ImageView marcheGauche = createImageView("/Sprite/BM_Sac_a_caca.png",width,height);
    private ImageView marcheDroite = createImageView("/Sprite/BM_Sac_a_caca.png",width,height);
    private ImageView marcheNon = createImageView("/Sprite/BM_Sac_a_caca.png",width,height);

    public SpriteMob(Jeu jeu, Pane screen, String nom){
        this.jeu = jeu;
        this.screen = screen;
        this.width=150;
        this.height=150;

        for(int i = 0; i < jeu.getMobs().size() ; i++) {
            if (nom.equals("Pierre")) {
                int finalI = i;
                jeu.getMobs().get(i).marcheGaucheProperty().addListener((obs, oldVal, newVal) -> mettreAJourSpriteMob(jeu.getMobs().get(finalI)));
                jeu.getMobs().get(i).marcheDroiteProperty().addListener((obs, oldVal, newVal) -> mettreAJourSpriteMob(jeu.getMobs().get(finalI)));
                jeu.getMobs().get(i).xProperty().addListener((obs, oldVal, newVal) -> mettreAJourSpriteMob(jeu.getMobs().get(finalI)));
            }
        }

    }

    public void mettreAJourSpriteMob(Entite entite) {
        String etatActuel;

        if (entite.getMarcheGauche()) {
            etatActuel = "gauche";
        } else if (entite.getMarcheDroite()) {
            etatActuel = "droite";
        } else {
            etatActuel = "stop";
        }

        // Si l'état n'a pas changé, ne rien faire
        if (etatActuel.equals(dernierEtat)) {
            return;
        }

        // Sinon, supprimer le sprite précédent s’il existe
        if (spriteActuel != null) {
            screen.getChildren().remove(spriteActuel);
        }

        // Créer le bon sprite
        if (etatActuel.equals("gauche")) {
            spriteActuel = createImageView("/Sprite/Chevalier_marcheGauche.gif",width,height);
        } else if (etatActuel.equals("droite")) {
            spriteActuel = createImageView("/Sprite/Chevalier_marcheDroite.gif",width,height);
        } else {
            spriteActuel = createImageView("/Sprite/BM_Sac_a_caca.png",width,height);
        }

        spriteActuel.setId(entite.getNom());
        spriteActuel.translateXProperty().bind(entite.xProperty());
        spriteActuel.translateYProperty().bind(entite.yProperty());
        spriteActuel.setFitWidth(54);
        spriteActuel.setFitHeight(64);
        screen.getChildren().add(spriteActuel);

        dernierEtat = etatActuel;
    }

}
