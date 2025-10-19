package fr.iut.saeterraria.sae.Vue;

import fr.iut.saeterraria.sae.Modele.Jeu;

import fr.iut.saeterraria.sae.Modele.Entites.Entite;
import fr.iut.saeterraria.sae.Modele.Entites.MH;
import fr.iut.saeterraria.sae.Modele.Entites.Ogre;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;



public class SpriteMob extends CreateRessourceVisuel {


    private Pane screen;
    private int width,height;


    private ImageView ogre = createImageView("/Sprite/BM_Sac_a_caca.png",width,height);
    private ImageView goblin = createImageView("/Sprite/ogre.png",width,height);
    private ImageView mh = createImageView("/Sprite/MH.png",width,height);

    public SpriteMob( Pane screen, String nom){
        this.screen = screen;
        this.width=150;
        this.height=150;

        for(int i = 0; i < Jeu.getUniqueJeu().getMobs().size() ; i++) {
            int finalI = i;
            Jeu.getUniqueJeu().getMobs().get(i).marcheGaucheProperty().addListener((obs, oldVal, newVal) -> mettreAJourSpriteMob(Jeu.getUniqueJeu().getMobs().get(finalI)));
                Jeu.getUniqueJeu().getMobs().get(i).marcheDroiteProperty().addListener((obs, oldVal, newVal) -> mettreAJourSpriteMob(Jeu.getUniqueJeu().getMobs().get(finalI)));
                Jeu.getUniqueJeu().getMobs().get(i).xProperty().addListener((obs, oldVal, newVal) -> mettreAJourSpriteMob(Jeu.getUniqueJeu().getMobs().get(finalI)));
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



        spriteActuel.setId("mob"+entite.getId());
        spriteActuel.translateXProperty().bind(entite.xProperty());
        spriteActuel.translateYProperty().bind(entite.yProperty());
        spriteActuel.setFitWidth(54);
        spriteActuel.setFitHeight(64);
        screen.getChildren().add(spriteActuel);

    }

}
