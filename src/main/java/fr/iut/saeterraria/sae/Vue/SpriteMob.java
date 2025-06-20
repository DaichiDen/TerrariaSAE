package fr.iut.saeterraria.sae.Vue;

import fr.iut.saeterraria.sae.Modele.Jeu;

import fr.iut.saeterraria.sae.Modele.Personnages.Entite;
import fr.iut.saeterraria.sae.Modele.Personnages.Joueur;
import fr.iut.saeterraria.sae.Modele.Personnages.MH;
import fr.iut.saeterraria.sae.Modele.Personnages.Ogre;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;



public class SpriteMob extends CreateRessourceVisuel {


    private Pane screen;
    private Jeu jeu;
    private int width,height;


    private ImageView ogre = createImageView("/Sprite/BM_Sac_a_caca.png",width,height);
    private ImageView goblin = createImageView("/Sprite/ogre.png",width,height);
    private ImageView mh = createImageView("/Sprite/MH.png",width,height);

    public SpriteMob(Jeu jeu, Pane screen, String nom){
        this.jeu = jeu;
        this.screen = screen;
        this.width=150;
        this.height=150;

        for(int i = 0; i < jeu.getMobs().size() ; i++) {
            int finalI = i;
            jeu.getMobs().get(i).marcheGaucheProperty().addListener((obs, oldVal, newVal) -> mettreAJourSpriteMob(jeu.getMobs().get(finalI)));
                jeu.getMobs().get(i).marcheDroiteProperty().addListener((obs, oldVal, newVal) -> mettreAJourSpriteMob(jeu.getMobs().get(finalI)));
                jeu.getMobs().get(i).xProperty().addListener((obs, oldVal, newVal) -> mettreAJourSpriteMob(jeu.getMobs().get(finalI)));
            }
        }



    public void mettreAJourSpriteMob(Entite entite) {
        ImageView spriteActuel = new ImageView();
        if(entite.getClass().equals(Ogre.class)) {
            spriteActuel = ogre;
        }
        else if (entite.getClass().equals(MH.class)) {
            spriteActuel = mh;
        }
        else {
            spriteActuel = goblin;
        }



        spriteActuel.setId(entite.getNom());
        spriteActuel.translateXProperty().bind(entite.xProperty());
        spriteActuel.translateYProperty().bind(entite.yProperty());
        spriteActuel.setFitWidth(54);
        spriteActuel.setFitHeight(64);
        screen.getChildren().add(spriteActuel);

    }

}
